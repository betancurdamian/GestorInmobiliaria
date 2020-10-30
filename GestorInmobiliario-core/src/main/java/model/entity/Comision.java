/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Ariel
 */
@Entity
@Table(name="comisiones")
public class Comision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "fk_contrato")
    private Contrato unContrato;
    
    @Column(name = "cantidad_cuota")
    private Integer cantidadDeCuotas;
    
    @Column(name = "montoTotal")
    private Float montoTotal;

    @OneToMany(mappedBy = "unaComision", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<LineaDeComision> linesasDecomisiones;

    public Comision() {
        this.linesasDecomisiones = new ArrayList<>();
    }

    public Comision(Contrato unContrato, Integer cantidadDeCuotas, Float montoTotal) {
        this.unContrato = unContrato;
        this.cantidadDeCuotas = cantidadDeCuotas;
        this.montoTotal = montoTotal;
        this.linesasDecomisiones = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contrato getUnContrato() {
        return unContrato;
    }

    public void setUnContrato(Contrato unContrato) {
        this.unContrato = unContrato;
    }

    public Integer getCantidadDeCuotas() {
        return cantidadDeCuotas;
    }

    public void setCantidadDeCuotas(Integer cantidadDeCuotas) {
        this.cantidadDeCuotas = cantidadDeCuotas;
    }

    public Float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<LineaDeComision> getLinesasDecomisiones() {
        return linesasDecomisiones;
    }

    public void setLinesasDecomisiones(List<LineaDeComision> linesasDecomisiones) {
        this.linesasDecomisiones = linesasDecomisiones;
    }
    

    
}