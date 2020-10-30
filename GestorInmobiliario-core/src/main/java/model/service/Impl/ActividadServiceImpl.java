/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.ActividadDTO;
import model.dao.Conexion;
import model.service.IActividadService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ActividadServiceImpl implements IActividadService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ActividadServiceImpl() {
        new Conexion();
    }

    
    @Override
    public ActividadDTO crear(ActividadDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ActividadDTO modificar(ActividadDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ActividadDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ActividadDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
