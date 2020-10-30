/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entity.Inmobiliaria;
import model.entity.Locatario;
import model.entity.ContratoAlquiler;
import model.entity.ComprobanteDeIngreso;
import model.entity.GaranteDependiente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class GaranteDependienteJpaController implements Serializable {

    public GaranteDependienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GaranteDependiente garanteDependiente) {
        if (garanteDependiente.getComprobantesDeIngresosGarantes() == null) {
            garanteDependiente.setComprobantesDeIngresosGarantes(new ArrayList<ComprobanteDeIngreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaGarante = garanteDependiente.getUnaInmobiliariaGarante();
            if (unaInmobiliariaGarante != null) {
                unaInmobiliariaGarante = em.getReference(unaInmobiliariaGarante.getClass(), unaInmobiliariaGarante.getId());
                garanteDependiente.setUnaInmobiliariaGarante(unaInmobiliariaGarante);
            }
            Locatario unLocatario = garanteDependiente.getUnLocatario();
            if (unLocatario != null) {
                unLocatario = em.getReference(unLocatario.getClass(), unLocatario.getId());
                garanteDependiente.setUnLocatario(unLocatario);
            }
            ContratoAlquiler unContratoAlquiler = garanteDependiente.getUnContratoAlquiler();
            if (unContratoAlquiler != null) {
                unContratoAlquiler = em.getReference(unContratoAlquiler.getClass(), unContratoAlquiler.getId());
                garanteDependiente.setUnContratoAlquiler(unContratoAlquiler);
            }
            List<ComprobanteDeIngreso> attachedComprobantesDeIngresosGarantes = new ArrayList<ComprobanteDeIngreso>();
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesComprobanteDeIngresoToAttach : garanteDependiente.getComprobantesDeIngresosGarantes()) {
                comprobantesDeIngresosGarantesComprobanteDeIngresoToAttach = em.getReference(comprobantesDeIngresosGarantesComprobanteDeIngresoToAttach.getClass(), comprobantesDeIngresosGarantesComprobanteDeIngresoToAttach.getId());
                attachedComprobantesDeIngresosGarantes.add(comprobantesDeIngresosGarantesComprobanteDeIngresoToAttach);
            }
            garanteDependiente.setComprobantesDeIngresosGarantes(attachedComprobantesDeIngresosGarantes);
            em.persist(garanteDependiente);
            if (unaInmobiliariaGarante != null) {
                unaInmobiliariaGarante.getGarantes().add(garanteDependiente);
                unaInmobiliariaGarante = em.merge(unaInmobiliariaGarante);
            }
            if (unLocatario != null) {
                model.entity.Garante oldUnGaranteOfUnLocatario = unLocatario.getUnGarante();
                if (oldUnGaranteOfUnLocatario != null) {
                    oldUnGaranteOfUnLocatario.setUnLocatario(null);
                    oldUnGaranteOfUnLocatario = em.merge(oldUnGaranteOfUnLocatario);
                }
                unLocatario.setUnGarante(garanteDependiente);
                unLocatario = em.merge(unLocatario);
            }
            if (unContratoAlquiler != null) {
                model.entity.Garante oldUnGaranteOfUnContratoAlquiler = unContratoAlquiler.getUnGarante();
                if (oldUnGaranteOfUnContratoAlquiler != null) {
                    oldUnGaranteOfUnContratoAlquiler.setUnContratoAlquiler(null);
                    oldUnGaranteOfUnContratoAlquiler = em.merge(oldUnGaranteOfUnContratoAlquiler);
                }
                unContratoAlquiler.setUnGarante(garanteDependiente);
                unContratoAlquiler = em.merge(unContratoAlquiler);
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesComprobanteDeIngreso : garanteDependiente.getComprobantesDeIngresosGarantes()) {
                comprobantesDeIngresosGarantesComprobanteDeIngreso.getGarantes().add(garanteDependiente);
                comprobantesDeIngresosGarantesComprobanteDeIngreso = em.merge(comprobantesDeIngresosGarantesComprobanteDeIngreso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GaranteDependiente garanteDependiente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GaranteDependiente persistentGaranteDependiente = em.find(GaranteDependiente.class, garanteDependiente.getId());
            Inmobiliaria unaInmobiliariaGaranteOld = persistentGaranteDependiente.getUnaInmobiliariaGarante();
            Inmobiliaria unaInmobiliariaGaranteNew = garanteDependiente.getUnaInmobiliariaGarante();
            Locatario unLocatarioOld = persistentGaranteDependiente.getUnLocatario();
            Locatario unLocatarioNew = garanteDependiente.getUnLocatario();
            ContratoAlquiler unContratoAlquilerOld = persistentGaranteDependiente.getUnContratoAlquiler();
            ContratoAlquiler unContratoAlquilerNew = garanteDependiente.getUnContratoAlquiler();
            List<ComprobanteDeIngreso> comprobantesDeIngresosGarantesOld = persistentGaranteDependiente.getComprobantesDeIngresosGarantes();
            List<ComprobanteDeIngreso> comprobantesDeIngresosGarantesNew = garanteDependiente.getComprobantesDeIngresosGarantes();
            if (unaInmobiliariaGaranteNew != null) {
                unaInmobiliariaGaranteNew = em.getReference(unaInmobiliariaGaranteNew.getClass(), unaInmobiliariaGaranteNew.getId());
                garanteDependiente.setUnaInmobiliariaGarante(unaInmobiliariaGaranteNew);
            }
            if (unLocatarioNew != null) {
                unLocatarioNew = em.getReference(unLocatarioNew.getClass(), unLocatarioNew.getId());
                garanteDependiente.setUnLocatario(unLocatarioNew);
            }
            if (unContratoAlquilerNew != null) {
                unContratoAlquilerNew = em.getReference(unContratoAlquilerNew.getClass(), unContratoAlquilerNew.getId());
                garanteDependiente.setUnContratoAlquiler(unContratoAlquilerNew);
            }
            List<ComprobanteDeIngreso> attachedComprobantesDeIngresosGarantesNew = new ArrayList<ComprobanteDeIngreso>();
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesNewComprobanteDeIngresoToAttach : comprobantesDeIngresosGarantesNew) {
                comprobantesDeIngresosGarantesNewComprobanteDeIngresoToAttach = em.getReference(comprobantesDeIngresosGarantesNewComprobanteDeIngresoToAttach.getClass(), comprobantesDeIngresosGarantesNewComprobanteDeIngresoToAttach.getId());
                attachedComprobantesDeIngresosGarantesNew.add(comprobantesDeIngresosGarantesNewComprobanteDeIngresoToAttach);
            }
            comprobantesDeIngresosGarantesNew = attachedComprobantesDeIngresosGarantesNew;
            garanteDependiente.setComprobantesDeIngresosGarantes(comprobantesDeIngresosGarantesNew);
            garanteDependiente = em.merge(garanteDependiente);
            if (unaInmobiliariaGaranteOld != null && !unaInmobiliariaGaranteOld.equals(unaInmobiliariaGaranteNew)) {
                unaInmobiliariaGaranteOld.getGarantes().remove(garanteDependiente);
                unaInmobiliariaGaranteOld = em.merge(unaInmobiliariaGaranteOld);
            }
            if (unaInmobiliariaGaranteNew != null && !unaInmobiliariaGaranteNew.equals(unaInmobiliariaGaranteOld)) {
                unaInmobiliariaGaranteNew.getGarantes().add(garanteDependiente);
                unaInmobiliariaGaranteNew = em.merge(unaInmobiliariaGaranteNew);
            }
            if (unLocatarioOld != null && !unLocatarioOld.equals(unLocatarioNew)) {
                unLocatarioOld.setUnGarante(null);
                unLocatarioOld = em.merge(unLocatarioOld);
            }
            if (unLocatarioNew != null && !unLocatarioNew.equals(unLocatarioOld)) {
                model.entity.Garante oldUnGaranteOfUnLocatario = unLocatarioNew.getUnGarante();
                if (oldUnGaranteOfUnLocatario != null) {
                    oldUnGaranteOfUnLocatario.setUnLocatario(null);
                    oldUnGaranteOfUnLocatario = em.merge(oldUnGaranteOfUnLocatario);
                }
                unLocatarioNew.setUnGarante(garanteDependiente);
                unLocatarioNew = em.merge(unLocatarioNew);
            }
            if (unContratoAlquilerOld != null && !unContratoAlquilerOld.equals(unContratoAlquilerNew)) {
                unContratoAlquilerOld.setUnGarante(null);
                unContratoAlquilerOld = em.merge(unContratoAlquilerOld);
            }
            if (unContratoAlquilerNew != null && !unContratoAlquilerNew.equals(unContratoAlquilerOld)) {
                model.entity.Garante oldUnGaranteOfUnContratoAlquiler = unContratoAlquilerNew.getUnGarante();
                if (oldUnGaranteOfUnContratoAlquiler != null) {
                    oldUnGaranteOfUnContratoAlquiler.setUnContratoAlquiler(null);
                    oldUnGaranteOfUnContratoAlquiler = em.merge(oldUnGaranteOfUnContratoAlquiler);
                }
                unContratoAlquilerNew.setUnGarante(garanteDependiente);
                unContratoAlquilerNew = em.merge(unContratoAlquilerNew);
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesOldComprobanteDeIngreso : comprobantesDeIngresosGarantesOld) {
                if (!comprobantesDeIngresosGarantesNew.contains(comprobantesDeIngresosGarantesOldComprobanteDeIngreso)) {
                    comprobantesDeIngresosGarantesOldComprobanteDeIngreso.getGarantes().remove(garanteDependiente);
                    comprobantesDeIngresosGarantesOldComprobanteDeIngreso = em.merge(comprobantesDeIngresosGarantesOldComprobanteDeIngreso);
                }
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesNewComprobanteDeIngreso : comprobantesDeIngresosGarantesNew) {
                if (!comprobantesDeIngresosGarantesOld.contains(comprobantesDeIngresosGarantesNewComprobanteDeIngreso)) {
                    comprobantesDeIngresosGarantesNewComprobanteDeIngreso.getGarantes().add(garanteDependiente);
                    comprobantesDeIngresosGarantesNewComprobanteDeIngreso = em.merge(comprobantesDeIngresosGarantesNewComprobanteDeIngreso);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = garanteDependiente.getId();
                if (findGaranteDependiente(id) == null) {
                    throw new NonexistentEntityException("The garanteDependiente with id " + id + " no longer exists.");
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
            GaranteDependiente garanteDependiente;
            try {
                garanteDependiente = em.getReference(GaranteDependiente.class, id);
                garanteDependiente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The garanteDependiente with id " + id + " no longer exists.", enfe);
            }
            Inmobiliaria unaInmobiliariaGarante = garanteDependiente.getUnaInmobiliariaGarante();
            if (unaInmobiliariaGarante != null) {
                unaInmobiliariaGarante.getGarantes().remove(garanteDependiente);
                unaInmobiliariaGarante = em.merge(unaInmobiliariaGarante);
            }
            Locatario unLocatario = garanteDependiente.getUnLocatario();
            if (unLocatario != null) {
                unLocatario.setUnGarante(null);
                unLocatario = em.merge(unLocatario);
            }
            ContratoAlquiler unContratoAlquiler = garanteDependiente.getUnContratoAlquiler();
            if (unContratoAlquiler != null) {
                unContratoAlquiler.setUnGarante(null);
                unContratoAlquiler = em.merge(unContratoAlquiler);
            }
            List<ComprobanteDeIngreso> comprobantesDeIngresosGarantes = garanteDependiente.getComprobantesDeIngresosGarantes();
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesComprobanteDeIngreso : comprobantesDeIngresosGarantes) {
                comprobantesDeIngresosGarantesComprobanteDeIngreso.getGarantes().remove(garanteDependiente);
                comprobantesDeIngresosGarantesComprobanteDeIngreso = em.merge(comprobantesDeIngresosGarantesComprobanteDeIngreso);
            }
            em.remove(garanteDependiente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GaranteDependiente> findGaranteDependienteEntities() {
        return findGaranteDependienteEntities(true, -1, -1);
    }

    public List<GaranteDependiente> findGaranteDependienteEntities(int maxResults, int firstResult) {
        return findGaranteDependienteEntities(false, maxResults, firstResult);
    }

    private List<GaranteDependiente> findGaranteDependienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GaranteDependiente.class));
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

    public GaranteDependiente findGaranteDependiente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GaranteDependiente.class, id);
        } finally {
            em.close();
        }
    }

    public int getGaranteDependienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GaranteDependiente> rt = cq.from(GaranteDependiente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
