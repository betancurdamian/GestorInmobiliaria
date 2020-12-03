/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Ariel
 */
@Entity
@DiscriminatorValue("CASA")
public class Casa extends Inmueble {

    @Column(name = "cantidad_dormitorio")
    private Integer cantidadDeDormitorios;

    @Column(name = "cantidad_banio")
    private Integer cantidadDeBanio;

    @Column(name = "numero_piso")
    private Integer numeroDePisos;

    @Column(name = "cochera")
    private Boolean cochera;

    @Column(name = "jardin")
    private Boolean jardin;

    public Casa() {
    }

    public Casa(Integer cantidadDeDormitorios, Integer cantidadDeBanio, Integer numeroDePisos, Boolean cochera, Boolean jardin) {
        this.cantidadDeDormitorios = cantidadDeDormitorios;
        this.cantidadDeBanio = cantidadDeBanio;
        this.numeroDePisos = numeroDePisos;
        this.cochera = cochera;
        this.jardin = jardin;
    }

    public Casa(Integer cantidadDeDormitorios, Integer cantidadDeBanio, Integer numeroDePisos, Boolean cochera, Boolean jardin, String direccionCalle, String direccionNumero, Integer superficieTotal, Boolean disponible, String descripcion, Inmobiliaria unaInmobiliariaInmueble, Locador unLocador, Float precioBaseVenta, Float precioBaseAlquiler) {
        super(direccionCalle, direccionNumero, superficieTotal, disponible, descripcion, unaInmobiliariaInmueble, unLocador, precioBaseVenta, precioBaseAlquiler);
        this.cantidadDeDormitorios = cantidadDeDormitorios;
        this.cantidadDeBanio = cantidadDeBanio;
        this.numeroDePisos = numeroDePisos;
        this.cochera = cochera;
        this.jardin = jardin;
    }

    

    public Integer getCantidadDeDormitorios() {
        return cantidadDeDormitorios;
    }

    public void setCantidadDeDormitorios(Integer cantidadDeDormitorios) {
        this.cantidadDeDormitorios = cantidadDeDormitorios;
    }

    public Integer getCantidadDeBanio() {
        return cantidadDeBanio;
    }

    public void setCantidadDeBanio(Integer cantidadDeBanio) {
        this.cantidadDeBanio = cantidadDeBanio;
    }

    public Integer getNumeroDePisos() {
        return numeroDePisos;
    }

    public void setNumeroDePisos(Integer numeroDePisos) {
        this.numeroDePisos = numeroDePisos;
    }

    public Boolean getCochera() {
        return cochera;
    }

    public void setCochera(Boolean cochera) {
        this.cochera = cochera;
    }

    public Boolean getJardin() {
        return jardin;
    }

    public void setJardin(Boolean jardin) {
        this.jardin = jardin;
    }

}
