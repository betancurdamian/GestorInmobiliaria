/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.LocatarioEstudianteDTO;
import model.dao.Conexion;
import model.service.ILocatarioEstudianteService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class LocatarioEstudianteServiceImpl implements ILocatarioEstudianteService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public LocatarioEstudianteServiceImpl() {
        new Conexion();
    }

    @Override
    public LocatarioEstudianteDTO crear(LocatarioEstudianteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocatarioEstudianteDTO modificar(LocatarioEstudianteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocatarioEstudianteDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LocatarioEstudianteDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
