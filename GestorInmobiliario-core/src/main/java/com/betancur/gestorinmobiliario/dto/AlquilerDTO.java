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
public class AlquilerDTO {

    private Long id;
    private String unaFechaInicio;
    private String unaFechaFin;
    private Long unContratoAlquilerID;
    private Long unInmuebleID;
    private Long unaInmobiliariaAlquilerID;
    private boolean disponible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnaFechaInicio() {
        return unaFechaInicio;
    }

    public void setUnaFechaInicio(String unaFechaInicio) {
        this.unaFechaInicio = unaFechaInicio;
    }

    public String getUnaFechaFin() {
        return unaFechaFin;
    }

    public void setUnaFechaFin(String unaFechaFin) {
        this.unaFechaFin = unaFechaFin;
    }

    public Long getUnContratoAlquilerID() {
        return unContratoAlquilerID;
    }

    public void setUnContratoAlquilerID(Long unContratoAlquilerID) {
        this.unContratoAlquilerID = unContratoAlquilerID;
    }

    public Long getUnInmuebleID() {
        return unInmuebleID;
    }

    public void setUnInmuebleID(Long unInmuebleID) {
        this.unInmuebleID = unInmuebleID;
    }

    public Long getUnaInmobiliariaAlquilerID() {
        return unaInmobiliariaAlquilerID;
    }

    public void setUnaInmobiliariaAlquilerID(Long unaInmobiliariaAlquilerID) {
        this.unaInmobiliariaAlquilerID = unaInmobiliariaAlquilerID;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    
}
