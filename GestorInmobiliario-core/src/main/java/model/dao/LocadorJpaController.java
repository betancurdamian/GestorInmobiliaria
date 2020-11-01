/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entity.Inmobiliaria;
import model.entity.UsuarioCliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.dao.exceptions.IllegalOrphanException;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Locador;

/**
 *
 * @author Ariel
 */
public class LocadorJpaController implements Serializable {

    public LocadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Locador locador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaCliente = locador.getUnaInmobiliariaCliente();
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente = em.getReference(unaInmobiliariaCliente.getClass(), unaInmobiliariaCliente.getId());
                locador.setUnaInmobiliariaCliente(unaInmobiliariaCliente);
            }
            UsuarioCliente unUsuarioCliente = locador.getUnUsuarioCliente();
            if (unUsuarioCliente != null) {
                unUsuarioCliente = em.getReference(unUsuarioCliente.getClass(), unUsuarioCliente.getId());
                locador.setUnUsuarioCliente(unUsuarioCliente);
            }
            em.persist(locador);
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente.getClientes().add(locador);
                unaInmobiliariaCliente = em.merge(unaInmobiliariaCliente);
            }
            if (unUsuarioCliente != null) {
                model.entity.Cliente oldUnClienteOfUnUsuarioCliente = unUsuarioCliente.getUnCliente();
                if (oldUnClienteOfUnUsuarioCliente != null) {
                    oldUnClienteOfUnUsuarioCliente.setUnUsuarioCliente(null);
                    oldUnClienteOfUnUsuarioCliente = em.merge(oldUnClienteOfUnUsuarioCliente);
                }
                unUsuarioCliente.setUnCliente(locador);
                unUsuarioCliente = em.merge(unUsuarioCliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Locador locador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Locador persistentLocador = em.find(Locador.class, locador.getId());
            Inmobiliaria unaInmobiliariaClienteOld = persistentLocador.getUnaInmobiliariaCliente();
            Inmobiliaria unaInmobiliariaClienteNew = locador.getUnaInmobiliariaCliente();
            UsuarioCliente unUsuarioClienteOld = persistentLocador.getUnUsuarioCliente();
            UsuarioCliente unUsuarioClienteNew = locador.getUnUsuarioCliente();
            List<String> illegalOrphanMessages = null;
            if (unUsuarioClienteOld != null && !unUsuarioClienteOld.equals(unUsuarioClienteNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain UsuarioCliente " + unUsuarioClienteOld + " since its unCliente field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (unaInmobiliariaClienteNew != null) {
                unaInmobiliariaClienteNew = em.getReference(unaInmobiliariaClienteNew.getClass(), unaInmobiliariaClienteNew.getId());
                locador.setUnaInmobiliariaCliente(unaInmobiliariaClienteNew);
            }
            if (unUsuarioClienteNew != null) {
                unUsuarioClienteNew = em.getReference(unUsuarioClienteNew.getClass(), unUsuarioClienteNew.getId());
                locador.setUnUsuarioCliente(unUsuarioClienteNew);
            }
            locador = em.merge(locador);
            if (unaInmobiliariaClienteOld != null && !unaInmobiliariaClienteOld.equals(unaInmobiliariaClienteNew)) {
                unaInmobiliariaClienteOld.getClientes().remove(locador);
                unaInmobiliariaClienteOld = em.merge(unaInmobiliariaClienteOld);
            }
            if (unaInmobiliariaClienteNew != null && !unaInmobiliariaClienteNew.equals(unaInmobiliariaClienteOld)) {
                unaInmobiliariaClienteNew.getClientes().add(locador);
                unaInmobiliariaClienteNew = em.merge(unaInmobiliariaClienteNew);
            }
            if (unUsuarioClienteNew != null && !unUsuarioClienteNew.equals(unUsuarioClienteOld)) {
                model.entity.Cliente oldUnClienteOfUnUsuarioCliente = unUsuarioClienteNew.getUnCliente();
                if (oldUnClienteOfUnUsuarioCliente != null) {
                    oldUnClienteOfUnUsuarioCliente.setUnUsuarioCliente(null);
                    oldUnClienteOfUnUsuarioCliente = em.merge(oldUnClienteOfUnUsuarioCliente);
                }
                unUsuarioClienteNew.setUnCliente(locador);
                unUsuarioClienteNew = em.merge(unUsuarioClienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = locador.getId();
                if (findLocador(id) == null) {
                    throw new NonexistentEntityException("The locador with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Locador locador;
            try {
                locador = em.getReference(Locador.class, id);
                locador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The locador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            UsuarioCliente unUsuarioClienteOrphanCheck = locador.getUnUsuarioCliente();
            if (unUsuarioClienteOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Locador (" + locador + ") cannot be destroyed since the UsuarioCliente " + unUsuarioClienteOrphanCheck + " in its unUsuarioCliente field has a non-nullable unCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Inmobiliaria unaInmobiliariaCliente = locador.getUnaInmobiliariaCliente();
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente.getClientes().remove(locador);
                unaInmobiliariaCliente = em.merge(unaInmobiliariaCliente);
            }
            em.remove(locador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Locador> findLocadorEntities() {
        return findLocadorEntities(true, -1, -1);
    }

    public List<Locador> findLocadorEntities(int maxResults, int firstResult) {
        return findLocadorEntities(false, maxResults, firstResult);
    }

    private List<Locador> findLocadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Locador.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Locador findLocador(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Locador.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Locador> rt = cq.from(Locador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
