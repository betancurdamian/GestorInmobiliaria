/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.BarrioDTO;
import java.util.ArrayList;
import model.dao.BarrioJpaController;
import model.dao.Conexion;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Barrio;
import model.service.IBarrioService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Ariel
 */
public class BarrioServiceImpl implements IBarrioService {

    private final BarrioJpaController barrioDAO;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public BarrioServiceImpl() {
        new Conexion();
        this.barrioDAO = new BarrioJpaController(Conexion.getEmf());
    }

    @Override
    public BarrioDTO crear(BarrioDTO dto) {
        if (dto != null) {
            ModelMapper modelMapper = new ModelMapper();
            Barrio entity = modelMapper.map(dto, Barrio.class);
            this.barrioDAO.create(entity);
            dto.setId(entity.getId());
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public BarrioDTO modificar(BarrioDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    ModelMapper modelMapper = new ModelMapper();
                    Barrio entity = modelMapper.map(dto, Barrio.class);

                    barrioDAO.edit(entity);
                    dto.setId(entity.getId());
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
            if (barrioDAO.findBarrio(id) != null) {
                try {
                    barrioDAO.destroy(id);
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
    public BarrioDTO listarID(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Barrio entity = barrioDAO.findBarrio(id);
        BarrioDTO dto = modelMapper.map(entity, BarrioDTO.class);

        return dto;
    }

    @Override
    public List<BarrioDTO> listarTodos() {
        ModelMapper modelMapper = new ModelMapper();
        BarrioDTO dtoAux = null;
        List<BarrioDTO> dtos = new ArrayList<>();

        for (Barrio entitiy : barrioDAO.findBarrioEntities()) {
            dtoAux = modelMapper.map(entitiy, BarrioDTO.class);
            dtos.add(dtoAux);
        }

        return dtos;
    }

}
