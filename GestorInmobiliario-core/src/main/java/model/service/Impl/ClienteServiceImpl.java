/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

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
import org.modelmapper.ModelMapper;

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

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ClienteServiceImpl() {
        new Conexion();
        this.clienteDAO = new ClienteJpaController(Conexion.getEmf());
        this.locadorDAO = new LocadorJpaController(Conexion.getEmf());
        this.locatarioDAO = new LocatarioJpaController(Conexion.getEmf());
        this.locatarioDependienteDAO = new LocatarioDependienteJpaController(Conexion.getEmf());
        this.locatarioIndependienteDAO = new LocatarioIndependienteJpaController(Conexion.getEmf());
        this.locatarioEstudianteDAO = new LocatarioEstudianteJpaController(Conexion.getEmf());
    }

    @Override
    public ClienteDTO crear(ClienteDTO dto) {
        if (dto != null) {
            if (dto instanceof LocadorDTO) {
                ModelMapper modelMapper = new ModelMapper();
                Locador entity = modelMapper.map(dto, Locador.class);
                this.locadorDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof LocatarioDependienteDTO) {
                ModelMapper modelMapper = new ModelMapper();
                LocatarioDependiente entity = modelMapper.map(dto, LocatarioDependiente.class);
                this.locatarioDependienteDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof LocatarioIndependienteDTO) {
                ModelMapper modelMapper = new ModelMapper();
                LocatarioIndependiente entity = modelMapper.map(dto, LocatarioIndependiente.class);
                this.locatarioIndependienteDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof LocatarioEstudianteDTO) {
                ModelMapper modelMapper = new ModelMapper();
                LocatarioEstudiante entity = modelMapper.map(dto, LocatarioEstudiante.class);
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
                        ModelMapper modelMapper = new ModelMapper();
                        Locador entity = modelMapper.map(dto, Locador.class);

                        locadorDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof LocatarioDependienteDTO) {
                    try {
                        ModelMapper modelMapper = new ModelMapper();
                        LocatarioDependiente entity = modelMapper.map(dto, LocatarioDependiente.class);

                        locatarioDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof LocatarioIndependienteDTO) {
                    try {
                        ModelMapper modelMapper = new ModelMapper();
                        LocatarioIndependiente entity = modelMapper.map(dto, LocatarioIndependiente.class);

                        locatarioIndependienteDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof LocatarioEstudianteDTO) {
                    try {
                        ModelMapper modelMapper = new ModelMapper();
                        LocatarioEstudiante entity = modelMapper.map(dto, LocatarioEstudiante.class);

                        locatarioEstudianteDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
        ModelMapper modelMapper = new ModelMapper();
        Cliente entity = clienteDAO.findCliente(id);
        ClienteDTO dto = modelMapper.map(entity, ClienteDTO.class);

        return dto;
    }

    @Override
    public List<ClienteDTO> listarTodos() {
        ModelMapper modelMapper = new ModelMapper();
        ClienteDTO dtoAux = null;
        List<ClienteDTO> dtos = new ArrayList<>();

        for (Cliente entitiy : clienteDAO.findClienteEntities()) {
            dtoAux = modelMapper.map(entitiy, ClienteDTO.class);
            dtos.add(dtoAux);
        }

        return dtos;
    }

    @Override
    public LocadorDTO listarLocadorID(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Locador entity = locadorDAO.findLocador(id);
        LocadorDTO dto = modelMapper.map(entity, LocadorDTO.class);

        return dto;
    }

    @Override
    public List<LocadorDTO> listarTodosLocadores() {
        ModelMapper modelMapper = new ModelMapper();
        LocadorDTO dtoAux = null;
        List<LocadorDTO> dtos = new ArrayList<>();

        for (Locador entity : locadorDAO.findLocadorEntities()) {
            dtoAux = modelMapper.map(entity, LocadorDTO.class);
            dtos.add(dtoAux);
        }

        return dtos;
    }

    @Override
    public LocatarioDTO listarLocatarioID(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Locatario entity = locatarioDAO.findLocatario(id);
        LocatarioDTO dto = modelMapper.map(entity, LocatarioDTO.class);

        return dto;
    }

    @Override
    public List<LocatarioDTO> listarTodosLocatarios() {
        ModelMapper modelMapper = new ModelMapper();
        LocatarioDTO dtoAux = null;
        List<LocatarioDTO> dtos = new ArrayList<>();

        for (Locatario entity : locatarioDAO.findLocatarioEntities()) {
            dtoAux = modelMapper.map(entity, LocatarioDTO.class);
            dtos.add(dtoAux);
        }

        return dtos;
    }

    @Override
    public LocatarioDependienteDTO listarLocatarioDependienteID(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        LocatarioDependiente entity = locatarioDependienteDAO.findLocatarioDependiente(id);
        LocatarioDependienteDTO dto = modelMapper.map(entity, LocatarioDependienteDTO.class);

        return dto;
    }

    @Override
    public List<LocatarioDependienteDTO> listarTodosLocatariosDependientes() {
        ModelMapper modelMapper = new ModelMapper();
        LocatarioDependienteDTO dtoAux = null;
        List<LocatarioDependienteDTO> dtos = new ArrayList<>();

        for (LocatarioDependiente entity : locatarioDependienteDAO.findLocatarioDependienteEntities()) {
            dtoAux = modelMapper.map(entity, LocatarioDependienteDTO.class);
            dtos.add(dtoAux);
        }

        return dtos;
    }

    @Override
    public LocatarioIndependienteDTO listarLocatarioIndependienteID(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        LocatarioIndependiente entity = locatarioIndependienteDAO.findLocatarioIndependiente(id);
        LocatarioIndependienteDTO dto = modelMapper.map(entity, LocatarioIndependienteDTO.class);

        return dto;
    }

    @Override
    public List<LocatarioIndependienteDTO> listarTodosLocatariosIndependientes() {
        ModelMapper modelMapper = new ModelMapper();
        LocatarioIndependienteDTO dtoAux = null;
        List<LocatarioIndependienteDTO> dtos = new ArrayList<>();

        for (LocatarioIndependiente entity : locatarioIndependienteDAO.findLocatarioIndependienteEntities()) {
            dtoAux = modelMapper.map(entity, LocatarioIndependienteDTO.class);
            dtos.add(dtoAux);
        }

        return dtos;
    }

    @Override
    public LocatarioEstudianteDTO listarLocatarioEstudianteID(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        LocatarioEstudiante entity = locatarioEstudianteDAO.findLocatarioEstudiante(id);
        LocatarioEstudianteDTO dto = modelMapper.map(entity, LocatarioEstudianteDTO.class);

        return dto;
    }

    @Override
    public List<LocatarioEstudianteDTO> listarTodosLocatariosEstudiantes() {
        ModelMapper modelMapper = new ModelMapper();
        LocatarioEstudianteDTO dtoAux = null;
        List<LocatarioEstudianteDTO> dtos = new ArrayList<>();

        for (LocatarioEstudiante entity : locatarioEstudianteDAO.findLocatarioEstudianteEntities()) {
            dtoAux = modelMapper.map(entity, LocatarioEstudianteDTO.class);
            dtos.add(dtoAux);
        }

        return dtos;
    }

}
