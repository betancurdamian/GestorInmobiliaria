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
public class LineaDeComisionDTO {
    private Long id;
    private Integer numeroCuota;
    private Float monto;    
    private ComisionDTO unaComision;

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

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public ComisionDTO getUnaComision() {
        return unaComision;
    }

    public void setUnaComision(ComisionDTO unaComision) {
        this.unaComision = unaComision;
    }
    
    
}
