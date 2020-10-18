/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Ariel
 */
@Entity
@Table(name = "inmuebles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_inmueble")
public abstract class Inmueble implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "direccion_calle")
    private String direccionCalle;

    @Column(name = "direccion_numero")
    private String direccionNumero;

    @ManyToOne
    @JoinColumn(name = "fk_direccion_provincia", nullable = false)
    private Provincia direccionProvincia;

    @ManyToOne
    @JoinColumn(name = "fk_direccion_localidad", nullable = false)
    private Localidad direccionLocalidad;

    @ManyToOne
    @JoinColumn(name = "fk_direccion_barrio", nullable = false)
    private Barrio direccionBarrio;

    @Column(name = "superficie_total")
    private int superficieTotal;

    @Column(name = "disponible")
    private boolean disponible;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "fk_inmobiliaria", nullable = false, updatable = true)
    private Inmobiliaria unaInmobiliariaInmueble;
    
    @OneToOne(mappedBy = "unInmueble")
    private Alquiler unAlquiler;

    public Inmueble() {
    }

    public Inmueble(String direccionCalle, String direccionNumero, Barrio direccionBarrio, int superficieTotal, boolean estaDisponible, String descripcion, Inmobiliaria unaInmobiliariaInmueble) {
        this.direccionCalle = direccionCalle;
        this.direccionNumero = direccionNumero;
        setDireccionBarrio(direccionBarrio);
        this.superficieTotal = superficieTotal;
        this.disponible = estaDisponible;
        this.descripcion = descripcion;
        this.unaInmobiliariaInmueble = unaInmobiliariaInmueble;
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
        if (!(object instanceof Inmueble)) {
            return false;
        }
        Inmueble other = (Inmueble) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betancur.gestorinmobiliario.model.Inmueble[ id=" + id + " ]";
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

    public Provincia getDireccionProvincia() {
        return direccionProvincia;
    }

    public Localidad getDireccionLocalidad() {
        return direccionLocalidad;
    }

    public Barrio getDireccionBarrio() {
        return direccionBarrio;
    }

    public void setDireccionBarrio(Barrio direccionBarrio) {
        this.direccionBarrio = direccionBarrio;
        this.direccionLocalidad = direccionBarrio.getUnaLocalidad();
        this.direccionProvincia = direccionBarrio.getUnaLocalidad().getUnaProvincia();

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Inmobiliaria getUnaInmobiliariaInmueble() {
        return unaInmobiliariaInmueble;
    }

    public void setUnaInmobiliariaInmueble(Inmobiliaria unaInmobiliariaInmueble) {
        this.unaInmobiliariaInmueble = unaInmobiliariaInmueble;
    }

    public int getSuperficieTotal() {
        return superficieTotal;
    }

    public void setSuperficieTotal(int superficieTotal) {
        this.superficieTotal = superficieTotal;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Alquiler getUnAlquiler() {
        return unAlquiler;
    }

    public void setUnAlquiler(Alquiler unAlquiler) {
        this.unAlquiler = unAlquiler;
    }

    

}
