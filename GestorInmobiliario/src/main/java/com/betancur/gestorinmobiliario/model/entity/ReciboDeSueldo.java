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
public class ReciboDeSueldo extends ComprobanteDeIngreso {

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "antiguedad")
    private int antiguedad;

    public ReciboDeSueldo() {
    }

    public ReciboDeSueldo(String nombreEmpresa, int antiguedad) {
        this.nombreEmpresa = nombreEmpresa;
        this.antiguedad = antiguedad;
    }

    public ReciboDeSueldo(String nombreEmpresa, int antiguedad, int mes, int anio, float importeBruto, float importeNeto) {
        super(mes, anio, importeBruto, importeNeto);
        this.nombreEmpresa = nombreEmpresa;
        this.antiguedad = antiguedad;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

}
