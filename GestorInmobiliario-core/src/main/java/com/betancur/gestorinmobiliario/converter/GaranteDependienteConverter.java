/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.GaranteDependienteDTO;
import com.betancur.gestorinmobiliario.model.entity.GaranteDependiente;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class GaranteDependienteConverter extends AbstractConverter<GaranteDependiente, GaranteDependienteDTO> {

    @Override
    public GaranteDependiente fromDto(GaranteDependienteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GaranteDependienteDTO fromEntity(GaranteDependiente entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GaranteDependienteDTO> fromEntity(List<GaranteDependiente> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GaranteDependiente> fromDto(List<GaranteDependienteDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
