/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.dao;

import com.betancur.gestorinmobiliario.model.ComprobanteDeIngreso;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.betancur.gestorinmobiliario.model.Locatario;
import java.util.ArrayList;
import java.util.List;
import com.betancur.gestorinmobiliario.model.Garante;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class ComprobanteDeIngresoJpaController implements Serializable {

    public ComprobanteDeIngresoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ComprobanteDeIngreso comprobanteDeIngreso) {
        if (comprobanteDeIngreso.getLocatarios() == null) {
            comprobanteDeIngreso.setLocatarios(new ArrayList<Locatario>());
        }
        if (comprobanteDeIngreso.getGarantes() == null) {
            comprobanteDeIngreso.setGarantes(new ArrayList<Garante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Locatario> attachedLocatarios = new ArrayList<Locatario>();
            for (Locatario locatariosLocatarioToAttach : comprobanteDeIngreso.getLocatarios()) {
                locatariosLocatarioToAttach = em.getReference(locatariosLocatarioToAttach.getClass(), locatariosLocatarioToAttach.getId());
                attachedLocatarios.add(locatariosLocatarioToAttach);
            }
            comprobanteDeIngreso.setLocatarios(attachedLocatarios);
            List<Garante> attachedGarantes = new ArrayList<Garante>();
            for (Garante garantesGaranteToAttach : comprobanteDeIngreso.getGarantes()) {
                garantesGaranteToAttach = em.getReference(garantesGaranteToAttach.getClass(), garantesGaranteToAttach.getId());
                attachedGarantes.add(garantesGaranteToAttach);
            }
            comprobanteDeIngreso.setGarantes(attachedGarantes);
            em.persist(comprobanteDeIngreso);
            for (Locatario locatariosLocatario : comprobanteDeIngreso.getLocatarios()) {
                locatariosLocatario.getComprobantesDeIngresosLocatarios().add(comprobanteDeIngreso);
                locatariosLocatario = em.merge(locatariosLocatario);
            }
            for (Garante garantesGarante : comprobanteDeIngreso.getGarantes()) {
                garantesGarante.getComprobantesDeIngresosGarantes().add(comprobanteDeIngreso);
                garantesGarante = em.merge(garantesGarante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ComprobanteDeIngreso comprobanteDeIngreso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ComprobanteDeIngreso persistentComprobanteDeIngreso = em.find(ComprobanteDeIngreso.class, comprobanteDeIngreso.getId());
            List<Locatario> locatariosOld = persistentComprobanteDeIngreso.getLocatarios();
            List<Locatario> locatariosNew = comprobanteDeIngreso.getLocatarios();
            List<Garante> garantesOld = persistentComprobanteDeIngreso.getGarantes();
            List<Garante> garantesNew = comprobanteDeIngreso.getGarantes();
            List<Locatario> attachedLocatariosNew = new ArrayList<Locatario>();
            for (Locatario locatariosNewLocatarioToAttach : locatariosNew) {
                locatariosNewLocatarioToAttach = em.getReference(locatariosNewLocatarioToAttach.getClass(), locatariosNewLocatarioToAttach.getId());
                attachedLocatariosNew.add(locatariosNewLocatarioToAttach);
            }
            locatariosNew = attachedLocatariosNew;
            comprobanteDeIngreso.setLocatarios(locatariosNew);
            List<Garante> attachedGarantesNew = new ArrayList<Garante>();
            for (Garante garantesNewGaranteToAttach : garantesNew) {
                garantesNewGaranteToAttach = em.getReference(garantesNewGaranteToAttach.getClass(), garantesNewGaranteToAttach.getId());
                attachedGarantesNew.add(garantesNewGaranteToAttach);
            }
            garantesNew = attachedGarantesNew;
            comprobanteDeIngreso.setGarantes(garantesNew);
            comprobanteDeIngreso = em.merge(comprobanteDeIngreso);
            for (Locatario locatariosOldLocatario : locatariosOld) {
                if (!locatariosNew.contains(locatariosOldLocatario)) {
                    locatariosOldLocatario.getComprobantesDeIngresosLocatarios().remove(comprobanteDeIngreso);
                    locatariosOldLocatario = em.merge(locatariosOldLocatario);
                }
            }
            for (Locatario locatariosNewLocatario : locatariosNew) {
                if (!locatariosOld.contains(locatariosNewLocatario)) {
                    locatariosNewLocatario.getComprobantesDeIngresosLocatarios().add(comprobanteDeIngreso);
                    locatariosNewLocatario = em.merge(locatariosNewLocatario);
                }
            }
            for (Garante garantesOldGarante : garantesOld) {
                if (!garantesNew.contains(garantesOldGarante)) {
                    garantesOldGarante.getComprobantesDeIngresosGarantes().remove(comprobanteDeIngreso);
                    garantesOldGarante = em.merge(garantesOldGarante);
                }
            }
            for (Garante garantesNewGarante : garantesNew) {
                if (!garantesOld.contains(garantesNewGarante)) {
                    garantesNewGarante.getComprobantesDeIngresosGarantes().add(comprobanteDeIngreso);
                    garantesNewGarante = em.merge(garantesNewGarante);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = comprobanteDeIngreso.getId();
                if (findComprobanteDeIngreso(id) == null) {
                    throw new NonexistentEntityException("The comprobanteDeIngreso with id " + id + " no longer exists.");
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
            ComprobanteDeIngreso comprobanteDeIngreso;
            try {
                comprobanteDeIngreso = em.getReference(ComprobanteDeIngreso.class, id);
                comprobanteDeIngreso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comprobanteDeIngreso with id " + id + " no longer exists.", enfe);
            }
            List<Locatario> locatarios = comprobanteDeIngreso.getLocatarios();
            for (Locatario locatariosLocatario : locatarios) {
                locatariosLocatario.getComprobantesDeIngresosLocatarios().remove(comprobanteDeIngreso);
                locatariosLocatario = em.merge(locatariosLocatario);
            }
            List<Garante> garantes = comprobanteDeIngreso.getGarantes();
            for (Garante garantesGarante : garantes) {
                garantesGarante.getComprobantesDeIngresosGarantes().remove(comprobanteDeIngreso);
                garantesGarante = em.merge(garantesGarante);
            }
            em.remove(comprobanteDeIngreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ComprobanteDeIngreso> findComprobanteDeIngresoEntities() {
        return findComprobanteDeIngresoEntities(true, -1, -1);
    }

    public List<ComprobanteDeIngreso> findComprobanteDeIngresoEntities(int maxResults, int firstResult) {
        return findComprobanteDeIngresoEntities(false, maxResults, firstResult);
    }

    private List<ComprobanteDeIngreso> findComprobanteDeIngresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ComprobanteDeIngreso.class));
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

    public ComprobanteDeIngreso findComprobanteDeIngreso(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ComprobanteDeIngreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getComprobanteDeIngresoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ComprobanteDeIngreso> rt = cq.from(ComprobanteDeIngreso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
