/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.TipoUsuarioDTO;
import com.betancur.gestorinmobiliario.model.entity.TipoUsuario;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class TipoUsuarioConverter extends AbstractConverter<TipoUsuario, TipoUsuarioDTO> {

    @Override
    public TipoUsuario fromDto(TipoUsuarioDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoUsuarioDTO fromEntity(TipoUsuario entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoUsuarioDTO> fromEntity(List<TipoUsuario> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoUsuario> fromDto(List<TipoUsuarioDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
