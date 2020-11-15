/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.ClienteConverter;
import dto.ClienteDTO;
import dto.LocadorDTO;
import dto.LocatarioDTO;
import dto.LocatarioDependienteDTO;
import dto.LocatarioEstudianteDTO;
import dto.LocatarioIndependienteDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.ClienteJpaController;
import model.dao.Conexion;
import model.dao.LocadorJpaController;
import model.dao.LocatarioDependienteJpaController;
import model.dao.LocatarioEstudianteJpaController;
import model.dao.LocatarioIndependienteJpaController;
import model.dao.LocatarioJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Cliente;
import model.entity.Locador;
import model.entity.Locatario;
import model.entity.LocatarioDependiente;
import model.entity.LocatarioEstudiante;
import model.entity.LocatarioIndependiente;
import model.service.IClienteService;

/**
 *
 * @author Ariel
 */
public class ClienteServiceImpl implements IClienteService {

    private final ClienteJpaController clienteDAO;
    private final LocadorJpaController locadorDAO;
    private final LocatarioJpaController locatarioDAO;
    private final LocatarioDependienteJpaController locatarioDependienteDAO;
    private final LocatarioIndependienteJpaController locatarioIndependienteDAO;
    private final LocatarioEstudianteJpaController locatarioEstudianteDAO;
    private final ClienteConverter converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ClienteServiceImpl() {
        new Conexion();
        this.clienteDAO = new ClienteJpaController(Conexion.getEmf());
        this.locadorDAO = new LocadorJpaController(Conexion.getEmf());
        this.locatarioDAO = new LocatarioJpaController(Conexion.getEmf());
        this.locatarioDependienteDAO = new LocatarioDependienteJpaController(Conexion.getEmf());
        this.locatarioIndependienteDAO = new LocatarioIndependienteJpaController(Conexion.getEmf());
        this.locatarioEstudianteDAO = new LocatarioEstudianteJpaController(Conexion.getEmf());
        this.converter = new ClienteConverter();
    }

