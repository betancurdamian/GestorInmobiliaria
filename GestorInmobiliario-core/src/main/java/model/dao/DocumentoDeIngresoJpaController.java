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
import model.entity.DocumentoDeIngreso;
import model.entity.Locatario;
import model.entity.Garante;

/**
 *
 * @author Ariel
 */
public class DocumentoDeIngresoJpaController implements Serializable {

    public DocumentoDeIngresoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DocumentoDeIngreso documentoDeIngreso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Locatario unLocatario = documentoDeIngreso.getUnLocatario();
            if (unLocatario != null) {
                unLocatario = em.getReference(unLocatario.getClass(), unLocatario.getId());
                documentoDeIngreso.setUnLocatario(unLocatario);
            }
            Garante unGarante = documentoDeIngreso.getUnGarante();
            if (unGarante != null) {
                unGarante = em.getReference(unGarante.getClass(), unGarante.getId());
                documentoDeIngreso.setUnGarante(unGarante);
            }
            em.persist(documentoDeIngreso);
            if (unLocatario != null) {
                unLocatario.getComprobantesDeIngresosLocatarios().add(documentoDeIngreso);
                unLocatario = em.merge(unLocatario);
            }
            if (unGarante != null) {
                unGarante.getComprobantesDeIngresosGarantes().add(documentoDeIngreso);
                unGarante = em.merge(unGarante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DocumentoDeIngreso documentoDeIngreso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DocumentoDeIngreso persistentDocumentoDeIngreso = em.find(DocumentoDeIngreso.class, documentoDeIngreso.getId());
            Locatario unLocatarioOld = persistentDocumentoDeIngreso.getUnLocatario();
            Locatario unLocatarioNew = documentoDeIngreso.getUnLocatario();
            Garante unGaranteOld = persistentDocumentoDeIngreso.getUnGarante();
            Garante unGaranteNew = documentoDeIngreso.getUnGarante();
            if (unLocatarioNew != null) {
                unLocatarioNew = em.getReference(unLocatarioNew.getClass(), unLocatarioNew.getId());
                documentoDeIngreso.setUnLocatario(unLocatarioNew);
            }
            if (unGaranteNew != null) {
                unGaranteNew = em.getReference(unGaranteNew.getClass(), unGaranteNew.getId());
                documentoDeIngreso.setUnGarante(unGaranteNew);
            }
            documentoDeIngreso = em.merge(documentoDeIngreso);
            if (unLocatarioOld != null && !unLocatarioOld.equals(unLocatarioNew)) {
                unLocatarioOld.getComprobantesDeIngresosLocatarios().remove(documentoDeIngreso);
                unLocatarioOld = em.merge(unLocatarioOld);
            }
            if (unLocatarioNew != null && !unLocatarioNew.equals(unLocatarioOld)) {
                unLocatarioNew.getComprobantesDeIngresosLocatarios().add(documentoDeIngreso);
                unLocatarioNew = em.merge(unLocatarioNew);
            }
            if (unGaranteOld != null && !unGaranteOld.equals(unGaranteNew)) {
                unGaranteOld.getComprobantesDeIngresosGarantes().remove(documentoDeIngreso);
                unGaranteOld = em.merge(unGaranteOld);
            }
            if (unGaranteNew != null && !unGaranteNew.equals(unGaranteOld)) {
                unGaranteNew.getComprobantesDeIngresosGarantes().add(documentoDeIngreso);
                unGaranteNew = em.merge(unGaranteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = documentoDeIngreso.getId();
                if (findDocumentoDeIngreso(id) == null) {
                    throw new NonexistentEntityException("The documentoDeIngreso with id " + id + " no longer exists.");
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
            DocumentoDeIngreso documentoDeIngreso;
            try {
                documentoDeIngreso = em.getReference(DocumentoDeIngreso.class, id);
                documentoDeIngreso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The documentoDeIngreso with id " + id + " no longer exists.", enfe);
            }
            Locatario unLocatario = documentoDeIngreso.getUnLocatario();
            if (unLocatario != null) {
                unLocatario.getComprobantesDeIngresosLocatarios().remove(documentoDeIngreso);
                unLocatario = em.merge(unLocatario);
            }
            Garante unGarante = documentoDeIngreso.getUnGarante();
            if (unGarante != null) {
                unGarante.getComprobantesDeIngresosGarantes().remove(documentoDeIngreso);
                unGarante = em.merge(unGarante);
            }
            em.remove(documentoDeIngreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DocumentoDeIngreso> findDocumentoDeIngresoEntities() {
        return findDocumentoDeIngresoEntities(true, -1, -1);
    }

    public List<DocumentoDeIngreso> findDocumentoDeIngresoEntities(int maxResults, int firstResult) {
        return findDocumentoDeIngresoEntities(false, maxResults, firstResult);
    }

    private List<DocumentoDeIngreso> findDocumentoDeIngresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DocumentoDeIngreso.class));
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

    public DocumentoDeIngreso findDocumentoDeIngreso(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DocumentoDeIngreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocumentoDeIngresoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DocumentoDeIngreso> rt = cq.from(DocumentoDeIngreso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
