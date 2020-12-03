/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Ariel
 */
@Entity
@DiscriminatorValue("GARANTE DEPENDIENTE")
public class GaranteDependiente extends Garante{        

    public GaranteDependiente() {
    }

    public GaranteDependiente(Actividad unaActividad, Inmobiliaria unaInmobiliariaGarante, Locatario unLocatario, ContratoAlquiler unContratoAlquiler) {
        super(unaActividad, unaInmobiliariaGarante, unLocatario, unContratoAlquiler);
    }

    public GaranteDependiente(Actividad unaActividad, Inmobiliaria unaInmobiliariaGarante, Locatario unLocatario, ContratoAlquiler unContratoAlquiler, String nombre, String apellido, TipoDNI unTipoDNI, String dni, EstadoCivil unEstadoCivil, String direccionCalle, String direccionNumero, String telefono, String correoElectronico) {
        super(unaActividad, unaInmobiliariaGarante, unLocatario, unContratoAlquiler, nombre, apellido, unTipoDNI, dni, unEstadoCivil, direccionCalle, direccionNumero, telefono, correoElectronico);
    }

    
    
}
