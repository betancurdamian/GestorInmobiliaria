/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.ContratoVentaDTO;
import model.dao.Conexion;
import model.service.IContratoVentaService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ContratoVentaServiceImpl implements IContratoVentaService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ContratoVentaServiceImpl() {
        new Conexion();
    }

    @Override
    public ContratoVentaDTO crear(ContratoVentaDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ContratoVentaDTO modificar(ContratoVentaDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ContratoVentaDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ContratoVentaDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
