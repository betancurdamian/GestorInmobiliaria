/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.ContratoAlquilerDTO;
import model.dao.Conexion;
import model.service.IContratoAlquilerService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ContratoAlquilerServiceImpl implements IContratoAlquilerService {

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ContratoAlquilerServiceImpl() {
        new Conexion();
    }

    @Override
    public ContratoAlquilerDTO crear(ContratoAlquilerDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ContratoAlquilerDTO modificar(ContratoAlquilerDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ContratoAlquilerDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ContratoAlquilerDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
