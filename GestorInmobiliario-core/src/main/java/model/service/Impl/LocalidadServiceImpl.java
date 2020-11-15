/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.LocalidadDTO;
import java.util.ArrayList;
import model.dao.Conexion;
import model.dao.LocalidadJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Localidad;
import model.service.ILocalidadService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Ariel
 */
public class LocalidadServiceImpl implements ILocalidadService{

    private final LocalidadJpaController localidadDAO;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public LocalidadServiceImpl() {
        new Conexion();
        this.localidadDAO = new LocalidadJpaController(Conexion.getEmf());        
    }

    
    
    @Override
    public LocalidadDTO crear(LocalidadDTO dto) {
        if (dto != null) {
            ModelMapper modelMapper = new ModelMapper();
            Localidad entity = modelMapper.map(dto, Localidad.class);
            this.localidadDAO.create(entity);
            dto.setId(entity.getId());
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public LocalidadDTO modificar(LocalidadDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    ModelMapper modelMapper = new ModelMapper();
                    Localidad entity = modelMapper.map(dto, Localidad.class);

                    localidadDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(LocalidadServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (localidadDAO.findLocalidad(id) != null) {
                try {
                    localidadDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(LocalidadServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public LocalidadDTO listarID(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Localidad entity = localidadDAO.findLocalidad(id);
        LocalidadDTO dto = modelMapper.map(entity, LocalidadDTO.class);

        return dto;
    }

    @Override
    public List<LocalidadDTO> listarTodos() {
        ModelMapper modelMapper = new ModelMapper();
        LocalidadDTO dtoAux = null;
        List<LocalidadDTO> dtos = new ArrayList<>();

        for (Localidad entitiy : localidadDAO.findLocalidadEntities()) {
            dtoAux = modelMapper.map(entitiy, LocalidadDTO.class);
            dtos.add(dtoAux);
        }

        return dtos;
    }
    
}
