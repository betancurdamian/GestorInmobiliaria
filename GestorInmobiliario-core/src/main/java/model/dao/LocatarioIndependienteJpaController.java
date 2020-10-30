/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.dao.exceptions.IllegalOrphanException;
import model.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entity.Garante;
import model.entity.Inmobiliaria;
import model.entity.UsuarioCliente;
import model.entity.ComprobanteDeIngreso;
import model.entity.LocatarioIndependiente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class LocatarioIndependienteJpaController implements Serializable {

    public LocatarioIndependienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LocatarioIndependiente locatarioIndependiente) {
        if (locatarioIndependiente.getComprobantesDeIngresosLocatarios() == null) {
            locatarioIndependiente.setComprobantesDeIngresosLocatarios(new ArrayList<ComprobanteDeIngreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Garante unGarante = locatarioIndependiente.getUnGarante();
            if (unGarante != null) {
                unGarante = em.getReference(unGarante.getClass(), unGarante.getId());
                locatarioIndependiente.setUnGarante(unGarante);
            }
            Inmobiliaria unaInmobiliariaCliente = locatarioIndependiente.getUnaInmobiliariaCliente();
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente = em.getReference(unaInmobiliariaCliente.getClass(), unaInmobiliariaCliente.getId());
                locatarioIndependiente.setUnaInmobiliariaCliente(unaInmobiliariaCliente);
            }
            UsuarioCliente unUsuarioCliente = locatarioIndependiente.getUnUsuarioCliente();
            if (unUsuarioCliente != null) {
                unUsuarioCliente = em.getReference(unUsuarioCliente.getClass(), unUsuarioCliente.getId());
                locatarioIndependiente.setUnUsuarioCliente(unUsuarioCliente);
            }
            List<ComprobanteDeIngreso> attachedComprobantesDeIngresosLocatarios = new ArrayList<ComprobanteDeIngreso>();
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach : locatarioIndependiente.getComprobantesDeIngresosLocatarios()) {
                comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach = em.getReference(comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach.getClass(), comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach.getId());
                attachedComprobantesDeIngresosLocatarios.add(comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach);
            }
            locatarioIndependiente.setComprobantesDeIngresosLocatarios(attachedComprobantesDeIngresosLocatarios);
            em.persist(locatarioIndependiente);
            if (unGarante != null) {
                model.entity.Locatario oldUnLocatarioOfUnGarante = unGarante.getUnLocatario();
                if (oldUnLocatarioOfUnGarante != null) {
                    oldUnLocatarioOfUnGarante.setUnGarante(null);
                    oldUnLocatarioOfUnGarante = em.merge(oldUnLocatarioOfUnGarante);
                }
                unGarante.setUnLocatario(locatarioIndependiente);
                unGarante = em.merge(unGarante);
            }
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente.getClientes().add(locatarioIndependiente);
                unaInmobiliariaCliente = em.merge(unaInmobiliariaCliente);
            }
            if (unUsuarioCliente != null) {
                model.entity.Cliente oldUnClienteOfUnUsuarioCliente = unUsuarioCliente.getUnCliente();
                if (oldUnClienteOfUnUsuarioCliente != null) {
                    oldUnClienteOfUnUsuarioCliente.setUnUsuarioCliente(null);
                    oldUnClienteOfUnUsuarioCliente = em.merge(oldUnClienteOfUnUsuarioCliente);
                }
                unUsuarioCliente.setUnCliente(locatarioIndependiente);
                unUsuarioCliente = em.merge(unUsuarioCliente);
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosComprobanteDeIngreso : locatarioIndependiente.getComprobantesDeIngresosLocatarios()) {
                comprobantesDeIngresosLocatariosComprobanteDeIngreso.getLocatarios().add(locatarioIndependiente);
                comprobantesDeIngresosLocatariosComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosComprobanteDeIngreso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LocatarioIndependiente locatarioIndependiente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LocatarioIndependiente persistentLocatarioIndependiente = em.find(LocatarioIndependiente.class, locatarioIndependiente.getId());
            Garante unGaranteOld = persistentLocatarioIndependiente.getUnGarante();
            Garante unGaranteNew = locatarioIndependiente.getUnGarante();
            Inmobiliaria unaInmobiliariaClienteOld = persistentLocatarioIndependiente.getUnaInmobiliariaCliente();
            Inmobiliaria unaInmobiliariaClienteNew = locatarioIndependiente.getUnaInmobiliariaCliente();
            UsuarioCliente unUsuarioClienteOld = persistentLocatarioIndependiente.getUnUsuarioCliente();
            UsuarioCliente unUsuarioClienteNew = locatarioIndependiente.getUnUsuarioCliente();
            List<ComprobanteDeIngreso> comprobantesDeIngresosLocatariosOld = persistentLocatarioIndependiente.getComprobantesDeIngresosLocatarios();
            List<ComprobanteDeIngreso> comprobantesDeIngresosLocatariosNew = locatarioIndependiente.getComprobantesDeIngresosLocatarios();
            List<String> illegalOrphanMessages = null;
            if (unUsuarioClienteOld != null && !unUsuarioClienteOld.equals(unUsuarioClienteNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain UsuarioCliente " + unUsuarioClienteOld + " since its unCliente field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (unGaranteNew != null) {
                unGaranteNew = em.getReference(unGaranteNew.getClass(), unGaranteNew.getId());
                locatarioIndependiente.setUnGarante(unGaranteNew);
            }
            if (unaInmobiliariaClienteNew != null) {
                unaInmobiliariaClienteNew = em.getReference(unaInmobiliariaClienteNew.getClass(), unaInmobiliariaClienteNew.getId());
                locatarioIndependiente.setUnaInmobiliariaCliente(unaInmobiliariaClienteNew);
            }
            if (unUsuarioClienteNew != null) {
                unUsuarioClienteNew = em.getReference(unUsuarioClienteNew.getClass(), unUsuarioClienteNew.getId());
                locatarioIndependiente.setUnUsuarioCliente(unUsuarioClienteNew);
            }
            List<ComprobanteDeIngreso> attachedComprobantesDeIngresosLocatariosNew = new ArrayList<ComprobanteDeIngreso>();
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach : comprobantesDeIngresosLocatariosNew) {
                comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach = em.getReference(comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach.getClass(), comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach.getId());
                attachedComprobantesDeIngresosLocatariosNew.add(comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach);
            }
            comprobantesDeIngresosLocatariosNew = attachedComprobantesDeIngresosLocatariosNew;
            locatarioIndependiente.setComprobantesDeIngresosLocatarios(comprobantesDeIngresosLocatariosNew);
            locatarioIndependiente = em.merge(locatarioIndependiente);
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
                unGaranteNew.setUnLocatario(locatarioIndependiente);
                unGaranteNew = em.merge(unGaranteNew);
            }
            if (unaInmobiliariaClienteOld != null && !unaInmobiliariaClienteOld.equals(unaInmobiliariaClienteNew)) {
                unaInmobiliariaClienteOld.getClientes().remove(locatarioIndependiente);
                unaInmobiliariaClienteOld = em.merge(unaInmobiliariaClienteOld);
            }
            if (unaInmobiliariaClienteNew != null && !unaInmobiliariaClienteNew.equals(unaInmobiliariaClienteOld)) {
                unaInmobiliariaClienteNew.getClientes().add(locatarioIndependiente);
                unaInmobiliariaClienteNew = em.merge(unaInmobiliariaClienteNew);
            }
            if (unUsuarioClienteNew != null && !unUsuarioClienteNew.equals(unUsuarioClienteOld)) {
                model.entity.Cliente oldUnClienteOfUnUsuarioCliente = unUsuarioClienteNew.getUnCliente();
                if (oldUnClienteOfUnUsuarioCliente != null) {
                    oldUnClienteOfUnUsuarioCliente.setUnUsuarioCliente(null);
                    oldUnClienteOfUnUsuarioCliente = em.merge(oldUnClienteOfUnUsuarioCliente);
                }
                unUsuarioClienteNew.setUnCliente(locatarioIndependiente);
                unUsuarioClienteNew = em.merge(unUsuarioClienteNew);
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosOldComprobanteDeIngreso : comprobantesDeIngresosLocatariosOld) {
                if (!comprobantesDeIngresosLocatariosNew.contains(comprobantesDeIngresosLocatariosOldComprobanteDeIngreso)) {
                    comprobantesDeIngresosLocatariosOldComprobanteDeIngreso.getLocatarios().remove(locatarioIndependiente);
                    comprobantesDeIngresosLocatariosOldComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosOldComprobanteDeIngreso);
                }
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosNewComprobanteDeIngreso : comprobantesDeIngresosLocatariosNew) {
                if (!comprobantesDeIngresosLocatariosOld.contains(comprobantesDeIngresosLocatariosNewComprobanteDeIngreso)) {
                    comprobantesDeIngresosLocatariosNewComprobanteDeIngreso.getLocatarios().add(locatarioIndependiente);
                    comprobantesDeIngresosLocatariosNewComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosNewComprobanteDeIngreso);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = locatarioIndependiente.getId();
                if (findLocatarioIndependiente(id) == null) {
                    throw new NonexistentEntityException("The locatarioIndependiente with id " + id + " no longer exists.");
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
            LocatarioIndependiente locatarioIndependiente;
            try {
                locatarioIndependiente = em.getReference(LocatarioIndependiente.class, id);
                locatarioIndependiente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The locatarioIndependiente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            UsuarioCliente unUsuarioClienteOrphanCheck = locatarioIndependiente.getUnUsuarioCliente();
            if (unUsuarioClienteOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This LocatarioIndependiente (" + locatarioIndependiente + ") cannot be destroyed since the UsuarioCliente " + unUsuarioClienteOrphanCheck + " in its unUsuarioCliente field has a non-nullable unCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Garante unGarante = locatarioIndependiente.getUnGarante();
            if (unGarante != null) {
                unGarante.setUnLocatario(null);
                unGarante = em.merge(unGarante);
            }
            Inmobiliaria unaInmobiliariaCliente = locatarioIndependiente.getUnaInmobiliariaCliente();
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente.getClientes().remove(locatarioIndependiente);
                unaInmobiliariaCliente = em.merge(unaInmobiliariaCliente);
            }
            List<ComprobanteDeIngreso> comprobantesDeIngresosLocatarios = locatarioIndependiente.getComprobantesDeIngresosLocatarios();
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosComprobanteDeIngreso : comprobantesDeIngresosLocatarios) {
                comprobantesDeIngresosLocatariosComprobanteDeIngreso.getLocatarios().remove(locatarioIndependiente);
                comprobantesDeIngresosLocatariosComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosComprobanteDeIngreso);
            }
            em.remove(locatarioIndependiente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LocatarioIndependiente> findLocatarioIndependienteEntities() {
        return findLocatarioIndependienteEntities(true, -1, -1);
    }

    public List<LocatarioIndependiente> findLocatarioIndependienteEntities(int maxResults, int firstResult) {
        return findLocatarioIndependienteEntities(false, maxResults, firstResult);
    }

    private List<LocatarioIndependiente> findLocatarioIndependienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LocatarioIndependiente.class));
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

    public LocatarioIndependiente findLocatarioIndependiente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LocatarioIndependiente.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocatarioIndependienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LocatarioIndependiente> rt = cq.from(LocatarioIndependiente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
