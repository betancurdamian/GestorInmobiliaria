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
public class RecargoPorMoraDTO {
    private Long id;
    private String unaFechaDeRecargo;
    private Float monto;
    private InmobiliariaDTO unaInmobiliariaRecargoPorMoraDTO;

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

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public InmobiliariaDTO getUnaInmobiliariaRecargoPorMoraDTO() {
        return unaInmobiliariaRecargoPorMoraDTO;
    }

    public void setUnaInmobiliariaRecargoPorMoraDTO(InmobiliariaDTO unaInmobiliariaRecargoPorMoraDTO) {
        this.unaInmobiliariaRecargoPorMoraDTO = unaInmobiliariaRecargoPorMoraDTO;
    }
    
    
}
