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
public class VentaDTO {
    private Long id;
    private String unaFechaVenta;
    private Boolean completa;
    private ContratoVentaDTO unContratoVenta;
    private InmuebleDTO unInmuebleVenta;
    private InmobiliariaDTO unaInmobiliariaVenta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnaFechaVenta() {
        return unaFechaVenta;
    }

    public void setUnaFechaVenta(String unaFechaVenta) {
        this.unaFechaVenta = unaFechaVenta;
    }

    public Boolean getCompleta() {
        return completa;
    }

    public void setCompleta(Boolean completa) {
        this.completa = completa;
    }

    public ContratoVentaDTO getUnContratoVenta() {
        return unContratoVenta;
    }

    public void setUnContratoVenta(ContratoVentaDTO unContratoVenta) {
        this.unContratoVenta = unContratoVenta;
    }

    public InmuebleDTO getUnInmuebleVenta() {
        return unInmuebleVenta;
    }

    public void setUnInmuebleVenta(InmuebleDTO unInmuebleVenta) {
        this.unInmuebleVenta = unInmuebleVenta;
    }

    public InmobiliariaDTO getUnaInmobiliariaVenta() {
        return unaInmobiliariaVenta;
    }

    public void setUnaInmobiliariaVenta(InmobiliariaDTO unaInmobiliariaVenta) {
        this.unaInmobiliariaVenta = unaInmobiliariaVenta;
    }

    
}
