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
import model.entity.Inmobiliaria;
import model.entity.UsuarioCliente;
import model.entity.ComprobanteDeIngreso;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Inmueble;
import model.entity.LocatarioDependiente;

/**
 *
 * @author Ariel
 */
public class LocatarioDependienteJpaController implements Serializable {

    public LocatarioDependienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LocatarioDependiente locatarioDependiente) {
        if (locatarioDependiente.getComprobantesDeIngresosLocatarios() == null) {
            locatarioDependiente.setComprobantesDeIngresosLocatarios(new ArrayList<ComprobanteDeIngreso>());
        }
        if (locatarioDependiente.getInmuebles() == null) {
            locatarioDependiente.setInmuebles(new ArrayList<Inmueble>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Garante unGarante = locatarioDependiente.getUnGarante();
            if (unGarante != null) {
                unGarante = em.getReference(unGarante.getClass(), unGarante.getId());
                locatarioDependiente.setUnGarante(unGarante);
            }
            Inmobiliaria unaInmobiliariaCliente = locatarioDependiente.getUnaInmobiliariaCliente();
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente = em.getReference(unaInmobiliariaCliente.getClass(), unaInmobiliariaCliente.getId());
                locatarioDependiente.setUnaInmobiliariaCliente(unaInmobiliariaCliente);
            }
            UsuarioCliente unUsuarioCliente = locatarioDependiente.getUnUsuarioCliente();
            if (unUsuarioCliente != null) {
                unUsuarioCliente = em.getReference(unUsuarioCliente.getClass(), unUsuarioCliente.getId());
                locatarioDependiente.setUnUsuarioCliente(unUsuarioCliente);
            }
            List<ComprobanteDeIngreso> attachedComprobantesDeIngresosLocatarios = new ArrayList<ComprobanteDeIngreso>();
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach : locatarioDependiente.getComprobantesDeIngresosLocatarios()) {
                comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach = em.getReference(comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach.getClass(), comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach.getId());
                attachedComprobantesDeIngresosLocatarios.add(comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach);
            }
            locatarioDependiente.setComprobantesDeIngresosLocatarios(attachedComprobantesDeIngresosLocatarios);
            List<Inmueble> attachedInmuebles = new ArrayList<Inmueble>();
            for (Inmueble inmueblesInmuebleToAttach : locatarioDependiente.getInmuebles()) {
                inmueblesInmuebleToAttach = em.getReference(inmueblesInmuebleToAttach.getClass(), inmueblesInmuebleToAttach.getId());
                attachedInmuebles.add(inmueblesInmuebleToAttach);
            }
            locatarioDependiente.setInmuebles(attachedInmuebles);
            em.persist(locatarioDependiente);
            if (unGarante != null) {
                model.entity.Locatario oldUnLocatarioOfUnGarante = unGarante.getUnLocatario();
                if (oldUnLocatarioOfUnGarante != null) {
                    oldUnLocatarioOfUnGarante.setUnGarante(null);
                    oldUnLocatarioOfUnGarante = em.merge(oldUnLocatarioOfUnGarante);
                }
                unGarante.setUnLocatario(locatarioDependiente);
                unGarante = em.merge(unGarante);
            }
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente.getClientes().add(locatarioDependiente);
                unaInmobiliariaCliente = em.merge(unaInmobiliariaCliente);
            }
            if (unUsuarioCliente != null) {
                model.entity.Cliente oldUnClienteOfUnUsuarioCliente = unUsuarioCliente.getUnCliente();
                if (oldUnClienteOfUnUsuarioCliente != null) {
                    oldUnClienteOfUnUsuarioCliente.setUnUsuarioCliente(null);
                    oldUnClienteOfUnUsuarioCliente = em.merge(oldUnClienteOfUnUsuarioCliente);
                }
                unUsuarioCliente.setUnCliente(locatarioDependiente);
                unUsuarioCliente = em.merge(unUsuarioCliente);
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosComprobanteDeIngreso : locatarioDependiente.getComprobantesDeIngresosLocatarios()) {
                model.entity.Locatario oldUnLocatarioOfComprobantesDeIngresosLocatariosComprobanteDeIngreso = comprobantesDeIngresosLocatariosComprobanteDeIngreso.getUnLocatario();
                comprobantesDeIngresosLocatariosComprobanteDeIngreso.setUnLocatario(locatarioDependiente);
                comprobantesDeIngresosLocatariosComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosComprobanteDeIngreso);
                if (oldUnLocatarioOfComprobantesDeIngresosLocatariosComprobanteDeIngreso != null) {
                    oldUnLocatarioOfComprobantesDeIngresosLocatariosComprobanteDeIngreso.getComprobantesDeIngresosLocatarios().remove(comprobantesDeIngresosLocatariosComprobanteDeIngreso);
                    oldUnLocatarioOfComprobantesDeIngresosLocatariosComprobanteDeIngreso = em.merge(oldUnLocatarioOfComprobantesDeIngresosLocatariosComprobanteDeIngreso);
                }
            }
            for (Inmueble inmueblesInmueble : locatarioDependiente.getInmuebles()) {
                model.entity.Cliente oldUnClienteOfInmueblesInmueble = inmueblesInmueble.getUnCliente();
                inmueblesInmueble.setUnCliente(locatarioDependiente);
                inmueblesInmueble = em.merge(inmueblesInmueble);
                if (oldUnClienteOfInmueblesInmueble != null) {
                    oldUnClienteOfInmueblesInmueble.getInmuebles().remove(inmueblesInmueble);
                    oldUnClienteOfInmueblesInmueble = em.merge(oldUnClienteOfInmueblesInmueble);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LocatarioDependiente locatarioDependiente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LocatarioDependiente persistentLocatarioDependiente = em.find(LocatarioDependiente.class, locatarioDependiente.getId());
            Garante unGaranteOld = persistentLocatarioDependiente.getUnGarante();
            Garante unGaranteNew = locatarioDependiente.getUnGarante();
            Inmobiliaria unaInmobiliariaClienteOld = persistentLocatarioDependiente.getUnaInmobiliariaCliente();
            Inmobiliaria unaInmobiliariaClienteNew = locatarioDependiente.getUnaInmobiliariaCliente();
            UsuarioCliente unUsuarioClienteOld = persistentLocatarioDependiente.getUnUsuarioCliente();
            UsuarioCliente unUsuarioClienteNew = locatarioDependiente.getUnUsuarioCliente();
            List<ComprobanteDeIngreso> comprobantesDeIngresosLocatariosOld = persistentLocatarioDependiente.getComprobantesDeIngresosLocatarios();
            List<ComprobanteDeIngreso> comprobantesDeIngresosLocatariosNew = locatarioDependiente.getComprobantesDeIngresosLocatarios();
            List<Inmueble> inmueblesOld = persistentLocatarioDependiente.getInmuebles();
            List<Inmueble> inmueblesNew = locatarioDependiente.getInmuebles();
            if (unGaranteNew != null) {
                unGaranteNew = em.getReference(unGaranteNew.getClass(), unGaranteNew.getId());
                locatarioDependiente.setUnGarante(unGaranteNew);
            }
            if (unaInmobiliariaClienteNew != null) {
                unaInmobiliariaClienteNew = em.getReference(unaInmobiliariaClienteNew.getClass(), unaInmobiliariaClienteNew.getId());
                locatarioDependiente.setUnaInmobiliariaCliente(unaInmobiliariaClienteNew);
            }
            if (unUsuarioClienteNew != null) {
                unUsuarioClienteNew = em.getReference(unUsuarioClienteNew.getClass(), unUsuarioClienteNew.getId());
                locatarioDependiente.setUnUsuarioCliente(unUsuarioClienteNew);
            }
            List<ComprobanteDeIngreso> attachedComprobantesDeIngresosLocatariosNew = new ArrayList<ComprobanteDeIngreso>();
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach : comprobantesDeIngresosLocatariosNew) {
                comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach = em.getReference(comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach.getClass(), comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach.getId());
                attachedComprobantesDeIngresosLocatariosNew.add(comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach);
            }
            comprobantesDeIngresosLocatariosNew = attachedComprobantesDeIngresosLocatariosNew;
            locatarioDependiente.setComprobantesDeIngresosLocatarios(comprobantesDeIngresosLocatariosNew);
            List<Inmueble> attachedInmueblesNew = new ArrayList<Inmueble>();
            for (Inmueble inmueblesNewInmuebleToAttach : inmueblesNew) {
                inmueblesNewInmuebleToAttach = em.getReference(inmueblesNewInmuebleToAttach.getClass(), inmueblesNewInmuebleToAttach.getId());
                attachedInmueblesNew.add(inmueblesNewInmuebleToAttach);
            }
            inmueblesNew = attachedInmueblesNew;
            locatarioDependiente.setInmuebles(inmueblesNew);
            locatarioDependiente = em.merge(locatarioDependiente);
            if (unGaranteOld != null && !unGaranteOld.equals(unGaranteNew)) {
                unGaranteOld.setUnLocatario(null);
                unGaranteOld = em.merge(unGaranteOld);
            }
            if (unGaranteNew != null && !unGaranteNew.equals(unGaranteOld)) {
                model.entity.Locatario oldUnLocatarioOfUnGarante = unGaranteNew.getUnLocatario();
                if (oldUnLocatarioOfUnGarante != null) {
                    oldUnLocatarioOfUnGarante.setUnGarante(null);
                    oldUnLocatarioOfUnGarante = em.merge(oldUnLocatarioOfUnGarante);
                }
                unGaranteNew.setUnLocatario(locatarioDependiente);
                unGaranteNew = em.merge(unGaranteNew);
            }
            if (unaInmobiliariaClienteOld != null && !unaInmobiliariaClienteOld.equals(unaInmobiliariaClienteNew)) {
                unaInmobiliariaClienteOld.getClientes().remove(locatarioDependiente);
                unaInmobiliariaClienteOld = em.merge(unaInmobiliariaClienteOld);
            }
            if (unaInmobiliariaClienteNew != null && !unaInmobiliariaClienteNew.equals(unaInmobiliariaClienteOld)) {
                unaInmobiliariaClienteNew.getClientes().add(locatarioDependiente);
                unaInmobiliariaClienteNew = em.merge(unaInmobiliariaClienteNew);
            }
            if (unUsuarioClienteOld != null && !unUsuarioClienteOld.equals(unUsuarioClienteNew)) {
                unUsuarioClienteOld.setUnCliente(null);
                unUsuarioClienteOld = em.merge(unUsuarioClienteOld);
            }
            if (unUsuarioClienteNew != null && !unUsuarioClienteNew.equals(unUsuarioClienteOld)) {
                model.entity.Cliente oldUnClienteOfUnUsuarioCliente = unUsuarioClienteNew.getUnCliente();
                if (oldUnClienteOfUnUsuarioCliente != null) {
                    oldUnClienteOfUnUsuarioCliente.setUnUsuarioCliente(null);
                    oldUnClienteOfUnUsuarioCliente = em.merge(oldUnClienteOfUnUsuarioCliente);
                }
                unUsuarioClienteNew.setUnCliente(locatarioDependiente);
                unUsuarioClienteNew = em.merge(unUsuarioClienteNew);
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosOldComprobanteDeIngreso : comprobantesDeIngresosLocatariosOld) {
                if (!comprobantesDeIngresosLocatariosNew.contains(comprobantesDeIngresosLocatariosOldComprobanteDeIngreso)) {
                    comprobantesDeIngresosLocatariosOldComprobanteDeIngreso.setUnLocatario(null);
                    comprobantesDeIngresosLocatariosOldComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosOldComprobanteDeIngreso);
                }
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosNewComprobanteDeIngreso : comprobantesDeIngresosLocatariosNew) {
                if (!comprobantesDeIngresosLocatariosOld.contains(comprobantesDeIngresosLocatariosNewComprobanteDeIngreso)) {
                    LocatarioDependiente oldUnLocatarioOfComprobantesDeIngresosLocatariosNewComprobanteDeIngreso = (LocatarioDependiente) comprobantesDeIngresosLocatariosNewComprobanteDeIngreso.getUnLocatario();
                    comprobantesDeIngresosLocatariosNewComprobanteDeIngreso.setUnLocatario(locatarioDependiente);
                    comprobantesDeIngresosLocatariosNewComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosNewComprobanteDeIngreso);
                    if (oldUnLocatarioOfComprobantesDeIngresosLocatariosNewComprobanteDeIngreso != null && !oldUnLocatarioOfComprobantesDeIngresosLocatariosNewComprobanteDeIngreso.equals(locatarioDependiente)) {
                        oldUnLocatarioOfComprobantesDeIngresosLocatariosNewComprobanteDeIngreso.getComprobantesDeIngresosLocatarios().remove(comprobantesDeIngresosLocatariosNewComprobanteDeIngreso);
                        oldUnLocatarioOfComprobantesDeIngresosLocatariosNewComprobanteDeIngreso = em.merge(oldUnLocatarioOfComprobantesDeIngresosLocatariosNewComprobanteDeIngreso);
                    }
                }
            }
            for (Inmueble inmueblesOldInmueble : inmueblesOld) {
                if (!inmueblesNew.contains(inmueblesOldInmueble)) {
                    inmueblesOldInmueble.setUnCliente(null);
                    inmueblesOldInmueble = em.merge(inmueblesOldInmueble);
                }
            }
            for (Inmueble inmueblesNewInmueble : inmueblesNew) {
                if (!inmueblesOld.contains(inmueblesNewInmueble)) {
                    LocatarioDependiente oldUnClienteOfInmueblesNewInmueble = (LocatarioDependiente) inmueblesNewInmueble.getUnCliente();
                    inmueblesNewInmueble.setUnCliente(locatarioDependiente);
                    inmueblesNewInmueble = em.merge(inmueblesNewInmueble);
                    if (oldUnClienteOfInmueblesNewInmueble != null && !oldUnClienteOfInmueblesNewInmueble.equals(locatarioDependiente)) {
                        oldUnClienteOfInmueblesNewInmueble.getInmuebles().remove(inmueblesNewInmueble);
                        oldUnClienteOfInmueblesNewInmueble = em.merge(oldUnClienteOfInmueblesNewInmueble);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = locatarioDependiente.getId();
                if (findLocatarioDependiente(id) == null) {
                    throw new NonexistentEntityException("The locatarioDependiente with id " + id + " no longer exists.");
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
            LocatarioDependiente locatarioDependiente;
            try {
                locatarioDependiente = em.getReference(LocatarioDependiente.class, id);
                locatarioDependiente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The locatarioDependiente with id " + id + " no longer exists.", enfe);
            }
            Garante unGarante = locatarioDependiente.getUnGarante();
            if (unGarante != null) {
                unGarante.setUnLocatario(null);
                unGarante = em.merge(unGarante);
            }
            Inmobiliaria unaInmobiliariaCliente = locatarioDependiente.getUnaInmobiliariaCliente();
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente.getClientes().remove(locatarioDependiente);
                unaInmobiliariaCliente = em.merge(unaInmobiliariaCliente);
            }
            UsuarioCliente unUsuarioCliente = locatarioDependiente.getUnUsuarioCliente();
            if (unUsuarioCliente != null) {
                unUsuarioCliente.setUnCliente(null);
                unUsuarioCliente = em.merge(unUsuarioCliente);
            }
            List<ComprobanteDeIngreso> comprobantesDeIngresosLocatarios = locatarioDependiente.getComprobantesDeIngresosLocatarios();
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosComprobanteDeIngreso : comprobantesDeIngresosLocatarios) {
                comprobantesDeIngresosLocatariosComprobanteDeIngreso.setUnLocatario(null);
                comprobantesDeIngresosLocatariosComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosComprobanteDeIngreso);
            }
            List<Inmueble> inmuebles = locatarioDependiente.getInmuebles();
            for (Inmueble inmueblesInmueble : inmuebles) {
                inmueblesInmueble.setUnCliente(null);
                inmueblesInmueble = em.merge(inmueblesInmueble);
            }
            em.remove(locatarioDependiente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LocatarioDependiente> findLocatarioDependienteEntities() {
        return findLocatarioDependienteEntities(true, -1, -1);
    }

    public List<LocatarioDependiente> findLocatarioDependienteEntities(int maxResults, int firstResult) {
        return findLocatarioDependienteEntities(false, maxResults, firstResult);
    }

    private List<LocatarioDependiente> findLocatarioDependienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LocatarioDependiente.class));
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

    public LocatarioDependiente findLocatarioDependiente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LocatarioDependiente.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocatarioDependienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LocatarioDependiente> rt = cq.from(LocatarioDependiente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
