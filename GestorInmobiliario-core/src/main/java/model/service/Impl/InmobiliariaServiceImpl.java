/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.InmobiliariaDTO;
import model.dao.Conexion;
import model.service.IInmobiliariaService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.InmobiliariaJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Inmobiliaria;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class InmobiliariaServiceImpl implements IInmobiliariaService{

    private final InmobiliariaJpaController inmobiliariaDAO;
    private final InmobiliariaMapper converter;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public InmobiliariaServiceImpl() {
        new Conexion();
        this.inmobiliariaDAO = new InmobiliariaJpaController(Conexion.getEmf());
        this.converter = Mappers.getMapper(InmobiliariaMapper.class);
    }

    @Override
    public InmobiliariaDTO crear(InmobiliariaDTO dto) {
        if (dto != null) {
            Inmobiliaria entity = converter.toEntity(dto);
            this.inmobiliariaDAO.create(entity);
            dto.setId(entity.getId());
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public InmobiliariaDTO modificar(InmobiliariaDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    Inmobiliaria entity = converter.toEntity(dto);
                    inmobiliariaDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(InmobiliariaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (inmobiliariaDAO.findInmobiliaria(id) != null) {
                try {
                    inmobiliariaDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(InmobiliariaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public InmobiliariaDTO listarID(Long id) {
        Inmobiliaria entity = inmobiliariaDAO.findInmobiliaria(id);
        return converter.toDTO(entity);
    }

    @Override
    public List<InmobiliariaDTO> listarTodos() {
        List<Inmobiliaria> entities = inmobiliariaDAO.findInmobiliariaEntities();
        return converter.toDTOList(entities);
    }
    
}
