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
@DiscriminatorValue("RECIBO DE SUELDO")
public class DocumentoDeIngreso extends ComprobanteDeIngreso {

    @Column(name = "validado")
    private Boolean validado;

    public DocumentoDeIngreso() {
    }

    public DocumentoDeIngreso(Boolean validado) {
        this.validado = validado;
    }

    public DocumentoDeIngreso(Boolean validado, Integer mes, Integer anio, Float importeBruto, Float importeNeto) {
        super(mes, anio, importeBruto, importeNeto);
        this.validado = validado;
    }

    public Boolean getValidado() {
        return validado;
    }

    public void setValidado(Boolean validado) {
        this.validado = validado;
    }

    

}
