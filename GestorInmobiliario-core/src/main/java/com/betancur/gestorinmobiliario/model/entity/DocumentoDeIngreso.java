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
public class DocumentoDeIngreso extends ComprobanteDeIngreso {

    @Column(name = "validado")
    private boolean validado;

    public DocumentoDeIngreso() {
    }

    public DocumentoDeIngreso(boolean validado) {
        this.validado = validado;
    }

    public DocumentoDeIngreso(boolean validado, int mes, int anio, float importeBruto, float importeNeto) {
        super(mes, anio, importeBruto, importeNeto);
        this.validado = validado;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

}
