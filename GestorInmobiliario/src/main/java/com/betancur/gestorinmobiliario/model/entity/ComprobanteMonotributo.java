/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model;

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
    private boolean estadoMonotributo;

    public ComprobanteMonotributo() {
    }

    public ComprobanteMonotributo(String cuit, boolean estadoMonotributo) {
        this.cuit = cuit;
        this.estadoMonotributo = estadoMonotributo;
    }

    public ComprobanteMonotributo(String cuit, boolean estadoMonotributo, int mes, int anio, float importeBruto, float importeNeto) {
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

    public boolean isEstadoMonotributo() {
        return estadoMonotributo;
    }

    public void setEstadoMonotributo(boolean estadoMonotributo) {
        this.estadoMonotributo = estadoMonotributo;
    }
    
    
    
}
