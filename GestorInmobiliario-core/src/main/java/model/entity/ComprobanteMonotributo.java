/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Ariel
 */
@Entity
@DiscriminatorValue("COMPROBANTE DE MONOTRIBUTO")
public class ComprobanteMonotributo extends ComprobanteDeIngreso {

    @Column(name = "estado_monotributo")
    private Boolean estadoMonotributo;

    public ComprobanteMonotributo() {
    }

    public ComprobanteMonotributo(Boolean estadoMonotributo) {
        this.estadoMonotributo = estadoMonotributo;
    }

    public ComprobanteMonotributo(Boolean estadoMonotributo, Integer mes, Integer anio, Float importeBruto, Float importeNeto, Locatario unLocatario, Garante unGarante) {
        super(mes, anio, importeBruto, importeNeto, unLocatario, unGarante);
        this.estadoMonotributo = estadoMonotributo;
    }
    
    public Boolean getEstadoMonotributo() {
        return estadoMonotributo;
    }

    public void setEstadoMonotributo(Boolean estadoMonotributo) {
        this.estadoMonotributo = estadoMonotributo;
    }

    
}
