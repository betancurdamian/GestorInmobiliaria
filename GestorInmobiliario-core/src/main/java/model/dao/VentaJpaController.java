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
import model.entity.Inmobiliaria;
import model.entity.Venta;

/**
 *
 * @author Ariel
 */
public class VentaJpaController implements Serializable {

    public VentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venta venta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContratoVenta unContratoVenta = venta.getUnContratoVenta();
            if (unContratoVenta != null) {
                unContratoVenta = em.getReference(unContratoVenta.getClass(), unContratoVenta.getId());
                venta.setUnContratoVenta(unContratoVenta);
            }
            Inmobiliaria unaInmobiliariaVenta = venta.getUnaInmobiliariaVenta();
            if (unaInmobiliariaVenta != null) {
                unaInmobiliariaVenta = em.getReference(unaInmobiliariaVenta.getClass(), unaInmobiliariaVenta.getId());
                venta.setUnaInmobiliariaVenta(unaInmobiliariaVenta);
            }
            em.persist(venta);
            if (unContratoVenta != null) {
                Venta oldUnaVentaOfUnContratoVenta = unContratoVenta.getUnaVenta();
                if (oldUnaVentaOfUnContratoVenta != null) {
                    oldUnaVentaOfUnContratoVenta.setUnContratoVenta(null);
                    oldUnaVentaOfUnContratoVenta = em.merge(oldUnaVentaOfUnContratoVenta);
                }
                unContratoVenta.setUnaVenta(venta);
                unContratoVenta = em.merge(unContratoVenta);
            }
            if (unaInmobiliariaVenta != null) {
                unaInmobiliariaVenta.getVentas().add(venta);
                unaInmobiliariaVenta = em.merge(unaInmobiliariaVenta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getId());
            ContratoVenta unContratoVentaOld = persistentVenta.getUnContratoVenta();
            ContratoVenta unContratoVentaNew = venta.getUnContratoVenta();
            Inmobiliaria unaInmobiliariaVentaOld = persistentVenta.getUnaInmobiliariaVenta();
            Inmobiliaria unaInmobiliariaVentaNew = venta.getUnaInmobiliariaVenta();
            if (unContratoVentaNew != null) {
                unContratoVentaNew = em.getReference(unContratoVentaNew.getClass(), unContratoVentaNew.getId());
                venta.setUnContratoVenta(unContratoVentaNew);
            }
            if (unaInmobiliariaVentaNew != null) {
                unaInmobiliariaVentaNew = em.getReference(unaInmobiliariaVentaNew.getClass(), unaInmobiliariaVentaNew.getId());
                venta.setUnaInmobiliariaVenta(unaInmobiliariaVentaNew);
            }
            venta = em.merge(venta);
            if (unContratoVentaOld != null && !unContratoVentaOld.equals(unContratoVentaNew)) {
                unContratoVentaOld.setUnaVenta(null);
                unContratoVentaOld = em.merge(unContratoVentaOld);
            }
            if (unContratoVentaNew != null && !unContratoVentaNew.equals(unContratoVentaOld)) {
                Venta oldUnaVentaOfUnContratoVenta = unContratoVentaNew.getUnaVenta();
                if (oldUnaVentaOfUnContratoVenta != null) {
                    oldUnaVentaOfUnContratoVenta.setUnContratoVenta(null);
                    oldUnaVentaOfUnContratoVenta = em.merge(oldUnaVentaOfUnContratoVenta);
                }
                unContratoVentaNew.setUnaVenta(venta);
                unContratoVentaNew = em.merge(unContratoVentaNew);
            }
            if (unaInmobiliariaVentaOld != null && !unaInmobiliariaVentaOld.equals(unaInmobiliariaVentaNew)) {
                unaInmobiliariaVentaOld.getVentas().remove(venta);
                unaInmobiliariaVentaOld = em.merge(unaInmobiliariaVentaOld);
            }
            if (unaInmobiliariaVentaNew != null && !unaInmobiliariaVentaNew.equals(unaInmobiliariaVentaOld)) {
                unaInmobiliariaVentaNew.getVentas().add(venta);
                unaInmobiliariaVentaNew = em.merge(unaInmobiliariaVentaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = venta.getId();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
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
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            ContratoVenta unContratoVenta = venta.getUnContratoVenta();
            if (unContratoVenta != null) {
                unContratoVenta.setUnaVenta(null);
                unContratoVenta = em.merge(unContratoVenta);
            }
            Inmobiliaria unaInmobiliariaVenta = venta.getUnaInmobiliariaVenta();
            if (unaInmobiliariaVenta != null) {
                unaInmobiliariaVenta.getVentas().remove(venta);
                unaInmobiliariaVenta = em.merge(unaInmobiliariaVenta);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
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

    public Venta findVenta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
