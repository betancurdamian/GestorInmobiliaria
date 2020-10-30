/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entity.Provincia;
import model.entity.Barrio;
import model.entity.Localidad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class LocalidadJpaController implements Serializable {

    public LocalidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Localidad localidad) {
        if (localidad.getBarrios() == null) {
            localidad.setBarrios(new ArrayList<Barrio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia unaProvincia = localidad.getUnaProvincia();
            if (unaProvincia != null) {
                unaProvincia = em.getReference(unaProvincia.getClass(), unaProvincia.getId());
                localidad.setUnaProvincia(unaProvincia);
            }
            List<Barrio> attachedBarrios = new ArrayList<Barrio>();
            for (Barrio barriosBarrioToAttach : localidad.getBarrios()) {
                barriosBarrioToAttach = em.getReference(barriosBarrioToAttach.getClass(), barriosBarrioToAttach.getId());
                attachedBarrios.add(barriosBarrioToAttach);
            }
            localidad.setBarrios(attachedBarrios);
            em.persist(localidad);
            if (unaProvincia != null) {
                unaProvincia.getLocalidades().add(localidad);
                unaProvincia = em.merge(unaProvincia);
            }
            for (Barrio barriosBarrio : localidad.getBarrios()) {
                Localidad oldUnaLocalidadOfBarriosBarrio = barriosBarrio.getUnaLocalidad();
                barriosBarrio.setUnaLocalidad(localidad);
                barriosBarrio = em.merge(barriosBarrio);
                if (oldUnaLocalidadOfBarriosBarrio != null) {
                    oldUnaLocalidadOfBarriosBarrio.getBarrios().remove(barriosBarrio);
                    oldUnaLocalidadOfBarriosBarrio = em.merge(oldUnaLocalidadOfBarriosBarrio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Localidad localidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Localidad persistentLocalidad = em.find(Localidad.class, localidad.getId());
            Provincia unaProvinciaOld = persistentLocalidad.getUnaProvincia();
            Provincia unaProvinciaNew = localidad.getUnaProvincia();
            List<Barrio> barriosOld = persistentLocalidad.getBarrios();
            List<Barrio> barriosNew = localidad.getBarrios();
            if (unaProvinciaNew != null) {
                unaProvinciaNew = em.getReference(unaProvinciaNew.getClass(), unaProvinciaNew.getId());
                localidad.setUnaProvincia(unaProvinciaNew);
            }
            List<Barrio> attachedBarriosNew = new ArrayList<Barrio>();
            for (Barrio barriosNewBarrioToAttach : barriosNew) {
                barriosNewBarrioToAttach = em.getReference(barriosNewBarrioToAttach.getClass(), barriosNewBarrioToAttach.getId());
                attachedBarriosNew.add(barriosNewBarrioToAttach);
            }
            barriosNew = attachedBarriosNew;
            localidad.setBarrios(barriosNew);
            localidad = em.merge(localidad);
            if (unaProvinciaOld != null && !unaProvinciaOld.equals(unaProvinciaNew)) {
                unaProvinciaOld.getLocalidades().remove(localidad);
                unaProvinciaOld = em.merge(unaProvinciaOld);
            }
            if (unaProvinciaNew != null && !unaProvinciaNew.equals(unaProvinciaOld)) {
                unaProvinciaNew.getLocalidades().add(localidad);
                unaProvinciaNew = em.merge(unaProvinciaNew);
            }
            for (Barrio barriosOldBarrio : barriosOld) {
                if (!barriosNew.contains(barriosOldBarrio)) {
                    barriosOldBarrio.setUnaLocalidad(null);
                    barriosOldBarrio = em.merge(barriosOldBarrio);
                }
            }
            for (Barrio barriosNewBarrio : barriosNew) {
                if (!barriosOld.contains(barriosNewBarrio)) {
                    Localidad oldUnaLocalidadOfBarriosNewBarrio = barriosNewBarrio.getUnaLocalidad();
                    barriosNewBarrio.setUnaLocalidad(localidad);
                    barriosNewBarrio = em.merge(barriosNewBarrio);
                    if (oldUnaLocalidadOfBarriosNewBarrio != null && !oldUnaLocalidadOfBarriosNewBarrio.equals(localidad)) {
                        oldUnaLocalidadOfBarriosNewBarrio.getBarrios().remove(barriosNewBarrio);
                        oldUnaLocalidadOfBarriosNewBarrio = em.merge(oldUnaLocalidadOfBarriosNewBarrio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = localidad.getId();
                if (findLocalidad(id) == null) {
                    throw new NonexistentEntityException("The localidad with id " + id + " no longer exists.");
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
            Localidad localidad;
            try {
                localidad = em.getReference(Localidad.class, id);
                localidad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The localidad with id " + id + " no longer exists.", enfe);
            }
            Provincia unaProvincia = localidad.getUnaProvincia();
            if (unaProvincia != null) {
                unaProvincia.getLocalidades().remove(localidad);
                unaProvincia = em.merge(unaProvincia);
            }
            List<Barrio> barrios = localidad.getBarrios();
            for (Barrio barriosBarrio : barrios) {
                barriosBarrio.setUnaLocalidad(null);
                barriosBarrio = em.merge(barriosBarrio);
            }
            em.remove(localidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Localidad> findLocalidadEntities() {
        return findLocalidadEntities(true, -1, -1);
    }

    public List<Localidad> findLocalidadEntities(int maxResults, int firstResult) {
        return findLocalidadEntities(false, maxResults, firstResult);
    }

    private List<Localidad> findLocalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Localidad.class));
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

    public Localidad findLocalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Localidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Localidad> rt = cq.from(Localidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
