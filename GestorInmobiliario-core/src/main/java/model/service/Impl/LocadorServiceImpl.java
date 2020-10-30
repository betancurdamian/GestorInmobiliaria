/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.LocadorDTO;
import model.dao.Conexion;
import model.service.ILocadorService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class LocadorServiceImpl implements ILocadorService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public LocadorServiceImpl() {
        new Conexion();
    }

    @Override
    public LocadorDTO crear(LocadorDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocadorDTO modificar(LocadorDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocadorDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LocadorDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
