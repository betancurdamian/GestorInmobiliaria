/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.service.Impl;

import com.betancur.gestorinmobiliario.dto.UsuarioEmpresaDTO;
import com.betancur.gestorinmobiliario.model.dao.Conexion;
import com.betancur.gestorinmobiliario.model.service.IUsuarioEmpresaService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class UsuarioEmpresaServiceImpl implements IUsuarioEmpresaService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public UsuarioEmpresaServiceImpl() {
        new Conexion();
    }

    @Override
    public UsuarioEmpresaDTO crear(UsuarioEmpresaDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioEmpresaDTO modificar(UsuarioEmpresaDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioEmpresaDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioEmpresaDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
