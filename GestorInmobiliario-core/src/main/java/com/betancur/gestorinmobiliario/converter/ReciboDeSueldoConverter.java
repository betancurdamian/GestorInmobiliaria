/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.ReciboDeSueldoDTO;
import com.betancur.gestorinmobiliario.model.entity.ReciboDeSueldo;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ReciboDeSueldoConverter extends AbstractConverter<ReciboDeSueldo, ReciboDeSueldoDTO> {

    @Override
    public ReciboDeSueldo fromDto(ReciboDeSueldoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReciboDeSueldoDTO fromEntity(ReciboDeSueldo entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReciboDeSueldoDTO> fromEntity(List<ReciboDeSueldo> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReciboDeSueldo> fromDto(List<ReciboDeSueldoDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
