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
import model.entity.ContratoVenta;
import model.entity.CuotaVenta;

/**
 *
 * @author Ariel
 */
public class CuotaVentaJpaController implements Serializable {

    public CuotaVentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CuotaVenta cuotaVenta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContratoVenta unContratoVenta = cuotaVenta.getUnContratoVenta();
            if (unContratoVenta != null) {
                unContratoVenta = em.getReference(unContratoVenta.getClass(), unContratoVenta.getId());
                cuotaVenta.setUnContratoVenta(unContratoVenta);
            }
            em.persist(cuotaVenta);
            if (unContratoVenta != null) {
                unContratoVenta.getCuotasVenta().add(cuotaVenta);
                unContratoVenta = em.merge(unContratoVenta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CuotaVenta cuotaVenta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CuotaVenta persistentCuotaVenta = em.find(CuotaVenta.class, cuotaVenta.getId());
            ContratoVenta unContratoVentaOld = persistentCuotaVenta.getUnContratoVenta();
            ContratoVenta unContratoVentaNew = cuotaVenta.getUnContratoVenta();
            if (unContratoVentaNew != null) {
                unContratoVentaNew = em.getReference(unContratoVentaNew.getClass(), unContratoVentaNew.getId());
                cuotaVenta.setUnContratoVenta(unContratoVentaNew);
            }
            cuotaVenta = em.merge(cuotaVenta);
            if (unContratoVentaOld != null && !unContratoVentaOld.equals(unContratoVentaNew)) {
                unContratoVentaOld.getCuotasVenta().remove(cuotaVenta);
                unContratoVentaOld = em.merge(unContratoVentaOld);
            }
            if (unContratoVentaNew != null && !unContratoVentaNew.equals(unContratoVentaOld)) {
                unContratoVentaNew.getCuotasVenta().add(cuotaVenta);
                unContratoVentaNew = em.merge(unContratoVentaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cuotaVenta.getId();
                if (findCuotaVenta(id) == null) {
                    throw new NonexistentEntityException("The cuotaVenta with id " + id + " no longer exists.");
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
            CuotaVenta cuotaVenta;
            try {
                cuotaVenta = em.getReference(CuotaVenta.class, id);
                cuotaVenta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuotaVenta with id " + id + " no longer exists.", enfe);
            }
            ContratoVenta unContratoVenta = cuotaVenta.getUnContratoVenta();
            if (unContratoVenta != null) {
                unContratoVenta.getCuotasVenta().remove(cuotaVenta);
                unContratoVenta = em.merge(unContratoVenta);
            }
            em.remove(cuotaVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CuotaVenta> findCuotaVentaEntities() {
        return findCuotaVentaEntities(true, -1, -1);
    }

    public List<CuotaVenta> findCuotaVentaEntities(int maxResults, int firstResult) {
        return findCuotaVentaEntities(false, maxResults, firstResult);
    }

    private List<CuotaVenta> findCuotaVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CuotaVenta.class));
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

    public CuotaVenta findCuotaVenta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CuotaVenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getCuotaVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CuotaVenta> rt = cq.from(CuotaVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
