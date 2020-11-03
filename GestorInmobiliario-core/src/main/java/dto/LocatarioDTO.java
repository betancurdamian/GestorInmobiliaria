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
public abstract class LocatarioDTO extends ClienteDTO{
    private ActividadDTO unaActividad;
    private GaranteDTO unGarante;
    private List<ComprobanteDeIngresoDTO> comprobantesDeIngresosLocatarios;

    public ActividadDTO getUnaActividad() {
        return unaActividad;
    }

    public void setUnaActividad(ActividadDTO unaActividad) {
        this.unaActividad = unaActividad;
    }

    public GaranteDTO getUnGarante() {
        return unGarante;
    }

    public void setUnGarante(GaranteDTO unGarante) {
        this.unGarante = unGarante;
    }

    public List<ComprobanteDeIngresoDTO> getComprobantesDeIngresosLocatarios() {
        return comprobantesDeIngresosLocatarios;
    }

    public void setComprobantesDeIngresosLocatarios(List<ComprobanteDeIngresoDTO> comprobantesDeIngresosLocatarios) {
        this.comprobantesDeIngresosLocatarios = comprobantesDeIngresosLocatarios;
    }
    
    
}
