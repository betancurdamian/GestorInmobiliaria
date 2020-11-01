/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.GaranteDTO;
import java.util.List;
import model.dao.Conexion;
import model.service.IGaranteService;

/**
 *
 * @author Ariel
 */
public class GaranteServiceImpl implements IGaranteService{

    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public GaranteServiceImpl() {
        new Conexion();
    }

    
    
    
    @Override
    public GaranteDTO crear(GaranteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GaranteDTO modificar(GaranteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GaranteDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GaranteDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
