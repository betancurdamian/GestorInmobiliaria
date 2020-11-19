/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.EstadoCivilDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.Conexion;
import model.dao.EstadoCivilJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.EstadoCivil;
import model.service.IEstadoCivilService;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class EstadoCivilServiceImpl implements IEstadoCivilService {

    private final EstadoCivilJpaController estadoCivilDAO;
    private final InmobiliariaMapper converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public EstadoCivilServiceImpl() {
        new Conexion();
        this.estadoCivilDAO = new EstadoCivilJpaController(Conexion.getEmf());
        this.converter = Mappers.getMapper(InmobiliariaMapper.class);
    }

    @Override
    public EstadoCivilDTO crear(EstadoCivilDTO dto) {
        if (dto != null) {
            EstadoCivil entity = converter.toEntity(dto);
            this.estadoCivilDAO.create(entity);
            dto.setId(entity.getId());
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public EstadoCivilDTO modificar(EstadoCivilDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    EstadoCivil entity = converter.toEntity(dto);
                    estadoCivilDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(EstadoCivilServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (estadoCivilDAO.findEstadoCivil(id) != null) {
                try {
                    estadoCivilDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(EstadoCivilServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public EstadoCivilDTO listarID(Long id) {
        EstadoCivil entity = estadoCivilDAO.findEstadoCivil(id);
        return converter.toDTO(entity);
    }

    @Override
    public List<EstadoCivilDTO> listarTodos() {
        List<EstadoCivil> entities = estadoCivilDAO.findEstadoCivilEntities();
        return converter.toDTOEstadoCivilList(entities);
    }

}
