/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.LineaDeComisionDTO;
import com.betancur.gestorinmobiliario.model.entity.LineaDeComision;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class LineaDeComisionConverter extends AbstractConverter<LineaDeComision, LineaDeComisionDTO> {

    @Override
    public LineaDeComision fromDto(LineaDeComisionDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LineaDeComisionDTO fromEntity(LineaDeComision entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LineaDeComisionDTO> fromEntity(List<LineaDeComision> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LineaDeComision> fromDto(List<LineaDeComisionDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
