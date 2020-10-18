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
import com.betancur.gestorinmobiliario.model.Locatario;
import com.betancur.gestorinmobiliario.model.ContratoAlquiler;
import com.betancur.gestorinmobiliario.model.ComprobanteDeIngreso;
import com.betancur.gestorinmobiliario.model.GaranteIndependiente;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class GaranteIndependienteJpaController implements Serializable {

    public GaranteIndependienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GaranteIndependiente garanteIndependiente) {
        if (garanteIndependiente.getComprobantesDeIngresosGarantes() == null) {
            garanteIndependiente.setComprobantesDeIngresosGarantes(new ArrayList<ComprobanteDeIngreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaGarante = garanteIndependiente.getUnaInmobiliariaGarante();
            if (unaInmobiliariaGarante != null) {
                unaInmobiliariaGarante = em.getReference(unaInmobiliariaGarante.getClass(), unaInmobiliariaGarante.getId());
                garanteIndependiente.setUnaInmobiliariaGarante(unaInmobiliariaGarante);
            }
            Locatario unLocatario = garanteIndependiente.getUnLocatario();
            if (unLocatario != null) {
                unLocatario = em.getReference(unLocatario.getClass(), unLocatario.getId());
                garanteIndependiente.setUnLocatario(unLocatario);
            }
            ContratoAlquiler unContratoAlquiler = garanteIndependiente.getUnContratoAlquiler();
            if (unContratoAlquiler != null) {
                unContratoAlquiler = em.getReference(unContratoAlquiler.getClass(), unContratoAlquiler.getId());
                garanteIndependiente.setUnContratoAlquiler(unContratoAlquiler);
            }
            List<ComprobanteDeIngreso> attachedComprobantesDeIngresosGarantes = new ArrayList<ComprobanteDeIngreso>();
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesComprobanteDeIngresoToAttach : garanteIndependiente.getComprobantesDeIngresosGarantes()) {
                comprobantesDeIngresosGarantesComprobanteDeIngresoToAttach = em.getReference(comprobantesDeIngresosGarantesComprobanteDeIngresoToAttach.getClass(), comprobantesDeIngresosGarantesComprobanteDeIngresoToAttach.getId());
                attachedComprobantesDeIngresosGarantes.add(comprobantesDeIngresosGarantesComprobanteDeIngresoToAttach);
            }
            garanteIndependiente.setComprobantesDeIngresosGarantes(attachedComprobantesDeIngresosGarantes);
            em.persist(garanteIndependiente);
            if (unaInmobiliariaGarante != null) {
                unaInmobiliariaGarante.getGarantes().add(garanteIndependiente);
                unaInmobiliariaGarante = em.merge(unaInmobiliariaGarante);
            }
            if (unLocatario != null) {
                com.betancur.gestorinmobiliario.model.Garante oldUnGaranteOfUnLocatario = unLocatario.getUnGarante();
                if (oldUnGaranteOfUnLocatario != null) {
                    oldUnGaranteOfUnLocatario.setUnLocatario(null);
                    oldUnGaranteOfUnLocatario = em.merge(oldUnGaranteOfUnLocatario);
                }
                unLocatario.setUnGarante(garanteIndependiente);
                unLocatario = em.merge(unLocatario);
            }
            if (unContratoAlquiler != null) {
                com.betancur.gestorinmobiliario.model.Garante oldUnGaranteOfUnContratoAlquiler = unContratoAlquiler.getUnGarante();
                if (oldUnGaranteOfUnContratoAlquiler != null) {
                    oldUnGaranteOfUnContratoAlquiler.setUnContratoAlquiler(null);
                    oldUnGaranteOfUnContratoAlquiler = em.merge(oldUnGaranteOfUnContratoAlquiler);
                }
                unContratoAlquiler.setUnGarante(garanteIndependiente);
                unContratoAlquiler = em.merge(unContratoAlquiler);
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesComprobanteDeIngreso : garanteIndependiente.getComprobantesDeIngresosGarantes()) {
                comprobantesDeIngresosGarantesComprobanteDeIngreso.getGarantes().add(garanteIndependiente);
                comprobantesDeIngresosGarantesComprobanteDeIngreso = em.merge(comprobantesDeIngresosGarantesComprobanteDeIngreso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GaranteIndependiente garanteIndependiente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GaranteIndependiente persistentGaranteIndependiente = em.find(GaranteIndependiente.class, garanteIndependiente.getId());
            Inmobiliaria unaInmobiliariaGaranteOld = persistentGaranteIndependiente.getUnaInmobiliariaGarante();
            Inmobiliaria unaInmobiliariaGaranteNew = garanteIndependiente.getUnaInmobiliariaGarante();
            Locatario unLocatarioOld = persistentGaranteIndependiente.getUnLocatario();
            Locatario unLocatarioNew = garanteIndependiente.getUnLocatario();
            ContratoAlquiler unContratoAlquilerOld = persistentGaranteIndependiente.getUnContratoAlquiler();
            ContratoAlquiler unContratoAlquilerNew = garanteIndependiente.getUnContratoAlquiler();
            List<ComprobanteDeIngreso> comprobantesDeIngresosGarantesOld = persistentGaranteIndependiente.getComprobantesDeIngresosGarantes();
            List<ComprobanteDeIngreso> comprobantesDeIngresosGarantesNew = garanteIndependiente.getComprobantesDeIngresosGarantes();
            if (unaInmobiliariaGaranteNew != null) {
                unaInmobiliariaGaranteNew = em.getReference(unaInmobiliariaGaranteNew.getClass(), unaInmobiliariaGaranteNew.getId());
                garanteIndependiente.setUnaInmobiliariaGarante(unaInmobiliariaGaranteNew);
            }
            if (unLocatarioNew != null) {
                unLocatarioNew = em.getReference(unLocatarioNew.getClass(), unLocatarioNew.getId());
                garanteIndependiente.setUnLocatario(unLocatarioNew);
            }
            if (unContratoAlquilerNew != null) {
                unContratoAlquilerNew = em.getReference(unContratoAlquilerNew.getClass(), unContratoAlquilerNew.getId());
                garanteIndependiente.setUnContratoAlquiler(unContratoAlquilerNew);
            }
            List<ComprobanteDeIngreso> attachedComprobantesDeIngresosGarantesNew = new ArrayList<ComprobanteDeIngreso>();
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesNewComprobanteDeIngresoToAttach : comprobantesDeIngresosGarantesNew) {
                comprobantesDeIngresosGarantesNewComprobanteDeIngresoToAttach = em.getReference(comprobantesDeIngresosGarantesNewComprobanteDeIngresoToAttach.getClass(), comprobantesDeIngresosGarantesNewComprobanteDeIngresoToAttach.getId());
                attachedComprobantesDeIngresosGarantesNew.add(comprobantesDeIngresosGarantesNewComprobanteDeIngresoToAttach);
            }
            comprobantesDeIngresosGarantesNew = attachedComprobantesDeIngresosGarantesNew;
            garanteIndependiente.setComprobantesDeIngresosGarantes(comprobantesDeIngresosGarantesNew);
            garanteIndependiente = em.merge(garanteIndependiente);
            if (unaInmobiliariaGaranteOld != null && !unaInmobiliariaGaranteOld.equals(unaInmobiliariaGaranteNew)) {
                unaInmobiliariaGaranteOld.getGarantes().remove(garanteIndependiente);
                unaInmobiliariaGaranteOld = em.merge(unaInmobiliariaGaranteOld);
            }
            if (unaInmobiliariaGaranteNew != null && !unaInmobiliariaGaranteNew.equals(unaInmobiliariaGaranteOld)) {
                unaInmobiliariaGaranteNew.getGarantes().add(garanteIndependiente);
                unaInmobiliariaGaranteNew = em.merge(unaInmobiliariaGaranteNew);
            }
            if (unLocatarioOld != null && !unLocatarioOld.equals(unLocatarioNew)) {
                unLocatarioOld.setUnGarante(null);
                unLocatarioOld = em.merge(unLocatarioOld);
            }
            if (unLocatarioNew != null && !unLocatarioNew.equals(unLocatarioOld)) {
                com.betancur.gestorinmobiliario.model.Garante oldUnGaranteOfUnLocatario = unLocatarioNew.getUnGarante();
                if (oldUnGaranteOfUnLocatario != null) {
                    oldUnGaranteOfUnLocatario.setUnLocatario(null);
                    oldUnGaranteOfUnLocatario = em.merge(oldUnGaranteOfUnLocatario);
                }
                unLocatarioNew.setUnGarante(garanteIndependiente);
                unLocatarioNew = em.merge(unLocatarioNew);
            }
            if (unContratoAlquilerOld != null && !unContratoAlquilerOld.equals(unContratoAlquilerNew)) {
                unContratoAlquilerOld.setUnGarante(null);
                unContratoAlquilerOld = em.merge(unContratoAlquilerOld);
            }
            if (unContratoAlquilerNew != null && !unContratoAlquilerNew.equals(unContratoAlquilerOld)) {
                com.betancur.gestorinmobiliario.model.Garante oldUnGaranteOfUnContratoAlquiler = unContratoAlquilerNew.getUnGarante();
                if (oldUnGaranteOfUnContratoAlquiler != null) {
                    oldUnGaranteOfUnContratoAlquiler.setUnContratoAlquiler(null);
                    oldUnGaranteOfUnContratoAlquiler = em.merge(oldUnGaranteOfUnContratoAlquiler);
                }
                unContratoAlquilerNew.setUnGarante(garanteIndependiente);
                unContratoAlquilerNew = em.merge(unContratoAlquilerNew);
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesOldComprobanteDeIngreso : comprobantesDeIngresosGarantesOld) {
                if (!comprobantesDeIngresosGarantesNew.contains(comprobantesDeIngresosGarantesOldComprobanteDeIngreso)) {
                    comprobantesDeIngresosGarantesOldComprobanteDeIngreso.getGarantes().remove(garanteIndependiente);
                    comprobantesDeIngresosGarantesOldComprobanteDeIngreso = em.merge(comprobantesDeIngresosGarantesOldComprobanteDeIngreso);
                }
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesNewComprobanteDeIngreso : comprobantesDeIngresosGarantesNew) {
                if (!comprobantesDeIngresosGarantesOld.contains(comprobantesDeIngresosGarantesNewComprobanteDeIngreso)) {
                    comprobantesDeIngresosGarantesNewComprobanteDeIngreso.getGarantes().add(garanteIndependiente);
                    comprobantesDeIngresosGarantesNewComprobanteDeIngreso = em.merge(comprobantesDeIngresosGarantesNewComprobanteDeIngreso);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = garanteIndependiente.getId();
                if (findGaranteIndependiente(id) == null) {
                    throw new NonexistentEntityException("The garanteIndependiente with id " + id + " no longer exists.");
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
            GaranteIndependiente garanteIndependiente;
            try {
                garanteIndependiente = em.getReference(GaranteIndependiente.class, id);
                garanteIndependiente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The garanteIndependiente with id " + id + " no longer exists.", enfe);
            }
            Inmobiliaria unaInmobiliariaGarante = garanteIndependiente.getUnaInmobiliariaGarante();
            if (unaInmobiliariaGarante != null) {
                unaInmobiliariaGarante.getGarantes().remove(garanteIndependiente);
                unaInmobiliariaGarante = em.merge(unaInmobiliariaGarante);
            }
            Locatario unLocatario = garanteIndependiente.getUnLocatario();
            if (unLocatario != null) {
                unLocatario.setUnGarante(null);
                unLocatario = em.merge(unLocatario);
            }
            ContratoAlquiler unContratoAlquiler = garanteIndependiente.getUnContratoAlquiler();
            if (unContratoAlquiler != null) {
                unContratoAlquiler.setUnGarante(null);
                unContratoAlquiler = em.merge(unContratoAlquiler);
            }
            List<ComprobanteDeIngreso> comprobantesDeIngresosGarantes = garanteIndependiente.getComprobantesDeIngresosGarantes();
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesComprobanteDeIngreso : comprobantesDeIngresosGarantes) {
                comprobantesDeIngresosGarantesComprobanteDeIngreso.getGarantes().remove(garanteIndependiente);
                comprobantesDeIngresosGarantesComprobanteDeIngreso = em.merge(comprobantesDeIngresosGarantesComprobanteDeIngreso);
            }
            em.remove(garanteIndependiente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GaranteIndependiente> findGaranteIndependienteEntities() {
        return findGaranteIndependienteEntities(true, -1, -1);
    }

    public List<GaranteIndependiente> findGaranteIndependienteEntities(int maxResults, int firstResult) {
        return findGaranteIndependienteEntities(false, maxResults, firstResult);
    }

    private List<GaranteIndependiente> findGaranteIndependienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GaranteIndependiente.class));
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

    public GaranteIndependiente findGaranteIndependiente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GaranteIndependiente.class, id);
        } finally {
            em.close();
        }
    }

    public int getGaranteIndependienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GaranteIndependiente> rt = cq.from(GaranteIndependiente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
