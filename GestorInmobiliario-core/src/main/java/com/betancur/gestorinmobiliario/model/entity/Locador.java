/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Ariel
 */
@Entity
@DiscriminatorValue("LOCADOR")
public class Locador extends Cliente {

    public Locador() {
    }

    public Locador(Inmobiliaria unaInmobiliariaCliente, UsuarioCliente unUsuarioCliente) {
        super(unaInmobiliariaCliente, unUsuarioCliente);
    }

    public Locador(Inmobiliaria unaInmobiliariaCliente, UsuarioCliente unUsuarioCliente, String nombre, String apellido, TipoDNI unTipoDNI, String dni, EstadoCivil unEstadoCivil, String direccionCalle, String direccionNumero, Provincia direccionProvincia, Localidad direccionLocalidad, Barrio direccionBarrio, String telefono, String correoElectronico) {
        super(unaInmobiliariaCliente, unUsuarioCliente, nombre, apellido, unTipoDNI, dni, unEstadoCivil, direccionCalle, direccionNumero, direccionProvincia, direccionLocalidad, direccionBarrio, telefono, correoElectronico);
    }

}
