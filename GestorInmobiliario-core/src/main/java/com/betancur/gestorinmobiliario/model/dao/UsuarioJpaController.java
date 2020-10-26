/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.dao;

import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.betancur.gestorinmobiliario.model.entity.Inmobiliaria;
import com.betancur.gestorinmobiliario.model.entity.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaUsuario = usuario.getUnaInmobiliariaUsuario();
            if (unaInmobiliariaUsuario != null) {
                unaInmobiliariaUsuario = em.getReference(unaInmobiliariaUsuario.getClass(), unaInmobiliariaUsuario.getId());
                usuario.setUnaInmobiliariaUsuario(unaInmobiliariaUsuario);
            }
            em.persist(usuario);
            if (unaInmobiliariaUsuario != null) {
                unaInmobiliariaUsuario.getUsuarios().add(usuario);
                unaInmobiliariaUsuario = em.merge(unaInmobiliariaUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            Inmobiliaria unaInmobiliariaUsuarioOld = persistentUsuario.getUnaInmobiliariaUsuario();
            Inmobiliaria unaInmobiliariaUsuarioNew = usuario.getUnaInmobiliariaUsuario();
            if (unaInmobiliariaUsuarioNew != null) {
                unaInmobiliariaUsuarioNew = em.getReference(unaInmobiliariaUsuarioNew.getClass(), unaInmobiliariaUsuarioNew.getId());
                usuario.setUnaInmobiliariaUsuario(unaInmobiliariaUsuarioNew);
            }
            usuario = em.merge(usuario);
            if (unaInmobiliariaUsuarioOld != null && !unaInmobiliariaUsuarioOld.equals(unaInmobiliariaUsuarioNew)) {
                unaInmobiliariaUsuarioOld.getUsuarios().remove(usuario);
                unaInmobiliariaUsuarioOld = em.merge(unaInmobiliariaUsuarioOld);
            }
            if (unaInmobiliariaUsuarioNew != null && !unaInmobiliariaUsuarioNew.equals(unaInmobiliariaUsuarioOld)) {
                unaInmobiliariaUsuarioNew.getUsuarios().add(usuario);
                unaInmobiliariaUsuarioNew = em.merge(unaInmobiliariaUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Inmobiliaria unaInmobiliariaUsuario = usuario.getUnaInmobiliariaUsuario();
            if (unaInmobiliariaUsuario != null) {
                unaInmobiliariaUsuario.getUsuarios().remove(usuario);
                unaInmobiliariaUsuario = em.merge(unaInmobiliariaUsuario);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}