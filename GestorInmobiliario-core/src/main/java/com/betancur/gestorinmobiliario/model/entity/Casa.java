/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.entity;

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

    @Column(name="cantidad_dormitorio")
    private int cantidadDeDormitorios;
    
    @Column(name="cantidad_banio")
    private int cantidadDeBanio;
    
    @Column(name="numero_piso")
    private int numeroDePisos;
    
    @Column(name="cochera")
    private boolean cochera;
    
    @Column(name="jardin")
    private boolean jardin;

    public Casa() {
    }

    public Casa(int cantidadDeDormitorios, int cantidadDeBanio, int numeroDePisos, boolean cochera, boolean jardin) {
        this.cantidadDeDormitorios = cantidadDeDormitorios;
        this.cantidadDeBanio = cantidadDeBanio;
        this.numeroDePisos = numeroDePisos;
        this.cochera = cochera;
        this.jardin = jardin;
    }

    public Casa(int cantidadDeDormitorios, int cantidadDeBanio, int numeroDePisos, boolean cochera, boolean jardin, String direccionCalle, String direccionNumero, Barrio direccionBarrio, int superficieTotal, boolean estaDisponible, String descripcion, Inmobiliaria unaInmobiliariaInmueble) {
        super(direccionCalle, direccionNumero, direccionBarrio, superficieTotal, estaDisponible, descripcion, unaInmobiliariaInmueble);
        this.cantidadDeDormitorios = cantidadDeDormitorios;
        this.cantidadDeBanio = cantidadDeBanio;
        this.numeroDePisos = numeroDePisos;
        this.cochera = cochera;
        this.jardin = jardin;
    }
    
    

    public int getCantidadDeDormitorios() {
        return cantidadDeDormitorios;
    }

    public void setCantidadDeDormitorios(int cantidadDeDormitorios) {
        this.cantidadDeDormitorios = cantidadDeDormitorios;
    }

    public int getCantidadDeBanio() {
        return cantidadDeBanio;
    }

    public void setCantidadDeBanio(int cantidadDeBanio) {
        this.cantidadDeBanio = cantidadDeBanio;
    }

    public int getNumeroDePisos() {
        return numeroDePisos;
    }

    public void setNumeroDePisos(int numeroDePisos) {
        this.numeroDePisos = numeroDePisos;
    }

    public boolean isCochera() {
        return cochera;
    }

    public void setCochera(boolean cochera) {
        this.cochera = cochera;
    }

    public boolean isJardin() {
        return jardin;
    }

    public void setJardin(boolean jardin) {
        this.jardin = jardin;
    }
    
    
}
