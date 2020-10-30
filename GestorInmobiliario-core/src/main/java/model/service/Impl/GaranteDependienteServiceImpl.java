/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.GaranteDependienteDTO;
import model.dao.Conexion;
import model.service.IGaranteDependienteService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class GaranteDependienteServiceImpl implements IGaranteDependienteService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public GaranteDependienteServiceImpl() {
        new Conexion();
    }

    @Override
    public GaranteDependienteDTO crear(GaranteDependienteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GaranteDependienteDTO modificar(GaranteDependienteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GaranteDependienteDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GaranteDependienteDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
