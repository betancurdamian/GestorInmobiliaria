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
public class ContratoVentaDTO extends ContratoDTO{
    private List<CuotaVentaDTO> cuotasVenta;
    private VentaDTO unaVenta;
    
    public List<CuotaVentaDTO> getCuotasVenta() {
        return cuotasVenta;
    }

    public void setCuotasVenta(List<CuotaVentaDTO> cuotasVenta) {
        this.cuotasVenta = cuotasVenta;
    }

    public VentaDTO getUnaVenta() {
        return unaVenta;
    }

    public void setUnaVenta(VentaDTO unaVenta) {
        this.unaVenta = unaVenta;
    }
    
    
}
