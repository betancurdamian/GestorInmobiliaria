/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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
    
    @Column(name = "fecha_primer_vencimiento")
    private LocalDate unaFechaPrimerVencimiento;

    @Column(name = "fecha_segundo_vencimiento")
    private LocalDate unaFechaSegundoVencimiento;

    @Column(name = "monto_total")
    private Float montoTotal;

    @Column(name = "cantidad_cuota")
    private Integer cantidadDeCuotas;

    @ManyToOne
    @JoinColumn(name = "fk_recargo_mora")
    private RecargoPorMora unRecargoPorMora;

    @ManyToOne
    @JoinColumn(name = "fk_locador")
    private Locador unLocador;

    @ManyToOne
    @JoinColumn(name = "fk_locatario")
    private Locatario unLocatario;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "fk_comision")
    private Comision unaComision;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ArancelEspecial> arancelesEspeciales;
    
    @OneToMany(mappedBy = "unContrato", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BoletaDePago> boletasDePago;

    public Contrato() {
        this.arancelesEspeciales = new ArrayList<>();
        this.boletasDePago = new ArrayList<>();
    }

    public Contrato(LocalDate unaFechaPrimerVencimiento, LocalDate unaFechaSegundoVencimiento, Float montoTotal, Integer cantidadDeCuotas, RecargoPorMora unRecargoPorMora, Locador unLocador, Locatario unLocatario) {
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

    public LocalDate getUnaFechaPrimerVencimiento() {
        return unaFechaPrimerVencimiento;
    }

    public void setUnaFechaPrimerVencimiento(LocalDate unaFechaPrimerVencimiento) {
        this.unaFechaPrimerVencimiento = unaFechaPrimerVencimiento;
    }

    public LocalDate getUnaFechaSegundoVencimiento() {
        return unaFechaSegundoVencimiento;
    }

    public void setUnaFechaSegundoVencimiento(LocalDate unaFechaSegundoVencimiento) {
        this.unaFechaSegundoVencimiento = unaFechaSegundoVencimiento;
    }

    public Float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getCantidadDeCuotas() {
        return cantidadDeCuotas;
    }

    public void setCantidadDeCuotas(Integer cantidadDeCuotas) {
        this.cantidadDeCuotas = cantidadDeCuotas;
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

    public Comision getUnaComision() {
        return unaComision;
    }

    public void setUnaComision(Comision unaComision) {
        this.unaComision = unaComision;
    }

    public List<ArancelEspecial> getArancelesEspeciales() {
        return arancelesEspeciales;
    }

    public void setArancelesEspeciales(List<ArancelEspecial> arancelesEspeciales) {
        this.arancelesEspeciales = arancelesEspeciales;
    }

    public List<BoletaDePago> getBoletasDePago() {
        return boletasDePago;
    }

    public void setBoletasDePago(List<BoletaDePago> boletasDePago) {
        this.boletasDePago = boletasDePago;
    }

    
}
