package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.ContratoAlquilerDTO;
import dto.ContratoDTO;
import dto.ContratoVentaDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.Conexion;
import model.dao.ContratoAlquilerJpaController;
import model.dao.ContratoJpaController;
import model.dao.ContratoVentaJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Contrato;
import model.entity.ContratoAlquiler;
import model.entity.ContratoVenta;
import model.service.IContratoService;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class ContratoServiceImpl implements IContratoService {

    private final ContratoJpaController contratoDAO;
    private final ContratoVentaJpaController contratoVentaDAO;
    private final ContratoAlquilerJpaController contratoAlquilerDAO;
    private final InmobiliariaMapper converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ContratoServiceImpl() {
        new Conexion();
        this.contratoDAO = new ContratoJpaController(Conexion.getEmf());
        this.contratoVentaDAO = new ContratoVentaJpaController(Conexion.getEmf());
        this.contratoAlquilerDAO = new ContratoAlquilerJpaController(Conexion.getEmf());
        this.converter = Mappers.getMapper(InmobiliariaMapper.class);
    }

    @Override
    public ContratoDTO crear(ContratoDTO dto) {
        if (dto != null) {
            if (dto instanceof ContratoVentaDTO) {
                ContratoVenta entity = converter.toContratoVentaEntity((ContratoVentaDTO) dto);
                this.contratoVentaDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof ContratoAlquilerDTO) {
                ContratoAlquiler entity = converter.toContratoAlquilerEntity((ContratoAlquilerDTO) dto);
                this.contratoAlquilerDAO.create(entity);
                dto.setId(entity.getId());
            }
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public ContratoDTO modificar(ContratoDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                if (dto instanceof ContratoVentaDTO) {
                    try {
                        ContratoVenta entity = converter.toContratoVentaEntity((ContratoVentaDTO) dto);
                        this.contratoVentaDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ContratoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof ContratoAlquilerDTO) {
                    try {
                        ContratoAlquiler entity = converter.toContratoAlquilerEntity((ContratoAlquilerDTO) dto);
                        this.contratoAlquilerDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ContratoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (contratoDAO.findContrato(id) != null) {
                try {
                    contratoDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(ArancelEspecialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public ContratoDTO listarID(Long id) {
        ContratoDTO dto = null;
        Contrato entity = contratoDAO.findContrato(id);
        if (entity instanceof ContratoVenta) {
            dto = converter.toContratoVentaDTO((ContratoVenta) entity);
        }
        if (entity instanceof ContratoAlquiler) {
            dto = converter.toContratoAlquilerDTO((ContratoAlquiler) entity);
        }
        return dto;
    }

    @Override
    public List<ContratoDTO> listarTodos() {
        List<Contrato> entities = contratoDAO.findContratoEntities();
        return converter.toDTOContratoList(entities);
    }

    @Override
    public ContratoVentaDTO listarContratoVentaID(Long id) {
        ContratoVenta entity = contratoVentaDAO.findContratoVenta(id);
        return converter.toContratoVentaDTO(entity);
    }

    @Override
    public List<ContratoVentaDTO> listarTodosContratosVentas() {
        List<ContratoVenta> entities = contratoVentaDAO.findContratoVentaEntities();
        return converter.toDTOContratoVentaList(entities);
    }

    @Override
    public ContratoAlquilerDTO listarContratoAlquilerID(Long id) {
        ContratoAlquiler entity = contratoAlquilerDAO.findContratoAlquiler(id);
        return converter.toContratoAlquilerDTO(entity);
    }

    @Override
    public List<ContratoAlquilerDTO> listarTodosContratosAlquileres() {
        List<ContratoAlquiler> entities = contratoAlquilerDAO.findContratoAlquilerEntities();
        return converter.toDTOContratoAlquilerList(entities);
    }

}
