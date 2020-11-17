
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.TipoDNIDTO;
import model.dao.Conexion;
import model.service.ITipoDNIService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.TipoDNIJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.TipoDNI;
import org.mapstruct.factory.Mappers;

public class TipoDNIServiceImpl implements ITipoDNIService{

    private final TipoDNIJpaController tipoDNIDAO;
    private final InmobiliariaMapper converter = Mappers.getMapper(InmobiliariaMapper.class);
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public TipoDNIServiceImpl() {
        new Conexion();
        this.tipoDNIDAO = new TipoDNIJpaController(Conexion.getEmf());
    }

    
    @Override
    public TipoDNIDTO crear(TipoDNIDTO dto) {
        if (dto != null) {
            TipoDNI entity = converter.toEntity(dto);
            this.tipoDNIDAO.create(entity);
            dto.setId(entity.getId());
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public TipoDNIDTO modificar(TipoDNIDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    TipoDNI entity = converter.toEntity(dto);
                    tipoDNIDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(TipoDNIServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (tipoDNIDAO.findTipoDNI(id) != null) {
                try {
                    tipoDNIDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(TipoDNIServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public TipoDNIDTO listarID(Long id) {
        TipoDNI entity = tipoDNIDAO.findTipoDNI(id);
        return converter.toDTO(entity);
    }

    @Override
    public List<TipoDNIDTO> listarTodos() {
        List<TipoDNI> entities = tipoDNIDAO.findTipoDNIEntities();        
        return converter.toDTOTipoDNIList(entities);
    }
    
}

