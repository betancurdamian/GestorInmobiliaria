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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @JoinColumn(name = "fk_actividad")
    private Actividad unaActividad;

    @ManyToOne
    @JoinColumn(name = "fk_inmobiliaria")
    private Inmobiliaria unaInmobiliariaGarante;

    @ManyToOne
    @JoinColumn(name = "fk_locatario")
    private Locatario unLocatario;

    @OneToOne(mappedBy = "unGarante")
    private ContratoAlquiler unContratoAlquiler;

    @OneToMany(mappedBy = "unGarante", cascade = CascadeType.ALL)
    private List<ComprobanteDeIngreso> comprobantesDeIngresosGarantes;

    public Garante() {
        this.comprobantesDeIngresosGarantes = new ArrayList<>();
    }

    public Garante(Actividad unaActividad, Inmobiliaria unaInmobiliariaGarante, Locatario unLocatario, ContratoAlquiler unContratoAlquiler) {
        this.unaActividad = unaActividad;
        this.unaInmobiliariaGarante = unaInmobiliariaGarante;
        this.unLocatario = unLocatario;
        this.unContratoAlquiler = unContratoAlquiler;
        this.comprobantesDeIngresosGarantes = new ArrayList<>();
    }

    public Garante(Actividad unaActividad, Inmobiliaria unaInmobiliariaGarante, Locatario unLocatario, ContratoAlquiler unContratoAlquiler, String nombre, String apellido, TipoDNI unTipoDNI, String dni, EstadoCivil unEstadoCivil, String direccionCalle, String direccionNumero, String telefono, String correoElectronico) {
        super(nombre, apellido, unTipoDNI, dni, unEstadoCivil, direccionCalle, direccionNumero, telefono, correoElectronico);
        this.unaActividad = unaActividad;
        this.unaInmobiliariaGarante = unaInmobiliariaGarante;
        this.unLocatario = unLocatario;
        this.unContratoAlquiler = unContratoAlquiler;
        this.comprobantesDeIngresosGarantes = new ArrayList<>();
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
