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
import model.entity.Garante;
import model.entity.Alquiler;
import model.entity.Comision;
import model.entity.BoletaDePago;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.dao.exceptions.IllegalOrphanException;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.ContratoAlquiler;

/**
 *
 * @author Ariel
 */
public class ContratoAlquilerJpaController implements Serializable {

    public ContratoAlquilerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ContratoAlquiler contratoAlquiler) {
        if (contratoAlquiler.getBoletasDePago() == null) {
            contratoAlquiler.setBoletasDePago(new ArrayList<BoletaDePago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Garante unGarante = contratoAlquiler.getUnGarante();
            if (unGarante != null) {
                unGarante = em.getReference(unGarante.getClass(), unGarante.getId());
                contratoAlquiler.setUnGarante(unGarante);
            }
            Alquiler unAlquiler = contratoAlquiler.getUnAlquiler();
            if (unAlquiler != null) {
                unAlquiler = em.getReference(unAlquiler.getClass(), unAlquiler.getId());
                contratoAlquiler.setUnAlquiler(unAlquiler);
            }
            Comision unaComision = contratoAlquiler.getUnaComision();
            if (unaComision != null) {
                unaComision = em.getReference(unaComision.getClass(), unaComision.getId());
                contratoAlquiler.setUnaComision(unaComision);
            }
            List<BoletaDePago> attachedBoletasDePago = new ArrayList<BoletaDePago>();
            for (BoletaDePago boletasDePagoBoletaDePagoToAttach : contratoAlquiler.getBoletasDePago()) {
                boletasDePagoBoletaDePagoToAttach = em.getReference(boletasDePagoBoletaDePagoToAttach.getClass(), boletasDePagoBoletaDePagoToAttach.getId());
                attachedBoletasDePago.add(boletasDePagoBoletaDePagoToAttach);
            }
            contratoAlquiler.setBoletasDePago(attachedBoletasDePago);
            em.persist(contratoAlquiler);
            if (unGarante != null) {
                ContratoAlquiler oldUnContratoAlquilerOfUnGarante = unGarante.getUnContratoAlquiler();
                if (oldUnContratoAlquilerOfUnGarante != null) {
                    oldUnContratoAlquilerOfUnGarante.setUnGarante(null);
                    oldUnContratoAlquilerOfUnGarante = em.merge(oldUnContratoAlquilerOfUnGarante);
                }
                unGarante.setUnContratoAlquiler(contratoAlquiler);
                unGarante = em.merge(unGarante);
            }
            if (unAlquiler != null) {
                ContratoAlquiler oldUnContratoAlquilerOfUnAlquiler = unAlquiler.getUnContratoAlquiler();
                if (oldUnContratoAlquilerOfUnAlquiler != null) {
                    oldUnContratoAlquilerOfUnAlquiler.setUnAlquiler(null);
                    oldUnContratoAlquilerOfUnAlquiler = em.merge(oldUnContratoAlquilerOfUnAlquiler);
                }
                unAlquiler.setUnContratoAlquiler(contratoAlquiler);
                unAlquiler = em.merge(unAlquiler);
            }
            if (unaComision != null) {
                model.entity.Contrato oldUnContratoOfUnaComision = unaComision.getUnContrato();
                if (oldUnContratoOfUnaComision != null) {
                    oldUnContratoOfUnaComision.setUnaComision(null);
                    oldUnContratoOfUnaComision = em.merge(oldUnContratoOfUnaComision);
                }
                unaComision.setUnContrato(contratoAlquiler);
                unaComision = em.merge(unaComision);
            }
            for (BoletaDePago boletasDePagoBoletaDePago : contratoAlquiler.getBoletasDePago()) {
                model.entity.Contrato oldUnContratoOfBoletasDePagoBoletaDePago = boletasDePagoBoletaDePago.getUnContrato();
                boletasDePagoBoletaDePago.setUnContrato(contratoAlquiler);
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

    public void edit(ContratoAlquiler contratoAlquiler) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContratoAlquiler persistentContratoAlquiler = em.find(ContratoAlquiler.class, contratoAlquiler.getId());
            Garante unGaranteOld = persistentContratoAlquiler.getUnGarante();
            Garante unGaranteNew = contratoAlquiler.getUnGarante();
            Alquiler unAlquilerOld = persistentContratoAlquiler.getUnAlquiler();
            Alquiler unAlquilerNew = contratoAlquiler.getUnAlquiler();
            Comision unaComisionOld = persistentContratoAlquiler.getUnaComision();
            Comision unaComisionNew = contratoAlquiler.getUnaComision();
            List<BoletaDePago> boletasDePagoOld = persistentContratoAlquiler.getBoletasDePago();
            List<BoletaDePago> boletasDePagoNew = contratoAlquiler.getBoletasDePago();
            List<String> illegalOrphanMessages = null;
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
            if (unGaranteNew != null) {
                unGaranteNew = em.getReference(unGaranteNew.getClass(), unGaranteNew.getId());
                contratoAlquiler.setUnGarante(unGaranteNew);
            }
            if (unAlquilerNew != null) {
                unAlquilerNew = em.getReference(unAlquilerNew.getClass(), unAlquilerNew.getId());
                contratoAlquiler.setUnAlquiler(unAlquilerNew);
            }
            if (unaComisionNew != null) {
                unaComisionNew = em.getReference(unaComisionNew.getClass(), unaComisionNew.getId());
                contratoAlquiler.setUnaComision(unaComisionNew);
            }
            List<BoletaDePago> attachedBoletasDePagoNew = new ArrayList<BoletaDePago>();
            for (BoletaDePago boletasDePagoNewBoletaDePagoToAttach : boletasDePagoNew) {
                boletasDePagoNewBoletaDePagoToAttach = em.getReference(boletasDePagoNewBoletaDePagoToAttach.getClass(), boletasDePagoNewBoletaDePagoToAttach.getId());
                attachedBoletasDePagoNew.add(boletasDePagoNewBoletaDePagoToAttach);
            }
            boletasDePagoNew = attachedBoletasDePagoNew;
            contratoAlquiler.setBoletasDePago(boletasDePagoNew);
            contratoAlquiler = em.merge(contratoAlquiler);
            if (unGaranteOld != null && !unGaranteOld.equals(unGaranteNew)) {
                unGaranteOld.setUnContratoAlquiler(null);
                unGaranteOld = em.merge(unGaranteOld);
            }
            if (unGaranteNew != null && !unGaranteNew.equals(unGaranteOld)) {
                ContratoAlquiler oldUnContratoAlquilerOfUnGarante = unGaranteNew.getUnContratoAlquiler();
                if (oldUnContratoAlquilerOfUnGarante != null) {
                    oldUnContratoAlquilerOfUnGarante.setUnGarante(null);
                    oldUnContratoAlquilerOfUnGarante = em.merge(oldUnContratoAlquilerOfUnGarante);
                }
                unGaranteNew.setUnContratoAlquiler(contratoAlquiler);
                unGaranteNew = em.merge(unGaranteNew);
            }
            if (unAlquilerOld != null && !unAlquilerOld.equals(unAlquilerNew)) {
                unAlquilerOld.setUnContratoAlquiler(null);
                unAlquilerOld = em.merge(unAlquilerOld);
            }
            if (unAlquilerNew != null && !unAlquilerNew.equals(unAlquilerOld)) {
                ContratoAlquiler oldUnContratoAlquilerOfUnAlquiler = unAlquilerNew.getUnContratoAlquiler();
                if (oldUnContratoAlquilerOfUnAlquiler != null) {
                    oldUnContratoAlquilerOfUnAlquiler.setUnAlquiler(null);
                    oldUnContratoAlquilerOfUnAlquiler = em.merge(oldUnContratoAlquilerOfUnAlquiler);
                }
                unAlquilerNew.setUnContratoAlquiler(contratoAlquiler);
                unAlquilerNew = em.merge(unAlquilerNew);
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
                unaComisionNew.setUnContrato(contratoAlquiler);
                unaComisionNew = em.merge(unaComisionNew);
            }
            for (BoletaDePago boletasDePagoNewBoletaDePago : boletasDePagoNew) {
                if (!boletasDePagoOld.contains(boletasDePagoNewBoletaDePago)) {
                    ContratoAlquiler oldUnContratoOfBoletasDePagoNewBoletaDePago = (ContratoAlquiler) boletasDePagoNewBoletaDePago.getUnContrato();
                    boletasDePagoNewBoletaDePago.setUnContrato(contratoAlquiler);
                    boletasDePagoNewBoletaDePago = em.merge(boletasDePagoNewBoletaDePago);
                    if (oldUnContratoOfBoletasDePagoNewBoletaDePago != null && !oldUnContratoOfBoletasDePagoNewBoletaDePago.equals(contratoAlquiler)) {
                        oldUnContratoOfBoletasDePagoNewBoletaDePago.getBoletasDePago().remove(boletasDePagoNewBoletaDePago);
                        oldUnContratoOfBoletasDePagoNewBoletaDePago = em.merge(oldUnContratoOfBoletasDePagoNewBoletaDePago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = contratoAlquiler.getId();
                if (findContratoAlquiler(id) == null) {
                    throw new NonexistentEntityException("The contratoAlquiler with id " + id + " no longer exists.");
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
            ContratoAlquiler contratoAlquiler;
            try {
                contratoAlquiler = em.getReference(ContratoAlquiler.class, id);
                contratoAlquiler.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contratoAlquiler with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<BoletaDePago> boletasDePagoOrphanCheck = contratoAlquiler.getBoletasDePago();
            for (BoletaDePago boletasDePagoOrphanCheckBoletaDePago : boletasDePagoOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ContratoAlquiler (" + contratoAlquiler + ") cannot be destroyed since the BoletaDePago " + boletasDePagoOrphanCheckBoletaDePago + " in its boletasDePago field has a non-nullable unContrato field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Garante unGarante = contratoAlquiler.getUnGarante();
            if (unGarante != null) {
                unGarante.setUnContratoAlquiler(null);
                unGarante = em.merge(unGarante);
            }
            Alquiler unAlquiler = contratoAlquiler.getUnAlquiler();
            if (unAlquiler != null) {
                unAlquiler.setUnContratoAlquiler(null);
                unAlquiler = em.merge(unAlquiler);
            }
            Comision unaComision = contratoAlquiler.getUnaComision();
            if (unaComision != null) {
                unaComision.setUnContrato(null);
                unaComision = em.merge(unaComision);
            }
            em.remove(contratoAlquiler);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ContratoAlquiler> findContratoAlquilerEntities() {
        return findContratoAlquilerEntities(true, -1, -1);
    }

    public List<ContratoAlquiler> findContratoAlquilerEntities(int maxResults, int firstResult) {
        return findContratoAlquilerEntities(false, maxResults, firstResult);
    }

    private List<ContratoAlquiler> findContratoAlquilerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ContratoAlquiler.class));
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

    public ContratoAlquiler findContratoAlquiler(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ContratoAlquiler.class, id);
        } finally {
            em.close();
        }
    }

    public int getContratoAlquilerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ContratoAlquiler> rt = cq.from(ContratoAlquiler.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
