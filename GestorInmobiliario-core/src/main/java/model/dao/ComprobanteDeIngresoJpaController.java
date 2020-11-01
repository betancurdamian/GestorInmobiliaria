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
import model.entity.ComprobanteDeIngreso;
import model.entity.Locatario;
import model.entity.Garante;

/**
 *
 * @author Ariel
 */
public class ComprobanteDeIngresoJpaController implements Serializable {

    public ComprobanteDeIngresoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ComprobanteDeIngreso comprobanteDeIngreso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Locatario unLocatario = comprobanteDeIngreso.getUnLocatario();
            if (unLocatario != null) {
                unLocatario = em.getReference(unLocatario.getClass(), unLocatario.getId());
                comprobanteDeIngreso.setUnLocatario(unLocatario);
            }
            Garante unGarante = comprobanteDeIngreso.getUnGarante();
            if (unGarante != null) {
                unGarante = em.getReference(unGarante.getClass(), unGarante.getId());
                comprobanteDeIngreso.setUnGarante(unGarante);
            }
            em.persist(comprobanteDeIngreso);
            if (unLocatario != null) {
                unLocatario.getComprobantesDeIngresosLocatarios().add(comprobanteDeIngreso);
                unLocatario = em.merge(unLocatario);
            }
            if (unGarante != null) {
                unGarante.getComprobantesDeIngresosGarantes().add(comprobanteDeIngreso);
                unGarante = em.merge(unGarante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ComprobanteDeIngreso comprobanteDeIngreso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ComprobanteDeIngreso persistentComprobanteDeIngreso = em.find(ComprobanteDeIngreso.class, comprobanteDeIngreso.getId());
            Locatario unLocatarioOld = persistentComprobanteDeIngreso.getUnLocatario();
            Locatario unLocatarioNew = comprobanteDeIngreso.getUnLocatario();
            Garante unGaranteOld = persistentComprobanteDeIngreso.getUnGarante();
            Garante unGaranteNew = comprobanteDeIngreso.getUnGarante();
            if (unLocatarioNew != null) {
                unLocatarioNew = em.getReference(unLocatarioNew.getClass(), unLocatarioNew.getId());
                comprobanteDeIngreso.setUnLocatario(unLocatarioNew);
            }
            if (unGaranteNew != null) {
                unGaranteNew = em.getReference(unGaranteNew.getClass(), unGaranteNew.getId());
                comprobanteDeIngreso.setUnGarante(unGaranteNew);
            }
            comprobanteDeIngreso = em.merge(comprobanteDeIngreso);
            if (unLocatarioOld != null && !unLocatarioOld.equals(unLocatarioNew)) {
                unLocatarioOld.getComprobantesDeIngresosLocatarios().remove(comprobanteDeIngreso);
                unLocatarioOld = em.merge(unLocatarioOld);
            }
            if (unLocatarioNew != null && !unLocatarioNew.equals(unLocatarioOld)) {
                unLocatarioNew.getComprobantesDeIngresosLocatarios().add(comprobanteDeIngreso);
                unLocatarioNew = em.merge(unLocatarioNew);
            }
            if (unGaranteOld != null && !unGaranteOld.equals(unGaranteNew)) {
                unGaranteOld.getComprobantesDeIngresosGarantes().remove(comprobanteDeIngreso);
                unGaranteOld = em.merge(unGaranteOld);
            }
            if (unGaranteNew != null && !unGaranteNew.equals(unGaranteOld)) {
                unGaranteNew.getComprobantesDeIngresosGarantes().add(comprobanteDeIngreso);
                unGaranteNew = em.merge(unGaranteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = comprobanteDeIngreso.getId();
                if (findComprobanteDeIngreso(id) == null) {
                    throw new NonexistentEntityException("The comprobanteDeIngreso with id " + id + " no longer exists.");
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
            ComprobanteDeIngreso comprobanteDeIngreso;
            try {
                comprobanteDeIngreso = em.getReference(ComprobanteDeIngreso.class, id);
                comprobanteDeIngreso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comprobanteDeIngreso with id " + id + " no longer exists.", enfe);
            }
            Locatario unLocatario = comprobanteDeIngreso.getUnLocatario();
            if (unLocatario != null) {
                unLocatario.getComprobantesDeIngresosLocatarios().remove(comprobanteDeIngreso);
                unLocatario = em.merge(unLocatario);
            }
            Garante unGarante = comprobanteDeIngreso.getUnGarante();
            if (unGarante != null) {
                unGarante.getComprobantesDeIngresosGarantes().remove(comprobanteDeIngreso);
                unGarante = em.merge(unGarante);
            }
            em.remove(comprobanteDeIngreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ComprobanteDeIngreso> findComprobanteDeIngresoEntities() {
        return findComprobanteDeIngresoEntities(true, -1, -1);
    }

    public List<ComprobanteDeIngreso> findComprobanteDeIngresoEntities(int maxResults, int firstResult) {
        return findComprobanteDeIngresoEntities(false, maxResults, firstResult);
    }

    private List<ComprobanteDeIngreso> findComprobanteDeIngresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ComprobanteDeIngreso.class));
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

    public ComprobanteDeIngreso findComprobanteDeIngreso(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ComprobanteDeIngreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getComprobanteDeIngresoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ComprobanteDeIngreso> rt = cq.from(ComprobanteDeIngreso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
