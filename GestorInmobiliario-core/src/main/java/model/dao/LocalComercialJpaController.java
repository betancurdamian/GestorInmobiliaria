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
import model.entity.LocalComercial;

/**
 *
 * @author Ariel
 */
public class LocalComercialJpaController implements Serializable {

    public LocalComercialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LocalComercial localComercial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaInmueble = localComercial.getUnaInmobiliariaInmueble();
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble = em.getReference(unaInmobiliariaInmueble.getClass(), unaInmobiliariaInmueble.getId());
                localComercial.setUnaInmobiliariaInmueble(unaInmobiliariaInmueble);
            }
            Locador unLocador = localComercial.getUnLocador();
            if (unLocador != null) {
                unLocador = em.getReference(unLocador.getClass(), unLocador.getId());
                localComercial.setUnLocador(unLocador);
            }
            em.persist(localComercial);
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble.getInmuebles().add(localComercial);
                unaInmobiliariaInmueble = em.merge(unaInmobiliariaInmueble);
            }
            if (unLocador != null) {
                unLocador.getInmuebles().add(localComercial);
                unLocador = em.merge(unLocador);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LocalComercial localComercial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LocalComercial persistentLocalComercial = em.find(LocalComercial.class, localComercial.getId());
            Inmobiliaria unaInmobiliariaInmuebleOld = persistentLocalComercial.getUnaInmobiliariaInmueble();
            Inmobiliaria unaInmobiliariaInmuebleNew = localComercial.getUnaInmobiliariaInmueble();
            Locador unLocadorOld = persistentLocalComercial.getUnLocador();
            Locador unLocadorNew = localComercial.getUnLocador();
            if (unaInmobiliariaInmuebleNew != null) {
                unaInmobiliariaInmuebleNew = em.getReference(unaInmobiliariaInmuebleNew.getClass(), unaInmobiliariaInmuebleNew.getId());
                localComercial.setUnaInmobiliariaInmueble(unaInmobiliariaInmuebleNew);
            }
            if (unLocadorNew != null) {
                unLocadorNew = em.getReference(unLocadorNew.getClass(), unLocadorNew.getId());
                localComercial.setUnLocador(unLocadorNew);
            }
            localComercial = em.merge(localComercial);
            if (unaInmobiliariaInmuebleOld != null && !unaInmobiliariaInmuebleOld.equals(unaInmobiliariaInmuebleNew)) {
                unaInmobiliariaInmuebleOld.getInmuebles().remove(localComercial);
                unaInmobiliariaInmuebleOld = em.merge(unaInmobiliariaInmuebleOld);
            }
            if (unaInmobiliariaInmuebleNew != null && !unaInmobiliariaInmuebleNew.equals(unaInmobiliariaInmuebleOld)) {
                unaInmobiliariaInmuebleNew.getInmuebles().add(localComercial);
                unaInmobiliariaInmuebleNew = em.merge(unaInmobiliariaInmuebleNew);
            }
            if (unLocadorOld != null && !unLocadorOld.equals(unLocadorNew)) {
                unLocadorOld.getInmuebles().remove(localComercial);
                unLocadorOld = em.merge(unLocadorOld);
            }
            if (unLocadorNew != null && !unLocadorNew.equals(unLocadorOld)) {
                unLocadorNew.getInmuebles().add(localComercial);
                unLocadorNew = em.merge(unLocadorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = localComercial.getId();
                if (findLocalComercial(id) == null) {
                    throw new NonexistentEntityException("The localComercial with id " + id + " no longer exists.");
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
            LocalComercial localComercial;
            try {
                localComercial = em.getReference(LocalComercial.class, id);
                localComercial.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The localComercial with id " + id + " no longer exists.", enfe);
            }
            Inmobiliaria unaInmobiliariaInmueble = localComercial.getUnaInmobiliariaInmueble();
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble.getInmuebles().remove(localComercial);
                unaInmobiliariaInmueble = em.merge(unaInmobiliariaInmueble);
            }
            Locador unLocador = localComercial.getUnLocador();
            if (unLocador != null) {
                unLocador.getInmuebles().remove(localComercial);
                unLocador = em.merge(unLocador);
            }
            em.remove(localComercial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LocalComercial> findLocalComercialEntities() {
        return findLocalComercialEntities(true, -1, -1);
    }

    public List<LocalComercial> findLocalComercialEntities(int maxResults, int firstResult) {
        return findLocalComercialEntities(false, maxResults, firstResult);
    }

    private List<LocalComercial> findLocalComercialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LocalComercial.class));
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

    public LocalComercial findLocalComercial(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LocalComercial.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocalComercialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LocalComercial> rt = cq.from(LocalComercial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
