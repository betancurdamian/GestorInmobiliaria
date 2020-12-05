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
public class BoletaDePagoDTO {
    private Long id;
    private String numeroBoleta;
    private String fechaPago;
    private String fechaPrimerVencimientoPago;
    private String fechaSegundoVencimientoPago;
    private Integer numeroCuota;
    private Float monto;
    private Boolean pagado;
    private ContratoDTO unContrato;

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

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    public ContratoDTO getUnContrato() {
        return unContrato;
    }

    public void setUnContrato(ContratoDTO unContrato) {
        this.unContrato = unContrato;
    }

    public String getFechaPrimerVencimientoPago() {
        return fechaPrimerVencimientoPago;
    }

    public void setFechaPrimerVencimientoPago(String fechaPrimerVencimientoPago) {
        this.fechaPrimerVencimientoPago = fechaPrimerVencimientoPago;
    }

    public String getFechaSegundoVencimientoPago() {
        return fechaSegundoVencimientoPago;
    }

    public void setFechaSegundoVencimientoPago(String fechaSegundoVencimientoPago) {
        this.fechaSegundoVencimientoPago = fechaSegundoVencimientoPago;
    }

    
    
}
