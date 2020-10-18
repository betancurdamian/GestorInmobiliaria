/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Ariel
 */
@Entity
@Table(name = "comprobantes_ingresos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_comprobante")
public abstract class ComprobanteDeIngreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "mes")
    private int mes;

    @Column(name = "anio")
    private int anio;

    @Column(name = "importe_bruto")
    private float importeBruto;

    @Column(name = "importe_neto")
    private float importeNeto;

    @ManyToMany(mappedBy = "comprobantesDeIngresosLocatarios")
    private List<Locatario> locatarios;

    @ManyToMany(mappedBy = "comprobantesDeIngresosGarantes")
    private List<Garante> garantes;

    public ComprobanteDeIngreso() {
        this.locatarios = new ArrayList<>();
        this.garantes = new ArrayList<>();
    }

    public ComprobanteDeIngreso(int mes, int anio, float importeBruto, float importeNeto) {
        this.mes = mes;
        this.anio = anio;
        this.importeBruto = importeBruto;
        this.importeNeto = importeNeto;
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
        if (!(object instanceof ComprobanteDeIngreso)) {
            return false;
        }
        ComprobanteDeIngreso other = (ComprobanteDeIngreso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betancur.gestorinmobiliario.model.ComprobanteDeIngreso[ id=" + id + " ]";
    }

    public List<Locatario> getLocatarios() {
        return locatarios;
    }

    public void setLocatarios(List<Locatario> locatarios) {
        this.locatarios = locatarios;
    }

    public List<Garante> getGarantes() {
        return garantes;
    }

    public void setGarantes(List<Garante> garantes) {
        this.garantes = garantes;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public float getImporteBruto() {
        return importeBruto;
    }

    public void setImporteBruto(float importeBruto) {
        this.importeBruto = importeBruto;
    }

    public float getImporteNeto() {
        return importeNeto;
    }

    public void setImporteNeto(float importeNeto) {
        this.importeNeto = importeNeto;
    }

}
