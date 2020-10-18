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
import com.betancur.gestorinmobiliario.model.Inmobiliaria;
import com.betancur.gestorinmobiliario.model.Alquiler;
import com.betancur.gestorinmobiliario.model.Terreno;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class TerrenoJpaController implements Serializable {

    public TerrenoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Terreno terreno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaInmueble = terreno.getUnaInmobiliariaInmueble();
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble = em.getReference(unaInmobiliariaInmueble.getClass(), unaInmobiliariaInmueble.getId());
                terreno.setUnaInmobiliariaInmueble(unaInmobiliariaInmueble);
            }
            Alquiler unAlquiler = terreno.getUnAlquiler();
            if (unAlquiler != null) {
                unAlquiler = em.getReference(unAlquiler.getClass(), unAlquiler.getId());
                terreno.setUnAlquiler(unAlquiler);
            }
            em.persist(terreno);
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble.getInmuebles().add(terreno);
                unaInmobiliariaInmueble = em.merge(unaInmobiliariaInmueble);
            }
            if (unAlquiler != null) {
                com.betancur.gestorinmobiliario.model.Inmueble oldUnInmuebleOfUnAlquiler = unAlquiler.getUnInmueble();
                if (oldUnInmuebleOfUnAlquiler != null) {
                    oldUnInmuebleOfUnAlquiler.setUnAlquiler(null);
                    oldUnInmuebleOfUnAlquiler = em.merge(oldUnInmuebleOfUnAlquiler);
                }
                unAlquiler.setUnInmueble(terreno);
                unAlquiler = em.merge(unAlquiler);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Terreno terreno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Terreno persistentTerreno = em.find(Terreno.class, terreno.getId());
            Inmobiliaria unaInmobiliariaInmuebleOld = persistentTerreno.getUnaInmobiliariaInmueble();
            Inmobiliaria unaInmobiliariaInmuebleNew = terreno.getUnaInmobiliariaInmueble();
            Alquiler unAlquilerOld = persistentTerreno.getUnAlquiler();
            Alquiler unAlquilerNew = terreno.getUnAlquiler();
            if (unaInmobiliariaInmuebleNew != null) {
                unaInmobiliariaInmuebleNew = em.getReference(unaInmobiliariaInmuebleNew.getClass(), unaInmobiliariaInmuebleNew.getId());
                terreno.setUnaInmobiliariaInmueble(unaInmobiliariaInmuebleNew);
            }
            if (unAlquilerNew != null) {
                unAlquilerNew = em.getReference(unAlquilerNew.getClass(), unAlquilerNew.getId());
                terreno.setUnAlquiler(unAlquilerNew);
            }
            terreno = em.merge(terreno);
            if (unaInmobiliariaInmuebleOld != null && !unaInmobiliariaInmuebleOld.equals(unaInmobiliariaInmuebleNew)) {
                unaInmobiliariaInmuebleOld.getInmuebles().remove(terreno);
                unaInmobiliariaInmuebleOld = em.merge(unaInmobiliariaInmuebleOld);
            }
            if (unaInmobiliariaInmuebleNew != null && !unaInmobiliariaInmuebleNew.equals(unaInmobiliariaInmuebleOld)) {
                unaInmobiliariaInmuebleNew.getInmuebles().add(terreno);
                unaInmobiliariaInmuebleNew = em.merge(unaInmobiliariaInmuebleNew);
            }
            if (unAlquilerOld != null && !unAlquilerOld.equals(unAlquilerNew)) {
                unAlquilerOld.setUnInmueble(null);
                unAlquilerOld = em.merge(unAlquilerOld);
            }
            if (unAlquilerNew != null && !unAlquilerNew.equals(unAlquilerOld)) {
                com.betancur.gestorinmobiliario.model.Inmueble oldUnInmuebleOfUnAlquiler = unAlquilerNew.getUnInmueble();
                if (oldUnInmuebleOfUnAlquiler != null) {
                    oldUnInmuebleOfUnAlquiler.setUnAlquiler(null);
                    oldUnInmuebleOfUnAlquiler = em.merge(oldUnInmuebleOfUnAlquiler);
                }
                unAlquilerNew.setUnInmueble(terreno);
                unAlquilerNew = em.merge(unAlquilerNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = terreno.getId();
                if (findTerreno(id) == null) {
                    throw new NonexistentEntityException("The terreno with id " + id + " no longer exists.");
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
            Terreno terreno;
            try {
                terreno = em.getReference(Terreno.class, id);
                terreno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The terreno with id " + id + " no longer exists.", enfe);
            }
            Inmobiliaria unaInmobiliariaInmueble = terreno.getUnaInmobiliariaInmueble();
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble.getInmuebles().remove(terreno);
                unaInmobiliariaInmueble = em.merge(unaInmobiliariaInmueble);
            }
            Alquiler unAlquiler = terreno.getUnAlquiler();
            if (unAlquiler != null) {
                unAlquiler.setUnInmueble(null);
                unAlquiler = em.merge(unAlquiler);
            }
            em.remove(terreno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Terreno> findTerrenoEntities() {
        return findTerrenoEntities(true, -1, -1);
    }

    public List<Terreno> findTerrenoEntities(int maxResults, int firstResult) {
        return findTerrenoEntities(false, maxResults, firstResult);
    }

    private List<Terreno> findTerrenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Terreno.class));
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

    public Terreno findTerreno(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Terreno.class, id);
        } finally {
            em.close();
        }
    }

    public int getTerrenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Terreno> rt = cq.from(Terreno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
