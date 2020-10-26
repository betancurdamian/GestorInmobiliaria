/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.service.Impl;

import com.betancur.gestorinmobiliario.converter.ArancelEspecialConverter;
import com.betancur.gestorinmobiliario.dto.ArancelEspecialDTO;
import com.betancur.gestorinmobiliario.dto.ArancelEspecialExpensaDTO;
import com.betancur.gestorinmobiliario.dto.ArancelEspecialServicioDTO;
import com.betancur.gestorinmobiliario.model.dao.ArancelEspecialExpensaJpaController;
import com.betancur.gestorinmobiliario.model.dao.ArancelEspecialJpaController;
import com.betancur.gestorinmobiliario.model.dao.ArancelEspecialServicioJpaController;
import com.betancur.gestorinmobiliario.model.dao.Conexion;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import com.betancur.gestorinmobiliario.model.entity.ArancelEspecialExpensa;
import com.betancur.gestorinmobiliario.model.entity.ArancelEspecialServicio;
import com.betancur.gestorinmobiliario.model.service.IArancelEspecialService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        return dto;
    }

    @Override
    public ArancelEspecialDTO modificar(ArancelEspecialDTO dto) {
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
        return dto;
    }

    @Override
    public void eliminar(Long id) {
        try {
            arancelEspecialDAO.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(InmuebleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArancelEspecialDTO listarID(Long id) {
        return this.converter.fromEntity(arancelEspecialDAO.findArancelEspecial(id));
    }

    @Override
    public List<ArancelEspecialDTO> listarTodos() {
        return converter.fromEntity(arancelEspecialDAO.findArancelEspecialEntities());
    }

}
