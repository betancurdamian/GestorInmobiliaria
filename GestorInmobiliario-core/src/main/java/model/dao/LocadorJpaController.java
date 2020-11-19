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
import model.entity.Inmobiliaria;
import model.entity.UsuarioCliente;
import model.entity.Inmueble;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Locador;

/**
 *
 * @author Ariel
 */
public class LocadorJpaController implements Serializable {

    public LocadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Locador locador) {
        if (locador.getInmuebles() == null) {
            locador.setInmuebles(new ArrayList<Inmueble>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaCliente = locador.getUnaInmobiliariaCliente();
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente = em.getReference(unaInmobiliariaCliente.getClass(), unaInmobiliariaCliente.getId());
                locador.setUnaInmobiliariaCliente(unaInmobiliariaCliente);
            }
            UsuarioCliente unUsuarioCliente = locador.getUnUsuarioCliente();
            if (unUsuarioCliente != null) {
                unUsuarioCliente = em.getReference(unUsuarioCliente.getClass(), unUsuarioCliente.getId());
                locador.setUnUsuarioCliente(unUsuarioCliente);
            }
            List<Inmueble> attachedInmuebles = new ArrayList<Inmueble>();
            for (Inmueble inmueblesInmuebleToAttach : locador.getInmuebles()) {
                inmueblesInmuebleToAttach = em.getReference(inmueblesInmuebleToAttach.getClass(), inmueblesInmuebleToAttach.getId());
                attachedInmuebles.add(inmueblesInmuebleToAttach);
            }
            locador.setInmuebles(attachedInmuebles);
            em.persist(locador);
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente.getClientes().add(locador);
                unaInmobiliariaCliente = em.merge(unaInmobiliariaCliente);
            }
            if (unUsuarioCliente != null) {
                model.entity.Cliente oldUnClienteOfUnUsuarioCliente = unUsuarioCliente.getUnCliente();
                if (oldUnClienteOfUnUsuarioCliente != null) {
                    oldUnClienteOfUnUsuarioCliente.setUnUsuarioCliente(null);
                    oldUnClienteOfUnUsuarioCliente = em.merge(oldUnClienteOfUnUsuarioCliente);
                }
                unUsuarioCliente.setUnCliente(locador);
                unUsuarioCliente = em.merge(unUsuarioCliente);
            }
            for (Inmueble inmueblesInmueble : locador.getInmuebles()) {
                Locador oldUnLocadorOfInmueblesInmueble = inmueblesInmueble.getUnLocador();
                inmueblesInmueble.setUnLocador(locador);
                inmueblesInmueble = em.merge(inmueblesInmueble);
                if (oldUnLocadorOfInmueblesInmueble != null) {
                    oldUnLocadorOfInmueblesInmueble.getInmuebles().remove(inmueblesInmueble);
                    oldUnLocadorOfInmueblesInmueble = em.merge(oldUnLocadorOfInmueblesInmueble);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Locador locador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Locador persistentLocador = em.find(Locador.class, locador.getId());
            Inmobiliaria unaInmobiliariaClienteOld = persistentLocador.getUnaInmobiliariaCliente();
            Inmobiliaria unaInmobiliariaClienteNew = locador.getUnaInmobiliariaCliente();
            UsuarioCliente unUsuarioClienteOld = persistentLocador.getUnUsuarioCliente();
            UsuarioCliente unUsuarioClienteNew = locador.getUnUsuarioCliente();
            List<Inmueble> inmueblesOld = persistentLocador.getInmuebles();
            List<Inmueble> inmueblesNew = locador.getInmuebles();
            if (unaInmobiliariaClienteNew != null) {
                unaInmobiliariaClienteNew = em.getReference(unaInmobiliariaClienteNew.getClass(), unaInmobiliariaClienteNew.getId());
                locador.setUnaInmobiliariaCliente(unaInmobiliariaClienteNew);
            }
            if (unUsuarioClienteNew != null) {
                unUsuarioClienteNew = em.getReference(unUsuarioClienteNew.getClass(), unUsuarioClienteNew.getId());
                locador.setUnUsuarioCliente(unUsuarioClienteNew);
            }
            List<Inmueble> attachedInmueblesNew = new ArrayList<Inmueble>();
            for (Inmueble inmueblesNewInmuebleToAttach : inmueblesNew) {
                inmueblesNewInmuebleToAttach = em.getReference(inmueblesNewInmuebleToAttach.getClass(), inmueblesNewInmuebleToAttach.getId());
                attachedInmueblesNew.add(inmueblesNewInmuebleToAttach);
            }
            inmueblesNew = attachedInmueblesNew;
            locador.setInmuebles(inmueblesNew);
            locador = em.merge(locador);
            if (unaInmobiliariaClienteOld != null && !unaInmobiliariaClienteOld.equals(unaInmobiliariaClienteNew)) {
                unaInmobiliariaClienteOld.getClientes().remove(locador);
                unaInmobiliariaClienteOld = em.merge(unaInmobiliariaClienteOld);
            }
            if (unaInmobiliariaClienteNew != null && !unaInmobiliariaClienteNew.equals(unaInmobiliariaClienteOld)) {
                unaInmobiliariaClienteNew.getClientes().add(locador);
                unaInmobiliariaClienteNew = em.merge(unaInmobiliariaClienteNew);
            }
            if (unUsuarioClienteOld != null && !unUsuarioClienteOld.equals(unUsuarioClienteNew)) {
                unUsuarioClienteOld.setUnCliente(null);
                unUsuarioClienteOld = em.merge(unUsuarioClienteOld);
            }
            if (unUsuarioClienteNew != null && !unUsuarioClienteNew.equals(unUsuarioClienteOld)) {
                model.entity.Cliente oldUnClienteOfUnUsuarioCliente = unUsuarioClienteNew.getUnCliente();
                if (oldUnClienteOfUnUsuarioCliente != null) {
                    oldUnClienteOfUnUsuarioCliente.setUnUsuarioCliente(null);
                    oldUnClienteOfUnUsuarioCliente = em.merge(oldUnClienteOfUnUsuarioCliente);
                }
                unUsuarioClienteNew.setUnCliente(locador);
                unUsuarioClienteNew = em.merge(unUsuarioClienteNew);
            }
            for (Inmueble inmueblesOldInmueble : inmueblesOld) {
                if (!inmueblesNew.contains(inmueblesOldInmueble)) {
                    inmueblesOldInmueble.setUnLocador(null);
                    inmueblesOldInmueble = em.merge(inmueblesOldInmueble);
                }
            }
            for (Inmueble inmueblesNewInmueble : inmueblesNew) {
                if (!inmueblesOld.contains(inmueblesNewInmueble)) {
                    Locador oldUnLocadorOfInmueblesNewInmueble = inmueblesNewInmueble.getUnLocador();
                    inmueblesNewInmueble.setUnLocador(locador);
                    inmueblesNewInmueble = em.merge(inmueblesNewInmueble);
                    if (oldUnLocadorOfInmueblesNewInmueble != null && !oldUnLocadorOfInmueblesNewInmueble.equals(locador)) {
                        oldUnLocadorOfInmueblesNewInmueble.getInmuebles().remove(inmueblesNewInmueble);
                        oldUnLocadorOfInmueblesNewInmueble = em.merge(oldUnLocadorOfInmueblesNewInmueble);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = locador.getId();
                if (findLocador(id) == null) {
                    throw new NonexistentEntityException("The locador with id " + id + " no longer exists.");
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
            Locador locador;
            try {
                locador = em.getReference(Locador.class, id);
                locador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The locador with id " + id + " no longer exists.", enfe);
            }
            Inmobiliaria unaInmobiliariaCliente = locador.getUnaInmobiliariaCliente();
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente.getClientes().remove(locador);
                unaInmobiliariaCliente = em.merge(unaInmobiliariaCliente);
            }
            UsuarioCliente unUsuarioCliente = locador.getUnUsuarioCliente();
            if (unUsuarioCliente != null) {
                unUsuarioCliente.setUnCliente(null);
                unUsuarioCliente = em.merge(unUsuarioCliente);
            }
            List<Inmueble> inmuebles = locador.getInmuebles();
            for (Inmueble inmueblesInmueble : inmuebles) {
                inmueblesInmueble.setUnLocador(null);
                inmueblesInmueble = em.merge(inmueblesInmueble);
            }
            em.remove(locador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Locador> findLocadorEntities() {
        return findLocadorEntities(true, -1, -1);
    }

    public List<Locador> findLocadorEntities(int maxResults, int firstResult) {
        return findLocadorEntities(false, maxResults, firstResult);
    }

    private List<Locador> findLocadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Locador.class));
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

    public Locador findLocador(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Locador.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Locador> rt = cq.from(Locador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
