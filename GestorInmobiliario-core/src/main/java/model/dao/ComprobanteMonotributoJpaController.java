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
import model.entity.ComprobanteMonotributo;
import model.entity.Locatario;
import model.entity.Garante;

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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Locatario unLocatario = comprobanteMonotributo.getUnLocatario();
            if (unLocatario != null) {
                unLocatario = em.getReference(unLocatario.getClass(), unLocatario.getId());
                comprobanteMonotributo.setUnLocatario(unLocatario);
            }
            Garante unGarante = comprobanteMonotributo.getUnGarante();
            if (unGarante != null) {
                unGarante = em.getReference(unGarante.getClass(), unGarante.getId());
                comprobanteMonotributo.setUnGarante(unGarante);
            }
            em.persist(comprobanteMonotributo);
            if (unLocatario != null) {
                unLocatario.getComprobantesDeIngresosLocatarios().add(comprobanteMonotributo);
                unLocatario = em.merge(unLocatario);
            }
            if (unGarante != null) {
                unGarante.getComprobantesDeIngresosGarantes().add(comprobanteMonotributo);
                unGarante = em.merge(unGarante);
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
            Locatario unLocatarioOld = persistentComprobanteMonotributo.getUnLocatario();
            Locatario unLocatarioNew = comprobanteMonotributo.getUnLocatario();
            Garante unGaranteOld = persistentComprobanteMonotributo.getUnGarante();
            Garante unGaranteNew = comprobanteMonotributo.getUnGarante();
            if (unLocatarioNew != null) {
                unLocatarioNew = em.getReference(unLocatarioNew.getClass(), unLocatarioNew.getId());
                comprobanteMonotributo.setUnLocatario(unLocatarioNew);
            }
            if (unGaranteNew != null) {
                unGaranteNew = em.getReference(unGaranteNew.getClass(), unGaranteNew.getId());
                comprobanteMonotributo.setUnGarante(unGaranteNew);
            }
            comprobanteMonotributo = em.merge(comprobanteMonotributo);
            if (unLocatarioOld != null && !unLocatarioOld.equals(unLocatarioNew)) {
                unLocatarioOld.getComprobantesDeIngresosLocatarios().remove(comprobanteMonotributo);
                unLocatarioOld = em.merge(unLocatarioOld);
            }
            if (unLocatarioNew != null && !unLocatarioNew.equals(unLocatarioOld)) {
                unLocatarioNew.getComprobantesDeIngresosLocatarios().add(comprobanteMonotributo);
                unLocatarioNew = em.merge(unLocatarioNew);
            }
            if (unGaranteOld != null && !unGaranteOld.equals(unGaranteNew)) {
                unGaranteOld.getComprobantesDeIngresosGarantes().remove(comprobanteMonotributo);
                unGaranteOld = em.merge(unGaranteOld);
            }
            if (unGaranteNew != null && !unGaranteNew.equals(unGaranteOld)) {
                unGaranteNew.getComprobantesDeIngresosGarantes().add(comprobanteMonotributo);
                unGaranteNew = em.merge(unGaranteNew);
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
            Locatario unLocatario = comprobanteMonotributo.getUnLocatario();
            if (unLocatario != null) {
                unLocatario.getComprobantesDeIngresosLocatarios().remove(comprobanteMonotributo);
                unLocatario = em.merge(unLocatario);
            }
            Garante unGarante = comprobanteMonotributo.getUnGarante();
            if (unGarante != null) {
                unGarante.getComprobantesDeIngresosGarantes().remove(comprobanteMonotributo);
                unGarante = em.merge(unGarante);
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
