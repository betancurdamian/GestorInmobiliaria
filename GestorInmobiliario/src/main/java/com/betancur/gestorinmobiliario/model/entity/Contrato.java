/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ariel
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_contrato")
@Table(name = "contratos")
public abstract class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_primer_vencimiento")
    private Date unaFechaPrimerVencimiento;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_segundo_vencimiento")
    private Date unaFechaSegundoVencimiento;

    @Column(name = "monto_total")
    private float montoTotal;

    @Column(name = "cantidad_cuota")
    private int cantidadDeCuotas;

    @ManyToOne
    @JoinColumn(name = "fk_recargo_mora", nullable = false)
    private RecargoPorMora unRecargoPorMora;

    @ManyToOne
    @JoinColumn(name = "fk_locador", nullable = false)
    private Locador unLocador;

    @ManyToOne
    @JoinColumn(name = "fk_locatario", nullable = false)
    private Locatario unLocatario;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "fk_comision")
    private Comision unaComision;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private List<ArancelEspecial> arancelesEspeciales;
    
    @OneToMany(mappedBy = "unContrato", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BoletaDePago> boletasDePago;

    public Contrato() {
        this.arancelesEspeciales = new ArrayList<>();
        this.boletasDePago = new ArrayList<>();
    }

    public Contrato(Date unaFechaPrimerVencimiento, Date unaFechaSegundoVencimiento, float montoTotal, int cantidadDeCuotas, RecargoPorMora unRecargoPorMora, Locador unLocador, Locatario unLocatario) {
        this.unaFechaPrimerVencimiento = unaFechaPrimerVencimiento;
        this.unaFechaSegundoVencimiento = unaFechaSegundoVencimiento;
        this.montoTotal = montoTotal;
        this.cantidadDeCuotas = cantidadDeCuotas;
        this.unRecargoPorMora = unRecargoPorMora;
        this.unLocador = unLocador;
        this.unLocatario = unLocatario;
        this.arancelesEspeciales = new ArrayList<>();
        this.boletasDePago = new ArrayList<>();
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
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betancur.gestorinmobiliario.model.Contrato[ id=" + id + " ]";
    }

    public Comision getUnaComision() {
        return unaComision;
    }

    public void setUnaComision(Comision unaComision) {
        this.unaComision = unaComision;
    }

    public RecargoPorMora getUnRecargoPorMora() {
        return unRecargoPorMora;
    }

    public void setUnRecargoPorMora(RecargoPorMora unRecargoPorMora) {
        this.unRecargoPorMora = unRecargoPorMora;
    }

    public Locador getUnLocador() {
        return unLocador;
    }

    public void setUnLocador(Locador unLocador) {
        this.unLocador = unLocador;
    }

    public Locatario getUnLocatario() {
        return unLocatario;
    }

    public void setUnLocatario(Locatario unLocatario) {
        this.unLocatario = unLocatario;
    }

    public List<ArancelEspecial> getArancelesEspeciales() {
        return arancelesEspeciales;
    }

    public void setArancelesEspeciales(List<ArancelEspecial> arancelesEspeciales) {
        this.arancelesEspeciales = arancelesEspeciales;
    }

    public Date getUnaFechaPrimerVencimiento() {
        return unaFechaPrimerVencimiento;
    }

    public void setUnaFechaPrimerVencimiento(Date unaFechaPrimerVencimiento) {
        this.unaFechaPrimerVencimiento = unaFechaPrimerVencimiento;
    }

    public Date getUnaFechaSegundoVencimiento() {
        return unaFechaSegundoVencimiento;
    }

    public void setUnaFechaSegundoVencimiento(Date unaFechaSegundoVencimiento) {
        this.unaFechaSegundoVencimiento = unaFechaSegundoVencimiento;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public int getCantidadDeCuotas() {
        return cantidadDeCuotas;
    }

    public void setCantidadDeCuotas(int cantidadDeCuotas) {
        this.cantidadDeCuotas = cantidadDeCuotas;
    }

    public List<BoletaDePago> getBoletasDePago() {
        return boletasDePago;
    }

    public void setBoletasDePago(List<BoletaDePago> boletasDePago) {
        this.boletasDePago = boletasDePago;
    }

    
}
