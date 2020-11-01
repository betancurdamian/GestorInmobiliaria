/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ComisionDTO;
import model.entity.Comision;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ComisionConverter extends AbstractConverter<Comision, ComisionDTO> {

    @Override
    public Comision fromDto(ComisionDTO dto) {
        Comision entity = null;
        if (dto != null) {
            entity = new Comision();
            if (dto.getId() != null) {
                entity.setId(dto.getId());
            }
            if (dto.getCantidadDeCuotas() != null) {
                entity.setCantidadDeCuotas(dto.getCantidadDeCuotas());
            }
            if (dto.getMontoTotal() != null) {
                entity.setMontoTotal(dto.getMontoTotal());
            }
            if (!dto.getLinesasDeComisionesDTO().isEmpty()) {
                LineaDeComisionConverter converter = new LineaDeComisionConverter();
                entity.setLinesasDeComisiones(converter.fromDto(dto.getLinesasDeComisionesDTO()));
            }
            return entity;
        } else {
            return entity;
        }
    }

    @Override
    public ComisionDTO fromEntity(Comision entity) {
        ComisionDTO dto = null;
        if (entity != null) {

            dto = new ComisionDTO();
            if (entity.getId() != null) {
                dto.setId(entity.getId());
            }
            if (entity.getCantidadDeCuotas() != null) {
                dto.setCantidadDeCuotas(entity.getCantidadDeCuotas());
            }
            if (entity.getMontoTotal() != null) {
                dto.setMontoTotal(entity.getMontoTotal());
            }
            if (!entity.getLinesasDeComisiones().isEmpty()) {
                LineaDeComisionConverter converter = new LineaDeComisionConverter();
                dto.setLinesasDeComisionesDTO(converter.fromEntity(entity.getLinesasDeComisiones()));
            }
            return dto;
        } else {
            return dto;
        }
    }

    @Override
    public List<ComisionDTO> fromEntity(List<Comision> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comision> fromDto(List<ComisionDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
