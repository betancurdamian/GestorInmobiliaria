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
public class ContratoAlquilerDTO extends ContratoDTO{
    private GaranteDTO unGaranteDTO;
    private AlquilerDTO unAlquilerDTO;

    public GaranteDTO getUnGaranteDTO() {
        return unGaranteDTO;
    }

    public void setUnGaranteDTO(GaranteDTO unGaranteDTO) {
        this.unGaranteDTO = unGaranteDTO;
    }

    public AlquilerDTO getUnAlquilerDTO() {
        return unAlquilerDTO;
    }

    public void setUnAlquilerDTO(AlquilerDTO unAlquilerDTO) {
        this.unAlquilerDTO = unAlquilerDTO;
    }
    
    
}
