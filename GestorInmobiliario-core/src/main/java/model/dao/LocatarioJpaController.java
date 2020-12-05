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
import model.entity.Locatario;

/**
 *
 * @author Ariel
 */
public class LocatarioJpaController implements Serializable {

    public LocatarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Locatario locatario) {
        if (locatario.getComprobantesDeIngresosLocatarios() == null) {
            locatario.setComprobantesDeIngresosLocatarios(new ArrayList<ComprobanteDeIngreso>());
        }
        if (locatario.getInmuebles() == null) {
            locatario.setInmuebles(new ArrayList<Inmueble>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Garante unGarante = locatario.getUnGarante();
            if (unGarante != null) {
                unGarante = em.getReference(unGarante.getClass(), unGarante.getId());
                locatario.setUnGarante(unGarante);
            }
            Inmobiliaria unaInmobiliariaCliente = locatario.getUnaInmobiliariaCliente();
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente = em.getReference(unaInmobiliariaCliente.getClass(), unaInmobiliariaCliente.getId());
                locatario.setUnaInmobiliariaCliente(unaInmobiliariaCliente);
            }
            UsuarioCliente unUsuarioCliente = locatario.getUnUsuarioCliente();
            if (unUsuarioCliente != null) {
                unUsuarioCliente = em.getReference(unUsuarioCliente.getClass(), unUsuarioCliente.getId());
                locatario.setUnUsuarioCliente(unUsuarioCliente);
            }
            List<ComprobanteDeIngreso> attachedComprobantesDeIngresosLocatarios = new ArrayList<ComprobanteDeIngreso>();
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach : locatario.getComprobantesDeIngresosLocatarios()) {
                comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach = em.getReference(comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach.getClass(), comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach.getId());
                attachedComprobantesDeIngresosLocatarios.add(comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach);
            }
            locatario.setComprobantesDeIngresosLocatarios(attachedComprobantesDeIngresosLocatarios);
            List<Inmueble> attachedInmuebles = new ArrayList<Inmueble>();
            for (Inmueble inmueblesInmuebleToAttach : locatario.getInmuebles()) {
                inmueblesInmuebleToAttach = em.getReference(inmueblesInmuebleToAttach.getClass(), inmueblesInmuebleToAttach.getId());
                attachedInmuebles.add(inmueblesInmuebleToAttach);
            }
            locatario.setInmuebles(attachedInmuebles);
            em.persist(locatario);
            if (unGarante != null) {
                Locatario oldUnLocatarioOfUnGarante = unGarante.getUnLocatario();
                if (oldUnLocatarioOfUnGarante != null) {
                    oldUnLocatarioOfUnGarante.setUnGarante(null);
                    oldUnLocatarioOfUnGarante = em.merge(oldUnLocatarioOfUnGarante);
                }
                unGarante.setUnLocatario(locatario);
                unGarante = em.merge(unGarante);
            }
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente.getClientes().add(locatario);
                unaInmobiliariaCliente = em.merge(unaInmobiliariaCliente);
            }
            if (unUsuarioCliente != null) {
                model.entity.Cliente oldUnClienteOfUnUsuarioCliente = unUsuarioCliente.getUnCliente();
                if (oldUnClienteOfUnUsuarioCliente != null) {
                    oldUnClienteOfUnUsuarioCliente.setUnUsuarioCliente(null);
                    oldUnClienteOfUnUsuarioCliente = em.merge(oldUnClienteOfUnUsuarioCliente);
                }
                unUsuarioCliente.setUnCliente(locatario);
                unUsuarioCliente = em.merge(unUsuarioCliente);
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosComprobanteDeIngreso : locatario.getComprobantesDeIngresosLocatarios()) {
                Locatario oldUnLocatarioOfComprobantesDeIngresosLocatariosComprobanteDeIngreso = comprobantesDeIngresosLocatariosComprobanteDeIngreso.getUnLocatario();
                comprobantesDeIngresosLocatariosComprobanteDeIngreso.setUnLocatario(locatario);
                comprobantesDeIngresosLocatariosComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosComprobanteDeIngreso);
                if (oldUnLocatarioOfComprobantesDeIngresosLocatariosComprobanteDeIngreso != null) {
                    oldUnLocatarioOfComprobantesDeIngresosLocatariosComprobanteDeIngreso.getComprobantesDeIngresosLocatarios().remove(comprobantesDeIngresosLocatariosComprobanteDeIngreso);
                    oldUnLocatarioOfComprobantesDeIngresosLocatariosComprobanteDeIngreso = em.merge(oldUnLocatarioOfComprobantesDeIngresosLocatariosComprobanteDeIngreso);
                }
            }
            for (Inmueble inmueblesInmueble : locatario.getInmuebles()) {
                model.entity.Cliente oldUnClienteOfInmueblesInmueble = inmueblesInmueble.getUnCliente();
                inmueblesInmueble.setUnCliente(locatario);
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

    public void edit(Locatario locatario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Locatario persistentLocatario = em.find(Locatario.class, locatario.getId());
            Garante unGaranteOld = persistentLocatario.getUnGarante();
            Garante unGaranteNew = locatario.getUnGarante();
            Inmobiliaria unaInmobiliariaClienteOld = persistentLocatario.getUnaInmobiliariaCliente();
            Inmobiliaria unaInmobiliariaClienteNew = locatario.getUnaInmobiliariaCliente();
            UsuarioCliente unUsuarioClienteOld = persistentLocatario.getUnUsuarioCliente();
            UsuarioCliente unUsuarioClienteNew = locatario.getUnUsuarioCliente();
            List<ComprobanteDeIngreso> comprobantesDeIngresosLocatariosOld = persistentLocatario.getComprobantesDeIngresosLocatarios();
            List<ComprobanteDeIngreso> comprobantesDeIngresosLocatariosNew = locatario.getComprobantesDeIngresosLocatarios();
            List<Inmueble> inmueblesOld = persistentLocatario.getInmuebles();
            List<Inmueble> inmueblesNew = locatario.getInmuebles();
            if (unGaranteNew != null) {
                unGaranteNew = em.getReference(unGaranteNew.getClass(), unGaranteNew.getId());
                locatario.setUnGarante(unGaranteNew);
            }
            if (unaInmobiliariaClienteNew != null) {
                unaInmobiliariaClienteNew = em.getReference(unaInmobiliariaClienteNew.getClass(), unaInmobiliariaClienteNew.getId());
                locatario.setUnaInmobiliariaCliente(unaInmobiliariaClienteNew);
            }
            if (unUsuarioClienteNew != null) {
                unUsuarioClienteNew = em.getReference(unUsuarioClienteNew.getClass(), unUsuarioClienteNew.getId());
                locatario.setUnUsuarioCliente(unUsuarioClienteNew);
            }
            List<ComprobanteDeIngreso> attachedComprobantesDeIngresosLocatariosNew = new ArrayList<ComprobanteDeIngreso>();
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach : comprobantesDeIngresosLocatariosNew) {
                comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach = em.getReference(comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach.getClass(), comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach.getId());
                attachedComprobantesDeIngresosLocatariosNew.add(comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach);
            }
            comprobantesDeIngresosLocatariosNew = attachedComprobantesDeIngresosLocatariosNew;
            locatario.setComprobantesDeIngresosLocatarios(comprobantesDeIngresosLocatariosNew);
            List<Inmueble> attachedInmueblesNew = new ArrayList<Inmueble>();
            for (Inmueble inmueblesNewInmuebleToAttach : inmueblesNew) {
                inmueblesNewInmuebleToAttach = em.getReference(inmueblesNewInmuebleToAttach.getClass(), inmueblesNewInmuebleToAttach.getId());
                attachedInmueblesNew.add(inmueblesNewInmuebleToAttach);
            }
            inmueblesNew = attachedInmueblesNew;
            locatario.setInmuebles(inmueblesNew);
            locatario = em.merge(locatario);
            if (unGaranteOld != null && !unGaranteOld.equals(unGaranteNew)) {
                unGaranteOld.setUnLocatario(null);
                unGaranteOld = em.merge(unGaranteOld);
            }
            if (unGaranteNew != null && !unGaranteNew.equals(unGaranteOld)) {
                Locatario oldUnLocatarioOfUnGarante = unGaranteNew.getUnLocatario();
                if (oldUnLocatarioOfUnGarante != null) {
                    oldUnLocatarioOfUnGarante.setUnGarante(null);
                    oldUnLocatarioOfUnGarante = em.merge(oldUnLocatarioOfUnGarante);
                }
                unGaranteNew.setUnLocatario(locatario);
                unGaranteNew = em.merge(unGaranteNew);
            }
            if (unaInmobiliariaClienteOld != null && !unaInmobiliariaClienteOld.equals(unaInmobiliariaClienteNew)) {
                unaInmobiliariaClienteOld.getClientes().remove(locatario);
                unaInmobiliariaClienteOld = em.merge(unaInmobiliariaClienteOld);
            }
            if (unaInmobiliariaClienteNew != null && !unaInmobiliariaClienteNew.equals(unaInmobiliariaClienteOld)) {
                unaInmobiliariaClienteNew.getClientes().add(locatario);
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
                unUsuarioClienteNew.setUnCliente(locatario);
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
                    Locatario oldUnLocatarioOfComprobantesDeIngresosLocatariosNewComprobanteDeIngreso = comprobantesDeIngresosLocatariosNewComprobanteDeIngreso.getUnLocatario();
                    comprobantesDeIngresosLocatariosNewComprobanteDeIngreso.setUnLocatario(locatario);
                    comprobantesDeIngresosLocatariosNewComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosNewComprobanteDeIngreso);
                    if (oldUnLocatarioOfComprobantesDeIngresosLocatariosNewComprobanteDeIngreso != null && !oldUnLocatarioOfComprobantesDeIngresosLocatariosNewComprobanteDeIngreso.equals(locatario)) {
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
                    Locatario oldUnClienteOfInmueblesNewInmueble = (Locatario) inmueblesNewInmueble.getUnCliente();
                    inmueblesNewInmueble.setUnCliente(locatario);
                    inmueblesNewInmueble = em.merge(inmueblesNewInmueble);
                    if (oldUnClienteOfInmueblesNewInmueble != null && !oldUnClienteOfInmueblesNewInmueble.equals(locatario)) {
                        oldUnClienteOfInmueblesNewInmueble.getInmuebles().remove(inmueblesNewInmueble);
                        oldUnClienteOfInmueblesNewInmueble = em.merge(oldUnClienteOfInmueblesNewInmueble);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = locatario.getId();
                if (findLocatario(id) == null) {
                    throw new NonexistentEntityException("The locatario with id " + id + " no longer exists.");
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
            Locatario locatario;
            try {
                locatario = em.getReference(Locatario.class, id);
                locatario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The locatario with id " + id + " no longer exists.", enfe);
            }
            Garante unGarante = locatario.getUnGarante();
            if (unGarante != null) {
                unGarante.setUnLocatario(null);
                unGarante = em.merge(unGarante);
            }
            Inmobiliaria unaInmobiliariaCliente = locatario.getUnaInmobiliariaCliente();
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente.getClientes().remove(locatario);
                unaInmobiliariaCliente = em.merge(unaInmobiliariaCliente);
            }
            UsuarioCliente unUsuarioCliente = locatario.getUnUsuarioCliente();
            if (unUsuarioCliente != null) {
                unUsuarioCliente.setUnCliente(null);
                unUsuarioCliente = em.merge(unUsuarioCliente);
            }
            List<ComprobanteDeIngreso> comprobantesDeIngresosLocatarios = locatario.getComprobantesDeIngresosLocatarios();
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosComprobanteDeIngreso : comprobantesDeIngresosLocatarios) {
                comprobantesDeIngresosLocatariosComprobanteDeIngreso.setUnLocatario(null);
                comprobantesDeIngresosLocatariosComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosComprobanteDeIngreso);
            }
            List<Inmueble> inmuebles = locatario.getInmuebles();
            for (Inmueble inmueblesInmueble : inmuebles) {
                inmueblesInmueble.setUnCliente(null);
                inmueblesInmueble = em.merge(inmueblesInmueble);
            }
            em.remove(locatario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Locatario> findLocatarioEntities() {
        return findLocatarioEntities(true, -1, -1);
    }

    public List<Locatario> findLocatarioEntities(int maxResults, int firstResult) {
        return findLocatarioEntities(false, maxResults, firstResult);
    }

    private List<Locatario> findLocatarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Locatario.class));
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

    public Locatario findLocatario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Locatario.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocatarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Locatario> rt = cq.from(Locatario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
