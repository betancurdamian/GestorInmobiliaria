/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.TipoUsuarioDTO;
import model.entity.TipoUsuario;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class TipoUsuarioConverter implements Converter<TipoUsuario, TipoUsuarioDTO> {

    @Override
    public TipoUsuario fomDTO(TipoUsuarioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        TipoUsuario entity = modelMapper.map(dto, TipoUsuario.class);

        return entity;
    }

    @Override
    public TipoUsuarioDTO fromDTO(TipoUsuario entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        TipoUsuarioDTO dto = modelMapper.map(entity, TipoUsuarioDTO.class);

        return dto;
    }
    
}
