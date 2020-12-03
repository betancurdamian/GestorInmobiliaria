/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Ariel
 */
@Entity
public abstract class Locatario extends Cliente {

    @ManyToOne
    @JoinColumn(name = "fk_actividad")
    private Actividad unaActividad;

    @OneToOne(mappedBy = "unLocatario")
    private Garante unGarante;

    @OneToMany(mappedBy = "unLocatario", cascade = CascadeType.ALL)
    private List<ComprobanteDeIngreso> comprobantesDeIngresosLocatarios;

    public Locatario() {
        this.comprobantesDeIngresosLocatarios = new ArrayList<>();
    }

    public Actividad getUnaActividad() {
        return unaActividad;
    }

    public void setUnaActividad(Actividad unaActividad) {
        this.unaActividad = unaActividad;
    }

    public List<ComprobanteDeIngreso> getComprobantesDeIngresosLocatarios() {
        return comprobantesDeIngresosLocatarios;
    }

    public void setComprobantesDeIngresosLocatarios(List<ComprobanteDeIngreso> comprobantesDeIngresosLocatarios) {
        this.comprobantesDeIngresosLocatarios = comprobantesDeIngresosLocatarios;
    }

    public Garante getUnGarante() {
        return unGarante;
    }

    public void setUnGarante(Garante unGarante) {
        this.unGarante = unGarante;
    }

}
