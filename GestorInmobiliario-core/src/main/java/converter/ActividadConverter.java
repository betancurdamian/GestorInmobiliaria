/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ActividadDTO;
import model.entity.Actividad;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class ActividadConverter implements Converter<Actividad, ActividadDTO> {

    @Override
    public Actividad fomDTO(ActividadDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        Actividad entity = modelMapper.map(dto, Actividad.class);

        return entity;
    }

    @Override
    public ActividadDTO fromDTO(Actividad entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        ActividadDTO dto = modelMapper.map(entity, ActividadDTO.class);

        return dto;
    }
    
}
