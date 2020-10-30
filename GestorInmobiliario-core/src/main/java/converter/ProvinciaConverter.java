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
public class ProvinciaConverter extends AbstractConverter<Provincia, ProvinciaDTO>{

    @Override
    public Provincia fromDto(ProvinciaDTO dto) {
        if (dto != null) {
            Provincia entity = new Provincia();
            
            if (dto.getId()!=null) {
                entity.setId(dto.getId());
            }
            entity.setNombre(dto.getNombre());
            entity.setCodigoPostal(dto.getCodigoPostal());
            
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
        if (entity != null) {
            ProvinciaDTO dto = new ProvinciaDTO();
            
            dto.setId(entity.getId());
            dto.setNombre(entity.getNombre());
            dto.setCodigoPostal(entity.getCodigoPostal());           
            
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
