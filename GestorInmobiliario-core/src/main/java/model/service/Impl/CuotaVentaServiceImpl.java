/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.CuotaVentaDTO;
import model.dao.Conexion;
import model.service.ICuotaVentaService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class CuotaVentaServiceImpl implements ICuotaVentaService {

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public CuotaVentaServiceImpl() {
        new Conexion();
    }

    @Override
    public CuotaVentaDTO crear(CuotaVentaDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CuotaVentaDTO modificar(CuotaVentaDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CuotaVentaDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CuotaVentaDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
