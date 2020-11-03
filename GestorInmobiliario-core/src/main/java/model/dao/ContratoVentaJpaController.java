/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entity.Venta;
import model.entity.Comision;
import model.entity.CuotaVenta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.BoletaDePago;
import model.entity.ContratoVenta;

/**
 *
 * @author Ariel
 */
public class ContratoVentaJpaController implements Serializable {

    public ContratoVentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ContratoVenta contratoVenta) {
        if (contratoVenta.getCuotasVenta() == null) {
            contratoVenta.setCuotasVenta(new ArrayList<CuotaVenta>());
        }
        if (contratoVenta.getBoletasDePago() == null) {
            contratoVenta.setBoletasDePago(new ArrayList<BoletaDePago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta unaVenta = contratoVenta.getUnaVenta();
            if (unaVenta != null) {
                unaVenta = em.getReference(unaVenta.getClass(), unaVenta.getId());
                contratoVenta.setUnaVenta(unaVenta);
            }
            Comision unaComision = contratoVenta.getUnaComision();
            if (unaComision != null) {
                unaComision = em.getReference(unaComision.getClass(), unaComision.getId());
                contratoVenta.setUnaComision(unaComision);
            }
            List<CuotaVenta> attachedCuotasVenta = new ArrayList<CuotaVenta>();
            for (CuotaVenta cuotasVentaCuotaVentaToAttach : contratoVenta.getCuotasVenta()) {
                cuotasVentaCuotaVentaToAttach = em.getReference(cuotasVentaCuotaVentaToAttach.getClass(), cuotasVentaCuotaVentaToAttach.getId());
                attachedCuotasVenta.add(cuotasVentaCuotaVentaToAttach);
            }
            contratoVenta.setCuotasVenta(attachedCuotasVenta);
            List<BoletaDePago> attachedBoletasDePago = new ArrayList<BoletaDePago>();
            for (BoletaDePago boletasDePagoBoletaDePagoToAttach : contratoVenta.getBoletasDePago()) {
                boletasDePagoBoletaDePagoToAttach = em.getReference(boletasDePagoBoletaDePagoToAttach.getClass(), boletasDePagoBoletaDePagoToAttach.getId());
                attachedBoletasDePago.add(boletasDePagoBoletaDePagoToAttach);
            }
            contratoVenta.setBoletasDePago(attachedBoletasDePago);
            em.persist(contratoVenta);
            if (unaVenta != null) {
                ContratoVenta oldUnContratoVentaOfUnaVenta = unaVenta.getUnContratoVenta();
                if (oldUnContratoVentaOfUnaVenta != null) {
                    oldUnContratoVentaOfUnaVenta.setUnaVenta(null);
                    oldUnContratoVentaOfUnaVenta = em.merge(oldUnContratoVentaOfUnaVenta);
                }
                unaVenta.setUnContratoVenta(contratoVenta);
                unaVenta = em.merge(unaVenta);
            }
            if (unaComision != null) {
                model.entity.Contrato oldUnContratoOfUnaComision = unaComision.getUnContrato();
                if (oldUnContratoOfUnaComision != null) {
                    oldUnContratoOfUnaComision.setUnaComision(null);
                    oldUnContratoOfUnaComision = em.merge(oldUnContratoOfUnaComision);
                }
                unaComision.setUnContrato(contratoVenta);
                unaComision = em.merge(unaComision);
            }
            for (CuotaVenta cuotasVentaCuotaVenta : contratoVenta.getCuotasVenta()) {
                ContratoVenta oldUnContratoVentaOfCuotasVentaCuotaVenta = cuotasVentaCuotaVenta.getUnContratoVenta();
                cuotasVentaCuotaVenta.setUnContratoVenta(contratoVenta);
                cuotasVentaCuotaVenta = em.merge(cuotasVentaCuotaVenta);
                if (oldUnContratoVentaOfCuotasVentaCuotaVenta != null) {
                    oldUnContratoVentaOfCuotasVentaCuotaVenta.getCuotasVenta().remove(cuotasVentaCuotaVenta);
                    oldUnContratoVentaOfCuotasVentaCuotaVenta = em.merge(oldUnContratoVentaOfCuotasVentaCuotaVenta);
                }
            }
            for (BoletaDePago boletasDePagoBoletaDePago : contratoVenta.getBoletasDePago()) {
                model.entity.Contrato oldUnContratoOfBoletasDePagoBoletaDePago = boletasDePagoBoletaDePago.getUnContrato();
                boletasDePagoBoletaDePago.setUnContrato(contratoVenta);
                boletasDePagoBoletaDePago = em.merge(boletasDePagoBoletaDePago);
                if (oldUnContratoOfBoletasDePagoBoletaDePago != null) {
                    oldUnContratoOfBoletasDePagoBoletaDePago.getBoletasDePago().remove(boletasDePagoBoletaDePago);
                    oldUnContratoOfBoletasDePagoBoletaDePago = em.merge(oldUnContratoOfBoletasDePagoBoletaDePago);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ContratoVenta contratoVenta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContratoVenta persistentContratoVenta = em.find(ContratoVenta.class, contratoVenta.getId());
            Venta unaVentaOld = persistentContratoVenta.getUnaVenta();
            Venta unaVentaNew = contratoVenta.getUnaVenta();
            Comision unaComisionOld = persistentContratoVenta.getUnaComision();
            Comision unaComisionNew = contratoVenta.getUnaComision();
            List<CuotaVenta> cuotasVentaOld = persistentContratoVenta.getCuotasVenta();
            List<CuotaVenta> cuotasVentaNew = contratoVenta.getCuotasVenta();
            List<BoletaDePago> boletasDePagoOld = persistentContratoVenta.getBoletasDePago();
            List<BoletaDePago> boletasDePagoNew = contratoVenta.getBoletasDePago();
            if (unaVentaNew != null) {
                unaVentaNew = em.getReference(unaVentaNew.getClass(), unaVentaNew.getId());
                contratoVenta.setUnaVenta(unaVentaNew);
            }
            if (unaComisionNew != null) {
                unaComisionNew = em.getReference(unaComisionNew.getClass(), unaComisionNew.getId());
                contratoVenta.setUnaComision(unaComisionNew);
            }
            List<CuotaVenta> attachedCuotasVentaNew = new ArrayList<CuotaVenta>();
            for (CuotaVenta cuotasVentaNewCuotaVentaToAttach : cuotasVentaNew) {
                cuotasVentaNewCuotaVentaToAttach = em.getReference(cuotasVentaNewCuotaVentaToAttach.getClass(), cuotasVentaNewCuotaVentaToAttach.getId());
                attachedCuotasVentaNew.add(cuotasVentaNewCuotaVentaToAttach);
            }
            cuotasVentaNew = attachedCuotasVentaNew;
            contratoVenta.setCuotasVenta(cuotasVentaNew);
            List<BoletaDePago> attachedBoletasDePagoNew = new ArrayList<BoletaDePago>();
            for (BoletaDePago boletasDePagoNewBoletaDePagoToAttach : boletasDePagoNew) {
                boletasDePagoNewBoletaDePagoToAttach = em.getReference(boletasDePagoNewBoletaDePagoToAttach.getClass(), boletasDePagoNewBoletaDePagoToAttach.getId());
                attachedBoletasDePagoNew.add(boletasDePagoNewBoletaDePagoToAttach);
            }
            boletasDePagoNew = attachedBoletasDePagoNew;
            contratoVenta.setBoletasDePago(boletasDePagoNew);
            contratoVenta = em.merge(contratoVenta);
            if (unaVentaOld != null && !unaVentaOld.equals(unaVentaNew)) {
                unaVentaOld.setUnContratoVenta(null);
                unaVentaOld = em.merge(unaVentaOld);
            }
            if (unaVentaNew != null && !unaVentaNew.equals(unaVentaOld)) {
                ContratoVenta oldUnContratoVentaOfUnaVenta = unaVentaNew.getUnContratoVenta();
                if (oldUnContratoVentaOfUnaVenta != null) {
                    oldUnContratoVentaOfUnaVenta.setUnaVenta(null);
                    oldUnContratoVentaOfUnaVenta = em.merge(oldUnContratoVentaOfUnaVenta);
                }
                unaVentaNew.setUnContratoVenta(contratoVenta);
                unaVentaNew = em.merge(unaVentaNew);
            }
            if (unaComisionOld != null && !unaComisionOld.equals(unaComisionNew)) {
                unaComisionOld.setUnContrato(null);
                unaComisionOld = em.merge(unaComisionOld);
            }
            if (unaComisionNew != null && !unaComisionNew.equals(unaComisionOld)) {
                model.entity.Contrato oldUnContratoOfUnaComision = unaComisionNew.getUnContrato();
                if (oldUnContratoOfUnaComision != null) {
                    oldUnContratoOfUnaComision.setUnaComision(null);
                    oldUnContratoOfUnaComision = em.merge(oldUnContratoOfUnaComision);
                }
                unaComisionNew.setUnContrato(contratoVenta);
                unaComisionNew = em.merge(unaComisionNew);
            }
            for (CuotaVenta cuotasVentaOldCuotaVenta : cuotasVentaOld) {
                if (!cuotasVentaNew.contains(cuotasVentaOldCuotaVenta)) {
                    cuotasVentaOldCuotaVenta.setUnContratoVenta(null);
                    cuotasVentaOldCuotaVenta = em.merge(cuotasVentaOldCuotaVenta);
                }
            }
            for (CuotaVenta cuotasVentaNewCuotaVenta : cuotasVentaNew) {
                if (!cuotasVentaOld.contains(cuotasVentaNewCuotaVenta)) {
                    ContratoVenta oldUnContratoVentaOfCuotasVentaNewCuotaVenta = cuotasVentaNewCuotaVenta.getUnContratoVenta();
                    cuotasVentaNewCuotaVenta.setUnContratoVenta(contratoVenta);
                    cuotasVentaNewCuotaVenta = em.merge(cuotasVentaNewCuotaVenta);
                    if (oldUnContratoVentaOfCuotasVentaNewCuotaVenta != null && !oldUnContratoVentaOfCuotasVentaNewCuotaVenta.equals(contratoVenta)) {
                        oldUnContratoVentaOfCuotasVentaNewCuotaVenta.getCuotasVenta().remove(cuotasVentaNewCuotaVenta);
                        oldUnContratoVentaOfCuotasVentaNewCuotaVenta = em.merge(oldUnContratoVentaOfCuotasVentaNewCuotaVenta);
                    }
                }
            }
            for (BoletaDePago boletasDePagoOldBoletaDePago : boletasDePagoOld) {
                if (!boletasDePagoNew.contains(boletasDePagoOldBoletaDePago)) {
                    boletasDePagoOldBoletaDePago.setUnContrato(null);
                    boletasDePagoOldBoletaDePago = em.merge(boletasDePagoOldBoletaDePago);
                }
            }
            for (BoletaDePago boletasDePagoNewBoletaDePago : boletasDePagoNew) {
                if (!boletasDePagoOld.contains(boletasDePagoNewBoletaDePago)) {
                    ContratoVenta oldUnContratoOfBoletasDePagoNewBoletaDePago = (ContratoVenta) boletasDePagoNewBoletaDePago.getUnContrato();
                    boletasDePagoNewBoletaDePago.setUnContrato(contratoVenta);
                    boletasDePagoNewBoletaDePago = em.merge(boletasDePagoNewBoletaDePago);
                    if (oldUnContratoOfBoletasDePagoNewBoletaDePago != null && !oldUnContratoOfBoletasDePagoNewBoletaDePago.equals(contratoVenta)) {
                        oldUnContratoOfBoletasDePagoNewBoletaDePago.getBoletasDePago().remove(boletasDePagoNewBoletaDePago);
                        oldUnContratoOfBoletasDePagoNewBoletaDePago = em.merge(oldUnContratoOfBoletasDePagoNewBoletaDePago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = contratoVenta.getId();
                if (findContratoVenta(id) == null) {
                    throw new NonexistentEntityException("The contratoVenta with id " + id + " no longer exists.");
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
            ContratoVenta contratoVenta;
            try {
                contratoVenta = em.getReference(ContratoVenta.class, id);
                contratoVenta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contratoVenta with id " + id + " no longer exists.", enfe);
            }
            Venta unaVenta = contratoVenta.getUnaVenta();
            if (unaVenta != null) {
                unaVenta.setUnContratoVenta(null);
                unaVenta = em.merge(unaVenta);
            }
            Comision unaComision = contratoVenta.getUnaComision();
            if (unaComision != null) {
                unaComision.setUnContrato(null);
                unaComision = em.merge(unaComision);
            }
            List<CuotaVenta> cuotasVenta = contratoVenta.getCuotasVenta();
            for (CuotaVenta cuotasVentaCuotaVenta : cuotasVenta) {
                cuotasVentaCuotaVenta.setUnContratoVenta(null);
                cuotasVentaCuotaVenta = em.merge(cuotasVentaCuotaVenta);
            }
            List<BoletaDePago> boletasDePago = contratoVenta.getBoletasDePago();
            for (BoletaDePago boletasDePagoBoletaDePago : boletasDePago) {
                boletasDePagoBoletaDePago.setUnContrato(null);
                boletasDePagoBoletaDePago = em.merge(boletasDePagoBoletaDePago);
            }
            em.remove(contratoVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ContratoVenta> findContratoVentaEntities() {
        return findContratoVentaEntities(true, -1, -1);
    }

    public List<ContratoVenta> findContratoVentaEntities(int maxResults, int firstResult) {
        return findContratoVentaEntities(false, maxResults, firstResult);
    }

    private List<ContratoVenta> findContratoVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ContratoVenta.class));
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

    public ContratoVenta findContratoVenta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ContratoVenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getContratoVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ContratoVenta> rt = cq.from(ContratoVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
