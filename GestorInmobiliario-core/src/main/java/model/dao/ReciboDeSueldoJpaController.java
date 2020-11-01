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
import model.entity.Locatario;
import model.entity.Garante;
import model.entity.ReciboDeSueldo;

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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Locatario unLocatario = reciboDeSueldo.getUnLocatario();
            if (unLocatario != null) {
                unLocatario = em.getReference(unLocatario.getClass(), unLocatario.getId());
                reciboDeSueldo.setUnLocatario(unLocatario);
            }
            Garante unGarante = reciboDeSueldo.getUnGarante();
            if (unGarante != null) {
                unGarante = em.getReference(unGarante.getClass(), unGarante.getId());
                reciboDeSueldo.setUnGarante(unGarante);
            }
            em.persist(reciboDeSueldo);
            if (unLocatario != null) {
                unLocatario.getComprobantesDeIngresosLocatarios().add(reciboDeSueldo);
                unLocatario = em.merge(unLocatario);
            }
            if (unGarante != null) {
                unGarante.getComprobantesDeIngresosGarantes().add(reciboDeSueldo);
                unGarante = em.merge(unGarante);
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
            Locatario unLocatarioOld = persistentReciboDeSueldo.getUnLocatario();
            Locatario unLocatarioNew = reciboDeSueldo.getUnLocatario();
            Garante unGaranteOld = persistentReciboDeSueldo.getUnGarante();
            Garante unGaranteNew = reciboDeSueldo.getUnGarante();
            if (unLocatarioNew != null) {
                unLocatarioNew = em.getReference(unLocatarioNew.getClass(), unLocatarioNew.getId());
                reciboDeSueldo.setUnLocatario(unLocatarioNew);
            }
            if (unGaranteNew != null) {
                unGaranteNew = em.getReference(unGaranteNew.getClass(), unGaranteNew.getId());
                reciboDeSueldo.setUnGarante(unGaranteNew);
            }
            reciboDeSueldo = em.merge(reciboDeSueldo);
            if (unLocatarioOld != null && !unLocatarioOld.equals(unLocatarioNew)) {
                unLocatarioOld.getComprobantesDeIngresosLocatarios().remove(reciboDeSueldo);
                unLocatarioOld = em.merge(unLocatarioOld);
            }
            if (unLocatarioNew != null && !unLocatarioNew.equals(unLocatarioOld)) {
                unLocatarioNew.getComprobantesDeIngresosLocatarios().add(reciboDeSueldo);
                unLocatarioNew = em.merge(unLocatarioNew);
            }
            if (unGaranteOld != null && !unGaranteOld.equals(unGaranteNew)) {
                unGaranteOld.getComprobantesDeIngresosGarantes().remove(reciboDeSueldo);
                unGaranteOld = em.merge(unGaranteOld);
            }
            if (unGaranteNew != null && !unGaranteNew.equals(unGaranteOld)) {
                unGaranteNew.getComprobantesDeIngresosGarantes().add(reciboDeSueldo);
                unGaranteNew = em.merge(unGaranteNew);
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
            Locatario unLocatario = reciboDeSueldo.getUnLocatario();
            if (unLocatario != null) {
                unLocatario.getComprobantesDeIngresosLocatarios().remove(reciboDeSueldo);
                unLocatario = em.merge(unLocatario);
            }
            Garante unGarante = reciboDeSueldo.getUnGarante();
            if (unGarante != null) {
                unGarante.getComprobantesDeIngresosGarantes().remove(reciboDeSueldo);
                unGarante = em.merge(unGarante);
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
