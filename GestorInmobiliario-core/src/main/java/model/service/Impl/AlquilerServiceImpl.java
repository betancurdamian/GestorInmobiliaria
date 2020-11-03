/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.AlquilerDTO;
import java.util.ArrayList;
import model.dao.AlquilerJpaController;
import model.dao.Conexion;
import model.dao.ContratoAlquilerJpaController;
import model.dao.InmobiliariaJpaController;
import model.dao.InmuebleJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Alquiler;
import model.service.IAlquilerService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Ariel
 */
public class AlquilerServiceImpl implements IAlquilerService {

    private final AlquilerJpaController alquilerDAO;
    private final InmobiliariaJpaController inmobiliariaDAO;
    private final InmuebleJpaController inmuebleDAO;
    private final ContratoAlquilerJpaController contratoAlquilerDAO;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public AlquilerServiceImpl() {
        new Conexion();
        this.alquilerDAO = new AlquilerJpaController(Conexion.getEmf());
        this.inmobiliariaDAO = new InmobiliariaJpaController(Conexion.getEmf());
        this.inmuebleDAO = new InmuebleJpaController(Conexion.getEmf());
        this.contratoAlquilerDAO = new ContratoAlquilerJpaController(Conexion.getEmf());

    }

    @Override
    public AlquilerDTO crear(AlquilerDTO dto) {
        if (dto != null) {
            ModelMapper modelMapper = new ModelMapper();
            Alquiler entity = modelMapper.map(dto, Alquiler.class);
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
                    ModelMapper modelMapper = new ModelMapper();
                    Alquiler entity = modelMapper.map(dto, Alquiler.class);

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
        ModelMapper modelMapper = new ModelMapper();
        Alquiler entity = alquilerDAO.findAlquiler(id);
        AlquilerDTO dto = modelMapper.map(entity, AlquilerDTO.class);

        return dto;
    }

    @Override
    public List<AlquilerDTO> listarTodos() {
        ModelMapper modelMapper = new ModelMapper();
        AlquilerDTO dtoAux = null;
        List<AlquilerDTO> dtos = new ArrayList<>();
        
        for (Alquiler entitiy : alquilerDAO.findAlquilerEntities()) {
            dtoAux = modelMapper.map(entitiy, AlquilerDTO.class);
            dtos.add(dtoAux);
        }
        
        return dtos;
    }

}
