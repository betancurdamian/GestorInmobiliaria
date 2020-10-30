/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

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
    private Integer mes;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "importe_bruto")
    private Float importeBruto;

    @Column(name = "importe_neto")
    private Float importeNeto;

    @ManyToMany(mappedBy = "comprobantesDeIngresosLocatarios")
    private List<Locatario> locatarios;

    @ManyToMany(mappedBy = "comprobantesDeIngresosGarantes")
    private List<Garante> garantes;

    public ComprobanteDeIngreso() {
        this.locatarios = new ArrayList<>();
        this.garantes = new ArrayList<>();
    }

    public ComprobanteDeIngreso(Integer mes, Integer anio, Float importeBruto, Float importeNeto) {
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

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Float getImporteBruto() {
        return importeBruto;
    }

    public void setImporteBruto(Float importeBruto) {
        this.importeBruto = importeBruto;
    }

    public Float getImporteNeto() {
        return importeNeto;
    }

    public void setImporteNeto(Float importeNeto) {
        this.importeNeto = importeNeto;
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

}
