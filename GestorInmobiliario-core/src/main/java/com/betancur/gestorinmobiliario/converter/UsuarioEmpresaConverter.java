/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.UsuarioEmpresaDTO;
import com.betancur.gestorinmobiliario.model.entity.UsuarioEmpresa;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class UsuarioEmpresaConverter extends AbstractConverter<UsuarioEmpresa, UsuarioEmpresaDTO> {

    @Override
    public UsuarioEmpresa fromDto(UsuarioEmpresaDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioEmpresaDTO fromEntity(UsuarioEmpresa entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioEmpresaDTO> fromEntity(List<UsuarioEmpresa> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioEmpresa> fromDto(List<UsuarioEmpresaDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}