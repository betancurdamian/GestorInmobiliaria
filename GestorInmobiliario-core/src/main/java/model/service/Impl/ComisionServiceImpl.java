/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.ComisionDTO;
import dto.LineaDeComisionDTO;
import java.util.ArrayList;
import model.dao.Conexion;
import model.service.IComisionService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.ComisionJpaController;
import model.dao.LineaDeComisionJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Comision;
import model.entity.LineaDeComision;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Ariel
 */
public class ComisionServiceImpl implements IComisionService {

    private final ComisionJpaController comisionDAO;
    private final LineaDeComisionJpaController lineaDeComisionDAO;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ComisionServiceImpl() {
        this.comisionDAO = new ComisionJpaController(Conexion.getEmf());
        this.lineaDeComisionDAO = new LineaDeComisionJpaController(Conexion.getEmf());
    }

    @Override
    public ComisionDTO crear(ComisionDTO dto) {
        System.out.println("");
        if (dto != null) {
            ModelMapper modelMapper = new ModelMapper();
            Comision entity = modelMapper.map(dto, Comision.class);
                        
            comisionDAO.create(entity);
            for (LineaDeComisionDTO lineasDeComisione : dto.getLinesasDeComisiones()) {
                LineaDeComision entityLinea = modelMapper.map(lineasDeComisione, LineaDeComision.class);
                entityLinea.setUnaComision(entity);
                lineaDeComisionDAO.create(entityLinea);
            }
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public ComisionDTO modificar(ComisionDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    ModelMapper modelMapper = new ModelMapper();
                    Comision entity = modelMapper.map(dto, Comision.class);

                    comisionDAO.edit(entity);
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
            if (comisionDAO.findComision(id) != null) {
                try {
                    comisionDAO.destroy(id);
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
    public ComisionDTO listarID(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Comision entity = comisionDAO.findComision(id);
        ComisionDTO dto = modelMapper.map(entity, ComisionDTO.class);

        return dto;
    }

    @Override
    public List<ComisionDTO> listarTodos() {
        ModelMapper modelMapper = new ModelMapper();
        ComisionDTO dtoAux = null;
        List<ComisionDTO> dtos = new ArrayList<>();

        for (Comision entitiy : comisionDAO.findComisionEntities()) {
            dtoAux = modelMapper.map(entitiy, ComisionDTO.class);
            dtos.add(dtoAux);
        }

        return dtos;
    }

}
