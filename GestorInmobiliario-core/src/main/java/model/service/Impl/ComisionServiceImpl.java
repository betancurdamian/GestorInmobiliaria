package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.ComisionDTO;
import model.dao.Conexion;
import model.service.IComisionService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.ComisionJpaController;
import model.dao.LineaDeComisionJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Comision;
import model.entity.LineaDeComision;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class ComisionServiceImpl implements IComisionService {

    private final ComisionJpaController comisionDAO;
    private final LineaDeComisionJpaController lineaDeComisionDAO;
    private final InmobiliariaMapper converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ComisionServiceImpl() {
        this.comisionDAO = new ComisionJpaController(Conexion.getEmf());
        this.lineaDeComisionDAO = new LineaDeComisionJpaController(Conexion.getEmf());
        this.converter = Mappers.getMapper(InmobiliariaMapper.class);
    }

    @Override
    public ComisionDTO crear(ComisionDTO dto) {
        if (dto != null) {
            Comision entity = converter.toEntity(dto);

            entity.setLineasDeComisiones(null);
            comisionDAO.create(entity);

            dto.getLineasDeComisiones().forEach(lcdto -> {
                LineaDeComision lc = new LineaDeComision();
                lc = converter.toEntity(lcdto);
                lc.setUnaComision(entity);
                lineaDeComisionDAO.create(lc);
            });
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public ComisionDTO modificar(ComisionDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    Comision entity = converter.toEntity(dto);
                    entity.setLineasDeComisiones(null);
                    comisionDAO.edit(entity);

                    dto.getLineasDeComisiones().forEach(lcdto -> {
                        LineaDeComision lc = new LineaDeComision();
                        lc = converter.toEntity(lcdto);
                        lc.setUnaComision(entity);
                        try {
                            lineaDeComisionDAO.edit(lc);
                        } catch (Exception ex) {
                            Logger.getLogger(ComisionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(ComisionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (comisionDAO.findComision(id) != null) {
                try {
                    comisionDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(ComisionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public ComisionDTO listarID(Long id) {
        Comision entity = comisionDAO.findComision(id);
        return converter.toDTO(entity);
    }

    @Override
    public List<ComisionDTO> listarTodos() {
        List<Comision> entities = comisionDAO.findComisionEntities();
        return converter.toDTOComisionList(entities);
    }

}
