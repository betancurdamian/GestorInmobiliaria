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
@DiscriminatorValue("TERRENO")
public class Terreno extends Inmueble {

    @Column(name = "ancho")
    private int ancho;

    @Column(name = "largo")
    private int largo;

    public Terreno() {

    }

    public Terreno(int ancho, int largo) {
        this.ancho = ancho;
        this.largo = largo;
    }

    public Terreno(int ancho, int largo, String direccionCalle, String direccionNumero, Barrio direccionBarrio, int superficieTotal, boolean estaDisponible, String descripcion, Inmobiliaria unaInmobiliariaInmueble) {
        super(direccionCalle, direccionNumero, direccionBarrio, superficieTotal, estaDisponible, descripcion, unaInmobiliariaInmueble);
        this.ancho = ancho;
        this.largo = largo;
    }

    
   

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

}
