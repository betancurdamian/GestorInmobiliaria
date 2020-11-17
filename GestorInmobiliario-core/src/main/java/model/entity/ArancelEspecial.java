/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Ariel
 */
@Entity
@Table(name = "aranceles_especiales")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_arancel")
public abstract class ArancelEspecial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fecha_recargo")
    private LocalDate unaFechaArancel;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "monto")
    private Float monto;

    @ManyToOne
    @JoinColumn(name = "fk_inmobiliaria")
    private Inmobiliaria unaInmobiliariaArancelEspecial;

    public ArancelEspecial() {
    }

    public ArancelEspecial(LocalDate unaFechaArancel, String descripcion, Float monto, Inmobiliaria unaInmobiliariaArancelEspecial) {
        this.unaFechaArancel = unaFechaArancel;
        this.descripcion = descripcion;
        this.monto = monto;
        this.unaInmobiliariaArancelEspecial = unaInmobiliariaArancelEspecial;
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
        if (!(object instanceof ArancelEspecial)) {
            return false;
        }
        ArancelEspecial other = (ArancelEspecial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betancur.gestorinmobiliario.model.ArancelEspecial[ id=" + id + " ]";
    }

    public LocalDate getUnaFechaArancel() {
        return unaFechaArancel;
    }

    public void setUnaFechaArancel(LocalDate unaFechaArancel) {
        this.unaFechaArancel = unaFechaArancel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public Inmobiliaria getUnaInmobiliariaArancelEspecial() {
        return unaInmobiliariaArancelEspecial;
    }

    public void setUnaInmobiliariaArancelEspecial(Inmobiliaria unaInmobiliariaArancelEspecial) {
        this.unaInmobiliariaArancelEspecial = unaInmobiliariaArancelEspecial;
    }

}
