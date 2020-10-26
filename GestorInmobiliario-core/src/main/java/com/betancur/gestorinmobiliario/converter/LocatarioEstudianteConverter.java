/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.LocatarioEstudianteDTO;
import com.betancur.gestorinmobiliario.model.entity.LocatarioEstudiante;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class LocatarioEstudianteConverter extends AbstractConverter<LocatarioEstudiante, LocatarioEstudianteDTO> {

    @Override
    public LocatarioEstudiante fromDto(LocatarioEstudianteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocatarioEstudianteDTO fromEntity(LocatarioEstudiante entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LocatarioEstudianteDTO> fromEntity(List<LocatarioEstudiante> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LocatarioEstudiante> fromDto(List<LocatarioEstudianteDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
