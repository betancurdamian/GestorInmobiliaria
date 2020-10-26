/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.service.Impl;

import com.betancur.gestorinmobiliario.dto.ReciboDeSueldoDTO;
import com.betancur.gestorinmobiliario.model.dao.Conexion;
import com.betancur.gestorinmobiliario.model.service.IReciboDeSueldoService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ReciboDeSueldoServiceImpl implements IReciboDeSueldoService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ReciboDeSueldoServiceImpl() {
        new Conexion();
    }

    @Override
    public ReciboDeSueldoDTO crear(ReciboDeSueldoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReciboDeSueldoDTO modificar(ReciboDeSueldoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReciboDeSueldoDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReciboDeSueldoDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
