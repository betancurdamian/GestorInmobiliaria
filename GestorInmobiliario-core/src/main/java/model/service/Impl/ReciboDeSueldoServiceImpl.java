/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.ReciboDeSueldoDTO;
import model.dao.Conexion;
import model.service.IReciboDeSueldoService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ReciboDeSueldoServiceImpl implements IReciboDeSueldoService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ReciboDeSueldoServiceImpl() {
        new Conexion();
    }

    @Override
    public ReciboDeSueldoDTO crear(ReciboDeSueldoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReciboDeSueldoDTO modificar(ReciboDeSueldoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReciboDeSueldoDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReciboDeSueldoDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}