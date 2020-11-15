/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.ActividadConverter;
import dto.ActividadDTO;
import java.util.ArrayList;
import model.dao.Conexion;
import model.service.IActividadService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.ActividadJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Actividad;

/**
 *
 * @author Ariel
 */
public class ActividadServiceImpl implements IActividadService {

    private final ActividadJpaController actividadDAO;
    private final ActividadConverter converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ActividadServiceImpl() {
        new Conexion();
        this.actividadDAO = new ActividadJpaController(Conexion.getEmf());
        this.converter = new ActividadConverter();

    }

    @Override
    public ActividadDTO crear(ActividadDTO dto) {
        if (dto != null) {
            Actividad entity = converter.fomDTO(dto);
            this.actividadDAO.create(entity);
            dto.setId(entity.getId());
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public ActividadDTO modificar(ActividadDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    Actividad entity = converter.fomDTO(dto);
                    actividadDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(ActividadServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (actividadDAO.findActividad(id) != null) {
                try {
                    actividadDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(ActividadServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public ActividadDTO listarID(Long id) {
        Actividad entity = actividadDAO.findActividad(id);
        ActividadDTO dto = converter.fromDTO(entity);

        return dto;
    }

    @Override
    public List<ActividadDTO> listarTodos() {
        ActividadDTO dtoAux = null;
        List<ActividadDTO> dtos = new ArrayList<>();

        for (Actividad entitiy : actividadDAO.findActividadEntities()) {
            dtoAux = converter.fromDTO(entitiy);
            dtos.add(dtoAux);
        }

        return dtos;
    }

}
