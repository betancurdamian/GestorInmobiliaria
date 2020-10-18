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
import com.betancur.gestorinmobiliario.model.Garante;
import com.betancur.gestorinmobiliario.model.Inmobiliaria;
import com.betancur.gestorinmobiliario.model.UsuarioCliente;
import com.betancur.gestorinmobiliario.model.ComprobanteDeIngreso;
import com.betancur.gestorinmobiliario.model.LocatarioEstudiante;
import com.betancur.gestorinmobiliario.model.dao.exceptions.IllegalOrphanException;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class LocatarioEstudianteJpaController implements Serializable {

    public LocatarioEstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LocatarioEstudiante locatarioEstudiante) {
        if (locatarioEstudiante.getComprobantesDeIngresosLocatarios() == null) {
            locatarioEstudiante.setComprobantesDeIngresosLocatarios(new ArrayList<ComprobanteDeIngreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Garante unGarante = locatarioEstudiante.getUnGarante();
            if (unGarante != null) {
                unGarante = em.getReference(unGarante.getClass(), unGarante.getId());
                locatarioEstudiante.setUnGarante(unGarante);
            }
            Inmobiliaria unaInmobiliariaCliente = locatarioEstudiante.getUnaInmobiliariaCliente();
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente = em.getReference(unaInmobiliariaCliente.getClass(), unaInmobiliariaCliente.getId());
                locatarioEstudiante.setUnaInmobiliariaCliente(unaInmobiliariaCliente);
            }
            UsuarioCliente unUsuarioCliente = locatarioEstudiante.getUnUsuarioCliente();
            if (unUsuarioCliente != null) {
                unUsuarioCliente = em.getReference(unUsuarioCliente.getClass(), unUsuarioCliente.getId());
                locatarioEstudiante.setUnUsuarioCliente(unUsuarioCliente);
            }
            List<ComprobanteDeIngreso> attachedComprobantesDeIngresosLocatarios = new ArrayList<ComprobanteDeIngreso>();
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach : locatarioEstudiante.getComprobantesDeIngresosLocatarios()) {
                comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach = em.getReference(comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach.getClass(), comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach.getId());
                attachedComprobantesDeIngresosLocatarios.add(comprobantesDeIngresosLocatariosComprobanteDeIngresoToAttach);
            }
            locatarioEstudiante.setComprobantesDeIngresosLocatarios(attachedComprobantesDeIngresosLocatarios);
            em.persist(locatarioEstudiante);
            if (unGarante != null) {
                com.betancur.gestorinmobiliario.model.Locatario oldUnLocatarioOfUnGarante = unGarante.getUnLocatario();
                if (oldUnLocatarioOfUnGarante != null) {
                    oldUnLocatarioOfUnGarante.setUnGarante(null);
                    oldUnLocatarioOfUnGarante = em.merge(oldUnLocatarioOfUnGarante);
                }
                unGarante.setUnLocatario(locatarioEstudiante);
                unGarante = em.merge(unGarante);
            }
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente.getClientes().add(locatarioEstudiante);
                unaInmobiliariaCliente = em.merge(unaInmobiliariaCliente);
            }
            if (unUsuarioCliente != null) {
                com.betancur.gestorinmobiliario.model.Cliente oldUnClienteOfUnUsuarioCliente = unUsuarioCliente.getUnCliente();
                if (oldUnClienteOfUnUsuarioCliente != null) {
                    oldUnClienteOfUnUsuarioCliente.setUnUsuarioCliente(null);
                    oldUnClienteOfUnUsuarioCliente = em.merge(oldUnClienteOfUnUsuarioCliente);
                }
                unUsuarioCliente.setUnCliente(locatarioEstudiante);
                unUsuarioCliente = em.merge(unUsuarioCliente);
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosComprobanteDeIngreso : locatarioEstudiante.getComprobantesDeIngresosLocatarios()) {
                comprobantesDeIngresosLocatariosComprobanteDeIngreso.getLocatarios().add(locatarioEstudiante);
                comprobantesDeIngresosLocatariosComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosComprobanteDeIngreso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LocatarioEstudiante locatarioEstudiante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LocatarioEstudiante persistentLocatarioEstudiante = em.find(LocatarioEstudiante.class, locatarioEstudiante.getId());
            Garante unGaranteOld = persistentLocatarioEstudiante.getUnGarante();
            Garante unGaranteNew = locatarioEstudiante.getUnGarante();
            Inmobiliaria unaInmobiliariaClienteOld = persistentLocatarioEstudiante.getUnaInmobiliariaCliente();
            Inmobiliaria unaInmobiliariaClienteNew = locatarioEstudiante.getUnaInmobiliariaCliente();
            UsuarioCliente unUsuarioClienteOld = persistentLocatarioEstudiante.getUnUsuarioCliente();
            UsuarioCliente unUsuarioClienteNew = locatarioEstudiante.getUnUsuarioCliente();
            List<ComprobanteDeIngreso> comprobantesDeIngresosLocatariosOld = persistentLocatarioEstudiante.getComprobantesDeIngresosLocatarios();
            List<ComprobanteDeIngreso> comprobantesDeIngresosLocatariosNew = locatarioEstudiante.getComprobantesDeIngresosLocatarios();
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
                locatarioEstudiante.setUnGarante(unGaranteNew);
            }
            if (unaInmobiliariaClienteNew != null) {
                unaInmobiliariaClienteNew = em.getReference(unaInmobiliariaClienteNew.getClass(), unaInmobiliariaClienteNew.getId());
                locatarioEstudiante.setUnaInmobiliariaCliente(unaInmobiliariaClienteNew);
            }
            if (unUsuarioClienteNew != null) {
                unUsuarioClienteNew = em.getReference(unUsuarioClienteNew.getClass(), unUsuarioClienteNew.getId());
                locatarioEstudiante.setUnUsuarioCliente(unUsuarioClienteNew);
            }
            List<ComprobanteDeIngreso> attachedComprobantesDeIngresosLocatariosNew = new ArrayList<ComprobanteDeIngreso>();
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach : comprobantesDeIngresosLocatariosNew) {
                comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach = em.getReference(comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach.getClass(), comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach.getId());
                attachedComprobantesDeIngresosLocatariosNew.add(comprobantesDeIngresosLocatariosNewComprobanteDeIngresoToAttach);
            }
            comprobantesDeIngresosLocatariosNew = attachedComprobantesDeIngresosLocatariosNew;
            locatarioEstudiante.setComprobantesDeIngresosLocatarios(comprobantesDeIngresosLocatariosNew);
            locatarioEstudiante = em.merge(locatarioEstudiante);
            if (unGaranteOld != null && !unGaranteOld.equals(unGaranteNew)) {
                unGaranteOld.setUnLocatario(null);
                unGaranteOld = em.merge(unGaranteOld);
            }
            if (unGaranteNew != null && !unGaranteNew.equals(unGaranteOld)) {
                com.betancur.gestorinmobiliario.model.Locatario oldUnLocatarioOfUnGarante = unGaranteNew.getUnLocatario();
                if (oldUnLocatarioOfUnGarante != null) {
                    oldUnLocatarioOfUnGarante.setUnGarante(null);
                    oldUnLocatarioOfUnGarante = em.merge(oldUnLocatarioOfUnGarante);
                }
                unGaranteNew.setUnLocatario(locatarioEstudiante);
                unGaranteNew = em.merge(unGaranteNew);
            }
            if (unaInmobiliariaClienteOld != null && !unaInmobiliariaClienteOld.equals(unaInmobiliariaClienteNew)) {
                unaInmobiliariaClienteOld.getClientes().remove(locatarioEstudiante);
                unaInmobiliariaClienteOld = em.merge(unaInmobiliariaClienteOld);
            }
            if (unaInmobiliariaClienteNew != null && !unaInmobiliariaClienteNew.equals(unaInmobiliariaClienteOld)) {
                unaInmobiliariaClienteNew.getClientes().add(locatarioEstudiante);
                unaInmobiliariaClienteNew = em.merge(unaInmobiliariaClienteNew);
            }
            if (unUsuarioClienteNew != null && !unUsuarioClienteNew.equals(unUsuarioClienteOld)) {
                com.betancur.gestorinmobiliario.model.Cliente oldUnClienteOfUnUsuarioCliente = unUsuarioClienteNew.getUnCliente();
                if (oldUnClienteOfUnUsuarioCliente != null) {
                    oldUnClienteOfUnUsuarioCliente.setUnUsuarioCliente(null);
                    oldUnClienteOfUnUsuarioCliente = em.merge(oldUnClienteOfUnUsuarioCliente);
                }
                unUsuarioClienteNew.setUnCliente(locatarioEstudiante);
                unUsuarioClienteNew = em.merge(unUsuarioClienteNew);
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosOldComprobanteDeIngreso : comprobantesDeIngresosLocatariosOld) {
                if (!comprobantesDeIngresosLocatariosNew.contains(comprobantesDeIngresosLocatariosOldComprobanteDeIngreso)) {
                    comprobantesDeIngresosLocatariosOldComprobanteDeIngreso.getLocatarios().remove(locatarioEstudiante);
                    comprobantesDeIngresosLocatariosOldComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosOldComprobanteDeIngreso);
                }
            }
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosNewComprobanteDeIngreso : comprobantesDeIngresosLocatariosNew) {
                if (!comprobantesDeIngresosLocatariosOld.contains(comprobantesDeIngresosLocatariosNewComprobanteDeIngreso)) {
                    comprobantesDeIngresosLocatariosNewComprobanteDeIngreso.getLocatarios().add(locatarioEstudiante);
                    comprobantesDeIngresosLocatariosNewComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosNewComprobanteDeIngreso);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = locatarioEstudiante.getId();
                if (findLocatarioEstudiante(id) == null) {
                    throw new NonexistentEntityException("The locatarioEstudiante with id " + id + " no longer exists.");
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
            LocatarioEstudiante locatarioEstudiante;
            try {
                locatarioEstudiante = em.getReference(LocatarioEstudiante.class, id);
                locatarioEstudiante.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The locatarioEstudiante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            UsuarioCliente unUsuarioClienteOrphanCheck = locatarioEstudiante.getUnUsuarioCliente();
            if (unUsuarioClienteOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This LocatarioEstudiante (" + locatarioEstudiante + ") cannot be destroyed since the UsuarioCliente " + unUsuarioClienteOrphanCheck + " in its unUsuarioCliente field has a non-nullable unCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Garante unGarante = locatarioEstudiante.getUnGarante();
            if (unGarante != null) {
                unGarante.setUnLocatario(null);
                unGarante = em.merge(unGarante);
            }
            Inmobiliaria unaInmobiliariaCliente = locatarioEstudiante.getUnaInmobiliariaCliente();
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente.getClientes().remove(locatarioEstudiante);
                unaInmobiliariaCliente = em.merge(unaInmobiliariaCliente);
            }
            List<ComprobanteDeIngreso> comprobantesDeIngresosLocatarios = locatarioEstudiante.getComprobantesDeIngresosLocatarios();
            for (ComprobanteDeIngreso comprobantesDeIngresosLocatariosComprobanteDeIngreso : comprobantesDeIngresosLocatarios) {
                comprobantesDeIngresosLocatariosComprobanteDeIngreso.getLocatarios().remove(locatarioEstudiante);
                comprobantesDeIngresosLocatariosComprobanteDeIngreso = em.merge(comprobantesDeIngresosLocatariosComprobanteDeIngreso);
            }
            em.remove(locatarioEstudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LocatarioEstudiante> findLocatarioEstudianteEntities() {
        return findLocatarioEstudianteEntities(true, -1, -1);
    }

    public List<LocatarioEstudiante> findLocatarioEstudianteEntities(int maxResults, int firstResult) {
        return findLocatarioEstudianteEntities(false, maxResults, firstResult);
    }

    private List<LocatarioEstudiante> findLocatarioEstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LocatarioEstudiante.class));
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

    public LocatarioEstudiante findLocatarioEstudiante(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LocatarioEstudiante.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocatarioEstudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LocatarioEstudiante> rt = cq.from(LocatarioEstudiante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
