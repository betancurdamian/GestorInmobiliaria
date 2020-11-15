/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.UsuarioClienteDTO;
import dto.UsuarioDTO;
import dto.UsuarioEmpresaDTO;
import model.entity.Usuario;
import model.entity.UsuarioCliente;
import model.entity.UsuarioEmpresa;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class UsuarioConverter implements Converter<Usuario, UsuarioDTO>  {

    @Override
    public Usuario fomDTO(UsuarioDTO dto) {
        Usuario entity = null;
        if (dto instanceof UsuarioEmpresaDTO) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(AccessLevel.PRIVATE);
            entity = modelMapper.map(dto, UsuarioEmpresa.class);
        }
        if (dto instanceof UsuarioClienteDTO) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(AccessLevel.PRIVATE);
            entity = modelMapper.map(dto, UsuarioCliente.class);
        }
        return entity;
    }

    @Override
    public UsuarioDTO fromDTO(Usuario entity) {
        UsuarioDTO dto = null;
        if (entity instanceof UsuarioEmpresa) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(AccessLevel.PRIVATE);
            dto = modelMapper.map(entity, UsuarioEmpresaDTO.class);
        }
        if (entity instanceof UsuarioCliente) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(AccessLevel.PRIVATE);
            dto = modelMapper.map(entity, UsuarioClienteDTO.class);
        }
        return dto;
    }
    
}
