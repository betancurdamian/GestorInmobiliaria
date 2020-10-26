/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.entity;

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

    public GaranteDependiente(Actividad unaActividad, Inmobiliaria unaInmobiliariaGarante, Locatario unLocatario, ContratoAlquiler unContratoAlquiler, List<ComprobanteDeIngreso> comprobantesDeIngresosGarantes) {
        super(unaActividad, unaInmobiliariaGarante, unLocatario, unContratoAlquiler, comprobantesDeIngresosGarantes);
    }

    public GaranteDependiente(Actividad unaActividad, Inmobiliaria unaInmobiliariaGarante, Locatario unLocatario, ContratoAlquiler unContratoAlquiler, List<ComprobanteDeIngreso> comprobantesDeIngresosGarantes, String nombre, String apellido, TipoDNI unTipoDNI, String dni, EstadoCivil unEstadoCivil, String direccionCalle, String direccionNumero, Provincia direccionProvincia, Localidad direccionLocalidad, Barrio direccionBarrio, String telefono, String correoElectronico) {
        super(unaActividad, unaInmobiliariaGarante, unLocatario, unContratoAlquiler, comprobantesDeIngresosGarantes, nombre, apellido, unTipoDNI, dni, unEstadoCivil, direccionCalle, direccionNumero, direccionProvincia, direccionLocalidad, direccionBarrio, telefono, correoElectronico);
    }

    
    
}
