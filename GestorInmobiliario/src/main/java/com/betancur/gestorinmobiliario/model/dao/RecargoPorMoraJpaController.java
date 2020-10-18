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
import com.betancur.gestorinmobiliario.model.RecargoPorMora;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class RecargoPorMoraJpaController implements Serializable {

    public RecargoPorMoraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RecargoPorMora recargoPorMora) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaRecargoPorMora = recargoPorMora.getUnaInmobiliariaRecargoPorMora();
            if (unaInmobiliariaRecargoPorMora != null) {
                unaInmobiliariaRecargoPorMora = em.getReference(unaInmobiliariaRecargoPorMora.getClass(), unaInmobiliariaRecargoPorMora.getId());
                recargoPorMora.setUnaInmobiliariaRecargoPorMora(unaInmobiliariaRecargoPorMora);
            }
            em.persist(recargoPorMora);
            if (unaInmobiliariaRecargoPorMora != null) {
                unaInmobiliariaRecargoPorMora.getRecargosPorMoras().add(recargoPorMora);
                unaInmobiliariaRecargoPorMora = em.merge(unaInmobiliariaRecargoPorMora);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RecargoPorMora recargoPorMora) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecargoPorMora persistentRecargoPorMora = em.find(RecargoPorMora.class, recargoPorMora.getId());
            Inmobiliaria unaInmobiliariaRecargoPorMoraOld = persistentRecargoPorMora.getUnaInmobiliariaRecargoPorMora();
            Inmobiliaria unaInmobiliariaRecargoPorMoraNew = recargoPorMora.getUnaInmobiliariaRecargoPorMora();
            if (unaInmobiliariaRecargoPorMoraNew != null) {
                unaInmobiliariaRecargoPorMoraNew = em.getReference(unaInmobiliariaRecargoPorMoraNew.getClass(), unaInmobiliariaRecargoPorMoraNew.getId());
                recargoPorMora.setUnaInmobiliariaRecargoPorMora(unaInmobiliariaRecargoPorMoraNew);
            }
            recargoPorMora = em.merge(recargoPorMora);
            if (unaInmobiliariaRecargoPorMoraOld != null && !unaInmobiliariaRecargoPorMoraOld.equals(unaInmobiliariaRecargoPorMoraNew)) {
                unaInmobiliariaRecargoPorMoraOld.getRecargosPorMoras().remove(recargoPorMora);
                unaInmobiliariaRecargoPorMoraOld = em.merge(unaInmobiliariaRecargoPorMoraOld);
            }
            if (unaInmobiliariaRecargoPorMoraNew != null && !unaInmobiliariaRecargoPorMoraNew.equals(unaInmobiliariaRecargoPorMoraOld)) {
                unaInmobiliariaRecargoPorMoraNew.getRecargosPorMoras().add(recargoPorMora);
                unaInmobiliariaRecargoPorMoraNew = em.merge(unaInmobiliariaRecargoPorMoraNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = recargoPorMora.getId();
                if (findRecargoPorMora(id) == null) {
                    throw new NonexistentEntityException("The recargoPorMora with id " + id + " no longer exists.");
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
            RecargoPorMora recargoPorMora;
            try {
                recargoPorMora = em.getReference(RecargoPorMora.class, id);
                recargoPorMora.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recargoPorMora with id " + id + " no longer exists.", enfe);
            }
            Inmobiliaria unaInmobiliariaRecargoPorMora = recargoPorMora.getUnaInmobiliariaRecargoPorMora();
            if (unaInmobiliariaRecargoPorMora != null) {
                unaInmobiliariaRecargoPorMora.getRecargosPorMoras().remove(recargoPorMora);
                unaInmobiliariaRecargoPorMora = em.merge(unaInmobiliariaRecargoPorMora);
            }
            em.remove(recargoPorMora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RecargoPorMora> findRecargoPorMoraEntities() {
        return findRecargoPorMoraEntities(true, -1, -1);
    }

    public List<RecargoPorMora> findRecargoPorMoraEntities(int maxResults, int firstResult) {
        return findRecargoPorMoraEntities(false, maxResults, firstResult);
    }

    private List<RecargoPorMora> findRecargoPorMoraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RecargoPorMora.class));
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

    public RecargoPorMora findRecargoPorMora(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RecargoPorMora.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecargoPorMoraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RecargoPorMora> rt = cq.from(RecargoPorMora.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
