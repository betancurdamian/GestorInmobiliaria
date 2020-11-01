/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entity.Contrato;
import model.entity.LineaDeComision;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.dao.exceptions.IllegalOrphanException;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Comision;

/**
 *
 * @author Ariel
 */
public class ComisionJpaController implements Serializable {

    public ComisionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comision comision) {
        if (comision.getLinesasDeComisiones() == null) {
            comision.setLinesasDeComisiones(new ArrayList<LineaDeComision>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contrato unContrato = comision.getUnContrato();
            if (unContrato != null) {
                unContrato = em.getReference(unContrato.getClass(), unContrato.getId());
                comision.setUnContrato(unContrato);
            }
            List<LineaDeComision> attachedLinesasDeComisiones = new ArrayList<LineaDeComision>();
            for (LineaDeComision linesasDeComisionesLineaDeComisionToAttach : comision.getLinesasDeComisiones()) {
                linesasDeComisionesLineaDeComisionToAttach = em.getReference(linesasDeComisionesLineaDeComisionToAttach.getClass(), linesasDeComisionesLineaDeComisionToAttach.getId());
                attachedLinesasDeComisiones.add(linesasDeComisionesLineaDeComisionToAttach);
            }
            comision.setLinesasDeComisiones(attachedLinesasDeComisiones);
            em.persist(comision);
            if (unContrato != null) {
                Comision oldUnaComisionOfUnContrato = unContrato.getUnaComision();
                if (oldUnaComisionOfUnContrato != null) {
                    oldUnaComisionOfUnContrato.setUnContrato(null);
                    oldUnaComisionOfUnContrato = em.merge(oldUnaComisionOfUnContrato);
                }
                unContrato.setUnaComision(comision);
                unContrato = em.merge(unContrato);
            }
            for (LineaDeComision linesasDeComisionesLineaDeComision : comision.getLinesasDeComisiones()) {
                Comision oldUnaComisionOfLinesasDeComisionesLineaDeComision = linesasDeComisionesLineaDeComision.getUnaComision();
                linesasDeComisionesLineaDeComision.setUnaComision(comision);
                linesasDeComisionesLineaDeComision = em.merge(linesasDeComisionesLineaDeComision);
                if (oldUnaComisionOfLinesasDeComisionesLineaDeComision != null) {
                    oldUnaComisionOfLinesasDeComisionesLineaDeComision.getLinesasDeComisiones().remove(linesasDeComisionesLineaDeComision);
                    oldUnaComisionOfLinesasDeComisionesLineaDeComision = em.merge(oldUnaComisionOfLinesasDeComisionesLineaDeComision);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comision comision) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comision persistentComision = em.find(Comision.class, comision.getId());
            Contrato unContratoOld = persistentComision.getUnContrato();
            Contrato unContratoNew = comision.getUnContrato();
            List<LineaDeComision> linesasDeComisionesOld = persistentComision.getLinesasDeComisiones();
            List<LineaDeComision> linesasDeComisionesNew = comision.getLinesasDeComisiones();
            List<String> illegalOrphanMessages = null;
            for (LineaDeComision linesasDeComisionesOldLineaDeComision : linesasDeComisionesOld) {
                if (!linesasDeComisionesNew.contains(linesasDeComisionesOldLineaDeComision)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain LineaDeComision " + linesasDeComisionesOldLineaDeComision + " since its unaComision field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (unContratoNew != null) {
                unContratoNew = em.getReference(unContratoNew.getClass(), unContratoNew.getId());
                comision.setUnContrato(unContratoNew);
            }
            List<LineaDeComision> attachedLinesasDeComisionesNew = new ArrayList<LineaDeComision>();
            for (LineaDeComision linesasDeComisionesNewLineaDeComisionToAttach : linesasDeComisionesNew) {
                linesasDeComisionesNewLineaDeComisionToAttach = em.getReference(linesasDeComisionesNewLineaDeComisionToAttach.getClass(), linesasDeComisionesNewLineaDeComisionToAttach.getId());
                attachedLinesasDeComisionesNew.add(linesasDeComisionesNewLineaDeComisionToAttach);
            }
            linesasDeComisionesNew = attachedLinesasDeComisionesNew;
            comision.setLinesasDeComisiones(linesasDeComisionesNew);
            comision = em.merge(comision);
            if (unContratoOld != null && !unContratoOld.equals(unContratoNew)) {
                unContratoOld.setUnaComision(null);
                unContratoOld = em.merge(unContratoOld);
            }
            if (unContratoNew != null && !unContratoNew.equals(unContratoOld)) {
                Comision oldUnaComisionOfUnContrato = unContratoNew.getUnaComision();
                if (oldUnaComisionOfUnContrato != null) {
                    oldUnaComisionOfUnContrato.setUnContrato(null);
                    oldUnaComisionOfUnContrato = em.merge(oldUnaComisionOfUnContrato);
                }
                unContratoNew.setUnaComision(comision);
                unContratoNew = em.merge(unContratoNew);
            }
            for (LineaDeComision linesasDeComisionesNewLineaDeComision : linesasDeComisionesNew) {
                if (!linesasDeComisionesOld.contains(linesasDeComisionesNewLineaDeComision)) {
                    Comision oldUnaComisionOfLinesasDeComisionesNewLineaDeComision = linesasDeComisionesNewLineaDeComision.getUnaComision();
                    linesasDeComisionesNewLineaDeComision.setUnaComision(comision);
                    linesasDeComisionesNewLineaDeComision = em.merge(linesasDeComisionesNewLineaDeComision);
                    if (oldUnaComisionOfLinesasDeComisionesNewLineaDeComision != null && !oldUnaComisionOfLinesasDeComisionesNewLineaDeComision.equals(comision)) {
                        oldUnaComisionOfLinesasDeComisionesNewLineaDeComision.getLinesasDeComisiones().remove(linesasDeComisionesNewLineaDeComision);
                        oldUnaComisionOfLinesasDeComisionesNewLineaDeComision = em.merge(oldUnaComisionOfLinesasDeComisionesNewLineaDeComision);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = comision.getId();
                if (findComision(id) == null) {
                    throw new NonexistentEntityException("The comision with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comision comision;
            try {
                comision = em.getReference(Comision.class, id);
                comision.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comision with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<LineaDeComision> linesasDeComisionesOrphanCheck = comision.getLinesasDeComisiones();
            for (LineaDeComision linesasDeComisionesOrphanCheckLineaDeComision : linesasDeComisionesOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Comision (" + comision + ") cannot be destroyed since the LineaDeComision " + linesasDeComisionesOrphanCheckLineaDeComision + " in its linesasDeComisiones field has a non-nullable unaComision field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Contrato unContrato = comision.getUnContrato();
            if (unContrato != null) {
                unContrato.setUnaComision(null);
                unContrato = em.merge(unContrato);
            }
            em.remove(comision);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comision> findComisionEntities() {
        return findComisionEntities(true, -1, -1);
    }

    public List<Comision> findComisionEntities(int maxResults, int firstResult) {
        return findComisionEntities(false, maxResults, firstResult);
    }

    private List<Comision> findComisionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comision.class));
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

    public Comision findComision(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comision.class, id);
        } finally {
            em.close();
        }
    }

    public int getComisionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comision> rt = cq.from(Comision.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
