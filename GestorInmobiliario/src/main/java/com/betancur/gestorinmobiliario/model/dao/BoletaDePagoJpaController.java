/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.dao;

import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import com.betancur.gestorinmobiliario.model.entity.BoletaDePago;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.betancur.gestorinmobiliario.model.entity.Contrato;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class BoletaDePagoJpaController implements Serializable {

    public BoletaDePagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BoletaDePago boletaDePago) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contrato unContrato = boletaDePago.getUnContrato();
            if (unContrato != null) {
                unContrato = em.getReference(unContrato.getClass(), unContrato.getId());
                boletaDePago.setUnContrato(unContrato);
            }
            em.persist(boletaDePago);
            if (unContrato != null) {
                unContrato.getBoletasDePago().add(boletaDePago);
                unContrato = em.merge(unContrato);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BoletaDePago boletaDePago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BoletaDePago persistentBoletaDePago = em.find(BoletaDePago.class, boletaDePago.getId());
            Contrato unContratoOld = persistentBoletaDePago.getUnContrato();
            Contrato unContratoNew = boletaDePago.getUnContrato();
            if (unContratoNew != null) {
                unContratoNew = em.getReference(unContratoNew.getClass(), unContratoNew.getId());
                boletaDePago.setUnContrato(unContratoNew);
            }
            boletaDePago = em.merge(boletaDePago);
            if (unContratoOld != null && !unContratoOld.equals(unContratoNew)) {
                unContratoOld.getBoletasDePago().remove(boletaDePago);
                unContratoOld = em.merge(unContratoOld);
            }
            if (unContratoNew != null && !unContratoNew.equals(unContratoOld)) {
                unContratoNew.getBoletasDePago().add(boletaDePago);
                unContratoNew = em.merge(unContratoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = boletaDePago.getId();
                if (findBoletaDePago(id) == null) {
                    throw new NonexistentEntityException("The boletaDePago with id " + id + " no longer exists.");
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
            BoletaDePago boletaDePago;
            try {
                boletaDePago = em.getReference(BoletaDePago.class, id);
                boletaDePago.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The boletaDePago with id " + id + " no longer exists.", enfe);
            }
            Contrato unContrato = boletaDePago.getUnContrato();
            if (unContrato != null) {
                unContrato.getBoletasDePago().remove(boletaDePago);
                unContrato = em.merge(unContrato);
            }
            em.remove(boletaDePago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BoletaDePago> findBoletaDePagoEntities() {
        return findBoletaDePagoEntities(true, -1, -1);
    }

    public List<BoletaDePago> findBoletaDePagoEntities(int maxResults, int firstResult) {
        return findBoletaDePagoEntities(false, maxResults, firstResult);
    }

    private List<BoletaDePago> findBoletaDePagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BoletaDePago.class));
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

    public BoletaDePago findBoletaDePago(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BoletaDePago.class, id);
        } finally {
            em.close();
        }
    }

    public int getBoletaDePagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BoletaDePago> rt = cq.from(BoletaDePago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
