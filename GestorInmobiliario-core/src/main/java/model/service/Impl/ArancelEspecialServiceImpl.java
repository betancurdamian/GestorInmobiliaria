/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.ArancelEspecialDTO;
import dto.ArancelEspecialExpensaDTO;
import dto.ArancelEspecialServicioDTO;
import java.util.ArrayList;
import model.dao.ArancelEspecialExpensaJpaController;
import model.dao.ArancelEspecialJpaController;
import model.dao.ArancelEspecialServicioJpaController;
import model.dao.Conexion;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.ArancelEspecialExpensa;
import model.entity.ArancelEspecialServicio;
import model.service.IArancelEspecialService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.ArancelEspecial;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Ariel
 */
public class ArancelEspecialServiceImpl implements IArancelEspecialService {

    private final ArancelEspecialExpensaJpaController arancelEspecialExpensaDAO;
    private final ArancelEspecialServicioJpaController arancelEspecialServicioDAO;
    private final ArancelEspecialJpaController arancelEspecialDAO;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ArancelEspecialServiceImpl() {
        new Conexion();
        this.arancelEspecialDAO = new ArancelEspecialJpaController(Conexion.getEmf());
        this.arancelEspecialExpensaDAO = new ArancelEspecialExpensaJpaController(Conexion.getEmf());
        this.arancelEspecialServicioDAO = new ArancelEspecialServicioJpaController(Conexion.getEmf());
    }

    @Override
    public ArancelEspecialDTO crear(ArancelEspecialDTO dto) {
        if (dto != null) {
            if (dto instanceof ArancelEspecialExpensaDTO) {
                ModelMapper modelMapper = new ModelMapper();
                ArancelEspecialExpensa entity = modelMapper.map(dto, ArancelEspecialExpensa.class);

                this.arancelEspecialExpensaDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof ArancelEspecialServicioDTO) {
                ModelMapper modelMapper = new ModelMapper();
                ArancelEspecialServicio entity = modelMapper.map(dto, ArancelEspecialServicio.class);

                this.arancelEspecialServicioDAO.create(entity);
                dto.setId(entity.getId());
            }
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public ArancelEspecialDTO modificar(ArancelEspecialDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                if (dto instanceof ArancelEspecialExpensaDTO) {
                    try {
                        ModelMapper modelMapper = new ModelMapper();
                        ArancelEspecialExpensa entity = modelMapper.map(dto, ArancelEspecialExpensa.class);
                        arancelEspecialExpensaDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ArancelEspecialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof ArancelEspecialServicioDTO) {
                    try {
                        ModelMapper modelMapper = new ModelMapper();
                        ArancelEspecialServicio entity = modelMapper.map(dto, ArancelEspecialServicio.class);
                        arancelEspecialServicioDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ArancelEspecialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
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
            if (arancelEspecialDAO.findArancelEspecial(id) != null) {
                try {
                    arancelEspecialDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(InmuebleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public ArancelEspecialDTO listarID(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        ArancelEspecial entity = arancelEspecialDAO.findArancelEspecial(id);
        ArancelEspecialDTO dto = modelMapper.map(entity, ArancelEspecialDTO.class);

        return dto;
    }

    @Override
    public List<ArancelEspecialDTO> listarTodos() {
        ModelMapper modelMapper = new ModelMapper();
        ArancelEspecialDTO dtoAux = null;
        List<ArancelEspecialDTO> dtos = new ArrayList<>();

        for (ArancelEspecial entitiy : arancelEspecialDAO.findArancelEspecialEntities()) {
            dtoAux = modelMapper.map(entitiy, ArancelEspecialDTO.class);
            dtos.add(dtoAux);
        }

        return dtos;
    }
}
