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
@DiscriminatorValue("LOCAL COMERCIAL")
public class LocalComercial extends Inmueble {

    @Column(name="cantidad_banio")
    private int cantidadDeBanio;    
    
    @Column(name="cochera")
    private boolean cochera;

    public LocalComercial() {
    }

    public LocalComercial(int cantidadDeBanio, boolean cochera) {
        this.cantidadDeBanio = cantidadDeBanio;
        this.cochera = cochera;
    }

    public LocalComercial(int cantidadDeBanio, boolean cochera, String direccionCalle, String direccionNumero, Barrio direccionBarrio, int superficieTotal, boolean estaDisponible, String descripcion, Inmobiliaria unaInmobiliariaInmueble) {
        super(direccionCalle, direccionNumero, direccionBarrio, superficieTotal, estaDisponible, descripcion, unaInmobiliariaInmueble);
        this.cantidadDeBanio = cantidadDeBanio;
        this.cochera = cochera;
    }

   

    public int getCantidadDeBanio() {
        return cantidadDeBanio;
    }

    public void setCantidadDeBanio(int cantidadDeBanio) {
        this.cantidadDeBanio = cantidadDeBanio;
    }

    public boolean isCochera() {
        return cochera;
    }

    public void setCochera(boolean cochera) {
        this.cochera = cochera;
    }
    
    
    
}
