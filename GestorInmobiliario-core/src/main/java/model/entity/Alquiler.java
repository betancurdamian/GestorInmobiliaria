/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
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
@Table(name = "alquileres")
public class Alquiler implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fecha_inicio")
    private LocalDate unaFechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate unaFechaFin;

    @ManyToOne
    @JoinColumn(name = "fk_contrato_alquiler")
    private ContratoAlquiler unContratoAlquiler;

    @ManyToOne
    @JoinColumn(name = "fk_inmueble")
    private Inmueble unInmuebleAlquiler;

    @ManyToOne
    @JoinColumn(name = "fk_inmobiliaria")
    private Inmobiliaria unaInmobiliariaAlquiler;

    @Column(name = "disponible")
    private Boolean disponible;

    public Alquiler() {
    }

    public Alquiler(LocalDate unaFechaInicio, LocalDate unaFechaFin, Inmueble unInmueble, Inmobiliaria unaInmobiliariaAlquiler, Boolean disponible) {
        this.unaFechaInicio = unaFechaInicio;
        this.unaFechaFin = unaFechaFin;
        this.unInmuebleAlquiler = unInmueble;
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

    public LocalDate getUnaFechaInicio() {
        return unaFechaInicio;
    }

    public void setUnaFechaInicio(LocalDate unaFechaInicio) {
        this.unaFechaInicio = unaFechaInicio;
    }

    public LocalDate getUnaFechaFin() {
        return unaFechaFin;
    }

    public void setUnaFechaFin(LocalDate unaFechaFin) {
        this.unaFechaFin = unaFechaFin;
    }

    public ContratoAlquiler getUnContratoAlquiler() {
        return unContratoAlquiler;
    }

    public void setUnContratoAlquiler(ContratoAlquiler unContratoAlquiler) {
        this.unContratoAlquiler = unContratoAlquiler;
    }

    public Inmueble getUnInmuebleAlquiler() {
        return unInmuebleAlquiler;
    }

    public void setUnInmuebleAlquiler(Inmueble unInmuebleAlquiler) {
        this.unInmuebleAlquiler = unInmuebleAlquiler;
    }

    public Inmobiliaria getUnaInmobiliariaAlquiler() {
        return unaInmobiliariaAlquiler;
    }

    public void setUnaInmobiliariaAlquiler(Inmobiliaria unaInmobiliariaAlquiler) {
        this.unaInmobiliariaAlquiler = unaInmobiliariaAlquiler;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
    
}
