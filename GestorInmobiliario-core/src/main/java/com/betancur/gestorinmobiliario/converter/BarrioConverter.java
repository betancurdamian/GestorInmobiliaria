/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.BarrioDTO;
import com.betancur.gestorinmobiliario.model.entity.Barrio;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class BarrioConverter extends AbstractConverter<Barrio, BarrioDTO>{

    @Override
    public Barrio fromDto(BarrioDTO dto) {
        if (dto != null) {
            Barrio entity = new Barrio();

            if (dto.getId()!=null) {
                entity.setId(dto.getId());
            }
            
            entity.setNombre(dto.getNombre());
            entity.setCodigoPostal(dto.getCodigoPostal());
            
            if (dto.getUnaLocalidadDTO()!=null) {
                LocalidadConverter converter = new LocalidadConverter();
                entity.setUnaLocalidad(converter.fromDto(dto.getUnaLocalidadDTO()));
            }
            
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public BarrioDTO fromEntity(Barrio entity) {
        if (entity != null) {
            BarrioDTO dto = new BarrioDTO();
            
            dto.setId(entity.getId());
            dto.setNombre(entity.getNombre());
            dto.setCodigoPostal(entity.getCodigoPostal());

            if (entity.getUnaLocalidad() != null) {
                LocalidadConverter converter = new LocalidadConverter();
                dto.setUnaLocalidadDTO(converter.fromEntity(entity.getUnaLocalidad()));
            }
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public List<BarrioDTO> fromEntity(List<Barrio> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Barrio> fromDto(List<BarrioDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
