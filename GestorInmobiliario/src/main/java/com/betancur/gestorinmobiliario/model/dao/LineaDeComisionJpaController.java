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
import com.betancur.gestorinmobiliario.model.Comision;
import com.betancur.gestorinmobiliario.model.LineaDeComision;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class LineaDeComisionJpaController implements Serializable {

    public LineaDeComisionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LineaDeComision lineaDeComision) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comision unaComision = lineaDeComision.getUnaComision();
            if (unaComision != null) {
                unaComision = em.getReference(unaComision.getClass(), unaComision.getId());
                lineaDeComision.setUnaComision(unaComision);
            }
            em.persist(lineaDeComision);
            if (unaComision != null) {
                unaComision.getLinesasDecomisiones().add(lineaDeComision);
                unaComision = em.merge(unaComision);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LineaDeComision lineaDeComision) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LineaDeComision persistentLineaDeComision = em.find(LineaDeComision.class, lineaDeComision.getId());
            Comision unaComisionOld = persistentLineaDeComision.getUnaComision();
            Comision unaComisionNew = lineaDeComision.getUnaComision();
            if (unaComisionNew != null) {
                unaComisionNew = em.getReference(unaComisionNew.getClass(), unaComisionNew.getId());
                lineaDeComision.setUnaComision(unaComisionNew);
            }
            lineaDeComision = em.merge(lineaDeComision);
            if (unaComisionOld != null && !unaComisionOld.equals(unaComisionNew)) {
                unaComisionOld.getLinesasDecomisiones().remove(lineaDeComision);
                unaComisionOld = em.merge(unaComisionOld);
            }
            if (unaComisionNew != null && !unaComisionNew.equals(unaComisionOld)) {
                unaComisionNew.getLinesasDecomisiones().add(lineaDeComision);
                unaComisionNew = em.merge(unaComisionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = lineaDeComision.getId();
                if (findLineaDeComision(id) == null) {
                    throw new NonexistentEntityException("The lineaDeComision with id " + id + " no longer exists.");
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
            LineaDeComision lineaDeComision;
            try {
                lineaDeComision = em.getReference(LineaDeComision.class, id);
                lineaDeComision.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lineaDeComision with id " + id + " no longer exists.", enfe);
            }
            Comision unaComision = lineaDeComision.getUnaComision();
            if (unaComision != null) {
                unaComision.getLinesasDecomisiones().remove(lineaDeComision);
                unaComision = em.merge(unaComision);
            }
            em.remove(lineaDeComision);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LineaDeComision> findLineaDeComisionEntities() {
        return findLineaDeComisionEntities(true, -1, -1);
    }

    public List<LineaDeComision> findLineaDeComisionEntities(int maxResults, int firstResult) {
        return findLineaDeComisionEntities(false, maxResults, firstResult);
    }

    private List<LineaDeComision> findLineaDeComisionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LineaDeComision.class));
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

    public LineaDeComision findLineaDeComision(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LineaDeComision.class, id);
        } finally {
            em.close();
        }
    }

    public int getLineaDeComisionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LineaDeComision> rt = cq.from(LineaDeComision.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
