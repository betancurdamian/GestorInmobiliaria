/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.dao;

import com.betancur.gestorinmobiliario.model.ArancelEspecial;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.betancur.gestorinmobiliario.model.Inmobiliaria;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class ArancelEspecialJpaController implements Serializable {

    public ArancelEspecialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ArancelEspecial arancelEspecial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaArancelEspecial = arancelEspecial.getUnaInmobiliariaArancelEspecial();
            if (unaInmobiliariaArancelEspecial != null) {
                unaInmobiliariaArancelEspecial = em.getReference(unaInmobiliariaArancelEspecial.getClass(), unaInmobiliariaArancelEspecial.getId());
                arancelEspecial.setUnaInmobiliariaArancelEspecial(unaInmobiliariaArancelEspecial);
            }
            em.persist(arancelEspecial);
            if (unaInmobiliariaArancelEspecial != null) {
                unaInmobiliariaArancelEspecial.getArancelesEspeciales().add(arancelEspecial);
                unaInmobiliariaArancelEspecial = em.merge(unaInmobiliariaArancelEspecial);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ArancelEspecial arancelEspecial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArancelEspecial persistentArancelEspecial = em.find(ArancelEspecial.class, arancelEspecial.getId());
            Inmobiliaria unaInmobiliariaArancelEspecialOld = persistentArancelEspecial.getUnaInmobiliariaArancelEspecial();
            Inmobiliaria unaInmobiliariaArancelEspecialNew = arancelEspecial.getUnaInmobiliariaArancelEspecial();
            if (unaInmobiliariaArancelEspecialNew != null) {
                unaInmobiliariaArancelEspecialNew = em.getReference(unaInmobiliariaArancelEspecialNew.getClass(), unaInmobiliariaArancelEspecialNew.getId());
                arancelEspecial.setUnaInmobiliariaArancelEspecial(unaInmobiliariaArancelEspecialNew);
            }
            arancelEspecial = em.merge(arancelEspecial);
            if (unaInmobiliariaArancelEspecialOld != null && !unaInmobiliariaArancelEspecialOld.equals(unaInmobiliariaArancelEspecialNew)) {
                unaInmobiliariaArancelEspecialOld.getArancelesEspeciales().remove(arancelEspecial);
                unaInmobiliariaArancelEspecialOld = em.merge(unaInmobiliariaArancelEspecialOld);
            }
            if (unaInmobiliariaArancelEspecialNew != null && !unaInmobiliariaArancelEspecialNew.equals(unaInmobiliariaArancelEspecialOld)) {
                unaInmobiliariaArancelEspecialNew.getArancelesEspeciales().add(arancelEspecial);
                unaInmobiliariaArancelEspecialNew = em.merge(unaInmobiliariaArancelEspecialNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = arancelEspecial.getId();
                if (findArancelEspecial(id) == null) {
                    throw new NonexistentEntityException("The arancelEspecial with id " + id + " no longer exists.");
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
            ArancelEspecial arancelEspecial;
            try {
                arancelEspecial = em.getReference(ArancelEspecial.class, id);
                arancelEspecial.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The arancelEspecial with id " + id + " no longer exists.", enfe);
            }
            Inmobiliaria unaInmobiliariaArancelEspecial = arancelEspecial.getUnaInmobiliariaArancelEspecial();
            if (unaInmobiliariaArancelEspecial != null) {
                unaInmobiliariaArancelEspecial.getArancelesEspeciales().remove(arancelEspecial);
                unaInmobiliariaArancelEspecial = em.merge(unaInmobiliariaArancelEspecial);
            }
            em.remove(arancelEspecial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ArancelEspecial> findArancelEspecialEntities() {
        return findArancelEspecialEntities(true, -1, -1);
    }

    public List<ArancelEspecial> findArancelEspecialEntities(int maxResults, int firstResult) {
        return findArancelEspecialEntities(false, maxResults, firstResult);
    }

    private List<ArancelEspecial> findArancelEspecialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ArancelEspecial.class));
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

    public ArancelEspecial findArancelEspecial(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ArancelEspecial.class, id);
        } finally {
            em.close();
        }
    }

    public int getArancelEspecialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ArancelEspecial> rt = cq.from(ArancelEspecial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
