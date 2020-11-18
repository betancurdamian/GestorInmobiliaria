/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.ComprobanteDeIngresoDTO;
import java.util.List;
import model.dao.Conexion;
import model.service.IComprobanteDeIngresoService;

/**
 *
 * @author Ariel
 */
public class ComprobanteDeIngresoServiceImpl implements IComprobanteDeIngresoService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ComprobanteDeIngresoServiceImpl() {
        new Conexion();
    }

    
    
    
    @Override
    public ComprobanteDeIngresoDTO crear(ComprobanteDeIngresoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComprobanteDeIngresoDTO modificar(ComprobanteDeIngresoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComprobanteDeIngresoDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ComprobanteDeIngresoDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
