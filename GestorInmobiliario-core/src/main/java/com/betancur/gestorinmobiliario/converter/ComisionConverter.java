/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.ComisionDTO;
import com.betancur.gestorinmobiliario.model.entity.Comision;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ComisionConverter extends AbstractConverter<Comision, ComisionDTO>{

    @Override
    public Comision fromDto(ComisionDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComisionDTO fromEntity(Comision entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ComisionDTO> fromEntity(List<Comision> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comision> fromDto(List<ComisionDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }
    
}
