/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.InmobiliariaMapper;
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
import org.mapstruct.factory.Mappers;
import util.Converter;

/**
 *
 * @author Ariel
 */
public class ArancelEspecialServiceImpl implements IArancelEspecialService {

    private final ArancelEspecialExpensaJpaController arancelEspecialExpensaDAO;
    private final ArancelEspecialServicioJpaController arancelEspecialServicioDAO;
    private final ArancelEspecialJpaController arancelEspecialDAO;
    private final InmobiliariaMapper converter = Mappers.getMapper(InmobiliariaMapper.class);

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
                ArancelEspecialExpensa entity = (ArancelEspecialExpensa) converter.toArancelEspecialExpensaEntity((ArancelEspecialExpensaDTO) dto);
                entity.setUnaFechaArancel(Converter.converterStringToLocalDate(dto.getUnaFechaArancel()));
                this.arancelEspecialExpensaDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof ArancelEspecialServicioDTO) {

                ArancelEspecialServicio entity = (ArancelEspecialServicio) converter.toArancelEspecialServicioEntity((ArancelEspecialServicioDTO) dto);
                entity.setUnaFechaArancel(Converter.converterStringToLocalDate(dto.getUnaFechaArancel()));
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
                        ArancelEspecialExpensa entity = (ArancelEspecialExpensa) converter.toArancelEspecialExpensaEntity((ArancelEspecialExpensaDTO) dto);
                        entity.setUnaFechaArancel(Converter.converterStringToLocalDate(dto.getUnaFechaArancel()));
                        this.arancelEspecialExpensaDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ArancelEspecialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof ArancelEspecialServicioDTO) {
                    try {
                        ArancelEspecialServicio entity = (ArancelEspecialServicio) converter.toArancelEspecialServicioEntity((ArancelEspecialServicioDTO) dto);
                        entity.setUnaFechaArancel(Converter.converterStringToLocalDate(dto.getUnaFechaArancel()));
                        this.arancelEspecialServicioDAO.edit(entity);
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
                    Logger.getLogger(ArancelEspecialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
        ArancelEspecialDTO dto = null;
        ArancelEspecial entity = arancelEspecialDAO.findArancelEspecial(id);
        if (entity instanceof ArancelEspecialExpensa) {
            dto = converter.toArancelEspecialExpensaDTO((ArancelEspecialExpensa) entity);
        }
        if (entity instanceof ArancelEspecialServicio) {
            dto = converter.toArancelEspecialServicioDTO((ArancelEspecialServicio) entity);
        }
        return dto;
    }

    @Override
    public List<ArancelEspecialDTO> listarTodos() {
        List<ArancelEspecial> entities = arancelEspecialDAO.findArancelEspecialEntities();
        return converter.toDTOArancelEspecialList(entities);
    }
}
