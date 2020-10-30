/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.dao.exceptions.NonexistentEntityException;
import model.entity.ArancelEspecialExpensa;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entity.Inmobiliaria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class ArancelEspecialExpensaJpaController implements Serializable {

    public ArancelEspecialExpensaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ArancelEspecialExpensa arancelEspecialExpensa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaArancelEspecial = arancelEspecialExpensa.getUnaInmobiliariaArancelEspecial();
            if (unaInmobiliariaArancelEspecial != null) {
                unaInmobiliariaArancelEspecial = em.getReference(unaInmobiliariaArancelEspecial.getClass(), unaInmobiliariaArancelEspecial.getId());
                arancelEspecialExpensa.setUnaInmobiliariaArancelEspecial(unaInmobiliariaArancelEspecial);
            }
            em.persist(arancelEspecialExpensa);
            if (unaInmobiliariaArancelEspecial != null) {
                unaInmobiliariaArancelEspecial.getArancelesEspeciales().add(arancelEspecialExpensa);
                unaInmobiliariaArancelEspecial = em.merge(unaInmobiliariaArancelEspecial);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ArancelEspecialExpensa arancelEspecialExpensa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArancelEspecialExpensa persistentArancelEspecialExpensa = em.find(ArancelEspecialExpensa.class, arancelEspecialExpensa.getId());
            Inmobiliaria unaInmobiliariaArancelEspecialOld = persistentArancelEspecialExpensa.getUnaInmobiliariaArancelEspecial();
            Inmobiliaria unaInmobiliariaArancelEspecialNew = arancelEspecialExpensa.getUnaInmobiliariaArancelEspecial();
            if (unaInmobiliariaArancelEspecialNew != null) {
                unaInmobiliariaArancelEspecialNew = em.getReference(unaInmobiliariaArancelEspecialNew.getClass(), unaInmobiliariaArancelEspecialNew.getId());
                arancelEspecialExpensa.setUnaInmobiliariaArancelEspecial(unaInmobiliariaArancelEspecialNew);
            }
            arancelEspecialExpensa = em.merge(arancelEspecialExpensa);
            if (unaInmobiliariaArancelEspecialOld != null && !unaInmobiliariaArancelEspecialOld.equals(unaInmobiliariaArancelEspecialNew)) {
                unaInmobiliariaArancelEspecialOld.getArancelesEspeciales().remove(arancelEspecialExpensa);
                unaInmobiliariaArancelEspecialOld = em.merge(unaInmobiliariaArancelEspecialOld);
            }
            if (unaInmobiliariaArancelEspecialNew != null && !unaInmobiliariaArancelEspecialNew.equals(unaInmobiliariaArancelEspecialOld)) {
                unaInmobiliariaArancelEspecialNew.getArancelesEspeciales().add(arancelEspecialExpensa);
                unaInmobiliariaArancelEspecialNew = em.merge(unaInmobiliariaArancelEspecialNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = arancelEspecialExpensa.getId();
                if (findArancelEspecialExpensa(id) == null) {
                    throw new NonexistentEntityException("The arancelEspecialExpensa with id " + id + " no longer exists.");
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
            ArancelEspecialExpensa arancelEspecialExpensa;
            try {
                arancelEspecialExpensa = em.getReference(ArancelEspecialExpensa.class, id);
                arancelEspecialExpensa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The arancelEspecialExpensa with id " + id + " no longer exists.", enfe);
            }
            Inmobiliaria unaInmobiliariaArancelEspecial = arancelEspecialExpensa.getUnaInmobiliariaArancelEspecial();
            if (unaInmobiliariaArancelEspecial != null) {
                unaInmobiliariaArancelEspecial.getArancelesEspeciales().remove(arancelEspecialExpensa);
                unaInmobiliariaArancelEspecial = em.merge(unaInmobiliariaArancelEspecial);
            }
            em.remove(arancelEspecialExpensa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ArancelEspecialExpensa> findArancelEspecialExpensaEntities() {
        return findArancelEspecialExpensaEntities(true, -1, -1);
    }

    public List<ArancelEspecialExpensa> findArancelEspecialExpensaEntities(int maxResults, int firstResult) {
        return findArancelEspecialExpensaEntities(false, maxResults, firstResult);
    }

    private List<ArancelEspecialExpensa> findArancelEspecialExpensaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ArancelEspecialExpensa.class));
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

    public ArancelEspecialExpensa findArancelEspecialExpensa(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ArancelEspecialExpensa.class, id);
        } finally {
            em.close();
        }
    }

    public int getArancelEspecialExpensaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ArancelEspecialExpensa> rt = cq.from(ArancelEspecialExpensa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
