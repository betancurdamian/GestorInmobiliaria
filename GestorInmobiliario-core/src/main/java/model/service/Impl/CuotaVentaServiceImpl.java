/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.CuotaVentaDTO;
import model.dao.Conexion;
import model.service.ICuotaVentaService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.CuotaVentaJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.CuotaVenta;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class CuotaVentaServiceImpl implements ICuotaVentaService {

    private final CuotaVentaJpaController cuotaVentaDAO;
    private final InmobiliariaMapper converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public CuotaVentaServiceImpl() {
        new Conexion();
        this.cuotaVentaDAO = new CuotaVentaJpaController(Conexion.getEmf());
        this.converter = Mappers.getMapper(InmobiliariaMapper.class);
    }

    @Override
    public CuotaVentaDTO crear(CuotaVentaDTO dto) {
        if (dto != null) {
            CuotaVenta entity = converter.toEntity(dto);
            this.cuotaVentaDAO.create(entity);
            dto.setId(entity.getId());
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public CuotaVentaDTO modificar(CuotaVentaDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    CuotaVenta entity = converter.toEntity(dto);
                    cuotaVentaDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(CuotaVentaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (cuotaVentaDAO.findCuotaVenta(id) != null) {
                try {
                    cuotaVentaDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(CuotaVentaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public CuotaVentaDTO listarID(Long id) {
        CuotaVenta entity = cuotaVentaDAO.findCuotaVenta(id);
        return converter.toDTO(entity);
    }

    @Override
    public List<CuotaVentaDTO> listarTodos() {
        List<CuotaVenta> entities = cuotaVentaDAO.findCuotaVentaEntities();
        return converter.toDTOCuotaVentaList(entities);
    }

}
