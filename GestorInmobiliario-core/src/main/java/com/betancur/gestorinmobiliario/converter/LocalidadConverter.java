/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.LocalidadDTO;
import com.betancur.gestorinmobiliario.model.entity.Localidad;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class LocalidadConverter extends AbstractConverter<Localidad, LocalidadDTO> {
    
    @Override
    public Localidad fromDto(LocalidadDTO dto) {
        
        if (dto != null) {
            Localidad entity = new Localidad();
            
            if (dto.getId()!=null) {
                entity.setId(dto.getId());
            }
            entity.setNombre(dto.getNombre());
            entity.setCodigoPostal(dto.getCodigoPostal());
            
            if (dto.getUnaProvinciaDTO() != null) {
                ProvinciaConverter converter = new ProvinciaConverter();
                entity.setUnaProvincia(converter.fromDto(dto.getUnaProvinciaDTO()));
            }
            
            if (!dto.getBarrios().isEmpty()) {
                BarrioConverter converter = new BarrioConverter();
                entity.setBarrios(converter.fromDto(dto.getBarrios()));
            }
            
            return entity;
        } else {
            return null;
        }
    }
    
    @Override
    public LocalidadDTO fromEntity(Localidad entity) {
        if (entity != null) {
            LocalidadDTO dto = new LocalidadDTO();
            
            dto.setId(entity.getId());
            dto.setNombre(entity.getNombre());
            dto.setCodigoPostal(entity.getCodigoPostal());
            
            if (entity.getUnaProvincia() != null) {
                ProvinciaConverter converter = new ProvinciaConverter();
                dto.setUnaProvinciaDTO(converter.fromEntity(entity.getUnaProvincia()));
            }
            
            if (!entity.getBarrios().isEmpty()) {
                BarrioConverter converter = new BarrioConverter();
                dto.setBarrios(converter.fromEntity(entity.getBarrios()));
            }
            
            return dto;
        } else {
            return null;
        }
    }
    
    @Override
    public List<LocalidadDTO> fromEntity(List<Localidad> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Localidad> fromDto(List<LocalidadDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }
    
}
