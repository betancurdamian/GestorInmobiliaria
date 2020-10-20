/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.TipoDNIDTO;
import com.betancur.gestorinmobiliario.model.entity.TipoDNI;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class TipoDNIConverter extends AbstractConverter<TipoDNI, TipoDNIDTO>{

    @Override
    public TipoDNI fromDto(TipoDNIDTO dto) {
        if (dto != null) {
            TipoDNI entity = new TipoDNI();

            if (dto.getId()!=null) {
                entity.setId(dto.getId());
            }
            
            entity.setDescripcion(dto.getDescripcion());
            
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public TipoDNIDTO fromEntity(TipoDNI entity) {
        if (entity != null) {
            TipoDNIDTO dto = new TipoDNIDTO();
            
            dto.setId(entity.getId());
            dto.setDescripcion(entity.getDescripcion());
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public List<TipoDNIDTO> fromEntity(List<TipoDNI> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoDNI> fromDto(List<TipoDNIDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }
    
}
