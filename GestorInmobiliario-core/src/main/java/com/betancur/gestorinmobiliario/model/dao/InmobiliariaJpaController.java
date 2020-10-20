/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.dao;

import com.betancur.gestorinmobiliario.model.dao.exceptions.IllegalOrphanException;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.betancur.gestorinmobiliario.model.entity.Inmueble;
import java.util.ArrayList;
import java.util.List;
import com.betancur.gestorinmobiliario.model.entity.Alquiler;
import com.betancur.gestorinmobiliario.model.entity.Venta;
import com.betancur.gestorinmobiliario.model.entity.Cliente;
import com.betancur.gestorinmobiliario.model.entity.Garante;
import com.betancur.gestorinmobiliario.model.entity.RecargoPorMora;
import com.betancur.gestorinmobiliario.model.entity.ArancelEspecial;
import com.betancur.gestorinmobiliario.model.entity.Inmobiliaria;
import com.betancur.gestorinmobiliario.model.entity.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ariel
 */
public class InmobiliariaJpaController implements Serializable {

    public InmobiliariaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Inmobiliaria inmobiliaria) {
        if (inmobiliaria.getInmuebles() == null) {
            inmobiliaria.setInmuebles(new ArrayList<Inmueble>());
        }
        if (inmobiliaria.getAlquileres() == null) {
            inmobiliaria.setAlquileres(new ArrayList<Alquiler>());
        }
        if (inmobiliaria.getVentas() == null) {
            inmobiliaria.setVentas(new ArrayList<Venta>());
        }
        if (inmobiliaria.getClientes() == null) {
            inmobiliaria.setClientes(new ArrayList<Cliente>());
        }
        if (inmobiliaria.getGarantes() == null) {
            inmobiliaria.setGarantes(new ArrayList<Garante>());
        }
        if (inmobiliaria.getRecargosPorMoras() == null) {
            inmobiliaria.setRecargosPorMoras(new ArrayList<RecargoPorMora>());
        }
        if (inmobiliaria.getArancelesEspeciales() == null) {
            inmobiliaria.setArancelesEspeciales(new ArrayList<ArancelEspecial>());
        }
        if (inmobiliaria.getUsuarios() == null) {
            inmobiliaria.setUsuarios(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Inmueble> attachedInmuebles = new ArrayList<Inmueble>();
            for (Inmueble inmueblesInmuebleToAttach : inmobiliaria.getInmuebles()) {
                inmueblesInmuebleToAttach = em.getReference(inmueblesInmuebleToAttach.getClass(), inmueblesInmuebleToAttach.getId());
                attachedInmuebles.add(inmueblesInmuebleToAttach);
            }
            inmobiliaria.setInmuebles(attachedInmuebles);
            List<Alquiler> attachedAlquileres = new ArrayList<Alquiler>();
            for (Alquiler alquileresAlquilerToAttach : inmobiliaria.getAlquileres()) {
                alquileresAlquilerToAttach = em.getReference(alquileresAlquilerToAttach.getClass(), alquileresAlquilerToAttach.getId());
                attachedAlquileres.add(alquileresAlquilerToAttach);
            }
            inmobiliaria.setAlquileres(attachedAlquileres);
            List<Venta> attachedVentas = new ArrayList<Venta>();
            for (Venta ventasVentaToAttach : inmobiliaria.getVentas()) {
                ventasVentaToAttach = em.getReference(ventasVentaToAttach.getClass(), ventasVentaToAttach.getId());
                attachedVentas.add(ventasVentaToAttach);
            }
            inmobiliaria.setVentas(attachedVentas);
            List<Cliente> attachedClientes = new ArrayList<Cliente>();
            for (Cliente clientesClienteToAttach : inmobiliaria.getClientes()) {
                clientesClienteToAttach = em.getReference(clientesClienteToAttach.getClass(), clientesClienteToAttach.getId());
                attachedClientes.add(clientesClienteToAttach);
            }
            inmobiliaria.setClientes(attachedClientes);
            List<Garante> attachedGarantes = new ArrayList<Garante>();
            for (Garante garantesGaranteToAttach : inmobiliaria.getGarantes()) {
                garantesGaranteToAttach = em.getReference(garantesGaranteToAttach.getClass(), garantesGaranteToAttach.getId());
                attachedGarantes.add(garantesGaranteToAttach);
            }
            inmobiliaria.setGarantes(attachedGarantes);
            List<RecargoPorMora> attachedRecargosPorMoras = new ArrayList<RecargoPorMora>();
            for (RecargoPorMora recargosPorMorasRecargoPorMoraToAttach : inmobiliaria.getRecargosPorMoras()) {
                recargosPorMorasRecargoPorMoraToAttach = em.getReference(recargosPorMorasRecargoPorMoraToAttach.getClass(), recargosPorMorasRecargoPorMoraToAttach.getId());
                attachedRecargosPorMoras.add(recargosPorMorasRecargoPorMoraToAttach);
            }
            inmobiliaria.setRecargosPorMoras(attachedRecargosPorMoras);
            List<ArancelEspecial> attachedArancelesEspeciales = new ArrayList<ArancelEspecial>();
            for (ArancelEspecial arancelesEspecialesArancelEspecialToAttach : inmobiliaria.getArancelesEspeciales()) {
                arancelesEspecialesArancelEspecialToAttach = em.getReference(arancelesEspecialesArancelEspecialToAttach.getClass(), arancelesEspecialesArancelEspecialToAttach.getId());
                attachedArancelesEspeciales.add(arancelesEspecialesArancelEspecialToAttach);
            }
            inmobiliaria.setArancelesEspeciales(attachedArancelesEspeciales);
            List<Usuario> attachedUsuarios = new ArrayList<Usuario>();
            for (Usuario usuariosUsuarioToAttach : inmobiliaria.getUsuarios()) {
                usuariosUsuarioToAttach = em.getReference(usuariosUsuarioToAttach.getClass(), usuariosUsuarioToAttach.getId());
                attachedUsuarios.add(usuariosUsuarioToAttach);
            }
            inmobiliaria.setUsuarios(attachedUsuarios);
            em.persist(inmobiliaria);
            for (Inmueble inmueblesInmueble : inmobiliaria.getInmuebles()) {
                Inmobiliaria oldUnaInmobiliariaInmuebleOfInmueblesInmueble = inmueblesInmueble.getUnaInmobiliariaInmueble();
                inmueblesInmueble.setUnaInmobiliariaInmueble(inmobiliaria);
                inmueblesInmueble = em.merge(inmueblesInmueble);
                if (oldUnaInmobiliariaInmuebleOfInmueblesInmueble != null) {
                    oldUnaInmobiliariaInmuebleOfInmueblesInmueble.getInmuebles().remove(inmueblesInmueble);
                    oldUnaInmobiliariaInmuebleOfInmueblesInmueble = em.merge(oldUnaInmobiliariaInmuebleOfInmueblesInmueble);
                }
            }
            for (Alquiler alquileresAlquiler : inmobiliaria.getAlquileres()) {
                Inmobiliaria oldUnaInmobiliariaAlquilerOfAlquileresAlquiler = alquileresAlquiler.getUnaInmobiliariaAlquiler();
                alquileresAlquiler.setUnaInmobiliariaAlquiler(inmobiliaria);
                alquileresAlquiler = em.merge(alquileresAlquiler);
                if (oldUnaInmobiliariaAlquilerOfAlquileresAlquiler != null) {
                    oldUnaInmobiliariaAlquilerOfAlquileresAlquiler.getAlquileres().remove(alquileresAlquiler);
                    oldUnaInmobiliariaAlquilerOfAlquileresAlquiler = em.merge(oldUnaInmobiliariaAlquilerOfAlquileresAlquiler);
                }
            }
            for (Venta ventasVenta : inmobiliaria.getVentas()) {
                Inmobiliaria oldUnaInmobiliariaVentaOfVentasVenta = ventasVenta.getUnaInmobiliariaVenta();
                ventasVenta.setUnaInmobiliariaVenta(inmobiliaria);
                ventasVenta = em.merge(ventasVenta);
                if (oldUnaInmobiliariaVentaOfVentasVenta != null) {
                    oldUnaInmobiliariaVentaOfVentasVenta.getVentas().remove(ventasVenta);
                    oldUnaInmobiliariaVentaOfVentasVenta = em.merge(oldUnaInmobiliariaVentaOfVentasVenta);
                }
            }
            for (Cliente clientesCliente : inmobiliaria.getClientes()) {
                Inmobiliaria oldUnaInmobiliariaClienteOfClientesCliente = clientesCliente.getUnaInmobiliariaCliente();
                clientesCliente.setUnaInmobiliariaCliente(inmobiliaria);
                clientesCliente = em.merge(clientesCliente);
                if (oldUnaInmobiliariaClienteOfClientesCliente != null) {
                    oldUnaInmobiliariaClienteOfClientesCliente.getClientes().remove(clientesCliente);
                    oldUnaInmobiliariaClienteOfClientesCliente = em.merge(oldUnaInmobiliariaClienteOfClientesCliente);
                }
            }
            for (Garante garantesGarante : inmobiliaria.getGarantes()) {
                Inmobiliaria oldUnaInmobiliariaGaranteOfGarantesGarante = garantesGarante.getUnaInmobiliariaGarante();
                garantesGarante.setUnaInmobiliariaGarante(inmobiliaria);
                garantesGarante = em.merge(garantesGarante);
                if (oldUnaInmobiliariaGaranteOfGarantesGarante != null) {
                    oldUnaInmobiliariaGaranteOfGarantesGarante.getGarantes().remove(garantesGarante);
                    oldUnaInmobiliariaGaranteOfGarantesGarante = em.merge(oldUnaInmobiliariaGaranteOfGarantesGarante);
                }
            }
            for (RecargoPorMora recargosPorMorasRecargoPorMora : inmobiliaria.getRecargosPorMoras()) {
                Inmobiliaria oldUnaInmobiliariaRecargoPorMoraOfRecargosPorMorasRecargoPorMora = recargosPorMorasRecargoPorMora.getUnaInmobiliariaRecargoPorMora();
                recargosPorMorasRecargoPorMora.setUnaInmobiliariaRecargoPorMora(inmobiliaria);
                recargosPorMorasRecargoPorMora = em.merge(recargosPorMorasRecargoPorMora);
                if (oldUnaInmobiliariaRecargoPorMoraOfRecargosPorMorasRecargoPorMora != null) {
                    oldUnaInmobiliariaRecargoPorMoraOfRecargosPorMorasRecargoPorMora.getRecargosPorMoras().remove(recargosPorMorasRecargoPorMora);
                    oldUnaInmobiliariaRecargoPorMoraOfRecargosPorMorasRecargoPorMora = em.merge(oldUnaInmobiliariaRecargoPorMoraOfRecargosPorMorasRecargoPorMora);
                }
            }
            for (ArancelEspecial arancelesEspecialesArancelEspecial : inmobiliaria.getArancelesEspeciales()) {
                Inmobiliaria oldUnaInmobiliariaArancelEspecialOfArancelesEspecialesArancelEspecial = arancelesEspecialesArancelEspecial.getUnaInmobiliariaArancelEspecial();
                arancelesEspecialesArancelEspecial.setUnaInmobiliariaArancelEspecial(inmobiliaria);
                arancelesEspecialesArancelEspecial = em.merge(arancelesEspecialesArancelEspecial);
                if (oldUnaInmobiliariaArancelEspecialOfArancelesEspecialesArancelEspecial != null) {
                    oldUnaInmobiliariaArancelEspecialOfArancelesEspecialesArancelEspecial.getArancelesEspeciales().remove(arancelesEspecialesArancelEspecial);
                    oldUnaInmobiliariaArancelEspecialOfArancelesEspecialesArancelEspecial = em.merge(oldUnaInmobiliariaArancelEspecialOfArancelesEspecialesArancelEspecial);
                }
            }
            for (Usuario usuariosUsuario : inmobiliaria.getUsuarios()) {
                Inmobiliaria oldUnaInmobiliariaUsuarioOfUsuariosUsuario = usuariosUsuario.getUnaInmobiliariaUsuario();
                usuariosUsuario.setUnaInmobiliariaUsuario(inmobiliaria);
                usuariosUsuario = em.merge(usuariosUsuario);
                if (oldUnaInmobiliariaUsuarioOfUsuariosUsuario != null) {
                    oldUnaInmobiliariaUsuarioOfUsuariosUsuario.getUsuarios().remove(usuariosUsuario);
                    oldUnaInmobiliariaUsuarioOfUsuariosUsuario = em.merge(oldUnaInmobiliariaUsuarioOfUsuariosUsuario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Inmobiliaria inmobiliaria) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inmobiliaria persistentInmobiliaria = em.find(Inmobiliaria.class, inmobiliaria.getId());
            List<Inmueble> inmueblesOld = persistentInmobiliaria.getInmuebles();
            List<Inmueble> inmueblesNew = inmobiliaria.getInmuebles();
            List<Alquiler> alquileresOld = persistentInmobiliaria.getAlquileres();
            List<Alquiler> alquileresNew = inmobiliaria.getAlquileres();
            List<Venta> ventasOld = persistentInmobiliaria.getVentas();
            List<Venta> ventasNew = inmobiliaria.getVentas();
            List<Cliente> clientesOld = persistentInmobiliaria.getClientes();
            List<Cliente> clientesNew = inmobiliaria.getClientes();
            List<Garante> garantesOld = persistentInmobiliaria.getGarantes();
            List<Garante> garantesNew = inmobiliaria.getGarantes();
            List<RecargoPorMora> recargosPorMorasOld = persistentInmobiliaria.getRecargosPorMoras();
            List<RecargoPorMora> recargosPorMorasNew = inmobiliaria.getRecargosPorMoras();
            List<ArancelEspecial> arancelesEspecialesOld = persistentInmobiliaria.getArancelesEspeciales();
            List<ArancelEspecial> arancelesEspecialesNew = inmobiliaria.getArancelesEspeciales();
            List<Usuario> usuariosOld = persistentInmobiliaria.getUsuarios();
            List<Usuario> usuariosNew = inmobiliaria.getUsuarios();
            List<String> illegalOrphanMessages = null;
            for (Inmueble inmueblesOldInmueble : inmueblesOld) {
                if (!inmueblesNew.contains(inmueblesOldInmueble)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Inmueble " + inmueblesOldInmueble + " since its unaInmobiliariaInmueble field is not nullable.");
                }
            }
            for (ArancelEspecial arancelesEspecialesOldArancelEspecial : arancelesEspecialesOld) {
                if (!arancelesEspecialesNew.contains(arancelesEspecialesOldArancelEspecial)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ArancelEspecial " + arancelesEspecialesOldArancelEspecial + " since its unaInmobiliariaArancelEspecial field is not nullable.");
                }
            }
            for (Usuario usuariosOldUsuario : usuariosOld) {
                if (!usuariosNew.contains(usuariosOldUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuario " + usuariosOldUsuario + " since its unaInmobiliariaUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Inmueble> attachedInmueblesNew = new ArrayList<Inmueble>();
            for (Inmueble inmueblesNewInmuebleToAttach : inmueblesNew) {
                inmueblesNewInmuebleToAttach = em.getReference(inmueblesNewInmuebleToAttach.getClass(), inmueblesNewInmuebleToAttach.getId());
                attachedInmueblesNew.add(inmueblesNewInmuebleToAttach);
            }
            inmueblesNew = attachedInmueblesNew;
            inmobiliaria.setInmuebles(inmueblesNew);
            List<Alquiler> attachedAlquileresNew = new ArrayList<Alquiler>();
            for (Alquiler alquileresNewAlquilerToAttach : alquileresNew) {
                alquileresNewAlquilerToAttach = em.getReference(alquileresNewAlquilerToAttach.getClass(), alquileresNewAlquilerToAttach.getId());
                attachedAlquileresNew.add(alquileresNewAlquilerToAttach);
            }
            alquileresNew = attachedAlquileresNew;
            inmobiliaria.setAlquileres(alquileresNew);
            List<Venta> attachedVentasNew = new ArrayList<Venta>();
            for (Venta ventasNewVentaToAttach : ventasNew) {
                ventasNewVentaToAttach = em.getReference(ventasNewVentaToAttach.getClass(), ventasNewVentaToAttach.getId());
                attachedVentasNew.add(ventasNewVentaToAttach);
            }
            ventasNew = attachedVentasNew;
            inmobiliaria.setVentas(ventasNew);
            List<Cliente> attachedClientesNew = new ArrayList<Cliente>();
            for (Cliente clientesNewClienteToAttach : clientesNew) {
                clientesNewClienteToAttach = em.getReference(clientesNewClienteToAttach.getClass(), clientesNewClienteToAttach.getId());
                attachedClientesNew.add(clientesNewClienteToAttach);
            }
            clientesNew = attachedClientesNew;
            inmobiliaria.setClientes(clientesNew);
            List<Garante> attachedGarantesNew = new ArrayList<Garante>();
            for (Garante garantesNewGaranteToAttach : garantesNew) {
                garantesNewGaranteToAttach = em.getReference(garantesNewGaranteToAttach.getClass(), garantesNewGaranteToAttach.getId());
                attachedGarantesNew.add(garantesNewGaranteToAttach);
            }
            garantesNew = attachedGarantesNew;
            inmobiliaria.setGarantes(garantesNew);
            List<RecargoPorMora> attachedRecargosPorMorasNew = new ArrayList<RecargoPorMora>();
            for (RecargoPorMora recargosPorMorasNewRecargoPorMoraToAttach : recargosPorMorasNew) {
                recargosPorMorasNewRecargoPorMoraToAttach = em.getReference(recargosPorMorasNewRecargoPorMoraToAttach.getClass(), recargosPorMorasNewRecargoPorMoraToAttach.getId());
                attachedRecargosPorMorasNew.add(recargosPorMorasNewRecargoPorMoraToAttach);
            }
            recargosPorMorasNew = attachedRecargosPorMorasNew;
            inmobiliaria.setRecargosPorMoras(recargosPorMorasNew);
            List<ArancelEspecial> attachedArancelesEspecialesNew = new ArrayList<ArancelEspecial>();
            for (ArancelEspecial arancelesEspecialesNewArancelEspecialToAttach : arancelesEspecialesNew) {
                arancelesEspecialesNewArancelEspecialToAttach = em.getReference(arancelesEspecialesNewArancelEspecialToAttach.getClass(), arancelesEspecialesNewArancelEspecialToAttach.getId());
                attachedArancelesEspecialesNew.add(arancelesEspecialesNewArancelEspecialToAttach);
            }
            arancelesEspecialesNew = attachedArancelesEspecialesNew;
            inmobiliaria.setArancelesEspeciales(arancelesEspecialesNew);
            List<Usuario> attachedUsuariosNew = new ArrayList<Usuario>();
            for (Usuario usuariosNewUsuarioToAttach : usuariosNew) {
                usuariosNewUsuarioToAttach = em.getReference(usuariosNewUsuarioToAttach.getClass(), usuariosNewUsuarioToAttach.getId());
                attachedUsuariosNew.add(usuariosNewUsuarioToAttach);
            }
            usuariosNew = attachedUsuariosNew;
            inmobiliaria.setUsuarios(usuariosNew);
            inmobiliaria = em.merge(inmobiliaria);
            for (Inmueble inmueblesNewInmueble : inmueblesNew) {
                if (!inmueblesOld.contains(inmueblesNewInmueble)) {
                    Inmobiliaria oldUnaInmobiliariaInmuebleOfInmueblesNewInmueble = inmueblesNewInmueble.getUnaInmobiliariaInmueble();
                    inmueblesNewInmueble.setUnaInmobiliariaInmueble(inmobiliaria);
                    inmueblesNewInmueble = em.merge(inmueblesNewInmueble);
                    if (oldUnaInmobiliariaInmuebleOfInmueblesNewInmueble != null && !oldUnaInmobiliariaInmuebleOfInmueblesNewInmueble.equals(inmobiliaria)) {
                        oldUnaInmobiliariaInmuebleOfInmueblesNewInmueble.getInmuebles().remove(inmueblesNewInmueble);
                        oldUnaInmobiliariaInmuebleOfInmueblesNewInmueble = em.merge(oldUnaInmobiliariaInmuebleOfInmueblesNewInmueble);
                    }
                }
            }
            for (Alquiler alquileresOldAlquiler : alquileresOld) {
                if (!alquileresNew.contains(alquileresOldAlquiler)) {
                    alquileresOldAlquiler.setUnaInmobiliariaAlquiler(null);
                    alquileresOldAlquiler = em.merge(alquileresOldAlquiler);
                }
            }
            for (Alquiler alquileresNewAlquiler : alquileresNew) {
                if (!alquileresOld.contains(alquileresNewAlquiler)) {
                    Inmobiliaria oldUnaInmobiliariaAlquilerOfAlquileresNewAlquiler = alquileresNewAlquiler.getUnaInmobiliariaAlquiler();
                    alquileresNewAlquiler.setUnaInmobiliariaAlquiler(inmobiliaria);
                    alquileresNewAlquiler = em.merge(alquileresNewAlquiler);
                    if (oldUnaInmobiliariaAlquilerOfAlquileresNewAlquiler != null && !oldUnaInmobiliariaAlquilerOfAlquileresNewAlquiler.equals(inmobiliaria)) {
                        oldUnaInmobiliariaAlquilerOfAlquileresNewAlquiler.getAlquileres().remove(alquileresNewAlquiler);
                        oldUnaInmobiliariaAlquilerOfAlquileresNewAlquiler = em.merge(oldUnaInmobiliariaAlquilerOfAlquileresNewAlquiler);
                    }
                }
            }
            for (Venta ventasOldVenta : ventasOld) {
                if (!ventasNew.contains(ventasOldVenta)) {
                    ventasOldVenta.setUnaInmobiliariaVenta(null);
                    ventasOldVenta = em.merge(ventasOldVenta);
                }
            }
            for (Venta ventasNewVenta : ventasNew) {
                if (!ventasOld.contains(ventasNewVenta)) {
                    Inmobiliaria oldUnaInmobiliariaVentaOfVentasNewVenta = ventasNewVenta.getUnaInmobiliariaVenta();
                    ventasNewVenta.setUnaInmobiliariaVenta(inmobiliaria);
                    ventasNewVenta = em.merge(ventasNewVenta);
                    if (oldUnaInmobiliariaVentaOfVentasNewVenta != null && !oldUnaInmobiliariaVentaOfVentasNewVenta.equals(inmobiliaria)) {
                        oldUnaInmobiliariaVentaOfVentasNewVenta.getVentas().remove(ventasNewVenta);
                        oldUnaInmobiliariaVentaOfVentasNewVenta = em.merge(oldUnaInmobiliariaVentaOfVentasNewVenta);
                    }
                }
            }
            for (Cliente clientesOldCliente : clientesOld) {
                if (!clientesNew.contains(clientesOldCliente)) {
                    clientesOldCliente.setUnaInmobiliariaCliente(null);
                    clientesOldCliente = em.merge(clientesOldCliente);
                }
            }
            for (Cliente clientesNewCliente : clientesNew) {
                if (!clientesOld.contains(clientesNewCliente)) {
                    Inmobiliaria oldUnaInmobiliariaClienteOfClientesNewCliente = clientesNewCliente.getUnaInmobiliariaCliente();
                    clientesNewCliente.setUnaInmobiliariaCliente(inmobiliaria);
                    clientesNewCliente = em.merge(clientesNewCliente);
                    if (oldUnaInmobiliariaClienteOfClientesNewCliente != null && !oldUnaInmobiliariaClienteOfClientesNewCliente.equals(inmobiliaria)) {
                        oldUnaInmobiliariaClienteOfClientesNewCliente.getClientes().remove(clientesNewCliente);
                        oldUnaInmobiliariaClienteOfClientesNewCliente = em.merge(oldUnaInmobiliariaClienteOfClientesNewCliente);
                    }
                }
            }
            for (Garante garantesOldGarante : garantesOld) {
                if (!garantesNew.contains(garantesOldGarante)) {
                    garantesOldGarante.setUnaInmobiliariaGarante(null);
                    garantesOldGarante = em.merge(garantesOldGarante);
                }
            }
            for (Garante garantesNewGarante : garantesNew) {
                if (!garantesOld.contains(garantesNewGarante)) {
                    Inmobiliaria oldUnaInmobiliariaGaranteOfGarantesNewGarante = garantesNewGarante.getUnaInmobiliariaGarante();
                    garantesNewGarante.setUnaInmobiliariaGarante(inmobiliaria);
                    garantesNewGarante = em.merge(garantesNewGarante);
                    if (oldUnaInmobiliariaGaranteOfGarantesNewGarante != null && !oldUnaInmobiliariaGaranteOfGarantesNewGarante.equals(inmobiliaria)) {
                        oldUnaInmobiliariaGaranteOfGarantesNewGarante.getGarantes().remove(garantesNewGarante);
                        oldUnaInmobiliariaGaranteOfGarantesNewGarante = em.merge(oldUnaInmobiliariaGaranteOfGarantesNewGarante);
                    }
                }
            }
            for (RecargoPorMora recargosPorMorasOldRecargoPorMora : recargosPorMorasOld) {
                if (!recargosPorMorasNew.contains(recargosPorMorasOldRecargoPorMora)) {
                    recargosPorMorasOldRecargoPorMora.setUnaInmobiliariaRecargoPorMora(null);
                    recargosPorMorasOldRecargoPorMora = em.merge(recargosPorMorasOldRecargoPorMora);
                }
            }
            for (RecargoPorMora recargosPorMorasNewRecargoPorMora : recargosPorMorasNew) {
                if (!recargosPorMorasOld.contains(recargosPorMorasNewRecargoPorMora)) {
                    Inmobiliaria oldUnaInmobiliariaRecargoPorMoraOfRecargosPorMorasNewRecargoPorMora = recargosPorMorasNewRecargoPorMora.getUnaInmobiliariaRecargoPorMora();
                    recargosPorMorasNewRecargoPorMora.setUnaInmobiliariaRecargoPorMora(inmobiliaria);
                    recargosPorMorasNewRecargoPorMora = em.merge(recargosPorMorasNewRecargoPorMora);
                    if (oldUnaInmobiliariaRecargoPorMoraOfRecargosPorMorasNewRecargoPorMora != null && !oldUnaInmobiliariaRecargoPorMoraOfRecargosPorMorasNewRecargoPorMora.equals(inmobiliaria)) {
                        oldUnaInmobiliariaRecargoPorMoraOfRecargosPorMorasNewRecargoPorMora.getRecargosPorMoras().remove(recargosPorMorasNewRecargoPorMora);
                        oldUnaInmobiliariaRecargoPorMoraOfRecargosPorMorasNewRecargoPorMora = em.merge(oldUnaInmobiliariaRecargoPorMoraOfRecargosPorMorasNewRecargoPorMora);
                    }
                }
            }
            for (ArancelEspecial arancelesEspecialesNewArancelEspecial : arancelesEspecialesNew) {
                if (!arancelesEspecialesOld.contains(arancelesEspecialesNewArancelEspecial)) {
                    Inmobiliaria oldUnaInmobiliariaArancelEspecialOfArancelesEspecialesNewArancelEspecial = arancelesEspecialesNewArancelEspecial.getUnaInmobiliariaArancelEspecial();
                    arancelesEspecialesNewArancelEspecial.setUnaInmobiliariaArancelEspecial(inmobiliaria);
                    arancelesEspecialesNewArancelEspecial = em.merge(arancelesEspecialesNewArancelEspecial);
                    if (oldUnaInmobiliariaArancelEspecialOfArancelesEspecialesNewArancelEspecial != null && !oldUnaInmobiliariaArancelEspecialOfArancelesEspecialesNewArancelEspecial.equals(inmobiliaria)) {
                        oldUnaInmobiliariaArancelEspecialOfArancelesEspecialesNewArancelEspecial.getArancelesEspeciales().remove(arancelesEspecialesNewArancelEspecial);
                        oldUnaInmobiliariaArancelEspecialOfArancelesEspecialesNewArancelEspecial = em.merge(oldUnaInmobiliariaArancelEspecialOfArancelesEspecialesNewArancelEspecial);
                    }
                }
            }
            for (Usuario usuariosNewUsuario : usuariosNew) {
                if (!usuariosOld.contains(usuariosNewUsuario)) {
                    Inmobiliaria oldUnaInmobiliariaUsuarioOfUsuariosNewUsuario = usuariosNewUsuario.getUnaInmobiliariaUsuario();
                    usuariosNewUsuario.setUnaInmobiliariaUsuario(inmobiliaria);
                    usuariosNewUsuario = em.merge(usuariosNewUsuario);
                    if (oldUnaInmobiliariaUsuarioOfUsuariosNewUsuario != null && !oldUnaInmobiliariaUsuarioOfUsuariosNewUsuario.equals(inmobiliaria)) {
                        oldUnaInmobiliariaUsuarioOfUsuariosNewUsuario.getUsuarios().remove(usuariosNewUsuario);
                        oldUnaInmobiliariaUsuarioOfUsuariosNewUsuario = em.merge(oldUnaInmobiliariaUsuarioOfUsuariosNewUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = inmobiliaria.getId();
                if (findInmobiliaria(id) == null) {
                    throw new NonexistentEntityException("The inmobiliaria with id " + id + " no longer exists.");
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
            Inmobiliaria inmobiliaria;
            try {
                inmobiliaria = em.getReference(Inmobiliaria.class, id);
                inmobiliaria.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inmobiliaria with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Inmueble> inmueblesOrphanCheck = inmobiliaria.getInmuebles();
            for (Inmueble inmueblesOrphanCheckInmueble : inmueblesOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Inmobiliaria (" + inmobiliaria + ") cannot be destroyed since the Inmueble " + inmueblesOrphanCheckInmueble + " in its inmuebles field has a non-nullable unaInmobiliariaInmueble field.");
            }
            List<ArancelEspecial> arancelesEspecialesOrphanCheck = inmobiliaria.getArancelesEspeciales();
            for (ArancelEspecial arancelesEspecialesOrphanCheckArancelEspecial : arancelesEspecialesOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Inmobiliaria (" + inmobiliaria + ") cannot be destroyed since the ArancelEspecial " + arancelesEspecialesOrphanCheckArancelEspecial + " in its arancelesEspeciales field has a non-nullable unaInmobiliariaArancelEspecial field.");
            }
            List<Usuario> usuariosOrphanCheck = inmobiliaria.getUsuarios();
            for (Usuario usuariosOrphanCheckUsuario : usuariosOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Inmobiliaria (" + inmobiliaria + ") cannot be destroyed since the Usuario " + usuariosOrphanCheckUsuario + " in its usuarios field has a non-nullable unaInmobiliariaUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Alquiler> alquileres = inmobiliaria.getAlquileres();
            for (Alquiler alquileresAlquiler : alquileres) {
                alquileresAlquiler.setUnaInmobiliariaAlquiler(null);
                alquileresAlquiler = em.merge(alquileresAlquiler);
            }
            List<Venta> ventas = inmobiliaria.getVentas();
            for (Venta ventasVenta : ventas) {
                ventasVenta.setUnaInmobiliariaVenta(null);
                ventasVenta = em.merge(ventasVenta);
            }
            List<Cliente> clientes = inmobiliaria.getClientes();
            for (Cliente clientesCliente : clientes) {
                clientesCliente.setUnaInmobiliariaCliente(null);
                clientesCliente = em.merge(clientesCliente);
            }
            List<Garante> garantes = inmobiliaria.getGarantes();
            for (Garante garantesGarante : garantes) {
                garantesGarante.setUnaInmobiliariaGarante(null);
                garantesGarante = em.merge(garantesGarante);
            }
            List<RecargoPorMora> recargosPorMoras = inmobiliaria.getRecargosPorMoras();
            for (RecargoPorMora recargosPorMorasRecargoPorMora : recargosPorMoras) {
                recargosPorMorasRecargoPorMora.setUnaInmobiliariaRecargoPorMora(null);
                recargosPorMorasRecargoPorMora = em.merge(recargosPorMorasRecargoPorMora);
            }
            em.remove(inmobiliaria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Inmobiliaria> findInmobiliariaEntities() {
        return findInmobiliariaEntities(true, -1, -1);
    }

    public List<Inmobiliaria> findInmobiliariaEntities(int maxResults, int firstResult) {
        return findInmobiliariaEntities(false, maxResults, firstResult);
    }

    private List<Inmobiliaria> findInmobiliariaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Inmobiliaria.class));
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

    public Inmobiliaria findInmobiliaria(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Inmobiliaria.class, id);
        } finally {
            em.close();
        }
    }

    public int getInmobiliariaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Inmobiliaria> rt = cq.from(Inmobiliaria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
