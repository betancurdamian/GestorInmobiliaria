/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.UsuarioDTO;
import com.betancur.gestorinmobiliario.model.entity.Usuario;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class UsuarioConverter extends AbstractConverter<Usuario, UsuarioDTO> {

    @Override
    public Usuario fromDto(UsuarioDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioDTO fromEntity(Usuario entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioDTO> fromEntity(List<Usuario> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> fromDto(List<UsuarioDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
