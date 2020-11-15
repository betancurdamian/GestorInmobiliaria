/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.AlquilerConverter;
import dto.AlquilerDTO;
import java.util.ArrayList;
import model.dao.AlquilerJpaController;
import model.dao.Conexion;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Alquiler;
import model.service.IAlquilerService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ariel
 */
public class AlquilerServiceImpl implements IAlquilerService {

    private final AlquilerJpaController alquilerDAO;
    private final AlquilerConverter converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public AlquilerServiceImpl() {
        new Conexion();
        this.alquilerDAO = new AlquilerJpaController(Conexion.getEmf());
        this.converter = new AlquilerConverter();
    }

    @Override
    public AlquilerDTO crear(AlquilerDTO dto) {
        if (dto != null) {
            Alquiler entity = converter.fomDTO(dto);
            this.alquilerDAO.create(entity);
            dto.setId(entity.getId());
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
                    Alquiler entity = converter.fomDTO(dto);
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
        AlquilerDTO dto = converter.fromDTO(entity);

        return dto;
    }

    @Override
    public List<AlquilerDTO> listarTodos() {
        AlquilerDTO dtoAux = null;
        List<AlquilerDTO> dtos = new ArrayList<>();
        
        for (Alquiler entitiy : alquilerDAO.findAlquilerEntities()) {
            dtoAux = converter.fromDTO(entitiy);
            dtos.add(dtoAux);
        }
        
        return dtos;
    }

}
