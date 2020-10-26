/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.UsuarioClienteDTO;
import com.betancur.gestorinmobiliario.model.entity.UsuarioCliente;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class UsuarioClienteConverter extends AbstractConverter<UsuarioCliente, UsuarioClienteDTO> {

    @Override
    public UsuarioCliente fromDto(UsuarioClienteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioClienteDTO fromEntity(UsuarioCliente entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioClienteDTO> fromEntity(List<UsuarioCliente> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioCliente> fromDto(List<UsuarioClienteDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
