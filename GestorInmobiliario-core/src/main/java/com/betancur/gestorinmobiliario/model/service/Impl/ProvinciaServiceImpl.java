/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.service.Impl;

import com.betancur.gestorinmobiliario.converter.ProvinciaConverter;
import com.betancur.gestorinmobiliario.dto.ProvinciaDTO;
import com.betancur.gestorinmobiliario.model.dao.Conexion;
import com.betancur.gestorinmobiliario.model.dao.ProvinciaJpaController;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import com.betancur.gestorinmobiliario.model.entity.Provincia;
import com.betancur.gestorinmobiliario.model.service.IProvinciaService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ariel
 */
public class ProvinciaServiceImpl implements IProvinciaService{

    private final ProvinciaJpaController provinciaDAO;
    
    private final ProvinciaConverter converter;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ProvinciaServiceImpl() {
        new Conexion();
        this.provinciaDAO = new ProvinciaJpaController(Conexion.getEmf());
        this.converter = new ProvinciaConverter();
    }

    
    
    @Override
    public ProvinciaDTO crear(ProvinciaDTO dto) {
        Provincia entity = this.converter.fromDto(dto);
        this.provinciaDAO.create(entity);
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public ProvinciaDTO modificar(ProvinciaDTO dto) {
        Provincia entity = this.converter.fromDto(dto);
        try {
            provinciaDAO.edit(entity);
        } catch (Exception ex) {
            Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dto;
    }

    @Override
    public void eliminar(Long id) {
        try {
            provinciaDAO.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public ProvinciaDTO listarID(Long id) {
        Provincia entity = provinciaDAO.findProvincia(id);
        return this.converter.fromEntity(entity);
    }

    @Override
    public List<ProvinciaDTO> listarTodos() {
        List<Provincia> entities = provinciaDAO.findProvinciaEntities();
        return this.converter.fromEntity(entities);
    }
    
}
