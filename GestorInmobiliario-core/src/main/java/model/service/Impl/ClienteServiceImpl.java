package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.ClienteDTO;
import dto.GaranteDTO;
import dto.LocadorDTO;
import dto.LocatarioDTO;
import dto.LocatarioDependienteDTO;
import dto.LocatarioEstudianteDTO;
import dto.LocatarioIndependienteDTO;
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
import org.mapstruct.factory.Mappers;

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
    private final InmobiliariaMapper converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ClienteServiceImpl() {
        new Conexion();
        this.clienteDAO = new ClienteJpaController(Conexion.getEmf());
        this.locadorDAO = new LocadorJpaController(Conexion.getEmf());
        this.locatarioDAO = new LocatarioJpaController(Conexion.getEmf());
        this.locatarioDependienteDAO = new LocatarioDependienteJpaController(Conexion.getEmf());
        this.locatarioIndependienteDAO = new LocatarioIndependienteJpaController(Conexion.getEmf());
        this.locatarioEstudianteDAO = new LocatarioEstudianteJpaController(Conexion.getEmf());
        this.converter = Mappers.getMapper(InmobiliariaMapper.class);
    }

    @Override
    public ClienteDTO crear(ClienteDTO dto) {
        if (dto != null) {
            if (dto instanceof LocadorDTO) {
                Locador entity = converter.toLocadorEntity((LocadorDTO) dto);
                this.locadorDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof LocatarioDependienteDTO) {
                LocatarioDependiente entity = converter.toLocatatioDependienteEntity((LocatarioDependienteDTO) dto);
                this.locatarioDependienteDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof LocatarioIndependienteDTO) {
                LocatarioIndependiente entity = converter.toLocatarioIndependienteEntity((LocatarioIndependienteDTO) dto);
                this.locatarioIndependienteDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof LocatarioEstudianteDTO) {
                LocatarioEstudiante entity = converter.toLocatarioEstudianteEntity((LocatarioEstudianteDTO) dto);
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
                        Locador entity = converter.toLocadorEntity((LocadorDTO) dto);
                        locadorDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof LocatarioDependienteDTO) {
                    try {
                        LocatarioDependiente entity = converter.toLocatatioDependienteEntity((LocatarioDependienteDTO) dto);
                        locatarioDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof LocatarioIndependienteDTO) {
                    try {
                        LocatarioIndependiente entity = converter.toLocatarioIndependienteEntity((LocatarioIndependienteDTO) dto);
                        locatarioIndependienteDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof LocatarioEstudianteDTO) {
                    try {
                        LocatarioEstudiante entity = converter.toLocatarioEstudianteEntity((LocatarioEstudianteDTO) dto);
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
            dto = converter.toLocadorDTO((Locador) entity);
        }
        if (entity instanceof LocatarioDependiente) {
            dto = converter.toLocatatioDependienteDTO((LocatarioDependiente) entity);
        }
        if (entity instanceof LocatarioIndependiente) {
            dto = converter.toLocatarioIndependienteDTO((LocatarioIndependiente) entity);
        }
        if (entity instanceof LocatarioEstudiante) {
            dto = converter.toLocatarioEstudianteDTO((LocatarioEstudiante) entity);
        }
        return dto;
    }

    @Override
    public List<ClienteDTO> listarTodos() {
        List<Cliente> entities = clienteDAO.findClienteEntities();
        return converter.toDTOClienteList(entities);
    }

    @Override
    public LocadorDTO listarLocadorID(Long id) {
        Locador entity = locadorDAO.findLocador(id);
        return converter.toLocadorDTO(entity);
    }

    @Override
    public List<LocadorDTO> listarTodosLocadores() {
        List<Locador> entities = locadorDAO.findLocadorEntities();
        return converter.toDTOLocadorList(entities);
    }

    @Override
    public LocatarioDTO listarLocatarioID(Long id) {
        Locatario entity = locatarioDAO.findLocatario(id);
        return converter.toLocatarioDTO(entity);
    }

    @Override
    public List<LocatarioDTO> listarTodosLocatarios() {
        List<Locatario> entities = locatarioDAO.findLocatarioEntities();
        return converter.toDTOLocatarioList(entities);
    }

    @Override
    public LocatarioDependienteDTO listarLocatarioDependienteID(Long id) {
        LocatarioDependiente entity = locatarioDependienteDAO.findLocatarioDependiente(id);
        return converter.toLocatatioDependienteDTO(entity);
    }

    @Override
    public List<LocatarioDependienteDTO> listarTodosLocatariosDependientes() {
        List<LocatarioDependiente> entities = locatarioDependienteDAO.findLocatarioDependienteEntities();
        return converter.toDTOLocatarioDependienteList(entities);
    }

    @Override
    public LocatarioIndependienteDTO listarLocatarioIndependienteID(Long id) {
        LocatarioIndependiente entity = locatarioIndependienteDAO.findLocatarioIndependiente(id);
        return converter.toLocatarioIndependienteDTO(entity);
    }

    @Override
    public List<LocatarioIndependienteDTO> listarTodosLocatariosIndependientes() {
        List<LocatarioIndependiente> entities = locatarioIndependienteDAO.findLocatarioIndependienteEntities();
        return converter.toDTOLocatarioIndependienteList(entities);
    }

    @Override
    public LocatarioEstudianteDTO listarLocatarioEstudianteID(Long id) {
        LocatarioEstudiante entity = locatarioEstudianteDAO.findLocatarioEstudiante(id);
        return converter.toLocatarioEstudianteDTO(entity);
    }

    @Override
    public List<LocatarioEstudianteDTO> listarTodosLocatariosEstudiantes() {
        List<LocatarioEstudiante> entities = locatarioEstudianteDAO.findLocatarioEstudianteEntities();
        return converter.toDTOLocatarioEstudianteList(entities);
    }


}
