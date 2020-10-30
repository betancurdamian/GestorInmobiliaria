/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.GaranteIndependienteDTO;
import model.dao.Conexion;
import model.service.IGaranteIndependienteService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class GaranteIndependienteServiceImpl implements IGaranteIndependienteService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public GaranteIndependienteServiceImpl() {
        new Conexion();
    }

    @Override
    public GaranteIndependienteDTO crear(GaranteIndependienteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GaranteIndependienteDTO modificar(GaranteIndependienteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GaranteIndependienteDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GaranteIndependienteDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
