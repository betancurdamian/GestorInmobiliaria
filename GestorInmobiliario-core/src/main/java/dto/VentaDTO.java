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
    private boolean completa;
    private ContratoVentaDTO unContratoVentaDTO;
    private InmobiliariaDTO unaInmobiliariaVentaDTO;

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

    public boolean isCompleta() {
        return completa;
    }

    public void setCompleta(boolean completa) {
        this.completa = completa;
    }

    public ContratoVentaDTO getUnContratoVentaDTO() {
        return unContratoVentaDTO;
    }

    public void setUnContratoVentaDTO(ContratoVentaDTO unContratoVentaDTO) {
        this.unContratoVentaDTO = unContratoVentaDTO;
    }

    public InmobiliariaDTO getUnaInmobiliariaVentaDTO() {
        return unaInmobiliariaVentaDTO;
    }

    public void setUnaInmobiliariaVentaDTO(InmobiliariaDTO unaInmobiliariaVentaDTO) {
        this.unaInmobiliariaVentaDTO = unaInmobiliariaVentaDTO;
    }
    
    
}
