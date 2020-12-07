
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.AlquilerDTO;
import dto.ContratoAlquilerDTO;
import model.dao.AlquilerJpaController;
import model.dao.Conexion;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Alquiler;
import model.service.IAlquilerService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class AlquilerServiceImpl implements IAlquilerService {

    private final AlquilerJpaController alquilerDAO;
    private final InmobiliariaMapper converter;
    private final ContratoServiceImpl contratoService;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public AlquilerServiceImpl() {
        new Conexion();
        this.alquilerDAO = new AlquilerJpaController(Conexion.getEmf());
        this.converter = Mappers.getMapper(InmobiliariaMapper.class);
        this.contratoService = new ContratoServiceImpl();
    }

    @Override
    public AlquilerDTO crear(AlquilerDTO dto) {
        if (dto != null) {
            Alquiler entity = converter.toAlquilerEntity(dto);
            entity.setUnContratoAlquiler(null);
            this.alquilerDAO.create(entity);
            dto.setId(entity.getId());
            
            ContratoAlquilerDTO contrato = dto.getUnContratoAlquiler();
            contrato.setUnAlquiler(dto);
            this.contratoService.crear(dto.getUnContratoAlquiler());
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public AlquilerDTO modificar(AlquilerDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    Alquiler entity = converter.toAlquilerEntity(dto);
                    alquilerDAO.edit(entity);
                } catch (Exception ex) {
                    Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (alquilerDAO.findAlquiler(id) != null) {
                try {
                    alquilerDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public AlquilerDTO listarID(Long id) {
        Alquiler entity = alquilerDAO.findAlquiler(id);
        return converter.toAlquilerDTO(entity);
    }

    @Override
    public List<AlquilerDTO> listarTodos() {
        List<Alquiler> entities = alquilerDAO.findAlquilerEntities();
        return converter.toDTOAlquilerList(entities);
    }

}
