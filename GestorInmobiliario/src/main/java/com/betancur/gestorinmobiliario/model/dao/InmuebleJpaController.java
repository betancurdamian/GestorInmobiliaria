/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.betancur.gestorinmobiliario.model.Inmobiliaria;
import com.betancur.gestorinmobiliario.model.Alquiler;
import com.betancur.gestorinmobiliario.model.Inmueble;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
            Alquiler unAlquiler = inmueble.getUnAlquiler();
            if (unAlquiler != null) {
                unAlquiler = em.getReference(unAlquiler.getClass(), unAlquiler.getId());
                inmueble.setUnAlquiler(unAlquiler);
            }
            em.persist(inmueble);
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble.getInmuebles().add(inmueble);
                unaInmobiliariaInmueble = em.merge(unaInmobiliariaInmueble);
            }
            if (unAlquiler != null) {
                Inmueble oldUnInmuebleOfUnAlquiler = unAlquiler.getUnInmueble();
                if (oldUnInmuebleOfUnAlquiler != null) {
                    oldUnInmuebleOfUnAlquiler.setUnAlquiler(null);
                    oldUnInmuebleOfUnAlquiler = em.merge(oldUnInmuebleOfUnAlquiler);
                }
                unAlquiler.setUnInmueble(inmueble);
                unAlquiler = em.merge(unAlquiler);
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
            Alquiler unAlquilerOld = persistentInmueble.getUnAlquiler();
            Alquiler unAlquilerNew = inmueble.getUnAlquiler();
            if (unaInmobiliariaInmuebleNew != null) {
                unaInmobiliariaInmuebleNew = em.getReference(unaInmobiliariaInmuebleNew.getClass(), unaInmobiliariaInmuebleNew.getId());
                inmueble.setUnaInmobiliariaInmueble(unaInmobiliariaInmuebleNew);
            }
            if (unAlquilerNew != null) {
                unAlquilerNew = em.getReference(unAlquilerNew.getClass(), unAlquilerNew.getId());
                inmueble.setUnAlquiler(unAlquilerNew);
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
            if (unAlquilerOld != null && !unAlquilerOld.equals(unAlquilerNew)) {
                unAlquilerOld.setUnInmueble(null);
                unAlquilerOld = em.merge(unAlquilerOld);
            }
            if (unAlquilerNew != null && !unAlquilerNew.equals(unAlquilerOld)) {
                Inmueble oldUnInmuebleOfUnAlquiler = unAlquilerNew.getUnInmueble();
                if (oldUnInmuebleOfUnAlquiler != null) {
                    oldUnInmuebleOfUnAlquiler.setUnAlquiler(null);
                    oldUnInmuebleOfUnAlquiler = em.merge(oldUnInmuebleOfUnAlquiler);
                }
                unAlquilerNew.setUnInmueble(inmueble);
                unAlquilerNew = em.merge(unAlquilerNew);
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
            Alquiler unAlquiler = inmueble.getUnAlquiler();
            if (unAlquiler != null) {
                unAlquiler.setUnInmueble(null);
                unAlquiler = em.merge(unAlquiler);
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
