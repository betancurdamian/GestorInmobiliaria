/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.dao;

import com.betancur.gestorinmobiliario.model.dao.exceptions.IllegalOrphanException;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import com.betancur.gestorinmobiliario.model.entity.Comision;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.betancur.gestorinmobiliario.model.entity.Contrato;
import com.betancur.gestorinmobiliario.model.entity.LineaDeComision;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
        if (comision.getLinesasDecomisiones() == null) {
            comision.setLinesasDecomisiones(new ArrayList<LineaDeComision>());
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
            List<LineaDeComision> attachedLinesasDecomisiones = new ArrayList<LineaDeComision>();
            for (LineaDeComision linesasDecomisionesLineaDeComisionToAttach : comision.getLinesasDecomisiones()) {
                linesasDecomisionesLineaDeComisionToAttach = em.getReference(linesasDecomisionesLineaDeComisionToAttach.getClass(), linesasDecomisionesLineaDeComisionToAttach.getId());
                attachedLinesasDecomisiones.add(linesasDecomisionesLineaDeComisionToAttach);
            }
            comision.setLinesasDecomisiones(attachedLinesasDecomisiones);
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
            for (LineaDeComision linesasDecomisionesLineaDeComision : comision.getLinesasDecomisiones()) {
                Comision oldUnaComisionOfLinesasDecomisionesLineaDeComision = linesasDecomisionesLineaDeComision.getUnaComision();
                linesasDecomisionesLineaDeComision.setUnaComision(comision);
                linesasDecomisionesLineaDeComision = em.merge(linesasDecomisionesLineaDeComision);
                if (oldUnaComisionOfLinesasDecomisionesLineaDeComision != null) {
                    oldUnaComisionOfLinesasDecomisionesLineaDeComision.getLinesasDecomisiones().remove(linesasDecomisionesLineaDeComision);
                    oldUnaComisionOfLinesasDecomisionesLineaDeComision = em.merge(oldUnaComisionOfLinesasDecomisionesLineaDeComision);
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
            List<LineaDeComision> linesasDecomisionesOld = persistentComision.getLinesasDecomisiones();
            List<LineaDeComision> linesasDecomisionesNew = comision.getLinesasDecomisiones();
            List<String> illegalOrphanMessages = null;
            for (LineaDeComision linesasDecomisionesOldLineaDeComision : linesasDecomisionesOld) {
                if (!linesasDecomisionesNew.contains(linesasDecomisionesOldLineaDeComision)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain LineaDeComision " + linesasDecomisionesOldLineaDeComision + " since its unaComision field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (unContratoNew != null) {
                unContratoNew = em.getReference(unContratoNew.getClass(), unContratoNew.getId());
                comision.setUnContrato(unContratoNew);
            }
            List<LineaDeComision> attachedLinesasDecomisionesNew = new ArrayList<LineaDeComision>();
            for (LineaDeComision linesasDecomisionesNewLineaDeComisionToAttach : linesasDecomisionesNew) {
                linesasDecomisionesNewLineaDeComisionToAttach = em.getReference(linesasDecomisionesNewLineaDeComisionToAttach.getClass(), linesasDecomisionesNewLineaDeComisionToAttach.getId());
                attachedLinesasDecomisionesNew.add(linesasDecomisionesNewLineaDeComisionToAttach);
            }
            linesasDecomisionesNew = attachedLinesasDecomisionesNew;
            comision.setLinesasDecomisiones(linesasDecomisionesNew);
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
            for (LineaDeComision linesasDecomisionesNewLineaDeComision : linesasDecomisionesNew) {
                if (!linesasDecomisionesOld.contains(linesasDecomisionesNewLineaDeComision)) {
                    Comision oldUnaComisionOfLinesasDecomisionesNewLineaDeComision = linesasDecomisionesNewLineaDeComision.getUnaComision();
                    linesasDecomisionesNewLineaDeComision.setUnaComision(comision);
                    linesasDecomisionesNewLineaDeComision = em.merge(linesasDecomisionesNewLineaDeComision);
                    if (oldUnaComisionOfLinesasDecomisionesNewLineaDeComision != null && !oldUnaComisionOfLinesasDecomisionesNewLineaDeComision.equals(comision)) {
                        oldUnaComisionOfLinesasDecomisionesNewLineaDeComision.getLinesasDecomisiones().remove(linesasDecomisionesNewLineaDeComision);
                        oldUnaComisionOfLinesasDecomisionesNewLineaDeComision = em.merge(oldUnaComisionOfLinesasDecomisionesNewLineaDeComision);
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
            List<LineaDeComision> linesasDecomisionesOrphanCheck = comision.getLinesasDecomisiones();
            for (LineaDeComision linesasDecomisionesOrphanCheckLineaDeComision : linesasDecomisionesOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Comision (" + comision + ") cannot be destroyed since the LineaDeComision " + linesasDecomisionesOrphanCheckLineaDeComision + " in its linesasDecomisiones field has a non-nullable unaComision field.");
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
