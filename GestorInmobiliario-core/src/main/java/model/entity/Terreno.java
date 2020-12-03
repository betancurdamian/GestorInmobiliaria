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
@DiscriminatorValue("TERRENO")
public class Terreno extends Inmueble {

    @Column(name = "ancho")
    private Integer ancho;

    @Column(name = "largo")
    private Integer largo;

    public Terreno() {
    }

    public Terreno(Integer ancho, Integer largo) {
        this.ancho = ancho;
        this.largo = largo;
    }

    public Terreno(Integer ancho, Integer largo, String direccionCalle, String direccionNumero, Integer superficieTotal, Boolean disponible, String descripcion, Inmobiliaria unaInmobiliariaInmueble, Locador unLocador, Float precioBaseVenta, Float precioBaseAlquiler) {
        super(direccionCalle, direccionNumero, superficieTotal, disponible, descripcion, unaInmobiliariaInmueble, unLocador, precioBaseVenta, precioBaseAlquiler);
        this.ancho = ancho;
        this.largo = largo;
    }

   

    

    public Integer getAncho() {
        return ancho;
    }

    public void setAncho(Integer ancho) {
        this.ancho = ancho;
    }

    public Integer getLargo() {
        return largo;
    }

    public void setLargo(Integer largo) {
        this.largo = largo;
    }

}
