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
public abstract class ArancelEspecialDTO {
    
    private Long id;
    private String unaFechaDeRecargo;    
    private String descripcion;    
    private Float monto;    
    private InmobiliariaDTO unaInmobiliariaArancelEspecialDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnaFechaDeRecargo() {
        return unaFechaDeRecargo;
    }

    public void setUnaFechaDeRecargo(String unaFechaDeRecargo) {
        this.unaFechaDeRecargo = unaFechaDeRecargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public InmobiliariaDTO getUnaInmobiliariaArancelEspecialDTO() {
        return unaInmobiliariaArancelEspecialDTO;
    }

    public void setUnaInmobiliariaArancelEspecialDTO(InmobiliariaDTO unaInmobiliariaArancelEspecialDTO) {
        this.unaInmobiliariaArancelEspecialDTO = unaInmobiliariaArancelEspecialDTO;
    }
    
    
}
