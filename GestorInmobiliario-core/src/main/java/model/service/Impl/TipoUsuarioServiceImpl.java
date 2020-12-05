/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.TipoUsuarioDTO;
import model.dao.Conexion;
import model.service.ITipoUsuarioService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.TipoUsuarioJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.TipoUsuario;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class TipoUsuarioServiceImpl implements ITipoUsuarioService{

    private final TipoUsuarioJpaController tipoUsuarioDAO;
    private final InmobiliariaMapper converter;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public TipoUsuarioServiceImpl() {
        new Conexion();
        this.tipoUsuarioDAO = new TipoUsuarioJpaController(Conexion.getEmf());
        this.converter = Mappers.getMapper(InmobiliariaMapper.class);
    }

    @Override
    public TipoUsuarioDTO crear(TipoUsuarioDTO dto) {
        if (dto != null) {
            TipoUsuario entity = converter.toTipoUsuarioEntity(dto);
            this.tipoUsuarioDAO.create(entity);
            dto.setId(entity.getId());
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public TipoUsuarioDTO modificar(TipoUsuarioDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    TipoUsuario entity = converter.toTipoUsuarioEntity(dto);
                    tipoUsuarioDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(TipoUsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (tipoUsuarioDAO.findTipoUsuario(id) != null) {
                try {
                    tipoUsuarioDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(TipoUsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public TipoUsuarioDTO listarID(Long id) {
        TipoUsuario entity = tipoUsuarioDAO.findTipoUsuario(id);
        return converter.toTipoUsuarioDTO(entity);
    }

    @Override
    public List<TipoUsuarioDTO> listarTodos() {
        List<TipoUsuario> entities = tipoUsuarioDAO.findTipoUsuarioEntities();        
        return converter.toDTOTipoUsuarioList(entities);
    }
    
}
