/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.RecargoPorMoraDTO;
import model.dao.Conexion;
import model.service.IRecargoPorMoraService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.RecargoPorMoraJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.RecargoPorMora;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class RecargoPorMoraServiceImpl implements IRecargoPorMoraService{

    private final RecargoPorMoraJpaController recargoPorMoraDAO;
    private final InmobiliariaMapper converter;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public RecargoPorMoraServiceImpl() {
        new Conexion();
        this.recargoPorMoraDAO = new RecargoPorMoraJpaController(Conexion.getEmf());
        this.converter = Mappers.getMapper(InmobiliariaMapper.class);
    }

    @Override
    public RecargoPorMoraDTO crear(RecargoPorMoraDTO dto) {
        if (dto != null) {
            RecargoPorMora entity = converter.toRecargoPorMoraEntity(dto);
            this.recargoPorMoraDAO.create(entity);
            dto.setId(entity.getId());
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public RecargoPorMoraDTO modificar(RecargoPorMoraDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    RecargoPorMora entity = converter.toRecargoPorMoraEntity(dto);
                    recargoPorMoraDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(RecargoPorMoraServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (recargoPorMoraDAO.findRecargoPorMora(id) != null) {
                try {
                    recargoPorMoraDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(RecargoPorMoraServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public RecargoPorMoraDTO listarID(Long id) {
        RecargoPorMora entity = recargoPorMoraDAO.findRecargoPorMora(id);
        return converter.toRecargoPorMoraDTO(entity);
    }

    @Override
    public List<RecargoPorMoraDTO> listarTodos() {
        List<RecargoPorMora> entities = recargoPorMoraDAO.findRecargoPorMoraEntities();
        return converter.toDTORecargoPorMoraList(entities);
    }

    @Override
    public RecargoPorMoraDTO obtenerUltimoRecargoPorMora() {
        RecargoPorMoraDTO ultimoRecargoPorMora = null;        
        for (RecargoPorMora rpm : recargoPorMoraDAO.findRecargoPorMoraEntities(1, recargoPorMoraDAO.getRecargoPorMoraCount()-1)) {
            ultimoRecargoPorMora = converter.toRecargoPorMoraDTO(rpm);
        }        
        return ultimoRecargoPorMora;
    }
    
}
