/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Ariel
 */
@Entity
@DiscriminatorValue("DEPARTAMENTO")
public class Departamento extends Inmueble {

    @Column(name="cantidad_dormitorio")
    private int cantidadDeDormitorios;
    
    @Column(name="cantidad_banio")
    private int cantidadDeBanio;    
    
    @Column(name="balcon")
    private boolean balcon;

    public Departamento() {
    }

    public Departamento(int cantidadDeDormitorios, int cantidadDeBanio, boolean balcon) {
        this.cantidadDeDormitorios = cantidadDeDormitorios;
        this.cantidadDeBanio = cantidadDeBanio;
        this.balcon = balcon;
    }

    public Departamento(int cantidadDeDormitorios, int cantidadDeBanio, boolean balcon, String direccionCalle, String direccionNumero, Barrio direccionBarrio, int superficieTotal, boolean estaDisponible, String descripcion, Inmobiliaria unaInmobiliariaInmueble) {
        super(direccionCalle, direccionNumero, direccionBarrio, superficieTotal, estaDisponible, descripcion, unaInmobiliariaInmueble);
        this.cantidadDeDormitorios = cantidadDeDormitorios;
        this.cantidadDeBanio = cantidadDeBanio;
        this.balcon = balcon;
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

    public boolean isBalcon() {
        return balcon;
    }

    public void setBalcon(boolean balcon) {
        this.balcon = balcon;
    }
    
    
    
}
