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
import model.entity.Comision;
import model.entity.BoletaDePago;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Contrato;

/**
 *
 * @author Ariel
 */
public class ContratoJpaController implements Serializable {

    public ContratoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contrato contrato) {
        if (contrato.getBoletasDePago() == null) {
            contrato.setBoletasDePago(new ArrayList<BoletaDePago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comision unaComision = contrato.getUnaComision();
            if (unaComision != null) {
                unaComision = em.getReference(unaComision.getClass(), unaComision.getId());
                contrato.setUnaComision(unaComision);
            }
            List<BoletaDePago> attachedBoletasDePago = new ArrayList<BoletaDePago>();
            for (BoletaDePago boletasDePagoBoletaDePagoToAttach : contrato.getBoletasDePago()) {
                boletasDePagoBoletaDePagoToAttach = em.getReference(boletasDePagoBoletaDePagoToAttach.getClass(), boletasDePagoBoletaDePagoToAttach.getId());
                attachedBoletasDePago.add(boletasDePagoBoletaDePagoToAttach);
            }
            contrato.setBoletasDePago(attachedBoletasDePago);
            em.persist(contrato);
            if (unaComision != null) {
                Contrato oldUnContratoOfUnaComision = unaComision.getUnContrato();
                if (oldUnContratoOfUnaComision != null) {
                    oldUnContratoOfUnaComision.setUnaComision(null);
                    oldUnContratoOfUnaComision = em.merge(oldUnContratoOfUnaComision);
                }
                unaComision.setUnContrato(contrato);
                unaComision = em.merge(unaComision);
            }
            for (BoletaDePago boletasDePagoBoletaDePago : contrato.getBoletasDePago()) {
                Contrato oldUnContratoOfBoletasDePagoBoletaDePago = boletasDePagoBoletaDePago.getUnContrato();
                boletasDePagoBoletaDePago.setUnContrato(contrato);
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

    public void edit(Contrato contrato) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contrato persistentContrato = em.find(Contrato.class, contrato.getId());
            Comision unaComisionOld = persistentContrato.getUnaComision();
            Comision unaComisionNew = contrato.getUnaComision();
            List<BoletaDePago> boletasDePagoOld = persistentContrato.getBoletasDePago();
            List<BoletaDePago> boletasDePagoNew = contrato.getBoletasDePago();
            if (unaComisionNew != null) {
                unaComisionNew = em.getReference(unaComisionNew.getClass(), unaComisionNew.getId());
                contrato.setUnaComision(unaComisionNew);
            }
            List<BoletaDePago> attachedBoletasDePagoNew = new ArrayList<BoletaDePago>();
            for (BoletaDePago boletasDePagoNewBoletaDePagoToAttach : boletasDePagoNew) {
                boletasDePagoNewBoletaDePagoToAttach = em.getReference(boletasDePagoNewBoletaDePagoToAttach.getClass(), boletasDePagoNewBoletaDePagoToAttach.getId());
                attachedBoletasDePagoNew.add(boletasDePagoNewBoletaDePagoToAttach);
            }
            boletasDePagoNew = attachedBoletasDePagoNew;
            contrato.setBoletasDePago(boletasDePagoNew);
            contrato = em.merge(contrato);
            if (unaComisionOld != null && !unaComisionOld.equals(unaComisionNew)) {
                unaComisionOld.setUnContrato(null);
                unaComisionOld = em.merge(unaComisionOld);
            }
            if (unaComisionNew != null && !unaComisionNew.equals(unaComisionOld)) {
                Contrato oldUnContratoOfUnaComision = unaComisionNew.getUnContrato();
                if (oldUnContratoOfUnaComision != null) {
                    oldUnContratoOfUnaComision.setUnaComision(null);
                    oldUnContratoOfUnaComision = em.merge(oldUnContratoOfUnaComision);
                }
                unaComisionNew.setUnContrato(contrato);
                unaComisionNew = em.merge(unaComisionNew);
            }
            for (BoletaDePago boletasDePagoOldBoletaDePago : boletasDePagoOld) {
                if (!boletasDePagoNew.contains(boletasDePagoOldBoletaDePago)) {
                    boletasDePagoOldBoletaDePago.setUnContrato(null);
                    boletasDePagoOldBoletaDePago = em.merge(boletasDePagoOldBoletaDePago);
                }
            }
            for (BoletaDePago boletasDePagoNewBoletaDePago : boletasDePagoNew) {
                if (!boletasDePagoOld.contains(boletasDePagoNewBoletaDePago)) {
                    Contrato oldUnContratoOfBoletasDePagoNewBoletaDePago = boletasDePagoNewBoletaDePago.getUnContrato();
                    boletasDePagoNewBoletaDePago.setUnContrato(contrato);
                    boletasDePagoNewBoletaDePago = em.merge(boletasDePagoNewBoletaDePago);
                    if (oldUnContratoOfBoletasDePagoNewBoletaDePago != null && !oldUnContratoOfBoletasDePagoNewBoletaDePago.equals(contrato)) {
                        oldUnContratoOfBoletasDePagoNewBoletaDePago.getBoletasDePago().remove(boletasDePagoNewBoletaDePago);
                        oldUnContratoOfBoletasDePagoNewBoletaDePago = em.merge(oldUnContratoOfBoletasDePagoNewBoletaDePago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = contrato.getId();
                if (findContrato(id) == null) {
                    throw new NonexistentEntityException("The contrato with id " + id + " no longer exists.");
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
            Contrato contrato;
            try {
                contrato = em.getReference(Contrato.class, id);
                contrato.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contrato with id " + id + " no longer exists.", enfe);
            }
            Comision unaComision = contrato.getUnaComision();
            if (unaComision != null) {
                unaComision.setUnContrato(null);
                unaComision = em.merge(unaComision);
            }
            List<BoletaDePago> boletasDePago = contrato.getBoletasDePago();
            for (BoletaDePago boletasDePagoBoletaDePago : boletasDePago) {
                boletasDePagoBoletaDePago.setUnContrato(null);
                boletasDePagoBoletaDePago = em.merge(boletasDePagoBoletaDePago);
            }
            em.remove(contrato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contrato> findContratoEntities() {
        return findContratoEntities(true, -1, -1);
    }

    public List<Contrato> findContratoEntities(int maxResults, int firstResult) {
        return findContratoEntities(false, maxResults, firstResult);
    }

    private List<Contrato> findContratoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contrato.class));
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

    public Contrato findContrato(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contrato.class, id);
        } finally {
            em.close();
        }
    }

    public int getContratoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contrato> rt = cq.from(Contrato.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
