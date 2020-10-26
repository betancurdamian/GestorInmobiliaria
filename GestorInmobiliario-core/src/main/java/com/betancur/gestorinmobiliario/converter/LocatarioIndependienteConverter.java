/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.LocatarioIndependienteDTO;
import com.betancur.gestorinmobiliario.model.entity.LocatarioIndependiente;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class LocatarioIndependienteConverter extends AbstractConverter<LocatarioIndependiente, LocatarioIndependienteDTO> {

    @Override
    public LocatarioIndependiente fromDto(LocatarioIndependienteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocatarioIndependienteDTO fromEntity(LocatarioIndependiente entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LocatarioIndependienteDTO> fromEntity(List<LocatarioIndependiente> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LocatarioIndependiente> fromDto(List<LocatarioIndependienteDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
