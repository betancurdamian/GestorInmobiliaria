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
    private List<CuotaVentaDTO> cuotasVentaDTO;
    private VentaDTO unaVentaDTO;
    
    public List<CuotaVentaDTO> getCuotasVentaDTO() {
        return cuotasVentaDTO;
    }

    public void setCuotasVentaDTO(List<CuotaVentaDTO> cuotasVentaDTO) {
        this.cuotasVentaDTO = cuotasVentaDTO;
    }

    public VentaDTO getUnaVentaDTO() {
        return unaVentaDTO;
    }

    public void setUnaVentaDTO(VentaDTO unaVentaDTO) {
        this.unaVentaDTO = unaVentaDTO;
    }
    
    
}
