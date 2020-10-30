/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.InmobiliariaDTO;
import model.dao.Conexion;
import model.service.IInmobiliariaService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class InmobiliariaServiceImpl implements IInmobiliariaService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public InmobiliariaServiceImpl() {
        new Conexion();
    }

    @Override
    public InmobiliariaDTO crear(InmobiliariaDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InmobiliariaDTO modificar(InmobiliariaDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InmobiliariaDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InmobiliariaDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
