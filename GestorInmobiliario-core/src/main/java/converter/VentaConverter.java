/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.VentaDTO;
import model.entity.Venta;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class VentaConverter implements Converter<Venta, VentaDTO> {

    @Override
    public Venta fomDTO(VentaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        Venta entity = modelMapper.map(dto, Venta.class);

        return entity;
    }

    @Override
    public VentaDTO fromDTO(Venta entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        VentaDTO dto = modelMapper.map(entity, VentaDTO.class);

        return dto;
    }
    
}
