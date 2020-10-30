/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.ComisionDTO;
import model.dao.Conexion;
import model.service.IComisionService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ComisionServiceImpl implements IComisionService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ComisionServiceImpl() {
        new Conexion();
    }

    @Override
    public ComisionDTO crear(ComisionDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComisionDTO modificar(ComisionDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComisionDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ComisionDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
