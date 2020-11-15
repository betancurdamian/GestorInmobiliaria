/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.TipoDNIDTO;
import model.entity.TipoDNI;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class TipoDNIConverter implements Converter<TipoDNI, TipoDNIDTO> {

    @Override
    public TipoDNI fomDTO(TipoDNIDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        TipoDNI entity = modelMapper.map(dto, TipoDNI.class);

        return entity;
    }

    @Override
    public TipoDNIDTO fromDTO(TipoDNI entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        TipoDNIDTO dto = modelMapper.map(entity, TipoDNIDTO.class);

        return dto;
    }
    
}
