/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.LineaDeComisionDTO;
import model.entity.LineaDeComision;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class LineaDeComisionConverter extends AbstractConverter<LineaDeComision, LineaDeComisionDTO> {

    @Override
    public LineaDeComision fromDto(LineaDeComisionDTO dto) {
        LineaDeComision entity = null;
        if (dto != null) {
            entity = new LineaDeComision();
            if (dto.getId() != null) {
                entity.setId(dto.getId());
            }
            if (dto.getNumeroCuota() != null) {
                entity.setNumeroCuota(dto.getNumeroCuota());
            }
            if (dto.getMonto() != null) {
                entity.setMonto(dto.getMonto());
            }
            if (dto.getUnaComisionDTO() != null) {
                ComisionConverter converter = new ComisionConverter();
                entity.setUnaComision(converter.fromDto(dto.getUnaComisionDTO()));
            }
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public LineaDeComisionDTO fromEntity(LineaDeComision entity) {
        LineaDeComisionDTO dto = null;
        if (entity != null) {
            dto = new LineaDeComisionDTO();
            if (entity.getId() != null) {
                dto.setId(entity.getId());
            }
            if (entity.getNumeroCuota() != null) {
                dto.setNumeroCuota(entity.getNumeroCuota());
            }
            if (entity.getMonto() != null) {
                dto.setMonto(entity.getMonto());
            }
            if (entity.getUnaComision() != null) {
                ComisionConverter converter = new ComisionConverter();
                dto.setUnaComisionDTO(converter.fromEntity(entity.getUnaComision()));
            }
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public List<LineaDeComisionDTO> fromEntity(List<LineaDeComision> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LineaDeComision> fromDto(List<LineaDeComisionDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
