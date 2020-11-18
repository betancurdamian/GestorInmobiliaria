/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.LineaDeComisionDTO;
import model.dao.Conexion;
import model.service.ILineaDeComisionService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class LineaDeComisionServiceImpl implements ILineaDeComisionService{

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public LineaDeComisionServiceImpl() {
        new Conexion();
    }

    @Override
    public LineaDeComisionDTO crear(LineaDeComisionDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LineaDeComisionDTO modificar(LineaDeComisionDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LineaDeComisionDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LineaDeComisionDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
