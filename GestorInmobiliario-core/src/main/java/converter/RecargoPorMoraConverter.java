/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.RecargoPorMoraDTO;
import model.entity.RecargoPorMora;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class RecargoPorMoraConverter implements Converter<RecargoPorMora, RecargoPorMoraDTO> {

    @Override
    public RecargoPorMora fomDTO(RecargoPorMoraDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        RecargoPorMora entity = modelMapper.map(dto, RecargoPorMora.class);

        return entity;
    }

    @Override
    public RecargoPorMoraDTO fromDTO(RecargoPorMora entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        RecargoPorMoraDTO dto = modelMapper.map(entity, RecargoPorMoraDTO.class);

        return dto;
    }
    
}
