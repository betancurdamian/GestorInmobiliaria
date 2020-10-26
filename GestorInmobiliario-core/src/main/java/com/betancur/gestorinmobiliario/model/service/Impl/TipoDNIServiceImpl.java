/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.service.Impl;

import com.betancur.gestorinmobiliario.dto.TipoDNIDTO;
import com.betancur.gestorinmobiliario.model.dao.Conexion;
import com.betancur.gestorinmobiliario.model.service.ITipoDNIService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class TipoDNIServiceImpl implements ITipoDNIService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public TipoDNIServiceImpl() {
        new Conexion();
        
    }

    
    @Override
    public TipoDNIDTO crear(TipoDNIDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoDNIDTO modificar(TipoDNIDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoDNIDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoDNIDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
