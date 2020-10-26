/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.service.Impl;

import com.betancur.gestorinmobiliario.converter.AlquilerConverter;
import com.betancur.gestorinmobiliario.converter.ContratoConverter;
import com.betancur.gestorinmobiliario.converter.InmobiliariaConverter;
import com.betancur.gestorinmobiliario.converter.InmuebleConverter;
import com.betancur.gestorinmobiliario.dto.AlquilerDTO;
import com.betancur.gestorinmobiliario.model.dao.AlquilerJpaController;
import com.betancur.gestorinmobiliario.model.dao.Conexion;
import com.betancur.gestorinmobiliario.model.dao.ContratoAlquilerJpaController;
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
    private final ContratoAlquilerJpaController contratoAlquilerDAO;
    
    private final AlquilerConverter converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public AlquilerServiceImpl() {
        new Conexion();
        this.alquilerDAO = new AlquilerJpaController(Conexion.getEmf());
        this.inmobiliariaDAO = new InmobiliariaJpaController(Conexion.getEmf());
        this.inmuebleDAO = new InmuebleJpaController(Conexion.getEmf());
        this.contratoAlquilerDAO = new ContratoAlquilerJpaController(Conexion.getEmf());
        
        this.converter = new AlquilerConverter();
    }

    @Override
    public AlquilerDTO crear(AlquilerDTO dto) {
        Alquiler entity = this.converter.fromDto(dto);

        if (dto.getUnInmuebleDTO()!= null) {
            InmuebleConverter converterInmueble = new InmuebleConverter();            
            entity.setUnInmuebleAlquiler(inmuebleDAO.findInmueble(converterInmueble.fromDto(dto.getUnInmuebleDTO()).getId()));
        }
        if (dto.getUnaInmobiliariaAlquilerDTO() != null) {
            InmobiliariaConverter converterInmobiliaria = new InmobiliariaConverter();            
            entity.setUnaInmobiliariaAlquiler(inmobiliariaDAO.findInmobiliaria(converterInmobiliaria.fromDto(dto.getUnaInmobiliariaAlquilerDTO()).getId()));
        }
        
        if (dto.getUnContratoAlquilerDTO()!= null) {
            ContratoConverter converterContratoAlquiler = new ContratoConverter();            
            entity.setUnContratoAlquiler(contratoAlquilerDAO.findContratoAlquiler(converterContratoAlquiler.fromDto(dto.getUnContratoAlquilerDTO()).getId()));
        }
        
        this.alquilerDAO.create(entity);
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public AlquilerDTO modificar(AlquilerDTO dto) {
        Alquiler entity = this.converter.fromDto(dto);

        try {
            alquilerDAO.edit(entity);
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
        Alquiler entity = alquilerDAO.findAlquiler(id);
        return this.converter.fromEntity(entity);
    }

    @Override
    public List<AlquilerDTO> listarTodos() {
        List<Alquiler> entities = alquilerDAO.findAlquilerEntities();
        return this.converter.fromEntity(entities);
    }

}
