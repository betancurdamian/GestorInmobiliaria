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
@DiscriminatorValue("DEPARTAMENTO")
public class Departamento extends Inmueble {

    @Column(name = "cantidad_dormitorio")
    private Integer cantidadDeDormitorios;

    @Column(name = "cantidad_banio")
    private Integer cantidadDeBanio;

    @Column(name = "balcon")
    private Boolean balcon;

    public Departamento() {
    }

    public Departamento(Integer cantidadDeDormitorios, Integer cantidadDeBanio, Boolean balcon) {
        this.cantidadDeDormitorios = cantidadDeDormitorios;
        this.cantidadDeBanio = cantidadDeBanio;
        this.balcon = balcon;
    }

    public Departamento(Integer cantidadDeDormitorios, Integer cantidadDeBanio, Boolean balcon, String direccionCalle, String direccionNumero, Provincia direccionProvincia, Localidad direccionLocalidad, Barrio direccionBarrio, Integer superficieTotal, Boolean disponible, String descripcion, Inmobiliaria unaInmobiliariaInmueble, Locador unaLocador) {
        super(direccionCalle, direccionNumero, direccionProvincia, direccionLocalidad, direccionBarrio, superficieTotal, disponible, descripcion, unaInmobiliariaInmueble, unaLocador);
        this.cantidadDeDormitorios = cantidadDeDormitorios;
        this.cantidadDeBanio = cantidadDeBanio;
        this.balcon = balcon;
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

    public Boolean getBalcon() {
        return balcon;
    }

    public void setBalcon(Boolean balcon) {
        this.balcon = balcon;
    }

}
