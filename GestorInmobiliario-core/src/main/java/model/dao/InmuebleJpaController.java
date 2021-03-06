/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Inmobiliaria;
import model.entity.Cliente;
import model.entity.Inmueble;

/**
 *
 * @author Ariel
 */
public class InmuebleJpaController implements Serializable {

    public InmuebleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Inmueble inmueble) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaInmueble = inmueble.getUnaInmobiliariaInmueble();
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble = em.getReference(unaInmobiliariaInmueble.getClass(), unaInmobiliariaInmueble.getId());
                inmueble.setUnaInmobiliariaInmueble(unaInmobiliariaInmueble);
            }
            Cliente unCliente = inmueble.getUnCliente();
            if (unCliente != null) {
                unCliente = em.getReference(unCliente.getClass(), unCliente.getId());
                inmueble.setUnCliente(unCliente);
            }
            em.persist(inmueble);
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble.getInmuebles().add(inmueble);
                unaInmobiliariaInmueble = em.merge(unaInmobiliariaInmueble);
            }
            if (unCliente != null) {
                unCliente.getInmuebles().add(inmueble);
                unCliente = em.merge(unCliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Inmueble inmueble) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmueble persistentInmueble = em.find(Inmueble.class, inmueble.getId());
            Inmobiliaria unaInmobiliariaInmuebleOld = persistentInmueble.getUnaInmobiliariaInmueble();
            Inmobiliaria unaInmobiliariaInmuebleNew = inmueble.getUnaInmobiliariaInmueble();
            Cliente unClienteOld = persistentInmueble.getUnCliente();
            Cliente unClienteNew = inmueble.getUnCliente();
            if (unaInmobiliariaInmuebleNew != null) {
                unaInmobiliariaInmuebleNew = em.getReference(unaInmobiliariaInmuebleNew.getClass(), unaInmobiliariaInmuebleNew.getId());
                inmueble.setUnaInmobiliariaInmueble(unaInmobiliariaInmuebleNew);
            }
            if (unClienteNew != null) {
                unClienteNew = em.getReference(unClienteNew.getClass(), unClienteNew.getId());
                inmueble.setUnCliente(unClienteNew);
            }
            inmueble = em.merge(inmueble);
            if (unaInmobiliariaInmuebleOld != null && !unaInmobiliariaInmuebleOld.equals(unaInmobiliariaInmuebleNew)) {
                unaInmobiliariaInmuebleOld.getInmuebles().remove(inmueble);
                unaInmobiliariaInmuebleOld = em.merge(unaInmobiliariaInmuebleOld);
            }
            if (unaInmobiliariaInmuebleNew != null && !unaInmobiliariaInmuebleNew.equals(unaInmobiliariaInmuebleOld)) {
                unaInmobiliariaInmuebleNew.getInmuebles().add(inmueble);
                unaInmobiliariaInmuebleNew = em.merge(unaInmobiliariaInmuebleNew);
            }
            if (unClienteOld != null && !unClienteOld.equals(unClienteNew)) {
                unClienteOld.getInmuebles().remove(inmueble);
                unClienteOld = em.merge(unClienteOld);
            }
            if (unClienteNew != null && !unClienteNew.equals(unClienteOld)) {
                unClienteNew.getInmuebles().add(inmueble);
                unClienteNew = em.merge(unClienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = inmueble.getId();
                if (findInmueble(id) == null) {
                    throw new NonexistentEntityException("The inmueble with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmueble inmueble;
            try {
                inmueble = em.getReference(Inmueble.class, id);
                inmueble.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inmueble with id " + id + " no longer exists.", enfe);
            }
            Inmobiliaria unaInmobiliariaInmueble = inmueble.getUnaInmobiliariaInmueble();
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble.getInmuebles().remove(inmueble);
                unaInmobiliariaInmueble = em.merge(unaInmobiliariaInmueble);
            }
            Cliente unCliente = inmueble.getUnCliente();
            if (unCliente != null) {
                unCliente.getInmuebles().remove(inmueble);
                unCliente = em.merge(unCliente);
            }
            em.remove(inmueble);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Inmueble> findInmuebleEntities() {
        return findInmuebleEntities(true, -1, -1);
    }

    public List<Inmueble> findInmuebleEntities(int maxResults, int firstResult) {
        return findInmuebleEntities(false, maxResults, firstResult);
    }

    private List<Inmueble> findInmuebleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Inmueble.class));
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

    public Inmueble findInmueble(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Inmueble.class, id);
        } finally {
            em.close();
        }
    }

    public int getInmuebleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Inmueble> rt = cq.from(Inmueble.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
