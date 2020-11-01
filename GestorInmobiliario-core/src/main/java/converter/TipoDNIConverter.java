/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.TipoDNIDTO;
import model.entity.TipoDNI;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class TipoDNIConverter extends AbstractConverter<TipoDNI, TipoDNIDTO> {

    @Override
    public TipoDNI fromDto(TipoDNIDTO dto) {
        TipoDNI entity = null;
        if (dto != null) {
            entity = new TipoDNI();

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
    public TipoDNIDTO fromEntity(TipoDNI entity) {
        TipoDNIDTO dto = null;
        if (entity != null) {
            dto = new TipoDNIDTO();

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
    public List<TipoDNIDTO> fromEntity(List<TipoDNI> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoDNI> fromDto(List<TipoDNIDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
