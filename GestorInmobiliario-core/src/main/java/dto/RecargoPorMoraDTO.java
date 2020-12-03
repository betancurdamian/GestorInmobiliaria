/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Ariel
 */
public class RecargoPorMoraDTO {
    private Long id;
    private String unaFechaDeRecargo;
    private Float porcentaje;
    private InmobiliariaDTO unaInmobiliariaRecargoPorMora;

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

    public Float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public InmobiliariaDTO getUnaInmobiliariaRecargoPorMora() {
        return unaInmobiliariaRecargoPorMora;
    }

    public void setUnaInmobiliariaRecargoPorMora(InmobiliariaDTO unaInmobiliariaRecargoPorMora) {
        this.unaInmobiliariaRecargoPorMora = unaInmobiliariaRecargoPorMora;
    }
    
    
}
