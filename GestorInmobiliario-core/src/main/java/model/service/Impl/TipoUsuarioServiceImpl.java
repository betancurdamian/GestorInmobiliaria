/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.TipoUsuarioDTO;
import model.dao.Conexion;
import model.service.ITipoUsuarioService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class TipoUsuarioServiceImpl implements ITipoUsuarioService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public TipoUsuarioServiceImpl() {
        new Conexion();
    }

    @Override
    public TipoUsuarioDTO crear(TipoUsuarioDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoUsuarioDTO modificar(TipoUsuarioDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoUsuarioDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoUsuarioDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
