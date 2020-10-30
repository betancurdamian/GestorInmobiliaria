/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.dao.exceptions.NonexistentEntityException;
import model.entity.ArancelEspecialServicio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entity.Inmobiliaria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class ArancelEspecialServicioJpaController implements Serializable {

    public ArancelEspecialServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ArancelEspecialServicio arancelEspecialServicio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaArancelEspecial = arancelEspecialServicio.getUnaInmobiliariaArancelEspecial();
            if (unaInmobiliariaArancelEspecial != null) {
                unaInmobiliariaArancelEspecial = em.getReference(unaInmobiliariaArancelEspecial.getClass(), unaInmobiliariaArancelEspecial.getId());
                arancelEspecialServicio.setUnaInmobiliariaArancelEspecial(unaInmobiliariaArancelEspecial);
            }
            em.persist(arancelEspecialServicio);
            if (unaInmobiliariaArancelEspecial != null) {
                unaInmobiliariaArancelEspecial.getArancelesEspeciales().add(arancelEspecialServicio);
                unaInmobiliariaArancelEspecial = em.merge(unaInmobiliariaArancelEspecial);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ArancelEspecialServicio arancelEspecialServicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArancelEspecialServicio persistentArancelEspecialServicio = em.find(ArancelEspecialServicio.class, arancelEspecialServicio.getId());
            Inmobiliaria unaInmobiliariaArancelEspecialOld = persistentArancelEspecialServicio.getUnaInmobiliariaArancelEspecial();
            Inmobiliaria unaInmobiliariaArancelEspecialNew = arancelEspecialServicio.getUnaInmobiliariaArancelEspecial();
            if (unaInmobiliariaArancelEspecialNew != null) {
                unaInmobiliariaArancelEspecialNew = em.getReference(unaInmobiliariaArancelEspecialNew.getClass(), unaInmobiliariaArancelEspecialNew.getId());
                arancelEspecialServicio.setUnaInmobiliariaArancelEspecial(unaInmobiliariaArancelEspecialNew);
            }
            arancelEspecialServicio = em.merge(arancelEspecialServicio);
            if (unaInmobiliariaArancelEspecialOld != null && !unaInmobiliariaArancelEspecialOld.equals(unaInmobiliariaArancelEspecialNew)) {
                unaInmobiliariaArancelEspecialOld.getArancelesEspeciales().remove(arancelEspecialServicio);
                unaInmobiliariaArancelEspecialOld = em.merge(unaInmobiliariaArancelEspecialOld);
            }
            if (unaInmobiliariaArancelEspecialNew != null && !unaInmobiliariaArancelEspecialNew.equals(unaInmobiliariaArancelEspecialOld)) {
                unaInmobiliariaArancelEspecialNew.getArancelesEspeciales().add(arancelEspecialServicio);
                unaInmobiliariaArancelEspecialNew = em.merge(unaInmobiliariaArancelEspecialNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = arancelEspecialServicio.getId();
                if (findArancelEspecialServicio(id) == null) {
                    throw new NonexistentEntityException("The arancelEspecialServicio with id " + id + " no longer exists.");
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
            ArancelEspecialServicio arancelEspecialServicio;
            try {
                arancelEspecialServicio = em.getReference(ArancelEspecialServicio.class, id);
                arancelEspecialServicio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The arancelEspecialServicio with id " + id + " no longer exists.", enfe);
            }
            Inmobiliaria unaInmobiliariaArancelEspecial = arancelEspecialServicio.getUnaInmobiliariaArancelEspecial();
            if (unaInmobiliariaArancelEspecial != null) {
                unaInmobiliariaArancelEspecial.getArancelesEspeciales().remove(arancelEspecialServicio);
                unaInmobiliariaArancelEspecial = em.merge(unaInmobiliariaArancelEspecial);
            }
            em.remove(arancelEspecialServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ArancelEspecialServicio> findArancelEspecialServicioEntities() {
        return findArancelEspecialServicioEntities(true, -1, -1);
    }

    public List<ArancelEspecialServicio> findArancelEspecialServicioEntities(int maxResults, int firstResult) {
        return findArancelEspecialServicioEntities(false, maxResults, firstResult);
    }

    private List<ArancelEspecialServicio> findArancelEspecialServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ArancelEspecialServicio.class));
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

    public ArancelEspecialServicio findArancelEspecialServicio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ArancelEspecialServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getArancelEspecialServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ArancelEspecialServicio> rt = cq.from(ArancelEspecialServicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
