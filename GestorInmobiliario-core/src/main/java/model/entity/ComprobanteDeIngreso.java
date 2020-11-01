/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
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

    @ManyToOne
    @JoinColumn(name = "fk_locatario")
    private Locatario unLocatario;

    @ManyToOne
    @JoinColumn(name = "fk_garante")
    private Garante unGarante;

    public ComprobanteDeIngreso() {
    }   

    public ComprobanteDeIngreso(Integer mes, Integer anio, Float importeBruto, Float importeNeto, Locatario unLocatario, Garante unGarante) {
        this.mes = mes;
        this.anio = anio;
        this.importeBruto = importeBruto;
        this.importeNeto = importeNeto;
        this.unLocatario = unLocatario;
        this.unGarante = unGarante;
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
        return "model.entity.ComprobanteDeIngreso_1[ id=" + id + " ]";
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

    public Locatario getUnLocatario() {
        return unLocatario;
    }

    public void setUnLocatario(Locatario unLocatario) {
        this.unLocatario = unLocatario;
    }

    public Garante getUnGarante() {
        return unGarante;
    }

    public void setUnGarante(Garante unGarante) {
        this.unGarante = unGarante;
    }
    
}
