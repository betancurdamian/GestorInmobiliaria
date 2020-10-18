/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.dao;

import com.betancur.gestorinmobiliario.model.ComprobanteMonotributo;
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
public class ComprobanteMonotributoJpaController implements Serializable {

    public ComprobanteMonotributoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ComprobanteMonotributo comprobanteMonotributo) {
        if (comprobanteMonotributo.getLocatarios() == null) {
            comprobanteMonotributo.setLocatarios(new ArrayList<Locatario>());
        }
        if (comprobanteMonotributo.getGarantes() == null) {
            comprobanteMonotributo.setGarantes(new ArrayList<Garante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Locatario> attachedLocatarios = new ArrayList<Locatario>();
            for (Locatario locatariosLocatarioToAttach : comprobanteMonotributo.getLocatarios()) {
                locatariosLocatarioToAttach = em.getReference(locatariosLocatarioToAttach.getClass(), locatariosLocatarioToAttach.getId());
                attachedLocatarios.add(locatariosLocatarioToAttach);
            }
            comprobanteMonotributo.setLocatarios(attachedLocatarios);
            List<Garante> attachedGarantes = new ArrayList<Garante>();
            for (Garante garantesGaranteToAttach : comprobanteMonotributo.getGarantes()) {
                garantesGaranteToAttach = em.getReference(garantesGaranteToAttach.getClass(), garantesGaranteToAttach.getId());
                attachedGarantes.add(garantesGaranteToAttach);
            }
            comprobanteMonotributo.setGarantes(attachedGarantes);
            em.persist(comprobanteMonotributo);
            for (Locatario locatariosLocatario : comprobanteMonotributo.getLocatarios()) {
                locatariosLocatario.getComprobantesDeIngresosLocatarios().add(comprobanteMonotributo);
                locatariosLocatario = em.merge(locatariosLocatario);
            }
            for (Garante garantesGarante : comprobanteMonotributo.getGarantes()) {
                garantesGarante.getComprobantesDeIngresosGarantes().add(comprobanteMonotributo);
                garantesGarante = em.merge(garantesGarante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ComprobanteMonotributo comprobanteMonotributo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ComprobanteMonotributo persistentComprobanteMonotributo = em.find(ComprobanteMonotributo.class, comprobanteMonotributo.getId());
            List<Locatario> locatariosOld = persistentComprobanteMonotributo.getLocatarios();
            List<Locatario> locatariosNew = comprobanteMonotributo.getLocatarios();
            List<Garante> garantesOld = persistentComprobanteMonotributo.getGarantes();
            List<Garante> garantesNew = comprobanteMonotributo.getGarantes();
            List<Locatario> attachedLocatariosNew = new ArrayList<Locatario>();
            for (Locatario locatariosNewLocatarioToAttach : locatariosNew) {
                locatariosNewLocatarioToAttach = em.getReference(locatariosNewLocatarioToAttach.getClass(), locatariosNewLocatarioToAttach.getId());
                attachedLocatariosNew.add(locatariosNewLocatarioToAttach);
            }
            locatariosNew = attachedLocatariosNew;
            comprobanteMonotributo.setLocatarios(locatariosNew);
            List<Garante> attachedGarantesNew = new ArrayList<Garante>();
            for (Garante garantesNewGaranteToAttach : garantesNew) {
                garantesNewGaranteToAttach = em.getReference(garantesNewGaranteToAttach.getClass(), garantesNewGaranteToAttach.getId());
                attachedGarantesNew.add(garantesNewGaranteToAttach);
            }
            garantesNew = attachedGarantesNew;
            comprobanteMonotributo.setGarantes(garantesNew);
            comprobanteMonotributo = em.merge(comprobanteMonotributo);
            for (Locatario locatariosOldLocatario : locatariosOld) {
                if (!locatariosNew.contains(locatariosOldLocatario)) {
                    locatariosOldLocatario.getComprobantesDeIngresosLocatarios().remove(comprobanteMonotributo);
                    locatariosOldLocatario = em.merge(locatariosOldLocatario);
                }
            }
            for (Locatario locatariosNewLocatario : locatariosNew) {
                if (!locatariosOld.contains(locatariosNewLocatario)) {
                    locatariosNewLocatario.getComprobantesDeIngresosLocatarios().add(comprobanteMonotributo);
                    locatariosNewLocatario = em.merge(locatariosNewLocatario);
                }
            }
            for (Garante garantesOldGarante : garantesOld) {
                if (!garantesNew.contains(garantesOldGarante)) {
                    garantesOldGarante.getComprobantesDeIngresosGarantes().remove(comprobanteMonotributo);
                    garantesOldGarante = em.merge(garantesOldGarante);
                }
            }
            for (Garante garantesNewGarante : garantesNew) {
                if (!garantesOld.contains(garantesNewGarante)) {
                    garantesNewGarante.getComprobantesDeIngresosGarantes().add(comprobanteMonotributo);
                    garantesNewGarante = em.merge(garantesNewGarante);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = comprobanteMonotributo.getId();
                if (findComprobanteMonotributo(id) == null) {
                    throw new NonexistentEntityException("The comprobanteMonotributo with id " + id + " no longer exists.");
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
            ComprobanteMonotributo comprobanteMonotributo;
            try {
                comprobanteMonotributo = em.getReference(ComprobanteMonotributo.class, id);
                comprobanteMonotributo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comprobanteMonotributo with id " + id + " no longer exists.", enfe);
            }
            List<Locatario> locatarios = comprobanteMonotributo.getLocatarios();
            for (Locatario locatariosLocatario : locatarios) {
                locatariosLocatario.getComprobantesDeIngresosLocatarios().remove(comprobanteMonotributo);
                locatariosLocatario = em.merge(locatariosLocatario);
            }
            List<Garante> garantes = comprobanteMonotributo.getGarantes();
            for (Garante garantesGarante : garantes) {
                garantesGarante.getComprobantesDeIngresosGarantes().remove(comprobanteMonotributo);
                garantesGarante = em.merge(garantesGarante);
            }
            em.remove(comprobanteMonotributo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ComprobanteMonotributo> findComprobanteMonotributoEntities() {
        return findComprobanteMonotributoEntities(true, -1, -1);
    }

    public List<ComprobanteMonotributo> findComprobanteMonotributoEntities(int maxResults, int firstResult) {
        return findComprobanteMonotributoEntities(false, maxResults, firstResult);
    }

    private List<ComprobanteMonotributo> findComprobanteMonotributoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ComprobanteMonotributo.class));
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

    public ComprobanteMonotributo findComprobanteMonotributo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ComprobanteMonotributo.class, id);
        } finally {
            em.close();
        }
    }

    public int getComprobanteMonotributoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ComprobanteMonotributo> rt = cq.from(ComprobanteMonotributo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
