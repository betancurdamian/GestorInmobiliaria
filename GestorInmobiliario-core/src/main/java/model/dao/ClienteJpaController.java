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
import model.entity.Cliente;

/**
 *
 * @author Ariel
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getInmuebles() == null) {
            cliente.setInmuebles(new ArrayList<Inmueble>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria unaInmobiliariaCliente = cliente.getUnaInmobiliariaCliente();
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente = em.getReference(unaInmobiliariaCliente.getClass(), unaInmobiliariaCliente.getId());
                cliente.setUnaInmobiliariaCliente(unaInmobiliariaCliente);
            }
            UsuarioCliente unUsuarioCliente = cliente.getUnUsuarioCliente();
            if (unUsuarioCliente != null) {
                unUsuarioCliente = em.getReference(unUsuarioCliente.getClass(), unUsuarioCliente.getId());
                cliente.setUnUsuarioCliente(unUsuarioCliente);
            }
            List<Inmueble> attachedInmuebles = new ArrayList<Inmueble>();
            for (Inmueble inmueblesInmuebleToAttach : cliente.getInmuebles()) {
                inmueblesInmuebleToAttach = em.getReference(inmueblesInmuebleToAttach.getClass(), inmueblesInmuebleToAttach.getId());
                attachedInmuebles.add(inmueblesInmuebleToAttach);
            }
            cliente.setInmuebles(attachedInmuebles);
            em.persist(cliente);
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente.getClientes().add(cliente);
                unaInmobiliariaCliente = em.merge(unaInmobiliariaCliente);
            }
            if (unUsuarioCliente != null) {
                Cliente oldUnClienteOfUnUsuarioCliente = unUsuarioCliente.getUnCliente();
                if (oldUnClienteOfUnUsuarioCliente != null) {
                    oldUnClienteOfUnUsuarioCliente.setUnUsuarioCliente(null);
                    oldUnClienteOfUnUsuarioCliente = em.merge(oldUnClienteOfUnUsuarioCliente);
                }
                unUsuarioCliente.setUnCliente(cliente);
                unUsuarioCliente = em.merge(unUsuarioCliente);
            }
            for (Inmueble inmueblesInmueble : cliente.getInmuebles()) {
                Cliente oldUnClienteOfInmueblesInmueble = inmueblesInmueble.getUnCliente();
                inmueblesInmueble.setUnCliente(cliente);
                inmueblesInmueble = em.merge(inmueblesInmueble);
                if (oldUnClienteOfInmueblesInmueble != null) {
                    oldUnClienteOfInmueblesInmueble.getInmuebles().remove(inmueblesInmueble);
                    oldUnClienteOfInmueblesInmueble = em.merge(oldUnClienteOfInmueblesInmueble);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getId());
            Inmobiliaria unaInmobiliariaClienteOld = persistentCliente.getUnaInmobiliariaCliente();
            Inmobiliaria unaInmobiliariaClienteNew = cliente.getUnaInmobiliariaCliente();
            UsuarioCliente unUsuarioClienteOld = persistentCliente.getUnUsuarioCliente();
            UsuarioCliente unUsuarioClienteNew = cliente.getUnUsuarioCliente();
            List<Inmueble> inmueblesOld = persistentCliente.getInmuebles();
            List<Inmueble> inmueblesNew = cliente.getInmuebles();
            if (unaInmobiliariaClienteNew != null) {
                unaInmobiliariaClienteNew = em.getReference(unaInmobiliariaClienteNew.getClass(), unaInmobiliariaClienteNew.getId());
                cliente.setUnaInmobiliariaCliente(unaInmobiliariaClienteNew);
            }
            if (unUsuarioClienteNew != null) {
                unUsuarioClienteNew = em.getReference(unUsuarioClienteNew.getClass(), unUsuarioClienteNew.getId());
                cliente.setUnUsuarioCliente(unUsuarioClienteNew);
            }
            List<Inmueble> attachedInmueblesNew = new ArrayList<Inmueble>();
            for (Inmueble inmueblesNewInmuebleToAttach : inmueblesNew) {
                inmueblesNewInmuebleToAttach = em.getReference(inmueblesNewInmuebleToAttach.getClass(), inmueblesNewInmuebleToAttach.getId());
                attachedInmueblesNew.add(inmueblesNewInmuebleToAttach);
            }
            inmueblesNew = attachedInmueblesNew;
            cliente.setInmuebles(inmueblesNew);
            cliente = em.merge(cliente);
            if (unaInmobiliariaClienteOld != null && !unaInmobiliariaClienteOld.equals(unaInmobiliariaClienteNew)) {
                unaInmobiliariaClienteOld.getClientes().remove(cliente);
                unaInmobiliariaClienteOld = em.merge(unaInmobiliariaClienteOld);
            }
            if (unaInmobiliariaClienteNew != null && !unaInmobiliariaClienteNew.equals(unaInmobiliariaClienteOld)) {
                unaInmobiliariaClienteNew.getClientes().add(cliente);
                unaInmobiliariaClienteNew = em.merge(unaInmobiliariaClienteNew);
            }
            if (unUsuarioClienteOld != null && !unUsuarioClienteOld.equals(unUsuarioClienteNew)) {
                unUsuarioClienteOld.setUnCliente(null);
                unUsuarioClienteOld = em.merge(unUsuarioClienteOld);
            }
            if (unUsuarioClienteNew != null && !unUsuarioClienteNew.equals(unUsuarioClienteOld)) {
                Cliente oldUnClienteOfUnUsuarioCliente = unUsuarioClienteNew.getUnCliente();
                if (oldUnClienteOfUnUsuarioCliente != null) {
                    oldUnClienteOfUnUsuarioCliente.setUnUsuarioCliente(null);
                    oldUnClienteOfUnUsuarioCliente = em.merge(oldUnClienteOfUnUsuarioCliente);
                }
                unUsuarioClienteNew.setUnCliente(cliente);
                unUsuarioClienteNew = em.merge(unUsuarioClienteNew);
            }
            for (Inmueble inmueblesOldInmueble : inmueblesOld) {
                if (!inmueblesNew.contains(inmueblesOldInmueble)) {
                    inmueblesOldInmueble.setUnCliente(null);
                    inmueblesOldInmueble = em.merge(inmueblesOldInmueble);
                }
            }
            for (Inmueble inmueblesNewInmueble : inmueblesNew) {
                if (!inmueblesOld.contains(inmueblesNewInmueble)) {
                    Cliente oldUnClienteOfInmueblesNewInmueble = inmueblesNewInmueble.getUnCliente();
                    inmueblesNewInmueble.setUnCliente(cliente);
                    inmueblesNewInmueble = em.merge(inmueblesNewInmueble);
                    if (oldUnClienteOfInmueblesNewInmueble != null && !oldUnClienteOfInmueblesNewInmueble.equals(cliente)) {
                        oldUnClienteOfInmueblesNewInmueble.getInmuebles().remove(inmueblesNewInmueble);
                        oldUnClienteOfInmueblesNewInmueble = em.merge(oldUnClienteOfInmueblesNewInmueble);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cliente.getId();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            Inmobiliaria unaInmobiliariaCliente = cliente.getUnaInmobiliariaCliente();
            if (unaInmobiliariaCliente != null) {
                unaInmobiliariaCliente.getClientes().remove(cliente);
                unaInmobiliariaCliente = em.merge(unaInmobiliariaCliente);
            }
            UsuarioCliente unUsuarioCliente = cliente.getUnUsuarioCliente();
            if (unUsuarioCliente != null) {
                unUsuarioCliente.setUnCliente(null);
                unUsuarioCliente = em.merge(unUsuarioCliente);
            }
            List<Inmueble> inmuebles = cliente.getInmuebles();
            for (Inmueble inmueblesInmueble : inmuebles) {
                inmueblesInmueble.setUnCliente(null);
                inmueblesInmueble = em.merge(inmueblesInmueble);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
