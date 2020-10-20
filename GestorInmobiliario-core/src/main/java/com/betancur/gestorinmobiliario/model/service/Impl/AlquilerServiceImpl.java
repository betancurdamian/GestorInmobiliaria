/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.service.Impl;

import com.betancur.gestorinmobiliario.converter.AlquilerConverter;
import com.betancur.gestorinmobiliario.dto.AlquilerDTO;
import com.betancur.gestorinmobiliario.model.dao.AlquilerJpaController;
import com.betancur.gestorinmobiliario.model.dao.Conexion;
import com.betancur.gestorinmobiliario.model.dao.InmobiliariaJpaController;
import com.betancur.gestorinmobiliario.model.dao.InmuebleJpaController;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import com.betancur.gestorinmobiliario.model.entity.Alquiler;
import com.betancur.gestorinmobiliario.model.service.IAlquilerService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ariel
 */
public class AlquilerServiceImpl implements IAlquilerService {

    private final AlquilerJpaController alquilerDAO;
    private final InmobiliariaJpaController inmobiliariaDAO;
    private final InmuebleJpaController inmuebleDAO;
    
    private final AlquilerConverter converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public AlquilerServiceImpl() {
        new Conexion();
        this.alquilerDAO = new AlquilerJpaController(Conexion.getEmf());
        this.inmobiliariaDAO = new InmobiliariaJpaController(Conexion.getEmf());
        this.inmuebleDAO = new InmuebleJpaController(Conexion.getEmf());
        
        this.converter = new AlquilerConverter();
    }

    @Override
    public AlquilerDTO crear(AlquilerDTO dto) {
        Alquiler alquilerEntity = this.converter.fromDto(dto);

        if (dto.getUnInmuebleID() != null) {
            alquilerEntity.setUnInmueble(inmuebleDAO.findInmueble(dto.getUnInmuebleID()));
        }
        if (dto.getUnaInmobiliariaAlquilerID() != null) {
            alquilerEntity.setUnaInmobiliariaAlquiler(inmobiliariaDAO.findInmobiliaria(dto.getUnaInmobiliariaAlquilerID()));
        }
        this.alquilerDAO.create(alquilerEntity);
        dto.setId(alquilerEntity.getId());
        return dto;
    }

    @Override
    public AlquilerDTO modificar(AlquilerDTO dto) {
        Alquiler alquilerEntity = this.converter.fromDto(dto);

        try {
            alquilerDAO.edit(alquilerEntity);
        } catch (Exception ex) {
            Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dto;
    }

    @Override
    public void eliminar(Long id) {
        try {
            alquilerDAO.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public AlquilerDTO listarID(Long id) {
        Alquiler alquiler = alquilerDAO.findAlquiler(id);
        return this.converter.fromEntity(alquiler);
    }

    @Override
    public List<AlquilerDTO> listarTodos() {
        List<Alquiler> alquileres = alquilerDAO.findAlquilerEntities();
        return this.converter.fromEntity(alquileres);
    }

}
