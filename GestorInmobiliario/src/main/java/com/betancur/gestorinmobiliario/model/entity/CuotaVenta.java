/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model;

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
    private int numeroCuota;

    @Column(name = "monto_cuota")
    private float montoCuota;

    @ManyToOne
    @JoinColumn(name = "fk_contrato_venta", nullable = false, updatable = true)
    private ContratoVenta unContratoVenta;

    public CuotaVenta() {
    }

    public CuotaVenta(int numeroCuota, float montoCuota, ContratoVenta unContratoVenta) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuotaVenta)) {
            return false;
        }
        CuotaVenta other = (CuotaVenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betancur.gestorinmobiliario.model.CuotaVenta[ id=" + id + " ]";
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public float getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(float montoCuota) {
        this.montoCuota = montoCuota;
    }

    public ContratoVenta getUnContratoVenta() {
        return unContratoVenta;
    }

    public void setUnContratoVenta(ContratoVenta unContratoVenta) {
        this.unContratoVenta = unContratoVenta;
    }

}
