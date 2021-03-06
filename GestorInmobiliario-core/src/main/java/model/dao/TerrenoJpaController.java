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
import model.entity.Inmobiliaria;
import model.entity.Cliente;
import model.entity.Terreno;

/**
 *
 * @author Ariel
 */
public class TerrenoJpaController implements Serializable {

    public TerrenoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Terreno terreno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaInmueble = terreno.getUnaInmobiliariaInmueble();
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble = em.getReference(unaInmobiliariaInmueble.getClass(), unaInmobiliariaInmueble.getId());
                terreno.setUnaInmobiliariaInmueble(unaInmobiliariaInmueble);
            }
            Cliente unCliente = terreno.getUnCliente();
            if (unCliente != null) {
                unCliente = em.getReference(unCliente.getClass(), unCliente.getId());
                terreno.setUnCliente(unCliente);
            }
            em.persist(terreno);
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble.getInmuebles().add(terreno);
                unaInmobiliariaInmueble = em.merge(unaInmobiliariaInmueble);
            }
            if (unCliente != null) {
                unCliente.getInmuebles().add(terreno);
                unCliente = em.merge(unCliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Terreno terreno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Terreno persistentTerreno = em.find(Terreno.class, terreno.getId());
            Inmobiliaria unaInmobiliariaInmuebleOld = persistentTerreno.getUnaInmobiliariaInmueble();
            Inmobiliaria unaInmobiliariaInmuebleNew = terreno.getUnaInmobiliariaInmueble();
            Cliente unClienteOld = persistentTerreno.getUnCliente();
            Cliente unClienteNew = terreno.getUnCliente();
            if (unaInmobiliariaInmuebleNew != null) {
                unaInmobiliariaInmuebleNew = em.getReference(unaInmobiliariaInmuebleNew.getClass(), unaInmobiliariaInmuebleNew.getId());
                terreno.setUnaInmobiliariaInmueble(unaInmobiliariaInmuebleNew);
            }
            if (unClienteNew != null) {
                unClienteNew = em.getReference(unClienteNew.getClass(), unClienteNew.getId());
                terreno.setUnCliente(unClienteNew);
            }
            terreno = em.merge(terreno);
            if (unaInmobiliariaInmuebleOld != null && !unaInmobiliariaInmuebleOld.equals(unaInmobiliariaInmuebleNew)) {
                unaInmobiliariaInmuebleOld.getInmuebles().remove(terreno);
                unaInmobiliariaInmuebleOld = em.merge(unaInmobiliariaInmuebleOld);
            }
            if (unaInmobiliariaInmuebleNew != null && !unaInmobiliariaInmuebleNew.equals(unaInmobiliariaInmuebleOld)) {
                unaInmobiliariaInmuebleNew.getInmuebles().add(terreno);
                unaInmobiliariaInmuebleNew = em.merge(unaInmobiliariaInmuebleNew);
            }
            if (unClienteOld != null && !unClienteOld.equals(unClienteNew)) {
                unClienteOld.getInmuebles().remove(terreno);
                unClienteOld = em.merge(unClienteOld);
            }
            if (unClienteNew != null && !unClienteNew.equals(unClienteOld)) {
                unClienteNew.getInmuebles().add(terreno);
                unClienteNew = em.merge(unClienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = terreno.getId();
                if (findTerreno(id) == null) {
                    throw new NonexistentEntityException("The terreno with id " + id + " no longer exists.");
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
            Terreno terreno;
            try {
                terreno = em.getReference(Terreno.class, id);
                terreno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The terreno with id " + id + " no longer exists.", enfe);
            }
            Inmobiliaria unaInmobiliariaInmueble = terreno.getUnaInmobiliariaInmueble();
            if (unaInmobiliariaInmueble != null) {
                unaInmobiliariaInmueble.getInmuebles().remove(terreno);
                unaInmobiliariaInmueble = em.merge(unaInmobiliariaInmueble);
            }
            Cliente unCliente = terreno.getUnCliente();
            if (unCliente != null) {
                unCliente.getInmuebles().remove(terreno);
                unCliente = em.merge(unCliente);
            }
            em.remove(terreno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Terreno> findTerrenoEntities() {
        return findTerrenoEntities(true, -1, -1);
    }

    public List<Terreno> findTerrenoEntities(int maxResults, int firstResult) {
        return findTerrenoEntities(false, maxResults, firstResult);
    }

    private List<Terreno> findTerrenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Terreno.class));
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

    public Terreno findTerreno(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Terreno.class, id);
        } finally {
            em.close();
        }
    }

    public int getTerrenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Terreno> rt = cq.from(Terreno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
