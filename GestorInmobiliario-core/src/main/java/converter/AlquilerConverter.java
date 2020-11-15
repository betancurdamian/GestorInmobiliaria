/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.AlquilerDTO;
import model.entity.Alquiler;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class AlquilerConverter implements Converter<Alquiler, AlquilerDTO> {

    @Override
    public Alquiler fomDTO(AlquilerDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        Alquiler entity = modelMapper.map(dto, Alquiler.class);

        return entity;
    }

    @Override
    public AlquilerDTO fromDTO(Alquiler entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        AlquilerDTO dto = modelMapper.map(entity, AlquilerDTO.class);

        return dto;
    }
    
}
