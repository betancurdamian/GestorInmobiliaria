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
import model.dao.exceptions.IllegalOrphanException;
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
public class ClienteServiceImpl implements IClienteService{

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
                Locador entity = (Locador) this.converter.fromDto(dto);
                this.locadorDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof LocatarioDependienteDTO) {
                LocatarioDependiente entity = (LocatarioDependiente) this.converter.fromDto(dto);
                this.locatarioDependienteDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof LocatarioIndependienteDTO) {
                LocatarioIndependiente entity = (LocatarioIndependiente) this.converter.fromDto(dto);
                this.locatarioIndependienteDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof LocatarioEstudianteDTO) {
                LocatarioEstudiante entity = (LocatarioEstudiante) this.converter.fromDto(dto);
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
                    Locador entity = (Locador) this.converter.fromDto(dto);
                    try {
                        this.locadorDAO.edit(entity);
                    } catch (Exception ex) {
                        Logger.getLogger(ArancelEspecialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dto.setId(entity.getId());
                }
                if (dto instanceof LocatarioDependienteDTO) {
                    LocatarioDependiente entity = (LocatarioDependiente) this.converter.fromDto(dto);
                    try {
                        this.locatarioDAO.edit(entity);
                    } catch (Exception ex) {
                        Logger.getLogger(ArancelEspecialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dto.setId(entity.getId());
                }
                if (dto instanceof LocatarioIndependienteDTO) {
                    LocatarioIndependiente entity = (LocatarioIndependiente) this.converter.fromDto(dto);
                    try {
                        this.locatarioIndependienteDAO.edit(entity);
                    } catch (Exception ex) {
                        Logger.getLogger(ArancelEspecialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dto.setId(entity.getId());
                }
                if (dto instanceof LocatarioEstudianteDTO) {
                    LocatarioEstudiante entity = (LocatarioEstudiante) this.converter.fromDto(dto);
                    try {
                        this.locatarioEstudianteDAO.edit(entity);
                    } catch (Exception ex) {
                        Logger.getLogger(ArancelEspecialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dto.setId(entity.getId());
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
                } catch (IllegalOrphanException ex) {
                    Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
        return this.converter.fromEntity(entity);
    }

    @Override
    public List<ClienteDTO> listarTodos() {
        List<Cliente> entities = clienteDAO.findClienteEntities();
        return this.converter.fromEntity(entities);
    }

    @Override
    public LocadorDTO listarLocadorID(Long id) {
        Locador entity = locadorDAO.findLocador(id);
        LocadorDTO entityLocador =(LocadorDTO) converter.fromEntity(entity);
        return entityLocador;
    }

    @Override
    public List<LocadorDTO> listarTodosLocadores() {
        List<Locador> entities = locadorDAO.findLocadorEntities();
        List<LocadorDTO> entitiesLocadores = new ArrayList<>();
        for (Locador entity : entities) {
            entitiesLocadores.add((LocadorDTO) converter.fromEntity(entity));
        }
        return entitiesLocadores;
    }
}
