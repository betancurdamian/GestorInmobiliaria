/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.LocatarioIndependienteDTO;
import model.dao.Conexion;
import model.service.ILocatarioIndependienteService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class LocatarioIndependienteServiceImpl implements ILocatarioIndependienteService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public LocatarioIndependienteServiceImpl() {
        new Conexion();
    }

    @Override
    public LocatarioIndependienteDTO crear(LocatarioIndependienteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocatarioIndependienteDTO modificar(LocatarioIndependienteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocatarioIndependienteDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LocatarioIndependienteDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
