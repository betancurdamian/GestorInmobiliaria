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
public abstract class GaranteDTO extends PersonaDTO{
    private ActividadDTO unaActividadDTO;
    private InmobiliariaDTO unaInmobiliariaGaranteDTO;
    private LocatarioDTO unLocatarioDTO;
    private ContratoAlquilerDTO unContratoAlquilerDTO;
    private List<ComprobanteDeIngresoDTO> comprobantesDeIngresosGarantesDTO;

    public ActividadDTO getUnaActividadDTO() {
        return unaActividadDTO;
    }

    public void setUnaActividadDTO(ActividadDTO unaActividadDTO) {
        this.unaActividadDTO = unaActividadDTO;
    }

    public InmobiliariaDTO getUnaInmobiliariaGaranteDTO() {
        return unaInmobiliariaGaranteDTO;
    }

    public void setUnaInmobiliariaGaranteDTO(InmobiliariaDTO unaInmobiliariaGaranteDTO) {
        this.unaInmobiliariaGaranteDTO = unaInmobiliariaGaranteDTO;
    }

    public LocatarioDTO getUnLocatarioDTO() {
        return unLocatarioDTO;
    }

    public void setUnLocatarioDTO(LocatarioDTO unLocatarioDTO) {
        this.unLocatarioDTO = unLocatarioDTO;
    }

    public ContratoAlquilerDTO getUnContratoAlquilerDTO() {
        return unContratoAlquilerDTO;
    }

    public void setUnContratoAlquilerDTO(ContratoAlquilerDTO unContratoAlquilerDTO) {
        this.unContratoAlquilerDTO = unContratoAlquilerDTO;
    }

    public List<ComprobanteDeIngresoDTO> getComprobantesDeIngresosGarantesDTO() {
        return comprobantesDeIngresosGarantesDTO;
    }

    public void setComprobantesDeIngresosGarantesDTO(List<ComprobanteDeIngresoDTO> comprobantesDeIngresosGarantesDTO) {
        this.comprobantesDeIngresosGarantesDTO = comprobantesDeIngresosGarantesDTO;
    }
    
    
}
