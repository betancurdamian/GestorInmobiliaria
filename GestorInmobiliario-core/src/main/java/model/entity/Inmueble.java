/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

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

    @Column(name = "superficie_total")
    private Integer superficieTotal;

    @Column(name = "disponible")
    private Boolean disponible;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "fk_inmobiliaria")
    private Inmobiliaria unaInmobiliariaInmueble;
    
    @ManyToOne
    @JoinColumn(name = "fk_duenio")
    private Cliente unCliente;
    
    @Column(name = "precio_base_venta")
    private Float precioBaseVenta;
    
    @Column(name = "precio_base_alquiler")
    private Float precioBaseAlquiler;

    public Inmueble() {
    }

    public Inmueble(String direccionCalle, String direccionNumero, Integer superficieTotal, Boolean disponible, String descripcion, Inmobiliaria unaInmobiliariaInmueble, Locador unLocador, Float precioBaseVenta, Float precioBaseAlquiler) {
        this.direccionCalle = direccionCalle;
        this.direccionNumero = direccionNumero;
        this.superficieTotal = superficieTotal;
        this.disponible = disponible;
        this.descripcion = descripcion;
        this.unaInmobiliariaInmueble = unaInmobiliariaInmueble;
        this.unCliente = unLocador;
        this.precioBaseVenta = precioBaseVenta;
        this.precioBaseAlquiler = precioBaseAlquiler;
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

    public Cliente getUnCliente() {
        return unCliente;
    }

    public void setUnCliente(Cliente unCliente) {
        this.unCliente = unCliente;
    }

    public Float getPrecioBaseVenta() {
        return precioBaseVenta;
    }

    public void setPrecioBaseVenta(Float precioBaseVenta) {
        this.precioBaseVenta = precioBaseVenta;
    }

    public Float getPrecioBaseAlquiler() {
        return precioBaseAlquiler;
    }

    public void setPrecioBaseAlquiler(Float precioBaseAlquiler) {
        this.precioBaseAlquiler = precioBaseAlquiler;
    }
    
    

}
