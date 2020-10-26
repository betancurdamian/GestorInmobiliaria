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
public class DepartamentoDTO extends InmuebleDTO{
    private Integer cantidadDeDormitorios;
    private Integer cantidadDeBanio;
    private Boolean balcon;

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
