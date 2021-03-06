
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.ArancelEspecialDTO;
import dto.ArancelEspecialExpensaDTO;
import dto.ArancelEspecialServicioDTO;
import model.dao.ArancelEspecialExpensaJpaController;
import model.dao.ArancelEspecialJpaController;
import model.dao.ArancelEspecialServicioJpaController;
import model.dao.Conexion;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.ArancelEspecialExpensa;
import model.entity.ArancelEspecialServicio;
import model.service.IArancelEspecialService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.ArancelEspecial;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class ArancelEspecialServiceImpl implements IArancelEspecialService {

    private final ArancelEspecialExpensaJpaController arancelEspecialExpensaDAO;
    private final ArancelEspecialServicioJpaController arancelEspecialServicioDAO;
    private final ArancelEspecialJpaController arancelEspecialDAO;
    private final InmobiliariaMapper converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ArancelEspecialServiceImpl() {
        new Conexion();
        this.arancelEspecialDAO = new ArancelEspecialJpaController(Conexion.getEmf());
        this.arancelEspecialExpensaDAO = new ArancelEspecialExpensaJpaController(Conexion.getEmf());
        this.arancelEspecialServicioDAO = new ArancelEspecialServicioJpaController(Conexion.getEmf());
        this.converter = Mappers.getMapper(InmobiliariaMapper.class);
    }

    @Override
    public ArancelEspecialDTO crear(ArancelEspecialDTO dto) {
        if (dto != null) {
            if (dto instanceof ArancelEspecialExpensaDTO) {
                ArancelEspecialExpensa entity = converter.toArancelEspecialExpensaEntity((ArancelEspecialExpensaDTO) dto);
                this.arancelEspecialExpensaDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof ArancelEspecialServicioDTO) {

                ArancelEspecialServicio entity = converter.toArancelEspecialServicioEntity((ArancelEspecialServicioDTO) dto);
                this.arancelEspecialServicioDAO.create(entity);
                dto.setId(entity.getId());
            }
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public ArancelEspecialDTO modificar(ArancelEspecialDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                if (dto instanceof ArancelEspecialExpensaDTO) {
                    try {
                        ArancelEspecialExpensa entity = converter.toArancelEspecialExpensaEntity((ArancelEspecialExpensaDTO) dto);
                        this.arancelEspecialExpensaDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ArancelEspecialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof ArancelEspecialServicioDTO) {
                    try {
                        ArancelEspecialServicio entity = converter.toArancelEspecialServicioEntity((ArancelEspecialServicioDTO) dto);
                        this.arancelEspecialServicioDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ArancelEspecialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (arancelEspecialDAO.findArancelEspecial(id) != null) {
                try {
                    arancelEspecialDAO.destroy(id);
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
    public ArancelEspecialDTO listarID(Long id) {
        ArancelEspecialDTO dto = null;
        ArancelEspecial entity = arancelEspecialDAO.findArancelEspecial(id);
        if (entity instanceof ArancelEspecialExpensa) {
            dto = converter.toArancelEspecialExpensaDTO((ArancelEspecialExpensa) entity);
        }
        if (entity instanceof ArancelEspecialServicio) {
            dto = converter.toArancelEspecialServicioDTO((ArancelEspecialServicio) entity);
        }
        return dto;
    }

    @Override
    public List<ArancelEspecialDTO> listarTodos() {
        List<ArancelEspecial> entities = arancelEspecialDAO.findArancelEspecialEntities();
        return converter.toDTOArancelEspecialList(entities);
    }

    @Override
    public ArancelEspecialExpensaDTO listarArancelEspecialExpensaID(Long id) {
        ArancelEspecialExpensa entity = arancelEspecialExpensaDAO.findArancelEspecialExpensa(id);
        return converter.toArancelEspecialExpensaDTO(entity);
    }

    @Override
    public List<ArancelEspecialExpensaDTO> listarTodosArancelEspecialesExpensas() {
        List<ArancelEspecialExpensa> entities = arancelEspecialExpensaDAO.findArancelEspecialExpensaEntities();
        return converter.toDTOArancelEspecialExpensaList(entities);
    }

    @Override
    public ArancelEspecialServicioDTO listarArancelEspecialServicioID(Long id) {
        ArancelEspecialServicio entity = arancelEspecialServicioDAO.findArancelEspecialServicio(id);
        return converter.toArancelEspecialServicioDTO(entity);
    }

    @Override
    public List<ArancelEspecialServicioDTO> listarTodosArancelEspecialesServicios() {
        List<ArancelEspecialServicio> entities = arancelEspecialServicioDAO.findArancelEspecialServicioEntities();
        return converter.toDTOArancelEspecialServicioList(entities);
    }
}
