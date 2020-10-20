/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.dao;

import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.betancur.gestorinmobiliario.model.entity.Locatario;
import java.util.ArrayList;
import java.util.List;
import com.betancur.gestorinmobiliario.model.entity.Garante;
import com.betancur.gestorinmobiliario.model.entity.ReciboDeSueldo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class ReciboDeSueldoJpaController implements Serializable {

    public ReciboDeSueldoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReciboDeSueldo reciboDeSueldo) {
        if (reciboDeSueldo.getLocatarios() == null) {
            reciboDeSueldo.setLocatarios(new ArrayList<Locatario>());
        }
        if (reciboDeSueldo.getGarantes() == null) {
            reciboDeSueldo.setGarantes(new ArrayList<Garante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Locatario> attachedLocatarios = new ArrayList<Locatario>();
            for (Locatario locatariosLocatarioToAttach : reciboDeSueldo.getLocatarios()) {
                locatariosLocatarioToAttach = em.getReference(locatariosLocatarioToAttach.getClass(), locatariosLocatarioToAttach.getId());
                attachedLocatarios.add(locatariosLocatarioToAttach);
            }
            reciboDeSueldo.setLocatarios(attachedLocatarios);
            List<Garante> attachedGarantes = new ArrayList<Garante>();
            for (Garante garantesGaranteToAttach : reciboDeSueldo.getGarantes()) {
                garantesGaranteToAttach = em.getReference(garantesGaranteToAttach.getClass(), garantesGaranteToAttach.getId());
                attachedGarantes.add(garantesGaranteToAttach);
            }
            reciboDeSueldo.setGarantes(attachedGarantes);
            em.persist(reciboDeSueldo);
            for (Locatario locatariosLocatario : reciboDeSueldo.getLocatarios()) {
                locatariosLocatario.getComprobantesDeIngresosLocatarios().add(reciboDeSueldo);
                locatariosLocatario = em.merge(locatariosLocatario);
            }
            for (Garante garantesGarante : reciboDeSueldo.getGarantes()) {
                garantesGarante.getComprobantesDeIngresosGarantes().add(reciboDeSueldo);
                garantesGarante = em.merge(garantesGarante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReciboDeSueldo reciboDeSueldo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReciboDeSueldo persistentReciboDeSueldo = em.find(ReciboDeSueldo.class, reciboDeSueldo.getId());
            List<Locatario> locatariosOld = persistentReciboDeSueldo.getLocatarios();
            List<Locatario> locatariosNew = reciboDeSueldo.getLocatarios();
            List<Garante> garantesOld = persistentReciboDeSueldo.getGarantes();
            List<Garante> garantesNew = reciboDeSueldo.getGarantes();
            List<Locatario> attachedLocatariosNew = new ArrayList<Locatario>();
            for (Locatario locatariosNewLocatarioToAttach : locatariosNew) {
                locatariosNewLocatarioToAttach = em.getReference(locatariosNewLocatarioToAttach.getClass(), locatariosNewLocatarioToAttach.getId());
                attachedLocatariosNew.add(locatariosNewLocatarioToAttach);
            }
            locatariosNew = attachedLocatariosNew;
            reciboDeSueldo.setLocatarios(locatariosNew);
            List<Garante> attachedGarantesNew = new ArrayList<Garante>();
            for (Garante garantesNewGaranteToAttach : garantesNew) {
                garantesNewGaranteToAttach = em.getReference(garantesNewGaranteToAttach.getClass(), garantesNewGaranteToAttach.getId());
                attachedGarantesNew.add(garantesNewGaranteToAttach);
            }
            garantesNew = attachedGarantesNew;
            reciboDeSueldo.setGarantes(garantesNew);
            reciboDeSueldo = em.merge(reciboDeSueldo);
            for (Locatario locatariosOldLocatario : locatariosOld) {
                if (!locatariosNew.contains(locatariosOldLocatario)) {
                    locatariosOldLocatario.getComprobantesDeIngresosLocatarios().remove(reciboDeSueldo);
                    locatariosOldLocatario = em.merge(locatariosOldLocatario);
                }
            }
            for (Locatario locatariosNewLocatario : locatariosNew) {
                if (!locatariosOld.contains(locatariosNewLocatario)) {
                    locatariosNewLocatario.getComprobantesDeIngresosLocatarios().add(reciboDeSueldo);
                    locatariosNewLocatario = em.merge(locatariosNewLocatario);
                }
            }
            for (Garante garantesOldGarante : garantesOld) {
                if (!garantesNew.contains(garantesOldGarante)) {
                    garantesOldGarante.getComprobantesDeIngresosGarantes().remove(reciboDeSueldo);
                    garantesOldGarante = em.merge(garantesOldGarante);
                }
            }
            for (Garante garantesNewGarante : garantesNew) {
                if (!garantesOld.contains(garantesNewGarante)) {
                    garantesNewGarante.getComprobantesDeIngresosGarantes().add(reciboDeSueldo);
                    garantesNewGarante = em.merge(garantesNewGarante);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = reciboDeSueldo.getId();
                if (findReciboDeSueldo(id) == null) {
                    throw new NonexistentEntityException("The reciboDeSueldo with id " + id + " no longer exists.");
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
            ReciboDeSueldo reciboDeSueldo;
            try {
                reciboDeSueldo = em.getReference(ReciboDeSueldo.class, id);
                reciboDeSueldo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reciboDeSueldo with id " + id + " no longer exists.", enfe);
            }
            List<Locatario> locatarios = reciboDeSueldo.getLocatarios();
            for (Locatario locatariosLocatario : locatarios) {
                locatariosLocatario.getComprobantesDeIngresosLocatarios().remove(reciboDeSueldo);
                locatariosLocatario = em.merge(locatariosLocatario);
            }
            List<Garante> garantes = reciboDeSueldo.getGarantes();
            for (Garante garantesGarante : garantes) {
                garantesGarante.getComprobantesDeIngresosGarantes().remove(reciboDeSueldo);
                garantesGarante = em.merge(garantesGarante);
            }
            em.remove(reciboDeSueldo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReciboDeSueldo> findReciboDeSueldoEntities() {
        return findReciboDeSueldoEntities(true, -1, -1);
    }

    public List<ReciboDeSueldo> findReciboDeSueldoEntities(int maxResults, int firstResult) {
        return findReciboDeSueldoEntities(false, maxResults, firstResult);
    }

    private List<ReciboDeSueldo> findReciboDeSueldoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ReciboDeSueldo.class));
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

    public ReciboDeSueldo findReciboDeSueldo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReciboDeSueldo.class, id);
        } finally {
            em.close();
        }
    }

    public int getReciboDeSueldoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ReciboDeSueldo> rt = cq.from(ReciboDeSueldo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
