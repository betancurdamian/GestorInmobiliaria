/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ComprobanteDeIngresoDTO;
import dto.ComprobanteMonotributoDTO;
import dto.DocumentoDeIngresoDTO;
import dto.ReciboDeSueldoDTO;
import model.entity.ComprobanteDeIngreso;
import model.entity.ComprobanteMonotributo;
import model.entity.DocumentoDeIngreso;
import model.entity.ReciboDeSueldo;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class ComprobanteDeIngresoConverter implements Converter<ComprobanteDeIngreso, ComprobanteDeIngresoDTO> {

    @Override
    public ComprobanteDeIngreso fomDTO(ComprobanteDeIngresoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);

        ComprobanteDeIngreso entity = null;
        if (dto instanceof ReciboDeSueldoDTO) {
            entity = modelMapper.map(dto, ReciboDeSueldo.class);
        }
        if (dto instanceof ComprobanteMonotributoDTO) {
            entity = modelMapper.map(dto, ComprobanteMonotributo.class);
        }
        if (dto instanceof DocumentoDeIngresoDTO) {
            entity = modelMapper.map(dto, DocumentoDeIngreso.class);
        }
        return entity;
    }

    @Override
    public ComprobanteDeIngresoDTO fromDTO(ComprobanteDeIngreso entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);

        ComprobanteDeIngresoDTO dto = null;
        if (entity instanceof ReciboDeSueldo) {
            dto = modelMapper.map(entity, ReciboDeSueldoDTO.class);
        }
        if (entity instanceof ComprobanteMonotributo) {
            dto = modelMapper.map(entity, ComprobanteMonotributoDTO.class);
        }
        if (entity instanceof DocumentoDeIngreso) {
            dto = modelMapper.map(entity, DocumentoDeIngresoDTO.class);
        }
        return dto;
    }
    
}
