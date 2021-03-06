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
@DiscriminatorValue("DOCUMENTO DE INGRESO")
public class DocumentoDeIngreso extends ComprobanteDeIngreso {

    @Column(name = "validado")
    private Boolean validado;

    public DocumentoDeIngreso() {
    }

    public DocumentoDeIngreso(Boolean validado) {
        this.validado = validado;
    }

    public DocumentoDeIngreso(Boolean validado, Integer mes, Integer anio, Float importeBruto, Float importeNeto, Locatario unLocatario, Garante unGarante) {
        super(mes, anio, importeBruto, importeNeto, unLocatario, unGarante);
        this.validado = validado;
    }

    

    public Boolean getValidado() {
        return validado;
    }

    public void setValidado(Boolean validado) {
        this.validado = validado;
    }

    

}
