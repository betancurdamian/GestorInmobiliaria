/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.LocatarioDTO;
import com.betancur.gestorinmobiliario.model.entity.Locatario;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class LocatarioConverter extends AbstractConverter<Locatario, LocatarioDTO> {

    @Override
    public Locatario fromDto(LocatarioDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocatarioDTO fromEntity(Locatario entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LocatarioDTO> fromEntity(List<Locatario> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Locatario> fromDto(List<LocatarioDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
