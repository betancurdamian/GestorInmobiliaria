/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.GaranteDTO;
import dto.GaranteDependienteDTO;
import dto.GaranteIndependienteDTO;
import model.entity.Garante;
import model.entity.GaranteDependiente;
import model.entity.GaranteIndependiente;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class GaranteConverter implements Converter<Garante, GaranteDTO>{

    @Override
    public Garante fomDTO(GaranteDTO dto) {
        Garante entity = null;
        if (dto instanceof GaranteDependienteDTO) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(AccessLevel.PRIVATE);
            entity = modelMapper.map(dto, GaranteDependiente.class);
        }
        if (dto instanceof GaranteIndependienteDTO) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(AccessLevel.PRIVATE);
            entity = modelMapper.map(dto, GaranteIndependiente.class);
        }
        return entity;
    }

    @Override
    public GaranteDTO fromDTO(Garante entity) {
        GaranteDTO dto = null;
        if (entity instanceof GaranteDependiente) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(AccessLevel.PRIVATE);
            dto = modelMapper.map(entity, GaranteDependienteDTO.class);
        }
        if (entity instanceof GaranteIndependiente) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(AccessLevel.PRIVATE);
            dto = modelMapper.map(entity, GaranteIndependienteDTO.class);
        }
        return dto;
    }
    
}
