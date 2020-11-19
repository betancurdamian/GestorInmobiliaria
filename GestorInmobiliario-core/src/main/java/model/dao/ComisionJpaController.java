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
        if (comision.getLineasDeComisiones() == null) {
            comision.setLineasDeComisiones(new ArrayList<LineaDeComision>());
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
            List<LineaDeComision> attachedLineasDeComisiones = new ArrayList<LineaDeComision>();
            for (LineaDeComision lineasDeComisionesLineaDeComisionToAttach : comision.getLineasDeComisiones()) {
                lineasDeComisionesLineaDeComisionToAttach = em.getReference(lineasDeComisionesLineaDeComisionToAttach.getClass(), lineasDeComisionesLineaDeComisionToAttach.getId());
                attachedLineasDeComisiones.add(lineasDeComisionesLineaDeComisionToAttach);
            }
            comision.setLineasDeComisiones(attachedLineasDeComisiones);
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
            for (LineaDeComision lineasDeComisionesLineaDeComision : comision.getLineasDeComisiones()) {
                Comision oldUnaComisionOfLineasDeComisionesLineaDeComision = lineasDeComisionesLineaDeComision.getUnaComision();
                lineasDeComisionesLineaDeComision.setUnaComision(comision);
                lineasDeComisionesLineaDeComision = em.merge(lineasDeComisionesLineaDeComision);
                if (oldUnaComisionOfLineasDeComisionesLineaDeComision != null) {
                    oldUnaComisionOfLineasDeComisionesLineaDeComision.getLineasDeComisiones().remove(lineasDeComisionesLineaDeComision);
                    oldUnaComisionOfLineasDeComisionesLineaDeComision = em.merge(oldUnaComisionOfLineasDeComisionesLineaDeComision);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comision comision) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comision persistentComision = em.find(Comision.class, comision.getId());
            Contrato unContratoOld = persistentComision.getUnContrato();
            Contrato unContratoNew = comision.getUnContrato();
            List<LineaDeComision> lineasDeComisionesOld = persistentComision.getLineasDeComisiones();
            List<LineaDeComision> lineasDeComisionesNew = comision.getLineasDeComisiones();
            if (unContratoNew != null) {
                unContratoNew = em.getReference(unContratoNew.getClass(), unContratoNew.getId());
                comision.setUnContrato(unContratoNew);
            }
            List<LineaDeComision> attachedLineasDeComisionesNew = new ArrayList<LineaDeComision>();
            for (LineaDeComision lineasDeComisionesNewLineaDeComisionToAttach : lineasDeComisionesNew) {
                lineasDeComisionesNewLineaDeComisionToAttach = em.getReference(lineasDeComisionesNewLineaDeComisionToAttach.getClass(), lineasDeComisionesNewLineaDeComisionToAttach.getId());
                attachedLineasDeComisionesNew.add(lineasDeComisionesNewLineaDeComisionToAttach);
            }
            lineasDeComisionesNew = attachedLineasDeComisionesNew;
            comision.setLineasDeComisiones(lineasDeComisionesNew);
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
            for (LineaDeComision lineasDeComisionesOldLineaDeComision : lineasDeComisionesOld) {
                if (!lineasDeComisionesNew.contains(lineasDeComisionesOldLineaDeComision)) {
                    lineasDeComisionesOldLineaDeComision.setUnaComision(null);
                    lineasDeComisionesOldLineaDeComision = em.merge(lineasDeComisionesOldLineaDeComision);
                }
            }
            for (LineaDeComision lineasDeComisionesNewLineaDeComision : lineasDeComisionesNew) {
                if (!lineasDeComisionesOld.contains(lineasDeComisionesNewLineaDeComision)) {
                    Comision oldUnaComisionOfLineasDeComisionesNewLineaDeComision = lineasDeComisionesNewLineaDeComision.getUnaComision();
                    lineasDeComisionesNewLineaDeComision.setUnaComision(comision);
                    lineasDeComisionesNewLineaDeComision = em.merge(lineasDeComisionesNewLineaDeComision);
                    if (oldUnaComisionOfLineasDeComisionesNewLineaDeComision != null && !oldUnaComisionOfLineasDeComisionesNewLineaDeComision.equals(comision)) {
                        oldUnaComisionOfLineasDeComisionesNewLineaDeComision.getLineasDeComisiones().remove(lineasDeComisionesNewLineaDeComision);
                        oldUnaComisionOfLineasDeComisionesNewLineaDeComision = em.merge(oldUnaComisionOfLineasDeComisionesNewLineaDeComision);
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

    public void destroy(Long id) throws NonexistentEntityException {
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
            Contrato unContrato = comision.getUnContrato();
            if (unContrato != null) {
                unContrato.setUnaComision(null);
                unContrato = em.merge(unContrato);
            }
            List<LineaDeComision> lineasDeComisiones = comision.getLineasDeComisiones();
            for (LineaDeComision lineasDeComisionesLineaDeComision : lineasDeComisiones) {
                lineasDeComisionesLineaDeComision.setUnaComision(null);
                lineasDeComisionesLineaDeComision = em.merge(lineasDeComisionesLineaDeComision);
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
