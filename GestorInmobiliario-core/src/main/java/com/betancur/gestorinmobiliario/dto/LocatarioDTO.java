/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.dto;

import java.util.List;

/**
 *
 * @author Ariel
 */
public abstract class LocatarioDTO extends ClienteDTO{
    private ActividadDTO unaActividadDTO;
    private GaranteDTO unGaranteDTO;
    private List<ComprobanteDeIngresoDTO> comprobantesDeIngresosLocatariosDTO;

    public ActividadDTO getUnaActividadDTO() {
        return unaActividadDTO;
    }

    public void setUnaActividadDTO(ActividadDTO unaActividadDTO) {
        this.unaActividadDTO = unaActividadDTO;
    }

    public GaranteDTO getUnGaranteDTO() {
        return unGaranteDTO;
    }

    public void setUnGaranteDTO(GaranteDTO unGaranteDTO) {
        this.unGaranteDTO = unGaranteDTO;
    }

    public List<ComprobanteDeIngresoDTO> getComprobantesDeIngresosLocatariosDTO() {
        return comprobantesDeIngresosLocatariosDTO;
    }

    public void setComprobantesDeIngresosLocatariosDTO(List<ComprobanteDeIngresoDTO> comprobantesDeIngresosLocatariosDTO) {
        this.comprobantesDeIngresosLocatariosDTO = comprobantesDeIngresosLocatariosDTO;
    }
    
    
}
