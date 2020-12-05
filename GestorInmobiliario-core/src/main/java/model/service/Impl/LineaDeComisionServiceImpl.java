/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.LineaDeComisionDTO;
import model.dao.Conexion;
import model.service.ILineaDeComisionService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.LineaDeComisionJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.LineaDeComision;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class LineaDeComisionServiceImpl implements ILineaDeComisionService{

    private final LineaDeComisionJpaController lineaDeComisionDAO;
    private final InmobiliariaMapper converter;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public LineaDeComisionServiceImpl() {
        new Conexion();
        this.lineaDeComisionDAO = new LineaDeComisionJpaController(Conexion.getEmf());
        this.converter = Mappers.getMapper(InmobiliariaMapper.class);
    }

    @Override
    public LineaDeComisionDTO crear(LineaDeComisionDTO dto) {
        if (dto != null) {
            LineaDeComision entity = converter.toLineaDeComisionEntity(dto);
            this.lineaDeComisionDAO.create(entity);
            dto.setId(entity.getId());
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public LineaDeComisionDTO modificar(LineaDeComisionDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    LineaDeComision entity = converter.toLineaDeComisionEntity(dto);
                    lineaDeComisionDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(LineaDeComisionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (lineaDeComisionDAO.findLineaDeComision(id) != null) {
                try {
                    lineaDeComisionDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(LineaDeComisionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public LineaDeComisionDTO listarID(Long id) {
        LineaDeComision entity = lineaDeComisionDAO.findLineaDeComision(id);
        return converter.toLineaDeComisionDTO(entity);
    }

    @Override
    public List<LineaDeComisionDTO> listarTodos() {
        List<LineaDeComision> entities = lineaDeComisionDAO.findLineaDeComisionEntities();
        return converter.toDTOLineaDeComisionList(entities);
    }
    
}
