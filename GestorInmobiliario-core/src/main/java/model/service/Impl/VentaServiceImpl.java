/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.ClienteDTO;
import dto.ContratoVentaDTO;
import dto.VentaDTO;
import java.util.ArrayList;
import model.dao.Conexion;
import model.service.IVentaService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.VentaJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Venta;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class VentaServiceImpl implements IVentaService {

    private final VentaJpaController ventaDAO;
    private final InmobiliariaMapper converter;
    private final ContratoServiceImpl contratoService;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public VentaServiceImpl() {
        new Conexion();
        this.ventaDAO = new VentaJpaController(Conexion.getEmf());
        this.converter = Mappers.getMapper(InmobiliariaMapper.class);
        this.contratoService = new ContratoServiceImpl();
    }

    @Override
    public VentaDTO crear(VentaDTO dto) {
        if (dto != null) {
            Venta entity = converter.toVentaEntity(dto);
            entity.setUnContratoVenta(null);
            this.ventaDAO.create(entity);
            dto.setId(entity.getId());

            ContratoVentaDTO contrato = dto.getUnContratoVenta();
            contrato.setUnaVenta(dto);
            this.contratoService.crear(dto.getUnContratoVenta());
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public VentaDTO modificar(VentaDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                try {
                    Venta entity = converter.toVentaEntity(dto);
                    ventaDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(VentaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (ventaDAO.findVenta(id) != null) {
                try {
                    ventaDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(VentaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public VentaDTO listarID(Long id) {
        Venta entity = ventaDAO.findVenta(id);
        return converter.toVentaDTO(entity);
    }

    @Override
    public List<VentaDTO> listarTodos() {
        List<Venta> entities = ventaDAO.findVentaEntities();
        return converter.toDTOVentaList(entities);
    }

    @Override
    public List<VentaDTO> listarVentasDeCliente(ClienteDTO unLocatario) {
        List<VentaDTO> ventasDeClientes = new ArrayList<>();
        if (unLocatario != null && unLocatario.getId() > 0) {
            for (Venta v : ventaDAO.findVentaEntities()) {
                if (v.getUnContratoVenta().getUnLocatario().getId() == unLocatario.getId()) {
                    ventasDeClientes.add(converter.toVentaDTO(v));
                }
            }
        }
        return ventasDeClientes;
    }

}
