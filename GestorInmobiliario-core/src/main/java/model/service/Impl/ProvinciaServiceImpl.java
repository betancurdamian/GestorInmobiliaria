/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.ProvinciaDTO;
import model.dao.Conexion;
import model.dao.ProvinciaJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Provincia;
import model.service.IProvinciaService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class ProvinciaServiceImpl implements IProvinciaService {

    private final ProvinciaJpaController provinciaDAO;
    private final InmobiliariaMapper converter = Mappers.getMapper(InmobiliariaMapper.class);

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ProvinciaServiceImpl() {
        new Conexion();
        this.provinciaDAO = new ProvinciaJpaController(Conexion.getEmf());        
    }

    @Override
    public ProvinciaDTO crear(ProvinciaDTO dto) {
        if (dto != null) {
            Provincia entity = converter.toEntity(dto);
            this.provinciaDAO.create(entity);
            dto.setId(entity.getId());
        } else {
            System.out.println("El DTO es null");
        }
        return dto;        
    }

    @Override
    public ProvinciaDTO modificar(ProvinciaDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    Provincia entity = converter.toEntity(dto);
                    provinciaDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(ProvinciaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (provinciaDAO.findProvincia(id) != null) {
                try {
                    provinciaDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(ProvinciaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public ProvinciaDTO listarID(Long id) {
        Provincia entity = provinciaDAO.findProvincia(id);
        return converter.toDTO(entity);
    }

    @Override
    public List<ProvinciaDTO> listarTodos() {
        List<Provincia> entities = provinciaDAO.findProvinciaEntities();
        return converter.toDTOProvinciaList(entities);
    }

}
