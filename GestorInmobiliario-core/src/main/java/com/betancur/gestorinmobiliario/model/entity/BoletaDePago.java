/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "boletas_pagos")
public class BoletaDePago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "numero_boleta")
    private String numeroBoleta;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    @Column(name = "numero_cuota")
    private Integer numeroCuota;

    @Column(name = "monto")
    private Float monto;

    @Column(name = "pagado")
    private Boolean pagado;

    @ManyToOne
    @JoinColumn(name = "fk_contrato", nullable = false, updatable = true)
    private Contrato unContrato;

    public BoletaDePago() {

    }

    public BoletaDePago(String numeroBoleta, LocalDate fechaPago, Integer numeroCuota, Float monto, Boolean pagado, Contrato unContrato) {
        this.numeroBoleta = numeroBoleta;
        this.fechaPago = fechaPago;
        this.numeroCuota = numeroCuota;
        this.monto = monto;
        this.pagado = pagado;
        this.unContrato = unContrato;
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
        if (!(object instanceof BoletaDePago)) {
            return false;
        }
        BoletaDePago other = (BoletaDePago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betancur.gestorinmobiliario.model.BoletaDePago[ id=" + id + " ]";
    }

    public String getNumeroBoleta() {
        return numeroBoleta;
    }

    public void setNumeroBoleta(String numeroBoleta) {
        this.numeroBoleta = numeroBoleta;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
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

    public Boolean isPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    public Contrato getUnContrato() {
        return unContrato;
    }

    public void setUnContrato(Contrato unContrato) {
        this.unContrato = unContrato;
    }

}
