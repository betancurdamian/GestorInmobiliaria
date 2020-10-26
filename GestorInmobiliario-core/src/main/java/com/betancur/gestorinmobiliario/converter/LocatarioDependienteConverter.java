/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.LocatarioDependienteDTO;
import com.betancur.gestorinmobiliario.model.entity.LocatarioDependiente;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class LocatarioDependienteConverter extends AbstractConverter<LocatarioDependiente, LocatarioDependienteDTO> {

    @Override
    public LocatarioDependiente fromDto(LocatarioDependienteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocatarioDependienteDTO fromEntity(LocatarioDependiente entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LocatarioDependienteDTO> fromEntity(List<LocatarioDependiente> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LocatarioDependiente> fromDto(List<LocatarioDependienteDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
