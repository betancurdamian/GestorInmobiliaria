/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Ariel
 */
@Entity
@DiscriminatorValue("RECIBO DE SUELDO")
public class ComprobanteMonotributo extends ComprobanteDeIngreso {

    @Column(name = "cuit")
    private String cuit;

    @Column(name = "estado_monotributo")
    private Boolean estadoMonotributo;

    public ComprobanteMonotributo() {
    }

    public ComprobanteMonotributo(String cuit, Boolean estadoMonotributo) {
        this.cuit = cuit;
        this.estadoMonotributo = estadoMonotributo;
    }

    public ComprobanteMonotributo(String cuit, Boolean estadoMonotributo, Integer mes, Integer anio, Float importeBruto, Float importeNeto) {
        super(mes, anio, importeBruto, importeNeto);
        this.cuit = cuit;
        this.estadoMonotributo = estadoMonotributo;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Boolean getEstadoMonotributo() {
        return estadoMonotributo;
    }

    public void setEstadoMonotributo(Boolean estadoMonotributo) {
        this.estadoMonotributo = estadoMonotributo;
    }

    
}
