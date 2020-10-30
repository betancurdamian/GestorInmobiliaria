/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Ariel
 */
@Entity
@Table(name="cuotas_ventas")
public class CuotaVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "numero_cuota")
    private Integer numeroCuota;

    @Column(name = "monto_cuota")
    private Float montoCuota;

    @ManyToOne
    @JoinColumn(name = "fk_contrato_venta", nullable = false, updatable = true)
    private ContratoVenta unContratoVenta;

    public CuotaVenta() {
    }

    public CuotaVenta(Integer numeroCuota, Float montoCuota, ContratoVenta unContratoVenta) {
        this.numeroCuota = numeroCuota;
        this.montoCuota = montoCuota;
        this.unContratoVenta = unContratoVenta;
    }

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

    public ContratoVenta getUnContratoVenta() {
        return unContratoVenta;
    }

    public void setUnContratoVenta(ContratoVenta unContratoVenta) {
        this.unContratoVenta = unContratoVenta;
    }

}
