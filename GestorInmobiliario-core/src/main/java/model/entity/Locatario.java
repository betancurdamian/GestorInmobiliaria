/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Ariel
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_locatario")
@Table(name = "locatarios")
public abstract class Locatario extends Cliente {

    @ManyToOne
    @JoinColumn(name = "fk_actividad", nullable = true)
    private Actividad unaActividad;

    @OneToOne(mappedBy = "unLocatario")
    private Garante unGarante;

    @OneToMany(mappedBy = "unLocatario", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
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
