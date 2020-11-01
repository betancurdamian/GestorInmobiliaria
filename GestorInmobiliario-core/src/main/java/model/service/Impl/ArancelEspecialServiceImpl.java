/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.ArancelEspecialConverter;
import dto.ArancelEspecialDTO;
import dto.ArancelEspecialExpensaDTO;
import dto.ArancelEspecialServicioDTO;
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

/**
 *
 * @author Ariel
 */
public class ArancelEspecialServiceImpl implements IArancelEspecialService {

    private final ArancelEspecialExpensaJpaController arancelEspecialExpensaDAO;
    private final ArancelEspecialServicioJpaController arancelEspecialServicioDAO;
    private final ArancelEspecialJpaController arancelEspecialDAO;

    private final ArancelEspecialConverter converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ArancelEspecialServiceImpl() {
        new Conexion();
        this.arancelEspecialDAO = new ArancelEspecialJpaController(Conexion.getEmf());
        this.arancelEspecialExpensaDAO = new ArancelEspecialExpensaJpaController(Conexion.getEmf());
        this.arancelEspecialServicioDAO = new ArancelEspecialServicioJpaController(Conexion.getEmf());
        this.converter = new ArancelEspecialConverter();
    }

    @Override
    public ArancelEspecialDTO crear(ArancelEspecialDTO dto) {
        if (dto != null) {
            if (dto instanceof ArancelEspecialExpensaDTO) {
                ArancelEspecialExpensa entity = (ArancelEspecialExpensa) this.converter.fromDto(dto);
                this.arancelEspecialExpensaDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof ArancelEspecialServicioDTO) {
                ArancelEspecialServicio entity = (ArancelEspecialServicio) this.converter.fromDto(dto);
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
                    ArancelEspecialExpensa entity = (ArancelEspecialExpensa) this.converter.fromDto(dto);
                    try {
                        this.arancelEspecialExpensaDAO.edit(entity);
                    } catch (Exception ex) {
                        Logger.getLogger(ArancelEspecialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dto.setId(entity.getId());
                }
                if (dto instanceof ArancelEspecialServicioDTO) {
                    ArancelEspecialServicio entity = (ArancelEspecialServicio) this.converter.fromDto(dto);
                    try {
                        this.arancelEspecialServicioDAO.edit(entity);
                    } catch (Exception ex) {
                        Logger.getLogger(ArancelEspecialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dto.setId(entity.getId());
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
        ArancelEspecial entity = arancelEspecialDAO.findArancelEspecial(id);
        return this.converter.fromEntity(entity);
    }

    @Override
    public List<ArancelEspecialDTO> listarTodos() {
        List<ArancelEspecial> entities = arancelEspecialDAO.findArancelEspecialEntities();
        return this.converter.fromEntity(entities);
    }
}
