/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.BoletaDePagoDTO;
import model.dao.Conexion;
import model.service.IBoletaDePagoService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class BoletaDePagoServiceImpl implements IBoletaDePagoService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public BoletaDePagoServiceImpl() {
        new Conexion();
    }

    
    @Override
    public BoletaDePagoDTO crear(BoletaDePagoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BoletaDePagoDTO modificar(BoletaDePagoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BoletaDePagoDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BoletaDePagoDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