    @Override
    public ClienteDTO crear(ClienteDTO dto) {
        if (dto != null) {
            if (dto instanceof LocadorDTO) {                
                Locador entity = (Locador) converter.fomDTO(dto);
                this.locadorDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof LocatarioDependienteDTO) {
                LocatarioDependiente entity = (LocatarioDependiente) converter.fomDTO(dto);
                this.locatarioDependienteDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof LocatarioIndependienteDTO) {
                LocatarioIndependiente entity = (LocatarioIndependiente) converter.fomDTO(dto);
                this.locatarioIndependienteDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof LocatarioEstudianteDTO) {
                LocatarioEstudiante entity = (LocatarioEstudiante) converter.fomDTO(dto);
                this.locatarioEstudianteDAO.create(entity);
                dto.setId(entity.getId());
            }
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public ClienteDTO modificar(ClienteDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                if (dto instanceof LocadorDTO) {
                    try {
                        Locador entity = (Locador) converter.fomDTO(dto);
                        locadorDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof LocatarioDependienteDTO) {
                    try {
                        LocatarioDependiente entity = (LocatarioDependiente) converter.fomDTO(dto);
                        locatarioDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof LocatarioIndependienteDTO) {
                    try {
                        LocatarioIndependiente entity = (LocatarioIndependiente) converter.fomDTO(dto);
                        locatarioIndependienteDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof LocatarioEstudianteDTO) {
                    try {
                        LocatarioEstudiante entity = (LocatarioEstudiante) converter.fomDTO(dto);
                        locatarioEstudianteDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (clienteDAO.findCliente(id) != null) {
                try {
                    clienteDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public ClienteDTO listarID(Long id) {
        Cliente entity = clienteDAO.findCliente(id);
        ClienteDTO dto = null;
        if (entity instanceof Locador) {
            dto = (LocadorDTO) converter.fromDTO(entity);
        }
        if (entity instanceof LocatarioDependiente) {
            dto = (LocatarioDependienteDTO) converter.fromDTO(entity);
        }
        if (entity instanceof LocatarioIndependiente) {
            dto = (LocatarioIndependienteDTO) converter.fromDTO(entity);
        }
        if (entity instanceof LocatarioEstudiante) {
            dto = (LocatarioEstudianteDTO) converter.fromDTO(entity);
        }
        return dto;
    }

    @Override
    public List<ClienteDTO> listarTodos() {
        ClienteDTO dtoAux = null;
        List<ClienteDTO> dtos = new ArrayList<>();

        for (Cliente entitiy : clienteDAO.findClienteEntities()) {
            dtoAux = converter.fromDTO(entitiy);
            dtos.add(dtoAux);
        }

        return dtos;
    }

    @Override
    public LocadorDTO listarLocadorID(Long id) {
        Locador entity = locadorDAO.findLocador(id);
        LocadorDTO dto = (LocadorDTO) converter.fromDTO(entity);
        return dto;
    }

    @Override
    public List<LocadorDTO> listarTodosLocadores() {
        LocadorDTO dtoAux = null;
        List<LocadorDTO> dtos = new ArrayList<>();

        for (Locador entity : locadorDAO.findLocadorEntities()) {
            dtoAux = (LocadorDTO) converter.fromDTO(entity);
            dtos.add(dtoAux);
        }

        return dtos;
    }

    @Override
    public LocatarioDTO listarLocatarioID(Long id) {
        Locatario entity = locatarioDAO.findLocatario(id);
        LocatarioDTO dto = (LocatarioDTO) converter.fromDTO(entity);
        return dto;
    }

    @Override
    public List<LocatarioDTO> listarTodosLocatarios() {
        LocatarioDTO dtoAux = null;
        List<LocatarioDTO> dtos = new ArrayList<>();

        for (Locatario entity : locatarioDAO.findLocatarioEntities()) {
            dtoAux = (LocatarioDTO) converter.fromDTO(entity);
            dtos.add(dtoAux);
        }

        return dtos;
    }

    @Override
    public LocatarioDependienteDTO listarLocatarioDependienteID(Long id) {
        LocatarioDependiente entity = locatarioDependienteDAO.findLocatarioDependiente(id);
        LocatarioDependienteDTO dto = (LocatarioDependienteDTO) converter.fromDTO(entity);
        return dto;
    }

    @Override
    public List<LocatarioDependienteDTO> listarTodosLocatariosDependientes() {
        LocatarioDependienteDTO dtoAux = null;
        List<LocatarioDependienteDTO> dtos = new ArrayList<>();

        for (LocatarioDependiente entity : locatarioDependienteDAO.findLocatarioDependienteEntities()) {
            dtoAux = (LocatarioDependienteDTO) converter.fromDTO(entity);
            dtos.add(dtoAux);
        }

        return dtos;
    }

    @Override
    public LocatarioIndependienteDTO listarLocatarioIndependienteID(Long id) {
        LocatarioIndependiente entity = locatarioIndependienteDAO.findLocatarioIndependiente(id);
        LocatarioIndependienteDTO dto = (LocatarioIndependienteDTO) converter.fromDTO(entity);
        return dto;
    }

    @Override
    public List<LocatarioIndependienteDTO> listarTodosLocatariosIndependientes() {
        LocatarioIndependienteDTO dtoAux = null;
        List<LocatarioIndependienteDTO> dtos = new ArrayList<>();

        for (LocatarioIndependiente entity : locatarioIndependienteDAO.findLocatarioIndependienteEntities()) {
            dtoAux = (LocatarioIndependienteDTO) converter.fromDTO(entity);
            dtos.add(dtoAux);
        }
        return dtos;
    }

    @Override
    public LocatarioEstudianteDTO listarLocatarioEstudianteID(Long id) {
        LocatarioEstudiante entity = locatarioEstudianteDAO.findLocatarioEstudiante(id);
        LocatarioEstudianteDTO dto = (LocatarioEstudianteDTO) converter.fromDTO(entity);
        return dto;
    }

    @Override
    public List<LocatarioEstudianteDTO> listarTodosLocatariosEstudiantes() {
        LocatarioEstudianteDTO dtoAux = null;
        List<LocatarioEstudianteDTO> dtos = new ArrayList<>();

        for (LocatarioEstudiante entity : locatarioEstudianteDAO.findLocatarioEstudianteEntities()) {
            dtoAux = (LocatarioEstudianteDTO) converter.fromDTO(entity);
            dtos.add(dtoAux);
        }
        return dtos;
    }

}
