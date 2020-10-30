/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ActividadDTO;
import model.entity.Actividad;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ActividadConverter extends AbstractConverter<Actividad, ActividadDTO> {

    @Override
    public Actividad fromDto(ActividadDTO dto) {
        if (dto != null) {
            Actividad entity = new Actividad();
            if (dto.getId() != null) {
                entity.setId(dto.getId());
            }
            if (dto.getNombre() != null) {
                entity.setNombre(dto.getNombre());
            }
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public ActividadDTO fromEntity(Actividad entity) {
        if (entity != null) {
            ActividadDTO dto = new ActividadDTO();
            if (entity.getId() != null) {
                dto.setId(entity.getId());
            }
            if (entity.getNombre() != null) {
                dto.setNombre(entity.getNombre());
            }
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public List<ActividadDTO> fromEntity(List<Actividad> entities) {
        return super.fromEntity(entities);
    }

    @Override
    public List<Actividad> fromDto(List<ActividadDTO> dtos) {
        return super.fromDto(dtos);
    }
}
