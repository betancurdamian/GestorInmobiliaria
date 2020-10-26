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
    private ContratoAlquilerDTO unContratoAlquilerDTO;
    private InmuebleDTO unInmuebleDTO;
    private InmobiliariaDTO unaInmobiliariaAlquilerDTO;
    private Boolean disponible;

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

    public ContratoAlquilerDTO getUnContratoAlquilerDTO() {
        return unContratoAlquilerDTO;
    }

    public void setUnContratoAlquilerDTO(ContratoAlquilerDTO unContratoAlquilerDTO) {
        this.unContratoAlquilerDTO = unContratoAlquilerDTO;
    }

    public InmuebleDTO getUnInmuebleDTO() {
        return unInmuebleDTO;
    }

    public void setUnInmuebleDTO(InmuebleDTO unInmuebleDTO) {
        this.unInmuebleDTO = unInmuebleDTO;
    }

    public InmobiliariaDTO getUnaInmobiliariaAlquilerDTO() {
        return unaInmobiliariaAlquilerDTO;
    }

    public void setUnaInmobiliariaAlquilerDTO(InmobiliariaDTO unaInmobiliariaAlquilerDTO) {
        this.unaInmobiliariaAlquilerDTO = unaInmobiliariaAlquilerDTO;
    }

    public Boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
    
    

}