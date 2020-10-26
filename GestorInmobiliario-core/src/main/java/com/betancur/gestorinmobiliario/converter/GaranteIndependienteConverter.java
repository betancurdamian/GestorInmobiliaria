/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.GaranteIndependienteDTO;
import com.betancur.gestorinmobiliario.model.entity.GaranteIndependiente;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class GaranteIndependienteConverter extends AbstractConverter<GaranteIndependiente, GaranteIndependienteDTO> {

    @Override
    public GaranteIndependiente fromDto(GaranteIndependienteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GaranteIndependienteDTO fromEntity(GaranteIndependiente entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GaranteIndependienteDTO> fromEntity(List<GaranteIndependiente> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GaranteIndependiente> fromDto(List<GaranteIndependienteDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
