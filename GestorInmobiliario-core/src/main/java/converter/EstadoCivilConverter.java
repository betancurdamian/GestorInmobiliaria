/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.EstadoCivilDTO;
import model.entity.EstadoCivil;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class EstadoCivilConverter implements Converter<EstadoCivil, EstadoCivilDTO> {

    @Override
    public EstadoCivil fomDTO(EstadoCivilDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        EstadoCivil entity = modelMapper.map(dto, EstadoCivil.class);

        return entity;
    }

    @Override
    public EstadoCivilDTO fromDTO(EstadoCivil entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        EstadoCivilDTO dto = modelMapper.map(entity, EstadoCivilDTO.class);

        return dto;
    }
    
}
