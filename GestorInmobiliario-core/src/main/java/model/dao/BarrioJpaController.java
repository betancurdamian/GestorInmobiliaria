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
import model.entity.Barrio;
import model.entity.Localidad;

/**
 *
 * @author Ariel
 */
public class BarrioJpaController implements Serializable {

    public BarrioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Barrio barrio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Localidad unaLocalidad = barrio.getUnaLocalidad();
            if (unaLocalidad != null) {
                unaLocalidad = em.getReference(unaLocalidad.getClass(), unaLocalidad.getId());
                barrio.setUnaLocalidad(unaLocalidad);
            }
            em.persist(barrio);
            if (unaLocalidad != null) {
                unaLocalidad.getBarrios().add(barrio);
                unaLocalidad = em.merge(unaLocalidad);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Barrio barrio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Barrio persistentBarrio = em.find(Barrio.class, barrio.getId());
            Localidad unaLocalidadOld = persistentBarrio.getUnaLocalidad();
            Localidad unaLocalidadNew = barrio.getUnaLocalidad();
            if (unaLocalidadNew != null) {
                unaLocalidadNew = em.getReference(unaLocalidadNew.getClass(), unaLocalidadNew.getId());
                barrio.setUnaLocalidad(unaLocalidadNew);
            }
            barrio = em.merge(barrio);
            if (unaLocalidadOld != null && !unaLocalidadOld.equals(unaLocalidadNew)) {
                unaLocalidadOld.getBarrios().remove(barrio);
                unaLocalidadOld = em.merge(unaLocalidadOld);
            }
            if (unaLocalidadNew != null && !unaLocalidadNew.equals(unaLocalidadOld)) {
                unaLocalidadNew.getBarrios().add(barrio);
                unaLocalidadNew = em.merge(unaLocalidadNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = barrio.getId();
                if (findBarrio(id) == null) {
                    throw new NonexistentEntityException("The barrio with id " + id + " no longer exists.");
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
            Barrio barrio;
            try {
                barrio = em.getReference(Barrio.class, id);
                barrio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The barrio with id " + id + " no longer exists.", enfe);
            }
            Localidad unaLocalidad = barrio.getUnaLocalidad();
            if (unaLocalidad != null) {
                unaLocalidad.getBarrios().remove(barrio);
                unaLocalidad = em.merge(unaLocalidad);
            }
            em.remove(barrio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Barrio> findBarrioEntities() {
        return findBarrioEntities(true, -1, -1);
    }

    public List<Barrio> findBarrioEntities(int maxResults, int firstResult) {
        return findBarrioEntities(false, maxResults, firstResult);
    }

    private List<Barrio> findBarrioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Barrio.class));
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

    public Barrio findBarrio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Barrio.class, id);
        } finally {
            em.close();
        }
    }

    public int getBarrioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Barrio> rt = cq.from(Barrio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
