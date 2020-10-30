/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.UsuarioClienteDTO;
import model.dao.Conexion;
import model.service.IUsuarioClienteService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class UsuarioClienteServiceImpl implements IUsuarioClienteService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public UsuarioClienteServiceImpl() {
        new Conexion();
    }

    @Override
    public UsuarioClienteDTO crear(UsuarioClienteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioClienteDTO modificar(UsuarioClienteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioClienteDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioClienteDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
