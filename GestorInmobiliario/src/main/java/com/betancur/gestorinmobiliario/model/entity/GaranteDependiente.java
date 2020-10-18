/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model;

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

    public GaranteDependiente(Actividad unaActividad, Inmobiliaria unaInmobiliariaGarante, Locatario unLocatario) {
        super(unaActividad, unaInmobiliariaGarante, unLocatario);
    }

    public GaranteDependiente(Actividad unaActividad, Inmobiliaria unaInmobiliariaGarante, Locatario unLocatario, String nombre, String apellido, TipoDNI unTipoDNI, String dni, EstadoCivil unEstadoCivil, String direccionCalle, String direccionNumero, Barrio direccionBarrio, String telefono, String correoElectronico) {
        super(unaActividad, unaInmobiliariaGarante, unLocatario, nombre, apellido, unTipoDNI, dni, unEstadoCivil, direccionCalle, direccionNumero, direccionBarrio, telefono, correoElectronico);
    }

    
    
}
