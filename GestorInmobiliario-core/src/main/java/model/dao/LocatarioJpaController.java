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
import model.entity.Locatario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
                comprobantesDeIngresosLocatariosComprobanteDeIngreso.getLocatarios().add(locatario);
                comprobantesDeIngresosLocatariosComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosComprobanteDeIngreso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Locatario locatario) throws IllegalOrphanException, NonexistentEntityException, Exception {
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
                    comprobantesDeIngresosLocatariosOldComprobanteDeIngreso.getLocatarios().remove(locatario);
                    comprobantesDeIngresosLocatariosOldComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosOldComprobanteDeIngreso);
                }
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosNewComprobanteDeIngreso : comprobantesDeIngresosLocatariosNew) {
                if (!comprobantesDeIngresosLocatariosOld.contains(comprobantesDeIngresosLocatariosNewComprobanteDeIngreso)) {
                    comprobantesDeIngresosLocatariosNewComprobanteDeIngreso.getLocatarios().add(locatario);
                    comprobantesDeIngresosLocatariosNewComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosNewComprobanteDeIngreso);
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

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            UsuarioCliente unUsuarioClienteOrphanCheck = locatario.getUnUsuarioCliente();
            if (unUsuarioClienteOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Locatario (" + locatario + ") cannot be destroyed since the UsuarioCliente " + unUsuarioClienteOrphanCheck + " in its unUsuarioCliente field has a non-nullable unCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
            List<ComprobanteDeIngreso> comprobantesDeIngresosLocatarios = locatario.getComprobantesDeIngresosLocatarios();
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosComprobanteDeIngreso : comprobantesDeIngresosLocatarios) {
                comprobantesDeIngresosLocatariosComprobanteDeIngreso.getLocatarios().remove(locatario);
                comprobantesDeIngresosLocatariosComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosComprobanteDeIngreso);
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
