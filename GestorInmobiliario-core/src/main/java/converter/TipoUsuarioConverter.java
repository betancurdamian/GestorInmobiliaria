/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.TipoUsuarioDTO;
import model.entity.TipoUsuario;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class TipoUsuarioConverter extends AbstractConverter<TipoUsuario, TipoUsuarioDTO> {

    @Override
    public TipoUsuario fromDto(TipoUsuarioDTO dto) {
        TipoUsuario entity = null;
        if (dto != null) {
            entity = new TipoUsuario();

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
    public TipoUsuarioDTO fromEntity(TipoUsuario entity) {
        TipoUsuarioDTO dto = null;
        if (entity != null) {
            dto = new TipoUsuarioDTO();

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
    public List<TipoUsuarioDTO> fromEntity(List<TipoUsuario> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoUsuario> fromDto(List<TipoUsuarioDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
