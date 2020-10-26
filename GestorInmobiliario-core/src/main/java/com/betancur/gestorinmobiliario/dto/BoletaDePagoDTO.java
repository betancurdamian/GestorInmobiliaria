/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.dto;

/**
 *
 * @author Ariel
 */
public class BoletaDePagoDTO {
    private Long id;
    private String numeroBoleta;
    private String fechaPago;
    private Integer numeroCuota;
    private Float monto;
    private boolean pagado;
    private ContratoDTO unContratoDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroBoleta() {
        return numeroBoleta;
    }

    public void setNumeroBoleta(String numeroBoleta) {
        this.numeroBoleta = numeroBoleta;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
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

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public ContratoDTO getUnContratoDTO() {
        return unContratoDTO;
    }

    public void setUnContratoDTO(ContratoDTO unContratoDTO) {
        this.unContratoDTO = unContratoDTO;
    }
    
    
}
