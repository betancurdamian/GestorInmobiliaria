/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.LocalidadDTO;
import model.entity.Localidad;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class LocalidadConverter implements Converter<Localidad, LocalidadDTO>{

    @Override
    public Localidad fomDTO(LocalidadDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        Localidad entity = modelMapper.map(dto, Localidad.class);

        return entity;
    }

    @Override
    public LocalidadDTO fromDTO(Localidad entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        LocalidadDTO dto = modelMapper.map(entity, LocalidadDTO.class);

        return dto;
    }
    
}
