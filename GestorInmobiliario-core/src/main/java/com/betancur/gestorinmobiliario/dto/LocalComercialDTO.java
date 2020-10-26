/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.dto;

/**
 *
 * @author Ariel
 */
public class LocalComercialDTO extends InmuebleDTO {

    private Integer cantidadDeBanio;
    private Boolean cochera;

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
