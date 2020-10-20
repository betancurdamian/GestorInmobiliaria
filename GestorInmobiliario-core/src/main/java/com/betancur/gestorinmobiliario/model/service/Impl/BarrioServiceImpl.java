/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.service.Impl;

import com.betancur.gestorinmobiliario.converter.BarrioConverter;
import com.betancur.gestorinmobiliario.dto.BarrioDTO;
import com.betancur.gestorinmobiliario.model.dao.BarrioJpaController;
import com.betancur.gestorinmobiliario.model.dao.Conexion;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import com.betancur.gestorinmobiliario.model.entity.Barrio;
import com.betancur.gestorinmobiliario.model.service.IBarrioService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ariel
 */
public class BarrioServiceImpl implements IBarrioService{

    private final BarrioJpaController barrioDAO;
    
    private final BarrioConverter converter;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public BarrioServiceImpl() {
        new Conexion();
        this.barrioDAO = new BarrioJpaController(Conexion.getEmf());
        this.converter = new BarrioConverter();
    }

    
    @Override
    public BarrioDTO crear(BarrioDTO dto) {
        Barrio barrioEntity = this.converter.fromDto(dto);
        this.barrioDAO.create(barrioEntity);
        dto.setId(barrioEntity.getId());
        return dto;
    }

    @Override
    public BarrioDTO modificar(BarrioDTO dto) {
        Barrio barrioEntity = this.converter.fromDto(dto);
        try {
            barrioDAO.edit(barrioEntity);
        } catch (Exception ex) {
            Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dto;
    }

    @Override
    public void eliminar(Long id) {
        try {
            barrioDAO.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public BarrioDTO listarID(Long id) {
        Barrio barrio = barrioDAO.findBarrio(id);
        return this.converter.fromEntity(barrio);
    }

    @Override
    public List<BarrioDTO> listarTodos() {
        List<Barrio> barrios = barrioDAO.findBarrioEntities();
        return this.converter.fromEntity(barrios);
    }
    
}
