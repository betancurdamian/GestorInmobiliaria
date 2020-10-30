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
import model.entity.Garante;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class GaranteJpaController implements Serializable {

    public GaranteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Garante garante) {
        if (garante.getComprobantesDeIngresosGarantes() == null) {
            garante.setComprobantesDeIngresosGarantes(new ArrayList<ComprobanteDeIngreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaGarante = garante.getUnaInmobiliariaGarante();
            if (unaInmobiliariaGarante != null) {
                unaInmobiliariaGarante = em.getReference(unaInmobiliariaGarante.getClass(), unaInmobiliariaGarante.getId());
                garante.setUnaInmobiliariaGarante(unaInmobiliariaGarante);
            }
            Locatario unLocatario = garante.getUnLocatario();
            if (unLocatario != null) {
                unLocatario = em.getReference(unLocatario.getClass(), unLocatario.getId());
                garante.setUnLocatario(unLocatario);
            }
            ContratoAlquiler unContratoAlquiler = garante.getUnContratoAlquiler();
            if (unContratoAlquiler != null) {
                unContratoAlquiler = em.getReference(unContratoAlquiler.getClass(), unContratoAlquiler.getId());
                garante.setUnContratoAlquiler(unContratoAlquiler);
            }
            List<ComprobanteDeIngreso> attachedComprobantesDeIngresosGarantes = new ArrayList<ComprobanteDeIngreso>();
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesComprobanteDeIngresoToAttach : garante.getComprobantesDeIngresosGarantes()) {
                comprobantesDeIngresosGarantesComprobanteDeIngresoToAttach = em.getReference(comprobantesDeIngresosGarantesComprobanteDeIngresoToAttach.getClass(), comprobantesDeIngresosGarantesComprobanteDeIngresoToAttach.getId());
                attachedComprobantesDeIngresosGarantes.add(comprobantesDeIngresosGarantesComprobanteDeIngresoToAttach);
            }
            garante.setComprobantesDeIngresosGarantes(attachedComprobantesDeIngresosGarantes);
            em.persist(garante);
            if (unaInmobiliariaGarante != null) {
                unaInmobiliariaGarante.getGarantes().add(garante);
                unaInmobiliariaGarante = em.merge(unaInmobiliariaGarante);
            }
            if (unLocatario != null) {
                Garante oldUnGaranteOfUnLocatario = unLocatario.getUnGarante();
                if (oldUnGaranteOfUnLocatario != null) {
                    oldUnGaranteOfUnLocatario.setUnLocatario(null);
                    oldUnGaranteOfUnLocatario = em.merge(oldUnGaranteOfUnLocatario);
                }
                unLocatario.setUnGarante(garante);
                unLocatario = em.merge(unLocatario);
            }
            if (unContratoAlquiler != null) {
                Garante oldUnGaranteOfUnContratoAlquiler = unContratoAlquiler.getUnGarante();
                if (oldUnGaranteOfUnContratoAlquiler != null) {
                    oldUnGaranteOfUnContratoAlquiler.setUnContratoAlquiler(null);
                    oldUnGaranteOfUnContratoAlquiler = em.merge(oldUnGaranteOfUnContratoAlquiler);
                }
                unContratoAlquiler.setUnGarante(garante);
                unContratoAlquiler = em.merge(unContratoAlquiler);
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesComprobanteDeIngreso : garante.getComprobantesDeIngresosGarantes()) {
                comprobantesDeIngresosGarantesComprobanteDeIngreso.getGarantes().add(garante);
                comprobantesDeIngresosGarantesComprobanteDeIngreso = em.merge(comprobantesDeIngresosGarantesComprobanteDeIngreso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Garante garante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Garante persistentGarante = em.find(Garante.class, garante.getId());
            Inmobiliaria unaInmobiliariaGaranteOld = persistentGarante.getUnaInmobiliariaGarante();
            Inmobiliaria unaInmobiliariaGaranteNew = garante.getUnaInmobiliariaGarante();
            Locatario unLocatarioOld = persistentGarante.getUnLocatario();
            Locatario unLocatarioNew = garante.getUnLocatario();
            ContratoAlquiler unContratoAlquilerOld = persistentGarante.getUnContratoAlquiler();
            ContratoAlquiler unContratoAlquilerNew = garante.getUnContratoAlquiler();
            List<ComprobanteDeIngreso> comprobantesDeIngresosGarantesOld = persistentGarante.getComprobantesDeIngresosGarantes();
            List<ComprobanteDeIngreso> comprobantesDeIngresosGarantesNew = garante.getComprobantesDeIngresosGarantes();
            if (unaInmobiliariaGaranteNew != null) {
                unaInmobiliariaGaranteNew = em.getReference(unaInmobiliariaGaranteNew.getClass(), unaInmobiliariaGaranteNew.getId());
                garante.setUnaInmobiliariaGarante(unaInmobiliariaGaranteNew);
            }
            if (unLocatarioNew != null) {
                unLocatarioNew = em.getReference(unLocatarioNew.getClass(), unLocatarioNew.getId());
                garante.setUnLocatario(unLocatarioNew);
            }
            if (unContratoAlquilerNew != null) {
                unContratoAlquilerNew = em.getReference(unContratoAlquilerNew.getClass(), unContratoAlquilerNew.getId());
                garante.setUnContratoAlquiler(unContratoAlquilerNew);
            }
            List<ComprobanteDeIngreso> attachedComprobantesDeIngresosGarantesNew = new ArrayList<ComprobanteDeIngreso>();
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesNewComprobanteDeIngresoToAttach : comprobantesDeIngresosGarantesNew) {
                comprobantesDeIngresosGarantesNewComprobanteDeIngresoToAttach = em.getReference(comprobantesDeIngresosGarantesNewComprobanteDeIngresoToAttach.getClass(), comprobantesDeIngresosGarantesNewComprobanteDeIngresoToAttach.getId());
                attachedComprobantesDeIngresosGarantesNew.add(comprobantesDeIngresosGarantesNewComprobanteDeIngresoToAttach);
            }
            comprobantesDeIngresosGarantesNew = attachedComprobantesDeIngresosGarantesNew;
            garante.setComprobantesDeIngresosGarantes(comprobantesDeIngresosGarantesNew);
            garante = em.merge(garante);
            if (unaInmobiliariaGaranteOld != null && !unaInmobiliariaGaranteOld.equals(unaInmobiliariaGaranteNew)) {
                unaInmobiliariaGaranteOld.getGarantes().remove(garante);
                unaInmobiliariaGaranteOld = em.merge(unaInmobiliariaGaranteOld);
            }
            if (unaInmobiliariaGaranteNew != null && !unaInmobiliariaGaranteNew.equals(unaInmobiliariaGaranteOld)) {
                unaInmobiliariaGaranteNew.getGarantes().add(garante);
                unaInmobiliariaGaranteNew = em.merge(unaInmobiliariaGaranteNew);
            }
            if (unLocatarioOld != null && !unLocatarioOld.equals(unLocatarioNew)) {
                unLocatarioOld.setUnGarante(null);
                unLocatarioOld = em.merge(unLocatarioOld);
            }
            if (unLocatarioNew != null && !unLocatarioNew.equals(unLocatarioOld)) {
                Garante oldUnGaranteOfUnLocatario = unLocatarioNew.getUnGarante();
                if (oldUnGaranteOfUnLocatario != null) {
                    oldUnGaranteOfUnLocatario.setUnLocatario(null);
                    oldUnGaranteOfUnLocatario = em.merge(oldUnGaranteOfUnLocatario);
                }
                unLocatarioNew.setUnGarante(garante);
                unLocatarioNew = em.merge(unLocatarioNew);
            }
            if (unContratoAlquilerOld != null && !unContratoAlquilerOld.equals(unContratoAlquilerNew)) {
                unContratoAlquilerOld.setUnGarante(null);
                unContratoAlquilerOld = em.merge(unContratoAlquilerOld);
            }
            if (unContratoAlquilerNew != null && !unContratoAlquilerNew.equals(unContratoAlquilerOld)) {
                Garante oldUnGaranteOfUnContratoAlquiler = unContratoAlquilerNew.getUnGarante();
                if (oldUnGaranteOfUnContratoAlquiler != null) {
                    oldUnGaranteOfUnContratoAlquiler.setUnContratoAlquiler(null);
                    oldUnGaranteOfUnContratoAlquiler = em.merge(oldUnGaranteOfUnContratoAlquiler);
                }
                unContratoAlquilerNew.setUnGarante(garante);
                unContratoAlquilerNew = em.merge(unContratoAlquilerNew);
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesOldComprobanteDeIngreso : comprobantesDeIngresosGarantesOld) {
                if (!comprobantesDeIngresosGarantesNew.contains(comprobantesDeIngresosGarantesOldComprobanteDeIngreso)) {
                    comprobantesDeIngresosGarantesOldComprobanteDeIngreso.getGarantes().remove(garante);
                    comprobantesDeIngresosGarantesOldComprobanteDeIngreso = em.merge(comprobantesDeIngresosGarantesOldComprobanteDeIngreso);
                }
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesNewComprobanteDeIngreso : comprobantesDeIngresosGarantesNew) {
                if (!comprobantesDeIngresosGarantesOld.contains(comprobantesDeIngresosGarantesNewComprobanteDeIngreso)) {
                    comprobantesDeIngresosGarantesNewComprobanteDeIngreso.getGarantes().add(garante);
                    comprobantesDeIngresosGarantesNewComprobanteDeIngreso = em.merge(comprobantesDeIngresosGarantesNewComprobanteDeIngreso);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = garante.getId();
                if (findGarante(id) == null) {
                    throw new NonexistentEntityException("The garante with id " + id + " no longer exists.");
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
            Garante garante;
            try {
                garante = em.getReference(Garante.class, id);
                garante.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The garante with id " + id + " no longer exists.", enfe);
            }
            Inmobiliaria unaInmobiliariaGarante = garante.getUnaInmobiliariaGarante();
            if (unaInmobiliariaGarante != null) {
                unaInmobiliariaGarante.getGarantes().remove(garante);
                unaInmobiliariaGarante = em.merge(unaInmobiliariaGarante);
            }
            Locatario unLocatario = garante.getUnLocatario();
            if (unLocatario != null) {
                unLocatario.setUnGarante(null);
                unLocatario = em.merge(unLocatario);
            }
            ContratoAlquiler unContratoAlquiler = garante.getUnContratoAlquiler();
            if (unContratoAlquiler != null) {
                unContratoAlquiler.setUnGarante(null);
                unContratoAlquiler = em.merge(unContratoAlquiler);
            }
            List<ComprobanteDeIngreso> comprobantesDeIngresosGarantes = garante.getComprobantesDeIngresosGarantes();
            for (ComprobanteDeIngreso comprobantesDeIngresosGarantesComprobanteDeIngreso : comprobantesDeIngresosGarantes) {
                comprobantesDeIngresosGarantesComprobanteDeIngreso.getGarantes().remove(garante);
                comprobantesDeIngresosGarantesComprobanteDeIngreso = em.merge(comprobantesDeIngresosGarantesComprobanteDeIngreso);
            }
            em.remove(garante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Garante> findGaranteEntities() {
        return findGaranteEntities(true, -1, -1);
    }

    public List<Garante> findGaranteEntities(int maxResults, int firstResult) {
        return findGaranteEntities(false, maxResults, firstResult);
    }

    private List<Garante> findGaranteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Garante.class));
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

    public Garante findGarante(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Garante.class, id);
        } finally {
            em.close();
        }
    }

    public int getGaranteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Garante> rt = cq.from(Garante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
