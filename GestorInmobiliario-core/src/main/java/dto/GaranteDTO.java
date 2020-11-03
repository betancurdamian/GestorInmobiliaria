/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;

/**
 *
 * @author Ariel
 */
public abstract class GaranteDTO extends PersonaDTO{
    private ActividadDTO unaActividad;
    private InmobiliariaDTO unaInmobiliariaGarante;
    private LocatarioDTO unLocatario;
    private ContratoAlquilerDTO unContratoAlquiler;
    private List<ComprobanteDeIngresoDTO> comprobantesDeIngresosGarantes;

    public ActividadDTO getUnaActividad() {
        return unaActividad;
    }

    public void setUnaActividad(ActividadDTO unaActividad) {
        this.unaActividad = unaActividad;
    }

    public InmobiliariaDTO getUnaInmobiliariaGarante() {
        return unaInmobiliariaGarante;
    }

    public void setUnaInmobiliariaGarante(InmobiliariaDTO unaInmobiliariaGarante) {
        this.unaInmobiliariaGarante = unaInmobiliariaGarante;
    }

    public LocatarioDTO getUnLocatario() {
        return unLocatario;
    }

    public void setUnLocatario(LocatarioDTO unLocatario) {
        this.unLocatario = unLocatario;
    }

    public ContratoAlquilerDTO getUnContratoAlquiler() {
        return unContratoAlquiler;
    }

    public void setUnContratoAlquiler(ContratoAlquilerDTO unContratoAlquiler) {
        this.unContratoAlquiler = unContratoAlquiler;
    }

    public List<ComprobanteDeIngresoDTO> getComprobantesDeIngresosGarantes() {
        return comprobantesDeIngresosGarantes;
    }

    public void setComprobantesDeIngresosGarantes(List<ComprobanteDeIngresoDTO> comprobantesDeIngresosGarantes) {
        this.comprobantesDeIngresosGarantes = comprobantesDeIngresosGarantes;
    }
    
    
}
