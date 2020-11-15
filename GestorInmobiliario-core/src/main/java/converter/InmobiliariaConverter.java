/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.InmobiliariaDTO;
import model.entity.Inmobiliaria;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class InmobiliariaConverter implements Converter<Inmobiliaria, InmobiliariaDTO> {

    @Override
    public Inmobiliaria fomDTO(InmobiliariaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        Inmobiliaria entity = modelMapper.map(dto, Inmobiliaria.class);

        return entity;
    }

    @Override
    public InmobiliariaDTO fromDTO(Inmobiliaria entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        InmobiliariaDTO dto = modelMapper.map(entity, InmobiliariaDTO.class);

        return dto;
    }
    
}
