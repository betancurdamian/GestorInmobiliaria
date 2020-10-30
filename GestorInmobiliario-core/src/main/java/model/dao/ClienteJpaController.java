/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.dao.exceptions.IllegalOrphanException;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Cliente;
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

/**
 *
 * @author Ariel
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaCliente = cliente.getUnaInmobiliariaCliente();
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente = em.getReference(unaInmobiliariaCliente.getClass(), unaInmobiliariaCliente.getId());
                cliente.setUnaInmobiliariaCliente(unaInmobiliariaCliente);
            }
            UsuarioCliente unUsuarioCliente = cliente.getUnUsuarioCliente();
            if (unUsuarioCliente != null) {
                unUsuarioCliente = em.getReference(unUsuarioCliente.getClass(), unUsuarioCliente.getId());
                cliente.setUnUsuarioCliente(unUsuarioCliente);
            }
            em.persist(cliente);
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente.getClientes().add(cliente);
                unaInmobiliariaCliente = em.merge(unaInmobiliariaCliente);
            }
            if (unUsuarioCliente != null) {
                Cliente oldUnClienteOfUnUsuarioCliente = unUsuarioCliente.getUnCliente();
                if (oldUnClienteOfUnUsuarioCliente != null) {
                    oldUnClienteOfUnUsuarioCliente.setUnUsuarioCliente(null);
                    oldUnClienteOfUnUsuarioCliente = em.merge(oldUnClienteOfUnUsuarioCliente);
                }
                unUsuarioCliente.setUnCliente(cliente);
                unUsuarioCliente = em.merge(unUsuarioCliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getId());
            Inmobiliaria unaInmobiliariaClienteOld = persistentCliente.getUnaInmobiliariaCliente();
            Inmobiliaria unaInmobiliariaClienteNew = cliente.getUnaInmobiliariaCliente();
            UsuarioCliente unUsuarioClienteOld = persistentCliente.getUnUsuarioCliente();
            UsuarioCliente unUsuarioClienteNew = cliente.getUnUsuarioCliente();
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
                cliente.setUnaInmobiliariaCliente(unaInmobiliariaClienteNew);
            }
            if (unUsuarioClienteNew != null) {
                unUsuarioClienteNew = em.getReference(unUsuarioClienteNew.getClass(), unUsuarioClienteNew.getId());
                cliente.setUnUsuarioCliente(unUsuarioClienteNew);
            }
            cliente = em.merge(cliente);
            if (unaInmobiliariaClienteOld != null && !unaInmobiliariaClienteOld.equals(unaInmobiliariaClienteNew)) {
                unaInmobiliariaClienteOld.getClientes().remove(cliente);
                unaInmobiliariaClienteOld = em.merge(unaInmobiliariaClienteOld);
            }
            if (unaInmobiliariaClienteNew != null && !unaInmobiliariaClienteNew.equals(unaInmobiliariaClienteOld)) {
                unaInmobiliariaClienteNew.getClientes().add(cliente);
                unaInmobiliariaClienteNew = em.merge(unaInmobiliariaClienteNew);
            }
            if (unUsuarioClienteNew != null && !unUsuarioClienteNew.equals(unUsuarioClienteOld)) {
                Cliente oldUnClienteOfUnUsuarioCliente = unUsuarioClienteNew.getUnCliente();
                if (oldUnClienteOfUnUsuarioCliente != null) {
                    oldUnClienteOfUnUsuarioCliente.setUnUsuarioCliente(null);
                    oldUnClienteOfUnUsuarioCliente = em.merge(oldUnClienteOfUnUsuarioCliente);
                }
                unUsuarioClienteNew.setUnCliente(cliente);
                unUsuarioClienteNew = em.merge(unUsuarioClienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cliente.getId();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            UsuarioCliente unUsuarioClienteOrphanCheck = cliente.getUnUsuarioCliente();
            if (unUsuarioClienteOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the UsuarioCliente " + unUsuarioClienteOrphanCheck + " in its unUsuarioCliente field has a non-nullable unCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Inmobiliaria unaInmobiliariaCliente = cliente.getUnaInmobiliariaCliente();
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente.getClientes().remove(cliente);
                unaInmobiliariaCliente = em.merge(unaInmobiliariaCliente);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
