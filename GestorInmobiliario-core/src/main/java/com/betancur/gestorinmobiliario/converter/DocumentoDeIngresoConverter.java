/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.DocumentoDeIngresoDTO;
import com.betancur.gestorinmobiliario.model.entity.DocumentoDeIngreso;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class DocumentoDeIngresoConverter extends AbstractConverter<DocumentoDeIngreso, DocumentoDeIngresoDTO> {

    @Override
    public DocumentoDeIngreso fromDto(DocumentoDeIngresoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DocumentoDeIngresoDTO fromEntity(DocumentoDeIngreso entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DocumentoDeIngresoDTO> fromEntity(List<DocumentoDeIngreso> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DocumentoDeIngreso> fromDto(List<DocumentoDeIngresoDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
