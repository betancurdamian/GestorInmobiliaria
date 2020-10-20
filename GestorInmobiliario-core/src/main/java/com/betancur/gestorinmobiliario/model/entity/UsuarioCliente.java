/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Ariel
 */
@Entity
@DiscriminatorValue("CLIENTE")
public class UsuarioCliente extends Usuario {

    @OneToOne
    @JoinColumn(name = "fk_cliente", updatable = false, nullable = false)
    private Cliente unCliente;

    public UsuarioCliente() {
    }

    public UsuarioCliente(Cliente cliente) {
    }

    public UsuarioCliente(Cliente cliente, String userName, String password, TipoUsuario unTipoUsuario, Inmobiliaria unaInmobiliariaUsuario) {
        super(userName, password, unTipoUsuario, unaInmobiliariaUsuario);
    }

    public Cliente getUnCliente() {
        return unCliente;
    }

    public void setUnCliente(Cliente unCliente) {
        this.unCliente = unCliente;
    }

}
