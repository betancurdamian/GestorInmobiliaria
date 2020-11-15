/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ContratoAlquilerDTO;
import dto.ContratoDTO;
import dto.ContratoVentaDTO;
import model.entity.Contrato;
import model.entity.ContratoAlquiler;
import model.entity.ContratoVenta;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class ContratoConverter implements Converter<Contrato, ContratoDTO>  {

    @Override
    public Contrato fomDTO(ContratoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);

        Contrato entity = null;
        if (dto instanceof ContratoVentaDTO) {
            entity = modelMapper.map(dto, ContratoVenta.class);
        }
        if (dto instanceof ContratoAlquilerDTO) {
            entity = modelMapper.map(dto, ContratoAlquiler.class);
        }
        
        return entity;
    }

    @Override
    public ContratoDTO fromDTO(Contrato entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);

        ContratoDTO dto = null;
        if (entity instanceof ContratoVenta) {
            dto = modelMapper.map(entity, ContratoVentaDTO.class);
        }
        if (entity instanceof ContratoAlquiler) {
            dto = modelMapper.map(entity, ContratoAlquilerDTO.class);
        }        
        return dto;
    }
    
}
