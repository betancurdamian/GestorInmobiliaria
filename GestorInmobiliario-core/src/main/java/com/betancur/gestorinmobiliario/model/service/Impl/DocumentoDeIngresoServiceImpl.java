/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.service.Impl;

import com.betancur.gestorinmobiliario.dto.DocumentoDeIngresoDTO;
import com.betancur.gestorinmobiliario.model.dao.Conexion;
import com.betancur.gestorinmobiliario.model.service.IDocumentoDeIngresoService;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class DocumentoDeIngresoServiceImpl implements IDocumentoDeIngresoService  {

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public DocumentoDeIngresoServiceImpl() {
        new Conexion();
    }

    @Override
    public DocumentoDeIngresoDTO crear(DocumentoDeIngresoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DocumentoDeIngresoDTO modificar(DocumentoDeIngresoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DocumentoDeIngresoDTO listarID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DocumentoDeIngresoDTO> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
