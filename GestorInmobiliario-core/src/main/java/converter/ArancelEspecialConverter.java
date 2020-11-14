/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ArancelEspecialDTO;
import dto.ArancelEspecialExpensaDTO;
import dto.ArancelEspecialServicioDTO;
import model.entity.ArancelEspecial;
import model.entity.ArancelEspecialExpensa;
import model.entity.ArancelEspecialServicio;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class ArancelEspecialConverter implements Converter<ArancelEspecial, ArancelEspecialDTO> {

    @Override
    public ArancelEspecial fomDTO(ArancelEspecialDTO dto) {
        ArancelEspecial entity = null;
        if (dto instanceof ArancelEspecialExpensaDTO) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(AccessLevel.PRIVATE);
            entity = modelMapper.map(dto, ArancelEspecialExpensa.class);
        }
        if (dto instanceof ArancelEspecialServicioDTO) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(AccessLevel.PRIVATE);
            entity = modelMapper.map(dto, ArancelEspecialServicio.class);
        }
        return entity;
    }

    @Override
    public ArancelEspecialDTO fromDTO(ArancelEspecial entity) {
        ArancelEspecialDTO dto = null;
        if (entity instanceof ArancelEspecialExpensa) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(AccessLevel.PRIVATE);
            dto = modelMapper.map(entity, ArancelEspecialExpensaDTO.class);
        }
        if (entity instanceof ArancelEspecialServicio) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(AccessLevel.PRIVATE);
            dto = modelMapper.map(entity, ArancelEspecialServicioDTO.class);
        }
        return dto;
    }

}
