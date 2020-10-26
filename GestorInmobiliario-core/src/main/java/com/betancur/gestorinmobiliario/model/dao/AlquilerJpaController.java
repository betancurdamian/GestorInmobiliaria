/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.dao;

import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import com.betancur.gestorinmobiliario.model.entity.Alquiler;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.betancur.gestorinmobiliario.model.entity.ContratoAlquiler;
import com.betancur.gestorinmobiliario.model.entity.Inmobiliaria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class AlquilerJpaController implements Serializable {

    public AlquilerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alquiler alquiler) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContratoAlquiler unContratoAlquiler = alquiler.getUnContratoAlquiler();
            if (unContratoAlquiler != null) {
                unContratoAlquiler = em.getReference(unContratoAlquiler.getClass(), unContratoAlquiler.getId());
                alquiler.setUnContratoAlquiler(unContratoAlquiler);
            }
            Inmobiliaria unaInmobiliariaAlquiler = alquiler.getUnaInmobiliariaAlquiler();
            if (unaInmobiliariaAlquiler != null) {
                unaInmobiliariaAlquiler = em.getReference(unaInmobiliariaAlquiler.getClass(), unaInmobiliariaAlquiler.getId());
                alquiler.setUnaInmobiliariaAlquiler(unaInmobiliariaAlquiler);
            }
            em.persist(alquiler);
            if (unContratoAlquiler != null) {
                Alquiler oldUnAlquilerOfUnContratoAlquiler = unContratoAlquiler.getUnAlquiler();
                if (oldUnAlquilerOfUnContratoAlquiler != null) {
                    oldUnAlquilerOfUnContratoAlquiler.setUnContratoAlquiler(null);
                    oldUnAlquilerOfUnContratoAlquiler = em.merge(oldUnAlquilerOfUnContratoAlquiler);
                }
                unContratoAlquiler.setUnAlquiler(alquiler);
                unContratoAlquiler = em.merge(unContratoAlquiler);
            }
            if (unaInmobiliariaAlquiler != null) {
                unaInmobiliariaAlquiler.getAlquileres().add(alquiler);
                unaInmobiliariaAlquiler = em.merge(unaInmobiliariaAlquiler);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alquiler alquiler) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alquiler persistentAlquiler = em.find(Alquiler.class, alquiler.getId());
            ContratoAlquiler unContratoAlquilerOld = persistentAlquiler.getUnContratoAlquiler();
            ContratoAlquiler unContratoAlquilerNew = alquiler.getUnContratoAlquiler();
            Inmobiliaria unaInmobiliariaAlquilerOld = persistentAlquiler.getUnaInmobiliariaAlquiler();
            Inmobiliaria unaInmobiliariaAlquilerNew = alquiler.getUnaInmobiliariaAlquiler();
            if (unContratoAlquilerNew != null) {
                unContratoAlquilerNew = em.getReference(unContratoAlquilerNew.getClass(), unContratoAlquilerNew.getId());
                alquiler.setUnContratoAlquiler(unContratoAlquilerNew);
            }
            if (unaInmobiliariaAlquilerNew != null) {
                unaInmobiliariaAlquilerNew = em.getReference(unaInmobiliariaAlquilerNew.getClass(), unaInmobiliariaAlquilerNew.getId());
                alquiler.setUnaInmobiliariaAlquiler(unaInmobiliariaAlquilerNew);
            }
            alquiler = em.merge(alquiler);
            if (unContratoAlquilerOld != null && !unContratoAlquilerOld.equals(unContratoAlquilerNew)) {
                unContratoAlquilerOld.setUnAlquiler(null);
                unContratoAlquilerOld = em.merge(unContratoAlquilerOld);
            }
            if (unContratoAlquilerNew != null && !unContratoAlquilerNew.equals(unContratoAlquilerOld)) {
                Alquiler oldUnAlquilerOfUnContratoAlquiler = unContratoAlquilerNew.getUnAlquiler();
                if (oldUnAlquilerOfUnContratoAlquiler != null) {
                    oldUnAlquilerOfUnContratoAlquiler.setUnContratoAlquiler(null);
                    oldUnAlquilerOfUnContratoAlquiler = em.merge(oldUnAlquilerOfUnContratoAlquiler);
                }
                unContratoAlquilerNew.setUnAlquiler(alquiler);
                unContratoAlquilerNew = em.merge(unContratoAlquilerNew);
            }
            if (unaInmobiliariaAlquilerOld != null && !unaInmobiliariaAlquilerOld.equals(unaInmobiliariaAlquilerNew)) {
                unaInmobiliariaAlquilerOld.getAlquileres().remove(alquiler);
                unaInmobiliariaAlquilerOld = em.merge(unaInmobiliariaAlquilerOld);
            }
            if (unaInmobiliariaAlquilerNew != null && !unaInmobiliariaAlquilerNew.equals(unaInmobiliariaAlquilerOld)) {
                unaInmobiliariaAlquilerNew.getAlquileres().add(alquiler);
                unaInmobiliariaAlquilerNew = em.merge(unaInmobiliariaAlquilerNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = alquiler.getId();
                if (findAlquiler(id) == null) {
                    throw new NonexistentEntityException("The alquiler with id " + id + " no longer exists.");
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
            Alquiler alquiler;
            try {
                alquiler = em.getReference(Alquiler.class, id);
                alquiler.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alquiler with id " + id + " no longer exists.", enfe);
            }
            ContratoAlquiler unContratoAlquiler = alquiler.getUnContratoAlquiler();
            if (unContratoAlquiler != null) {
                unContratoAlquiler.setUnAlquiler(null);
                unContratoAlquiler = em.merge(unContratoAlquiler);
            }
            Inmobiliaria unaInmobiliariaAlquiler = alquiler.getUnaInmobiliariaAlquiler();
            if (unaInmobiliariaAlquiler != null) {
                unaInmobiliariaAlquiler.getAlquileres().remove(alquiler);
                unaInmobiliariaAlquiler = em.merge(unaInmobiliariaAlquiler);
            }
            em.remove(alquiler);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alquiler> findAlquilerEntities() {
        return findAlquilerEntities(true, -1, -1);
    }

    public List<Alquiler> findAlquilerEntities(int maxResults, int firstResult) {
        return findAlquilerEntities(false, maxResults, firstResult);
    }

    private List<Alquiler> findAlquilerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alquiler.class));
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

    public Alquiler findAlquiler(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alquiler.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlquilerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alquiler> rt = cq.from(Alquiler.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
