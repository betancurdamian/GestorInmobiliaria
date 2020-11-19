/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.ArancelEspecialExpensaDTO;
import dto.UsuarioClienteDTO;
import dto.UsuarioDTO;
import dto.UsuarioEmpresaDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.Conexion;
import model.dao.UsuarioClienteJpaController;
import model.dao.UsuarioEmpresaJpaController;
import model.dao.UsuarioJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Usuario;
import model.entity.UsuarioCliente;
import model.entity.UsuarioEmpresa;
import model.service.IUsuarioService;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioJpaController usuarioDAO;
    private final UsuarioEmpresaJpaController usuarioEmpresaDAO;
    private final UsuarioClienteJpaController usuarioClienteDAO;
    private final InmobiliariaMapper converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public UsuarioServiceImpl() {
        new Conexion();
        this.usuarioDAO = new UsuarioJpaController(Conexion.getEmf());
        this.usuarioEmpresaDAO = new UsuarioEmpresaJpaController(Conexion.getEmf());
        this.usuarioClienteDAO = new UsuarioClienteJpaController(Conexion.getEmf());
        this.converter = Mappers.getMapper(InmobiliariaMapper.class);
    }

    @Override
    public UsuarioDTO crear(UsuarioDTO dto) {
        if (dto != null) {
            if (dto instanceof UsuarioEmpresaDTO) {
                UsuarioEmpresa entity = converter.toUsuarioEmpresaEntity((UsuarioEmpresaDTO) dto);
                this.usuarioEmpresaDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof UsuarioClienteDTO) {

                UsuarioCliente entity = converter.toUsuarioClienteEntity((UsuarioClienteDTO) dto);
                this.usuarioClienteDAO.create(entity);
                dto.setId(entity.getId());
            }
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public UsuarioDTO modificar(UsuarioDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                if (dto instanceof UsuarioEmpresaDTO) {
                    try {
                        UsuarioEmpresa entity = converter.toUsuarioEmpresaEntity((UsuarioEmpresaDTO) dto);
                        this.usuarioEmpresaDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof UsuarioClienteDTO) {
                    try {
                        UsuarioCliente entity = converter.toUsuarioClienteEntity((UsuarioClienteDTO) dto);
                        this.usuarioClienteDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                System.out.println("ID  DTO is null");
            }
        } else {
            System.out.println("DTO is null");
        }
        return dto;
    }

    @Override
    public void eliminar(Long id) {
        if (id != null) {
            if (usuarioDAO.findUsuario(id) != null) {
                try {
                    usuarioDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public UsuarioDTO listarID(Long id) {
        UsuarioDTO dto = null;
        Usuario entity = usuarioDAO.findUsuario(id);
        if (entity instanceof UsuarioEmpresa) {
            dto = converter.toUsuarioClienteDTO((UsuarioCliente) entity);
        }
        if (entity instanceof UsuarioEmpresa) {
            dto = converter.toUsuarioEmpresaDTO((UsuarioEmpresa) entity);
        }
        return dto;
    }

    @Override
    public List<UsuarioDTO> listarTodos() {
        List<Usuario> entities = usuarioDAO.findUsuarioEntities();
        return converter.toDTOUsuarioList(entities);
    }

    @Override
    public UsuarioEmpresaDTO listarUsuarioEmpresaID(Long id) {
        UsuarioEmpresa entity = usuarioEmpresaDAO.findUsuarioEmpresa(id);
        return converter.toUsuarioEmpresaDTO(entity);
    }

    @Override
    public List<UsuarioEmpresaDTO> listarTodosUsuariosEmpresas() {
        List<UsuarioEmpresa> entities = usuarioEmpresaDAO.findUsuarioEmpresaEntities();
        return converter.toDTOUsuarioEmpresaList(entities);
    }

    @Override
    public UsuarioClienteDTO listarUsuarioClienteID(Long id) {
        UsuarioCliente entity = usuarioClienteDAO.findUsuarioCliente(id);
        return converter.toUsuarioClienteDTO(entity);
    }

    @Override
    public List<UsuarioClienteDTO> listarTodosUsuariosClientes() {
        List<UsuarioCliente> entities = usuarioClienteDAO.findUsuarioClienteEntities();
        return converter.toDTOUsuarioClienteList(entities);
    }

}
