/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.dao;

import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.betancur.gestorinmobiliario.model.entity.Inmobiliaria;
import com.betancur.gestorinmobiliario.model.entity.Alquiler;
import com.betancur.gestorinmobiliario.model.entity.Casa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class CasaJpaController implements Serializable {

    public CasaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Casa casa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaInmueble = casa.getUnaInmobiliariaInmueble();
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble = em.getReference(unaInmobiliariaInmueble.getClass(), unaInmobiliariaInmueble.getId());
                casa.setUnaInmobiliariaInmueble(unaInmobiliariaInmueble);
            }
            Alquiler unAlquiler = casa.getUnAlquiler();
            if (unAlquiler != null) {
                unAlquiler = em.getReference(unAlquiler.getClass(), unAlquiler.getId());
                casa.setUnAlquiler(unAlquiler);
            }
            em.persist(casa);
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble.getInmuebles().add(casa);
                unaInmobiliariaInmueble = em.merge(unaInmobiliariaInmueble);
            }
            if (unAlquiler != null) {
                com.betancur.gestorinmobiliario.model.entity.Inmueble oldUnInmuebleOfUnAlquiler = unAlquiler.getUnInmueble();
                if (oldUnInmuebleOfUnAlquiler != null) {
                    oldUnInmuebleOfUnAlquiler.setUnAlquiler(null);
                    oldUnInmuebleOfUnAlquiler = em.merge(oldUnInmuebleOfUnAlquiler);
                }
                unAlquiler.setUnInmueble(casa);
                unAlquiler = em.merge(unAlquiler);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Casa casa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Casa persistentCasa = em.find(Casa.class, casa.getId());
            Inmobiliaria unaInmobiliariaInmuebleOld = persistentCasa.getUnaInmobiliariaInmueble();
            Inmobiliaria unaInmobiliariaInmuebleNew = casa.getUnaInmobiliariaInmueble();
            Alquiler unAlquilerOld = persistentCasa.getUnAlquiler();
            Alquiler unAlquilerNew = casa.getUnAlquiler();
            if (unaInmobiliariaInmuebleNew != null) {
                unaInmobiliariaInmuebleNew = em.getReference(unaInmobiliariaInmuebleNew.getClass(), unaInmobiliariaInmuebleNew.getId());
                casa.setUnaInmobiliariaInmueble(unaInmobiliariaInmuebleNew);
            }
            if (unAlquilerNew != null) {
                unAlquilerNew = em.getReference(unAlquilerNew.getClass(), unAlquilerNew.getId());
                casa.setUnAlquiler(unAlquilerNew);
            }
            casa = em.merge(casa);
            if (unaInmobiliariaInmuebleOld != null && !unaInmobiliariaInmuebleOld.equals(unaInmobiliariaInmuebleNew)) {
                unaInmobiliariaInmuebleOld.getInmuebles().remove(casa);
                unaInmobiliariaInmuebleOld = em.merge(unaInmobiliariaInmuebleOld);
            }
            if (unaInmobiliariaInmuebleNew != null && !unaInmobiliariaInmuebleNew.equals(unaInmobiliariaInmuebleOld)) {
                unaInmobiliariaInmuebleNew.getInmuebles().add(casa);
                unaInmobiliariaInmuebleNew = em.merge(unaInmobiliariaInmuebleNew);
            }
            if (unAlquilerOld != null && !unAlquilerOld.equals(unAlquilerNew)) {
                unAlquilerOld.setUnInmueble(null);
                unAlquilerOld = em.merge(unAlquilerOld);
            }
            if (unAlquilerNew != null && !unAlquilerNew.equals(unAlquilerOld)) {
                com.betancur.gestorinmobiliario.model.entity.Inmueble oldUnInmuebleOfUnAlquiler = unAlquilerNew.getUnInmueble();
                if (oldUnInmuebleOfUnAlquiler != null) {
                    oldUnInmuebleOfUnAlquiler.setUnAlquiler(null);
                    oldUnInmuebleOfUnAlquiler = em.merge(oldUnInmuebleOfUnAlquiler);
                }
                unAlquilerNew.setUnInmueble(casa);
                unAlquilerNew = em.merge(unAlquilerNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = casa.getId();
                if (findCasa(id) == null) {
                    throw new NonexistentEntityException("The casa with id " + id + " no longer exists.");
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
            Casa casa;
            try {
                casa = em.getReference(Casa.class, id);
                casa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The casa with id " + id + " no longer exists.", enfe);
            }
            Inmobiliaria unaInmobiliariaInmueble = casa.getUnaInmobiliariaInmueble();
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble.getInmuebles().remove(casa);
                unaInmobiliariaInmueble = em.merge(unaInmobiliariaInmueble);
            }
            Alquiler unAlquiler = casa.getUnAlquiler();
            if (unAlquiler != null) {
                unAlquiler.setUnInmueble(null);
                unAlquiler = em.merge(unAlquiler);
            }
            em.remove(casa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Casa> findCasaEntities() {
        return findCasaEntities(true, -1, -1);
    }

    public List<Casa> findCasaEntities(int maxResults, int firstResult) {
        return findCasaEntities(false, maxResults, firstResult);
    }

    private List<Casa> findCasaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Casa.class));
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

    public Casa findCasa(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Casa.class, id);
        } finally {
            em.close();
        }
    }

    public int getCasaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Casa> rt = cq.from(Casa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
