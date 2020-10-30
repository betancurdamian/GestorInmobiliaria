/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.dao.exceptions.NonexistentEntityException;
import model.entity.DocumentoDeIngreso;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entity.Locatario;
import java.util.ArrayList;
import java.util.List;
import model.entity.Garante;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
        if (documentoDeIngreso.getLocatarios() == null) {
            documentoDeIngreso.setLocatarios(new ArrayList<Locatario>());
        }
        if (documentoDeIngreso.getGarantes() == null) {
            documentoDeIngreso.setGarantes(new ArrayList<Garante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Locatario> attachedLocatarios = new ArrayList<Locatario>();
            for (Locatario locatariosLocatarioToAttach : documentoDeIngreso.getLocatarios()) {
                locatariosLocatarioToAttach = em.getReference(locatariosLocatarioToAttach.getClass(), locatariosLocatarioToAttach.getId());
                attachedLocatarios.add(locatariosLocatarioToAttach);
            }
            documentoDeIngreso.setLocatarios(attachedLocatarios);
            List<Garante> attachedGarantes = new ArrayList<Garante>();
            for (Garante garantesGaranteToAttach : documentoDeIngreso.getGarantes()) {
                garantesGaranteToAttach = em.getReference(garantesGaranteToAttach.getClass(), garantesGaranteToAttach.getId());
                attachedGarantes.add(garantesGaranteToAttach);
            }
            documentoDeIngreso.setGarantes(attachedGarantes);
            em.persist(documentoDeIngreso);
            for (Locatario locatariosLocatario : documentoDeIngreso.getLocatarios()) {
                locatariosLocatario.getComprobantesDeIngresosLocatarios().add(documentoDeIngreso);
                locatariosLocatario = em.merge(locatariosLocatario);
            }
            for (Garante garantesGarante : documentoDeIngreso.getGarantes()) {
                garantesGarante.getComprobantesDeIngresosGarantes().add(documentoDeIngreso);
                garantesGarante = em.merge(garantesGarante);
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
            List<Locatario> locatariosOld = persistentDocumentoDeIngreso.getLocatarios();
            List<Locatario> locatariosNew = documentoDeIngreso.getLocatarios();
            List<Garante> garantesOld = persistentDocumentoDeIngreso.getGarantes();
            List<Garante> garantesNew = documentoDeIngreso.getGarantes();
            List<Locatario> attachedLocatariosNew = new ArrayList<Locatario>();
            for (Locatario locatariosNewLocatarioToAttach : locatariosNew) {
                locatariosNewLocatarioToAttach = em.getReference(locatariosNewLocatarioToAttach.getClass(), locatariosNewLocatarioToAttach.getId());
                attachedLocatariosNew.add(locatariosNewLocatarioToAttach);
            }
            locatariosNew = attachedLocatariosNew;
            documentoDeIngreso.setLocatarios(locatariosNew);
            List<Garante> attachedGarantesNew = new ArrayList<Garante>();
            for (Garante garantesNewGaranteToAttach : garantesNew) {
                garantesNewGaranteToAttach = em.getReference(garantesNewGaranteToAttach.getClass(), garantesNewGaranteToAttach.getId());
                attachedGarantesNew.add(garantesNewGaranteToAttach);
            }
            garantesNew = attachedGarantesNew;
            documentoDeIngreso.setGarantes(garantesNew);
            documentoDeIngreso = em.merge(documentoDeIngreso);
            for (Locatario locatariosOldLocatario : locatariosOld) {
                if (!locatariosNew.contains(locatariosOldLocatario)) {
                    locatariosOldLocatario.getComprobantesDeIngresosLocatarios().remove(documentoDeIngreso);
                    locatariosOldLocatario = em.merge(locatariosOldLocatario);
                }
            }
            for (Locatario locatariosNewLocatario : locatariosNew) {
                if (!locatariosOld.contains(locatariosNewLocatario)) {
                    locatariosNewLocatario.getComprobantesDeIngresosLocatarios().add(documentoDeIngreso);
                    locatariosNewLocatario = em.merge(locatariosNewLocatario);
                }
            }
            for (Garante garantesOldGarante : garantesOld) {
                if (!garantesNew.contains(garantesOldGarante)) {
                    garantesOldGarante.getComprobantesDeIngresosGarantes().remove(documentoDeIngreso);
                    garantesOldGarante = em.merge(garantesOldGarante);
                }
            }
            for (Garante garantesNewGarante : garantesNew) {
                if (!garantesOld.contains(garantesNewGarante)) {
                    garantesNewGarante.getComprobantesDeIngresosGarantes().add(documentoDeIngreso);
                    garantesNewGarante = em.merge(garantesNewGarante);
                }
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
            List<Locatario> locatarios = documentoDeIngreso.getLocatarios();
            for (Locatario locatariosLocatario : locatarios) {
                locatariosLocatario.getComprobantesDeIngresosLocatarios().remove(documentoDeIngreso);
                locatariosLocatario = em.merge(locatariosLocatario);
            }
            List<Garante> garantes = documentoDeIngreso.getGarantes();
            for (Garante garantesGarante : garantes) {
                garantesGarante.getComprobantesDeIngresosGarantes().remove(documentoDeIngreso);
                garantesGarante = em.merge(garantesGarante);
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
