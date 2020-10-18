/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ariel
 */
@Entity
@Table(name="ventas")
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_venta")
    private Date unaFechaVenta;
    
    @Column(name = "completa")
    private boolean completa;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_contrato_venta")
    private ContratoVenta unContratoVenta;
    
    
    @ManyToOne
    @JoinColumn(name = "fk_inmobiliaria", nullable = true, updatable = true)
    private Inmobiliaria unaInmobiliariaVenta;

    public Venta() {
    }

    public Venta(Date unaFechaVenta, boolean completa, ContratoVenta unContratoVenta, Inmobiliaria unaInmobiliariaVenta) {
        this.unaFechaVenta = unaFechaVenta;
        this.completa = completa;
        this.unContratoVenta = unContratoVenta;
        this.unaInmobiliariaVenta = unaInmobiliariaVenta;
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
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betancur.gestorinmobiliario.model.Venta[ id=" + id + " ]";
    }

    public Inmobiliaria getUnaInmobiliariaVenta() {
        return unaInmobiliariaVenta;
    }

    public void setUnaInmobiliariaVenta(Inmobiliaria unaInmobiliariaVenta) {
        this.unaInmobiliariaVenta = unaInmobiliariaVenta;
    }

    public Date getUnaFechaVenta() {
        return unaFechaVenta;
    }

    public void setUnaFechaVenta(Date unaFechaVenta) {
        this.unaFechaVenta = unaFechaVenta;
    }

    public boolean isCompleta() {
        return completa;
    }

    public void setCompleta(boolean completa) {
        this.completa = completa;
    }

    public ContratoVenta getUnContratoVenta() {
        return unContratoVenta;
    }

    public void setUnContratoVenta(ContratoVenta unContratoVenta) {
        this.unContratoVenta = unContratoVenta;
    }
    
    

}
