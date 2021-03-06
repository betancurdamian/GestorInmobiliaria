/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

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
@Table(name="ventas")
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "fecha_venta", columnDefinition = "DATE")
    private LocalDate unaFechaVenta;
    
    @Column(name = "completa")
    private Boolean completa;
    
    @ManyToOne
    @JoinColumn(name = "fk_contrato_venta")
    private ContratoVenta unContratoVenta;

    @ManyToOne
    @JoinColumn(name = "fk_inmobiliaria")
    private Inmobiliaria unaInmobiliariaVenta;
    
    @ManyToOne
    @JoinColumn(name = "fk_inmueble")
    private Inmueble unInmuebleVenta;

    public Venta() {
    }

    public Venta(LocalDate unaFechaVenta, Boolean completa, Inmobiliaria unaInmobiliariaVenta, Inmueble unInmuebleVenta) {
        this.unaFechaVenta = unaFechaVenta;
        this.completa = completa;
        this.unaInmobiliariaVenta = unaInmobiliariaVenta;
        this.unInmuebleVenta = unInmuebleVenta;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getUnaFechaVenta() {
        return unaFechaVenta;
    }

    public void setUnaFechaVenta(LocalDate unaFechaVenta) {
        this.unaFechaVenta = unaFechaVenta;
    }

    public Boolean getCompleta() {
        return completa;
    }

    public void setCompleta(Boolean completa) {
        this.completa = completa;
    }

    public ContratoVenta getUnContratoVenta() {
        return unContratoVenta;
    }

    public void setUnContratoVenta(ContratoVenta unContratoVenta) {
        this.unContratoVenta = unContratoVenta;
    }

    public Inmobiliaria getUnaInmobiliariaVenta() {
        return unaInmobiliariaVenta;
    }

    public void setUnaInmobiliariaVenta(Inmobiliaria unaInmobiliariaVenta) {
        this.unaInmobiliariaVenta = unaInmobiliariaVenta;
    }

    public Inmueble getUnInmuebleVenta() {
        return unInmuebleVenta;
    }

    public void setUnInmuebleVenta(Inmueble unInmuebleVenta) {
        this.unInmuebleVenta = unInmuebleVenta;
    }

    
}
