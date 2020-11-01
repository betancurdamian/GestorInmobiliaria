/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.EstadoCivilDTO;
import model.entity.EstadoCivil;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class EstadoCivilConverter extends AbstractConverter<EstadoCivil, EstadoCivilDTO> {

    @Override
    public EstadoCivil fromDto(EstadoCivilDTO dto) {
        EstadoCivil entity = null;
        if (dto != null) {
            entity = new EstadoCivil();

            if (dto.getId() != null) {
                entity.setId(dto.getId());
            }
            if (dto.getDescripcion() != null) {
                entity.setDescripcion(dto.getDescripcion());
            }
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public EstadoCivilDTO fromEntity(EstadoCivil entity) {
        EstadoCivilDTO dto = null;
        if (entity != null) {
            dto = new EstadoCivilDTO();

            if (entity.getId() != null) {
                dto.setId(entity.getId());
            }
            if (entity.getDescripcion() != null) {
                dto.setDescripcion(entity.getDescripcion());
            }
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public List<EstadoCivilDTO> fromEntity(List<EstadoCivil> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EstadoCivil> fromDto(List<EstadoCivilDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
