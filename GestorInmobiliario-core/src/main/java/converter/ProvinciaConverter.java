/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ProvinciaDTO;
import model.entity.Provincia;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class ProvinciaConverter implements Converter<Provincia, ProvinciaDTO> {

    @Override
    public Provincia fomDTO(ProvinciaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        Provincia entity = modelMapper.map(dto, Provincia.class);

        return entity;
    }

    @Override
    public ProvinciaDTO fromDTO(Provincia entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        ProvinciaDTO dto = modelMapper.map(entity, ProvinciaDTO.class);

        return dto;
    }
    
}
