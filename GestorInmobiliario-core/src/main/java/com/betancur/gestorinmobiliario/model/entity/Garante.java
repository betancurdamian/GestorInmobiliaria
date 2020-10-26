/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Ariel
 */
@Entity
@Table(name = "garantes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_garante")
public abstract class Garante extends Persona {

    @ManyToOne
    @JoinColumn(name = "fk_actividad", nullable = true, updatable = true)
    private Actividad unaActividad;

    @ManyToOne
    @JoinColumn(name = "fk_inmobiliaria", nullable = true, updatable = true)
    private Inmobiliaria unaInmobiliariaGarante;

    @OneToOne
    @JoinColumn(name = "fk_locatario")
    private Locatario unLocatario;
    
    @OneToOne(mappedBy = "unGarante")
    private ContratoAlquiler unContratoAlquiler;

    @ManyToMany
    @JoinTable(name = "rel_garante_comprobante_ingreso",
            joinColumns = @JoinColumn(name = "id_garante"),
            inverseJoinColumns = @JoinColumn(name = "id_comprobante_ingreso"))
    private List<ComprobanteDeIngreso> comprobantesDeIngresosGarantes;

    public Garante() {
    }

    public Garante(Actividad unaActividad, Inmobiliaria unaInmobiliariaGarante, Locatario unLocatario, ContratoAlquiler unContratoAlquiler, List<ComprobanteDeIngreso> comprobantesDeIngresosGarantes) {
        this.unaActividad = unaActividad;
        this.unaInmobiliariaGarante = unaInmobiliariaGarante;
        this.unLocatario = unLocatario;
        this.unContratoAlquiler = unContratoAlquiler;
        this.comprobantesDeIngresosGarantes = comprobantesDeIngresosGarantes;
    }

    public Garante(Actividad unaActividad, Inmobiliaria unaInmobiliariaGarante, Locatario unLocatario, ContratoAlquiler unContratoAlquiler, List<ComprobanteDeIngreso> comprobantesDeIngresosGarantes, String nombre, String apellido, TipoDNI unTipoDNI, String dni, EstadoCivil unEstadoCivil, String direccionCalle, String direccionNumero, Provincia direccionProvincia, Localidad direccionLocalidad, Barrio direccionBarrio, String telefono, String correoElectronico) {
        super(nombre, apellido, unTipoDNI, dni, unEstadoCivil, direccionCalle, direccionNumero, direccionProvincia, direccionLocalidad, direccionBarrio, telefono, correoElectronico);
        this.unaActividad = unaActividad;
        this.unaInmobiliariaGarante = unaInmobiliariaGarante;
        this.unLocatario = unLocatario;
        this.unContratoAlquiler = unContratoAlquiler;
        this.comprobantesDeIngresosGarantes = comprobantesDeIngresosGarantes;
    }

   

    public Actividad getUnaActividad() {
        return unaActividad;
    }

    public void setUnaActividad(Actividad unaActividad) {
        this.unaActividad = unaActividad;
    }

    public Inmobiliaria getUnaInmobiliariaGarante() {
        return unaInmobiliariaGarante;
    }

    public void setUnaInmobiliariaGarante(Inmobiliaria unaInmobiliariaGarante) {
        this.unaInmobiliariaGarante = unaInmobiliariaGarante;
    }

    public List<ComprobanteDeIngreso> getComprobantesDeIngresosGarantes() {
        return comprobantesDeIngresosGarantes;
    }

    public void setComprobantesDeIngresosGarantes(List<ComprobanteDeIngreso> comprobantesDeIngresosGarantes) {
        this.comprobantesDeIngresosGarantes = comprobantesDeIngresosGarantes;
    }

    public Locatario getUnLocatario() {
        return unLocatario;
    }

    public void setUnLocatario(Locatario unLocatario) {
        this.unLocatario = unLocatario;
    }

    public ContratoAlquiler getUnContratoAlquiler() {
        return unContratoAlquiler;
    }

    public void setUnContratoAlquiler(ContratoAlquiler unContratoAlquiler) {
        this.unContratoAlquiler = unContratoAlquiler;
    }

    
}
