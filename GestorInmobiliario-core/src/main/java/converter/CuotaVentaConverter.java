/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.CuotaVentaDTO;
import model.entity.CuotaVenta;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class CuotaVentaConverter implements Converter<CuotaVenta, CuotaVentaDTO> {

    @Override
    public CuotaVenta fomDTO(CuotaVentaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        CuotaVenta entity = modelMapper.map(dto, CuotaVenta.class);

        return entity;
    }

    @Override
    public CuotaVentaDTO fromDTO(CuotaVenta entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        CuotaVentaDTO dto = modelMapper.map(entity, CuotaVentaDTO.class);

        return dto;
    }
    
}
