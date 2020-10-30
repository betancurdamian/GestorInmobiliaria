/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.EstadoCivilDTO;
import model.dao.Conexion;
import model.service.IEstadoCivilService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class EstadoCivilServiceImpl implements IEstadoCivilService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public EstadoCivilServiceImpl() {
        new Conexion();
    }

    @Override
    public EstadoCivilDTO crear(EstadoCivilDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EstadoCivilDTO modificar(EstadoCivilDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EstadoCivilDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EstadoCivilDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
