package converter;

import dto.BarrioDTO;
import model.entity.Barrio;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ariel
 */
public class BarrioConverter implements Converter<Barrio, BarrioDTO> {

    @Override
    public Barrio fomDTO(BarrioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        Barrio entity = modelMapper.map(dto, Barrio.class);

        return entity;
    }

    @Override
    public BarrioDTO fromDTO(Barrio entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        BarrioDTO dto = modelMapper.map(entity, BarrioDTO.class);

        return dto;
    }

}
