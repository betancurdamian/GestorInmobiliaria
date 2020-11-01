/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ProvinciaDTO;
import model.entity.Provincia;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ProvinciaConverter extends AbstractConverter<Provincia, ProvinciaDTO> {

    @Override
    public Provincia fromDto(ProvinciaDTO dto) {
        Provincia entity = null;
        if (dto != null) {
            entity = new Provincia();

            if (dto.getId() != null) {
                entity.setId(dto.getId());
            }
            if (dto.getNombre() != null) {
                entity.setNombre(dto.getNombre());
            }
            if (dto.getCodigoPostal() != null) {
                entity.setCodigoPostal(dto.getCodigoPostal());
            }
            if (!dto.getLocalidades().isEmpty()) {
                LocalidadConverter converter = new LocalidadConverter();
                entity.setLocalidades(converter.fromDto(dto.getLocalidades()));
            }
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public ProvinciaDTO fromEntity(Provincia entity) {
        ProvinciaDTO dto = null;
        if (entity != null) {
            dto = new ProvinciaDTO();

            if (entity.getId() != null) {
                dto.setId(entity.getId());
            }
            if (entity.getNombre() != null) {
                dto.setNombre(entity.getNombre());
            }
            if (entity.getCodigoPostal() != null) {
                dto.setCodigoPostal(entity.getCodigoPostal());
            }
            if (!entity.getLocalidades().isEmpty()) {
                LocalidadConverter converter = new LocalidadConverter();
                dto.setLocalidades(converter.fromEntity(entity.getLocalidades()));
            }
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public List<ProvinciaDTO> fromEntity(List<Provincia> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Provincia> fromDto(List<ProvinciaDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
