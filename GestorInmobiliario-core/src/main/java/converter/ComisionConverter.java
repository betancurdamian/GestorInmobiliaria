/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ComisionDTO;
import model.entity.Comision;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class ComisionConverter implements Converter<Comision, ComisionDTO> {

    @Override
    public Comision fomDTO(ComisionDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        Comision entity = modelMapper.map(dto, Comision.class);

        return entity;
    }

    @Override
    public ComisionDTO fromDTO(Comision entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        ComisionDTO dto = modelMapper.map(entity, ComisionDTO.class);

        return dto;
    }

}
