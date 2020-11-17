/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.LocalidadDTO;
import model.dao.Conexion;
import model.dao.LocalidadJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Localidad;
import model.service.ILocalidadService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mapstruct.factory.Mappers;

public class LocalidadServiceImpl implements ILocalidadService{

    private final LocalidadJpaController localidadDAO;
    private final InmobiliariaMapper converter = Mappers.getMapper(InmobiliariaMapper.class);
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public LocalidadServiceImpl() {
        new Conexion();
        this.localidadDAO = new LocalidadJpaController(Conexion.getEmf());        
    }
    
    @Override
    public LocalidadDTO crear(LocalidadDTO dto) {
        if (dto != null) {
            Localidad entity = converter.toEntity(dto);
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
                    Localidad entity = converter.toEntity(dto);

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
        Localidad entity = localidadDAO.findLocalidad(id);
        return converter.toDTO(entity);
    }

    @Override
    public List<LocalidadDTO> listarTodos() {
        List<Localidad> entities = localidadDAO.findLocalidadEntities();        
        return converter.toDTOLocalidadList(entities);        
    }
    
}
