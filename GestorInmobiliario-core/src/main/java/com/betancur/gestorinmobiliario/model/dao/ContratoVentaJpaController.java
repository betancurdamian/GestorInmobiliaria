/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.dao;

import com.betancur.gestorinmobiliario.model.dao.exceptions.IllegalOrphanException;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.betancur.gestorinmobiliario.model.entity.Venta;
import com.betancur.gestorinmobiliario.model.entity.Comision;
import com.betancur.gestorinmobiliario.model.entity.CuotaVenta;
import java.util.ArrayList;
import java.util.List;
import com.betancur.gestorinmobiliario.model.entity.BoletaDePago;
import com.betancur.gestorinmobiliario.model.entity.ContratoVenta;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
                com.betancur.gestorinmobiliario.model.entity.Contrato oldUnContratoOfUnaComision = unaComision.getUnContrato();
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
                com.betancur.gestorinmobiliario.model.entity.Contrato oldUnContratoOfBoletasDePagoBoletaDePago = boletasDePagoBoletaDePago.getUnContrato();
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

    public void edit(ContratoVenta contratoVenta) throws IllegalOrphanException, NonexistentEntityException, Exception {
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
            List<String> illegalOrphanMessages = null;
            for (CuotaVenta cuotasVentaOldCuotaVenta : cuotasVentaOld) {
                if (!cuotasVentaNew.contains(cuotasVentaOldCuotaVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CuotaVenta " + cuotasVentaOldCuotaVenta + " since its unContratoVenta field is not nullable.");
                }
            }
            for (BoletaDePago boletasDePagoOldBoletaDePago : boletasDePagoOld) {
                if (!boletasDePagoNew.contains(boletasDePagoOldBoletaDePago)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BoletaDePago " + boletasDePagoOldBoletaDePago + " since its unContrato field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
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
                com.betancur.gestorinmobiliario.model.entity.Contrato oldUnContratoOfUnaComision = unaComisionNew.getUnContrato();
                if (oldUnContratoOfUnaComision != null) {
                    oldUnContratoOfUnaComision.setUnaComision(null);
                    oldUnContratoOfUnaComision = em.merge(oldUnContratoOfUnaComision);
                }
                unaComisionNew.setUnContrato(contratoVenta);
                unaComisionNew = em.merge(unaComisionNew);
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

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<CuotaVenta> cuotasVentaOrphanCheck = contratoVenta.getCuotasVenta();
            for (CuotaVenta cuotasVentaOrphanCheckCuotaVenta : cuotasVentaOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ContratoVenta (" + contratoVenta + ") cannot be destroyed since the CuotaVenta " + cuotasVentaOrphanCheckCuotaVenta + " in its cuotasVenta field has a non-nullable unContratoVenta field.");
            }
            List<BoletaDePago> boletasDePagoOrphanCheck = contratoVenta.getBoletasDePago();
            for (BoletaDePago boletasDePagoOrphanCheckBoletaDePago : boletasDePagoOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ContratoVenta (" + contratoVenta + ") cannot be destroyed since the BoletaDePago " + boletasDePagoOrphanCheckBoletaDePago + " in its boletasDePago field has a non-nullable unContrato field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
