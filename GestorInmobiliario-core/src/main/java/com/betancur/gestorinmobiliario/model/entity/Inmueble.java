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
    @JoinColumn(name = "fk_direccion_provincia")
    private Provincia direccionProvincia;

    @ManyToOne
    @JoinColumn(name = "fk_direccion_localidad")
    private Localidad direccionLocalidad;

    @ManyToOne
    @JoinColumn(name = "fk_direccion_barrio")
    private Barrio direccionBarrio;

    @Column(name = "superficie_total")
    private Integer superficieTotal;

    @Column(name = "disponible")
    private Boolean disponible;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "fk_inmobiliaria")
    private Inmobiliaria unaInmobiliariaInmueble;   
    public Inmueble() {
    }

    public Inmueble(String direccionCalle, String direccionNumero, Provincia direccionProvincia, Localidad direccionLocalidad, Barrio direccionBarrio, Integer superficieTotal, Boolean disponible, String descripcion, Inmobiliaria unaInmobiliariaInmueble) {
        this.direccionCalle = direccionCalle;
        this.direccionNumero = direccionNumero;
        this.direccionProvincia = direccionProvincia;
        this.direccionLocalidad = direccionLocalidad;
        this.direccionBarrio = direccionBarrio;
        this.superficieTotal = superficieTotal;
        this.disponible = disponible;
        this.descripcion = descripcion;
        this.unaInmobiliariaInmueble = unaInmobiliariaInmueble;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setDireccionProvincia(Provincia direccionProvincia) {
        this.direccionProvincia = direccionProvincia;
    }

    public Localidad getDireccionLocalidad() {
        return direccionLocalidad;
    }

    public void setDireccionLocalidad(Localidad direccionLocalidad) {
        this.direccionLocalidad = direccionLocalidad;
    }

    public Barrio getDireccionBarrio() {
        return direccionBarrio;
    }

    public void setDireccionBarrio(Barrio direccionBarrio) {
        this.direccionBarrio = direccionBarrio;
    }

    public Integer getSuperficieTotal() {
        return superficieTotal;
    }

    public void setSuperficieTotal(Integer superficieTotal) {
        this.superficieTotal = superficieTotal;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
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

    
}
