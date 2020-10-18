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
@DiscriminatorValue("EMPRESA")
public class UsuarioEmpresa extends Usuario{

    public UsuarioEmpresa() {
    }

    public UsuarioEmpresa(String userName, String password, TipoUsuario unTipoUsuario, Inmobiliaria unaInmobiliariaUsuario) {
        super(userName, password, unTipoUsuario, unaInmobiliariaUsuario);
    }
    
}