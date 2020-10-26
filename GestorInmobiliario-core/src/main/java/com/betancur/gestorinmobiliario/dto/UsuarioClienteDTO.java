/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.dto;

/**
 *
 * @author Ariel
 */
public class UsuarioClienteDTO extends UsuarioDTO{
    private ClienteDTO unClienteDTO;

    public ClienteDTO getUnClienteDTO() {
        return unClienteDTO;
    }

    public void setUnClienteDTO(ClienteDTO unClienteDTO) {
        this.unClienteDTO = unClienteDTO;
    }
    
    
}
