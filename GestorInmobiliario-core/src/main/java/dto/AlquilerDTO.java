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
public class AlquilerDTO {

    private Long id;
    private String unaFechaInicio;
    private String unaFechaFin;
    private ContratoAlquilerDTO unContratoAlquiler;
    private InmuebleDTO unInmueble;
    private InmobiliariaDTO unaInmobiliariaAlquiler;
    private Boolean completa;

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

    public ContratoAlquilerDTO getUnContratoAlquiler() {
        return unContratoAlquiler;
    }

    public void setUnContratoAlquiler(ContratoAlquilerDTO unContratoAlquiler) {
        this.unContratoAlquiler = unContratoAlquiler;
    }

    public InmuebleDTO getUnInmueble() {
        return unInmueble;
    }

    public void setUnInmueble(InmuebleDTO unInmueble) {
        this.unInmueble = unInmueble;
    }

    public InmobiliariaDTO getUnaInmobiliariaAlquiler() {
        return unaInmobiliariaAlquiler;
    }

    public void setUnaInmobiliariaAlquiler(InmobiliariaDTO unaInmobiliariaAlquiler) {
        this.unaInmobiliariaAlquiler = unaInmobiliariaAlquiler;
    }

    public Boolean getCompleta() {
        return completa;
    }

    public void setCompleta(Boolean completa) {
        this.completa = completa;
    }

}
