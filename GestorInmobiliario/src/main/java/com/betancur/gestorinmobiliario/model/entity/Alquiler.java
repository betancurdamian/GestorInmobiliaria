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
@Table(name = "alquileres")
public class Alquiler implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio")
    private Date unaFechaInicio;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin")
    private Date unaFechaFin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_contrato_alquiler")
    private ContratoAlquiler unContratoAlquiler;

    @OneToOne
    @JoinColumn(name = "fk_inmueble")
    private Inmueble unInmueble;

    @ManyToOne
    @JoinColumn(name = "fk_inmobiliaria")
    private Inmobiliaria unaInmobiliariaAlquiler;
    
    @Column(name = "disponible")
    private boolean disponible;

    public Alquiler() {
    }

    public Alquiler(Date unaFechaInicio, Date unaFechaFin, Inmueble unInmueble, Inmobiliaria unaInmobiliariaAlquiler, boolean disponible) {
        this.unaFechaInicio = unaFechaInicio;
        this.unaFechaFin = unaFechaFin;
        this.unInmueble = unInmueble;
        this.unaInmobiliariaAlquiler = unaInmobiliariaAlquiler;
        this.disponible = disponible;
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
        if (!(object instanceof Alquiler)) {
            return false;
        }
        Alquiler other = (Alquiler) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betancur.gestorinmobiliario.model.Alquiler[ id=" + id + " ]";
    }

    public Date getUnaFechaInicio() {
        return unaFechaInicio;
    }

    public void setUnaFechaInicio(Date unaFechaInicio) {
        this.unaFechaInicio = unaFechaInicio;
    }

    public Date getUnaFechaFin() {
        return unaFechaFin;
    }

    public void setUnaFechaFin(Date unaFechaFin) {
        this.unaFechaFin = unaFechaFin;
    }

    public Contrato getUnContratoAlquiler() {
        return unContratoAlquiler;
    }

    public void setUnContratoAlquiler(ContratoAlquiler unContratoAlquiler) {
        this.unContratoAlquiler = unContratoAlquiler;
    }

    public Inmueble getUnInmueble() {
        return unInmueble;
    }

    public void setUnInmueble(Inmueble unInmueble) {
        this.unInmueble = unInmueble;
    }

    public Inmobiliaria getUnaInmobiliariaAlquiler() {
        return unaInmobiliariaAlquiler;
    }

    public void setUnaInmobiliariaAlquiler(Inmobiliaria unaInmobiliariaAlquiler) {
        this.unaInmobiliariaAlquiler = unaInmobiliariaAlquiler;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    
}
