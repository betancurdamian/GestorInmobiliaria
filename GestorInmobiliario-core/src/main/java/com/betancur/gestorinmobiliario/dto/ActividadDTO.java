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
public class ActividadDTO {
    private Long id;

    private String unaFechaInicio;

    private String unaFechaFin;

    private String unContratoAlquiler;

    private String unInmueble;

    private String unaInmobiliariaAlquiler;

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

    public String getUnContratoAlquiler() {
        return unContratoAlquiler;
    }

    public void setUnContratoAlquiler(String unContratoAlquiler) {
        this.unContratoAlquiler = unContratoAlquiler;
    }

    public String getUnInmueble() {
        return unInmueble;
    }

    public void setUnInmueble(String unInmueble) {
        this.unInmueble = unInmueble;
    }

    public String getUnaInmobiliariaAlquiler() {
        return unaInmobiliariaAlquiler;
    }

    public void setUnaInmobiliariaAlquiler(String unaInmobiliariaAlquiler) {
        this.unaInmobiliariaAlquiler = unaInmobiliariaAlquiler;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    
}
