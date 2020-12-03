/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author Ariel
 */
@Entity
@Table(name = "inmobiliarias")
public class Inmobiliaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "cuit")
    private String cuit;

    @Column(name = "direccion_calle")
    private String direccionCalle;

    @Column(name = "direccion_numero")
    private String direccionNumero;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @OneToMany(mappedBy = "unaInmobiliariaCliente", cascade = CascadeType.ALL, targetEntity = Cliente.class)
    @PrivateOwned
    private List<Cliente> clientes;

    @OneToMany(mappedBy = "unaInmobiliariaGarante", cascade = CascadeType.ALL, targetEntity = Garante.class)
    @PrivateOwned
    private List<Garante> garantes;

    @OneToMany(mappedBy = "unaInmobiliariaInmueble", cascade = CascadeType.ALL, targetEntity = Inmueble.class)
    @PrivateOwned
    private List<Inmueble> inmuebles;

    @OneToMany(mappedBy = "unaInmobiliariaAlquiler", cascade = CascadeType.ALL, targetEntity = Alquiler.class)
    @PrivateOwned
    private List<Alquiler> alquileres;

    @OneToMany(mappedBy = "unaInmobiliariaVenta", cascade = CascadeType.ALL, targetEntity = Venta.class)
    @PrivateOwned
    private List<Venta> ventas;

    @OneToMany(mappedBy = "unaInmobiliariaRecargoPorMora", cascade = CascadeType.ALL, targetEntity = RecargoPorMora.class)
    @PrivateOwned
    private List<RecargoPorMora> recargosPorMoras;

    @OneToMany(mappedBy = "unaInmobiliariaArancelEspecial", cascade = CascadeType.ALL, targetEntity = ArancelEspecial.class)
    @PrivateOwned
    private List<ArancelEspecial> arancelesEspeciales;

    @OneToMany(mappedBy = "unaInmobiliariaUsuario", cascade = CascadeType.ALL, targetEntity = Usuario.class)
    @PrivateOwned
    private List<Usuario> usuarios;

    public Inmobiliaria() {
        this.garantes = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.inmuebles = new ArrayList<>();
        this.alquileres = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.recargosPorMoras = new ArrayList<>();
        this.arancelesEspeciales = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public Inmobiliaria(String razonSocial, String cuit, String direccionCalle, String direccionNumero, String telefono, String correoElectronico) {
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.direccionCalle = direccionCalle;
        this.direccionNumero = direccionNumero;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.garantes = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.inmuebles = new ArrayList<>();
        this.alquileres = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.recargosPorMoras = new ArrayList<>();
        this.arancelesEspeciales = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inmobiliaria)) {
            return false;
        }
        Inmobiliaria other = (Inmobiliaria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betancur.gestorinmobiliario.dao.Inmobiliaria[ id=" + id + " ]";
    }

    public boolean verificarUsuario(Usuario unUsuario) {

        for (Usuario usuarioR : usuarios) {
            if (usuarioR.getUserName().equals(unUsuario.getUserName())) {

            }
        }
        return false;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getDireccionCalle() {
        return direccionCalle;
    }

    public void setDireccionCalle(String direccionCalle) {
        this.direccionCalle = direccionCalle;
    }

    public String getDireccionNumero() {
        return direccionNumero;
    }

    public void setDireccionNumero(String direccionNumero) {
        this.direccionNumero = direccionNumero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Garante> getGarantes() {
        return garantes;
    }

    public void setGarantes(List<Garante> garantes) {
        this.garantes = garantes;
    }

    public List<Inmueble> getInmuebles() {
        return inmuebles;
    }

    public void setInmuebles(List<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
    }

    public List<Alquiler> getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(List<Alquiler> alquileres) {
        this.alquileres = alquileres;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public List<RecargoPorMora> getRecargosPorMoras() {
        return recargosPorMoras;
    }

    public void setRecargosPorMoras(List<RecargoPorMora> recargosPorMoras) {
        this.recargosPorMoras = recargosPorMoras;
    }

    public List<ArancelEspecial> getArancelesEspeciales() {
        return arancelesEspeciales;
    }

    public void setArancelesEspeciales(List<ArancelEspecial> arancelesEspeciales) {
        this.arancelesEspeciales = arancelesEspeciales;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
