/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.UsuarioDTO;
import java.util.List;
import model.dao.Conexion;
import model.service.IUsuarioService;

/**
 *
 * @author Ariel
 */
public class UsuarioServiceImpl implements IUsuarioService{

    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public UsuarioServiceImpl() {
        new Conexion();
    }

    
    
    @Override
    public UsuarioDTO crear(UsuarioDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioDTO modificar(UsuarioDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
