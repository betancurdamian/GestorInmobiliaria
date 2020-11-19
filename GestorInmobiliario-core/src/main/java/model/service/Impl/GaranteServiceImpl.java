/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.GaranteDTO;
import dto.GaranteDependienteDTO;
import dto.GaranteIndependienteDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.Conexion;
import model.dao.GaranteDependienteJpaController;
import model.dao.GaranteIndependienteJpaController;
import model.dao.GaranteJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Garante;
import model.entity.GaranteDependiente;
import model.entity.GaranteIndependiente;
import model.service.IGaranteService;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class GaranteServiceImpl implements IGaranteService {

    private final GaranteJpaController garanteDAO;
    private final GaranteDependienteJpaController garanteDependienteDAO;
    private final GaranteIndependienteJpaController garanteIndependienteDAO;
    private final InmobiliariaMapper converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public GaranteServiceImpl() {
        new Conexion();
        this.garanteDAO = new GaranteJpaController(Conexion.getEmf());
        this.garanteDependienteDAO = new GaranteDependienteJpaController(Conexion.getEmf());
        this.garanteIndependienteDAO = new GaranteIndependienteJpaController(Conexion.getEmf());
        this.converter = Mappers.getMapper(InmobiliariaMapper.class);
    }

    @Override
    public GaranteDTO crear(GaranteDTO dto) {
        if (dto != null) {
            if (dto instanceof GaranteDependienteDTO) {
                GaranteDependiente entity = converter.toGaranteDependienteEntity((GaranteDependienteDTO) dto);
                this.garanteDependienteDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof GaranteIndependienteDTO) {
                GaranteIndependiente entity = converter.toGaranteIndependienteEntity((GaranteIndependienteDTO) dto);
                this.garanteIndependienteDAO.create(entity);
                dto.setId(entity.getId());
            }
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public GaranteDTO modificar(GaranteDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                if (dto instanceof GaranteDependienteDTO) {
                    try {
                        GaranteDependiente entity = converter.toGaranteDependienteEntity((GaranteDependienteDTO) dto);
                        this.garanteDependienteDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(GaranteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof GaranteIndependienteDTO) {
                    try {
                        GaranteIndependiente entity = converter.toGaranteIndependienteEntity((GaranteIndependienteDTO) dto);
                        this.garanteIndependienteDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(GaranteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (garanteDAO.findGarante(id) != null) {
                try {
                    garanteDAO.destroy(id);
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
    public GaranteDTO listarID(Long id) {
        GaranteDTO dto = null;
        Garante entity = garanteDAO.findGarante(id);
        if (entity instanceof GaranteDependiente) {
            dto = converter.toGaranteDependienteDTO((GaranteDependiente) entity);
        }
        if (entity instanceof GaranteIndependiente) {
            dto = converter.toGaranteIndependienteDTO((GaranteIndependiente) entity);
        }
        return dto;
    }

    @Override
    public List<GaranteDTO> listarTodos() {
        List<Garante> entities = garanteDAO.findGaranteEntities();
        return converter.toDTOGaranteList(entities);
    }

    @Override
    public GaranteDependienteDTO listarGaranteDependienteID(Long id) {
        GaranteDependiente entity = garanteDependienteDAO.findGaranteDependiente(id);
        return converter.toGaranteDependienteDTO(entity);
    }

    @Override
    public List<GaranteDependienteDTO> listarTodosGarantesDependientes() {
        List<GaranteDependiente> entities = garanteDependienteDAO.findGaranteDependienteEntities();
        return converter.toDTOGaranteDependienteList(entities);
    }

    @Override
    public GaranteIndependienteDTO listarGaranteIndependienteID(Long id) {
        GaranteIndependiente entity = garanteIndependienteDAO.findGaranteIndependiente(id);
        return converter.toGaranteIndependienteDTO(entity);
    }

    @Override
    public List<GaranteIndependienteDTO> listarTodosGarantesIndependientes() {
        List<GaranteIndependiente> entities = garanteIndependienteDAO.findGaranteIndependienteEntities();
        return converter.toDTOGaranteIndependienteList(entities);
    }

}
