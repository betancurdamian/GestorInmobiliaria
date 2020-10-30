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
public class CuotaVentaDTO {
    private Long id;
    private Integer numeroCuota;
    private Float montoCuota;
    private ContratoVentaDTO unContratoVentaDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Float getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(Float montoCuota) {
        this.montoCuota = montoCuota;
    }

    public ContratoVentaDTO getUnContratoVentaDTO() {
        return unContratoVentaDTO;
    }

    public void setUnContratoVentaDTO(ContratoVentaDTO unContratoVentaDTO) {
        this.unContratoVentaDTO = unContratoVentaDTO;
    }
    
    
}
