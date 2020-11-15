/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.BoletaDePagoDTO;
import model.entity.BoletaDePago;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class BoletaDePagoConverter implements Converter<BoletaDePago, BoletaDePagoDTO> {

    @Override
    public BoletaDePago fomDTO(BoletaDePagoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        BoletaDePago entity = modelMapper.map(dto, BoletaDePago.class);

        return entity;
    }

    @Override
    public BoletaDePagoDTO fromDTO(BoletaDePago entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        BoletaDePagoDTO dto = modelMapper.map(entity, BoletaDePagoDTO.class);

        return dto;
    }
    
}
