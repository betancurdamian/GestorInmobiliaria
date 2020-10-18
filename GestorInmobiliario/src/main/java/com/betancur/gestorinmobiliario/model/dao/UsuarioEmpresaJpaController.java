/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.betancur.gestorinmobiliario.model.Inmobiliaria;
import com.betancur.gestorinmobiliario.model.UsuarioEmpresa;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class UsuarioEmpresaJpaController implements Serializable {

    public UsuarioEmpresaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UsuarioEmpresa usuarioEmpresa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaUsuario = usuarioEmpresa.getUnaInmobiliariaUsuario();
            if (unaInmobiliariaUsuario != null) {
                unaInmobiliariaUsuario = em.getReference(unaInmobiliariaUsuario.getClass(), unaInmobiliariaUsuario.getId());
                usuarioEmpresa.setUnaInmobiliariaUsuario(unaInmobiliariaUsuario);
            }
            em.persist(usuarioEmpresa);
            if (unaInmobiliariaUsuario != null) {
                unaInmobiliariaUsuario.getUsuarios().add(usuarioEmpresa);
                unaInmobiliariaUsuario = em.merge(unaInmobiliariaUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsuarioEmpresa usuarioEmpresa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioEmpresa persistentUsuarioEmpresa = em.find(UsuarioEmpresa.class, usuarioEmpresa.getId());
            Inmobiliaria unaInmobiliariaUsuarioOld = persistentUsuarioEmpresa.getUnaInmobiliariaUsuario();
            Inmobiliaria unaInmobiliariaUsuarioNew = usuarioEmpresa.getUnaInmobiliariaUsuario();
            if (unaInmobiliariaUsuarioNew != null) {
                unaInmobiliariaUsuarioNew = em.getReference(unaInmobiliariaUsuarioNew.getClass(), unaInmobiliariaUsuarioNew.getId());
                usuarioEmpresa.setUnaInmobiliariaUsuario(unaInmobiliariaUsuarioNew);
            }
            usuarioEmpresa = em.merge(usuarioEmpresa);
            if (unaInmobiliariaUsuarioOld != null && !unaInmobiliariaUsuarioOld.equals(unaInmobiliariaUsuarioNew)) {
                unaInmobiliariaUsuarioOld.getUsuarios().remove(usuarioEmpresa);
                unaInmobiliariaUsuarioOld = em.merge(unaInmobiliariaUsuarioOld);
            }
            if (unaInmobiliariaUsuarioNew != null && !unaInmobiliariaUsuarioNew.equals(unaInmobiliariaUsuarioOld)) {
                unaInmobiliariaUsuarioNew.getUsuarios().add(usuarioEmpresa);
                unaInmobiliariaUsuarioNew = em.merge(unaInmobiliariaUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = usuarioEmpresa.getId();
                if (findUsuarioEmpresa(id) == null) {
                    throw new NonexistentEntityException("The usuarioEmpresa with id " + id + " no longer exists.");
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
            UsuarioEmpresa usuarioEmpresa;
            try {
                usuarioEmpresa = em.getReference(UsuarioEmpresa.class, id);
                usuarioEmpresa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioEmpresa with id " + id + " no longer exists.", enfe);
            }
            Inmobiliaria unaInmobiliariaUsuario = usuarioEmpresa.getUnaInmobiliariaUsuario();
            if (unaInmobiliariaUsuario != null) {
                unaInmobiliariaUsuario.getUsuarios().remove(usuarioEmpresa);
                unaInmobiliariaUsuario = em.merge(unaInmobiliariaUsuario);
            }
            em.remove(usuarioEmpresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsuarioEmpresa> findUsuarioEmpresaEntities() {
        return findUsuarioEmpresaEntities(true, -1, -1);
    }

    public List<UsuarioEmpresa> findUsuarioEmpresaEntities(int maxResults, int firstResult) {
        return findUsuarioEmpresaEntities(false, maxResults, firstResult);
    }

    private List<UsuarioEmpresa> findUsuarioEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UsuarioEmpresa.class));
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

    public UsuarioEmpresa findUsuarioEmpresa(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsuarioEmpresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UsuarioEmpresa> rt = cq.from(UsuarioEmpresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
