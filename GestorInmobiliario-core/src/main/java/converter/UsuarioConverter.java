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
import java.util.List;
import model.entity.UsuarioCliente;
import model.entity.UsuarioEmpresa;

/**
 *
 * @author Ariel
 */
public class UsuarioConverter extends AbstractConverter<Usuario, UsuarioDTO> {

    @Override
    public Usuario fromDto(UsuarioDTO dto) {
        Usuario entity = null;
        if (dto != null) {
            if (dto instanceof UsuarioEmpresaDTO) {
                entity = new UsuarioEmpresa();

                if (dto.getId() != null) {
                    entity.setId(dto.getId());
                }
                if (dto.getUserName() != null) {
                    entity.setUserName(dto.getUserName());
                }
                if (dto.getPassword() != null) {
                    entity.setPassword(dto.getPassword());
                }
                if (dto.getUnTipoUsuarioDTO() != null) {
                    TipoUsuarioConverter converter = new TipoUsuarioConverter();
                    entity.setUnTipoUsuario(converter.fromDto(dto.getUnTipoUsuarioDTO()));
                }
                if (dto.getUnaInmobiliariaUsuarioDTO() != null) {
                    InmobiliariaConverter converter = new InmobiliariaConverter();
                    entity.setUnaInmobiliariaUsuario(converter.fromDto(dto.getUnaInmobiliariaUsuarioDTO()));
                }
            }
            if (dto instanceof UsuarioClienteDTO) {
                entity = new UsuarioCliente();

                if (dto.getId() != null) {
                    entity.setId(dto.getId());
                }
                if (dto.getId() != null) {
                    entity.setId(dto.getId());
                }
                if (dto.getUserName() != null) {
                    entity.setUserName(dto.getUserName());
                }
                if (dto.getPassword() != null) {
                    entity.setPassword(dto.getPassword());
                }
                if (dto.getUnTipoUsuarioDTO() != null) {
                    TipoUsuarioConverter converter = new TipoUsuarioConverter();
                    entity.setUnTipoUsuario(converter.fromDto(dto.getUnTipoUsuarioDTO()));
                }
                if (dto.getUnaInmobiliariaUsuarioDTO() != null) {
                    InmobiliariaConverter converter = new InmobiliariaConverter();
                    entity.setUnaInmobiliariaUsuario(converter.fromDto(dto.getUnaInmobiliariaUsuarioDTO()));
                }
                if (((UsuarioClienteDTO) dto).getUnClienteDTO() != null) {
                    ClienteConverter converter = new ClienteConverter();
                    ((UsuarioCliente) entity).setUnCliente(converter.fromDto(((UsuarioClienteDTO) dto).getUnClienteDTO()));
                }
            }
            return entity;
        } else {
            return entity;
        }
    }

    @Override
    public UsuarioDTO fromEntity(Usuario entity) {
        UsuarioDTO dto = null;
        if (entity != null) {
            if (entity instanceof UsuarioEmpresa) {
                dto = new UsuarioEmpresaDTO();

                if (entity.getId() != null) {
                    dto.setId(entity.getId());
                }
                if (entity.getUserName() != null) {
                    dto.setUserName(entity.getUserName());
                }
                if (entity.getPassword() != null) {
                    dto.setPassword(entity.getPassword());
                }
                if (entity.getUnTipoUsuario() != null) {
                    TipoUsuarioConverter converter = new TipoUsuarioConverter();
                    dto.setUnTipoUsuarioDTO(converter.fromEntity(entity.getUnTipoUsuario()));
                }
                if (entity.getUnaInmobiliariaUsuario() != null) {
                    InmobiliariaConverter converter = new InmobiliariaConverter();
                    dto.setUnaInmobiliariaUsuarioDTO(converter.fromEntity(entity.getUnaInmobiliariaUsuario()));
                }
            }
            if (entity instanceof UsuarioCliente) {
                dto = new UsuarioClienteDTO();

                if (entity.getId() != null) {
                    dto.setId(entity.getId());
                }
                if (entity.getUserName() != null) {
                    dto.setUserName(entity.getUserName());
                }
                if (entity.getPassword() != null) {
                    dto.setPassword(entity.getPassword());
                }
                if (entity.getUnTipoUsuario() != null) {
                    TipoUsuarioConverter converter = new TipoUsuarioConverter();
                    dto.setUnTipoUsuarioDTO(converter.fromEntity(entity.getUnTipoUsuario()));
                }
                if (entity.getUnaInmobiliariaUsuario() != null) {
                    InmobiliariaConverter converter = new InmobiliariaConverter();
                    dto.setUnaInmobiliariaUsuarioDTO(converter.fromEntity(entity.getUnaInmobiliariaUsuario()));
                }
                if (((UsuarioCliente) entity).getUnCliente() != null) {
                    ClienteConverter converter = new ClienteConverter();
                    ((UsuarioClienteDTO) dto).setUnClienteDTO(converter.fromEntity(((UsuarioCliente) entity).getUnCliente()));
                }
            }
            return dto;
        } else {
            return dto;
        }
    }

    @Override
    public List<UsuarioDTO> fromEntity(List<Usuario> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> fromDto(List<UsuarioDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
