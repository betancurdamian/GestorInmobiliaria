/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Inmobiliaria;
import model.entity.Locador;
import model.entity.Terreno;

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
            Locador unLocador = terreno.getUnLocador();
            if (unLocador != null) {
                unLocador = em.getReference(unLocador.getClass(), unLocador.getId());
                terreno.setUnLocador(unLocador);
            }
            em.persist(terreno);
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble.getInmuebles().add(terreno);
                unaInmobiliariaInmueble = em.merge(unaInmobiliariaInmueble);
            }
            if (unLocador != null) {
                unLocador.getInmuebles().add(terreno);
                unLocador = em.merge(unLocador);
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
            Locador unLocadorOld = persistentTerreno.getUnLocador();
            Locador unLocadorNew = terreno.getUnLocador();
            if (unaInmobiliariaInmuebleNew != null) {
                unaInmobiliariaInmuebleNew = em.getReference(unaInmobiliariaInmuebleNew.getClass(), unaInmobiliariaInmuebleNew.getId());
                terreno.setUnaInmobiliariaInmueble(unaInmobiliariaInmuebleNew);
            }
            if (unLocadorNew != null) {
                unLocadorNew = em.getReference(unLocadorNew.getClass(), unLocadorNew.getId());
                terreno.setUnLocador(unLocadorNew);
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
            if (unLocadorOld != null && !unLocadorOld.equals(unLocadorNew)) {
                unLocadorOld.getInmuebles().remove(terreno);
                unLocadorOld = em.merge(unLocadorOld);
            }
            if (unLocadorNew != null && !unLocadorNew.equals(unLocadorOld)) {
                unLocadorNew.getInmuebles().add(terreno);
                unLocadorNew = em.merge(unLocadorNew);
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
            Locador unLocador = terreno.getUnLocador();
            if (unLocador != null) {
                unLocador.getInmuebles().remove(terreno);
                unLocador = em.merge(unLocador);
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
