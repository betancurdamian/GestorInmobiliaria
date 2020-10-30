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
@DiscriminatorValue("LOCAL COMERCIAL")
public class LocalComercial extends Inmueble {

    @Column(name = "cantidad_banio")
    private Integer cantidadDeBanio;

    @Column(name = "cochera")
    private Boolean cochera;

    public LocalComercial() {
    }

    public LocalComercial(Integer cantidadDeBanio, Boolean cochera) {
        this.cantidadDeBanio = cantidadDeBanio;
        this.cochera = cochera;
    }

    public LocalComercial(Integer cantidadDeBanio, Boolean cochera, String direccionCalle, String direccionNumero, Provincia direccionProvincia, Localidad direccionLocalidad, Barrio direccionBarrio, Integer superficieTotal, Boolean disponible, String descripcion, Inmobiliaria unaInmobiliariaInmueble) {
        super(direccionCalle, direccionNumero, direccionProvincia, direccionLocalidad, direccionBarrio, superficieTotal, disponible, descripcion, unaInmobiliariaInmueble);
        this.cantidadDeBanio = cantidadDeBanio;
        this.cochera = cochera;
    }

    public Integer getCantidadDeBanio() {
        return cantidadDeBanio;
    }

    public void setCantidadDeBanio(Integer cantidadDeBanio) {
        this.cantidadDeBanio = cantidadDeBanio;
    }

    public Boolean getCochera() {
        return cochera;
    }

    public void setCochera(Boolean cochera) {
        this.cochera = cochera;
    }

    

}
