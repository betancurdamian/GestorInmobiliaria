/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.AlquilerDTO;
import com.betancur.gestorinmobiliario.model.entity.Alquiler;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class AlquilerConverter extends AbstractConverter<Alquiler, AlquilerDTO> {

    @Override
    public Alquiler fromDto(AlquilerDTO dto) {
        if (dto != null) {
            Alquiler entity = new Alquiler();
            entity.setId(dto.getId());

            entity.setId(dto.getId());
            entity.setUnaFechaInicio(Converter.converterStringToLocalDate(dto.getUnaFechaInicio()));
            entity.setUnaFechaFin(Converter.converterStringToLocalDate(dto.getUnaFechaFin()));

            
            //Para asignar el ID es necesrio obtenerlo del EntityManager, se realiza en el service
            //entity.setUnContratoAlquiler(dto.getUnContratoAlquiler().getId());
            
            //Para asignar el ID es necesrio obtenerlo del EntityManager, se realiza en el service
            //entity.setUnInmueble(dto.getUnInmueble().getId());
            
            //Para asignar el ID es necesrio obtenerlo del EntityManager, se realiza en el service
            //entity.setUnaInmobiliariaAlquiler(dto.getUnaInmobiliariaAlquiler().getId());
            dto.setDisponible(dto.isDisponible());

            return entity;
        } else {
            return null;
        }
    }

    @Override
    public AlquilerDTO fromEntity(Alquiler entity) {
        if (entity != null) {
            AlquilerDTO dto = new AlquilerDTO();
            dto.setId(entity.getId());
            dto.setUnaFechaInicio(Converter.converterLocalDateToString(entity.getUnaFechaInicio()));
            dto.setUnaFechaFin(Converter.converterLocalDateToString(entity.getUnaFechaFin()));

            if (entity.getUnContratoAlquiler() != null) {
                dto.setUnContratoAlquilerID(entity.getUnContratoAlquiler().getId());
            }

            if (entity.getUnInmueble() != null) {
                dto.setUnInmuebleID(entity.getUnInmueble().getId());
            }

            if (entity.getUnaInmobiliariaAlquiler() != null) {
                dto.setUnaInmobiliariaAlquilerID(entity.getUnaInmobiliariaAlquiler().getId());
            }

            dto.setDisponible(entity.isDisponible());

            return dto;
        } else {
            return null;
        }
    }

    @Override
    public List<AlquilerDTO> fromEntity(List<Alquiler> entities) {
        return super.fromEntity(entities); 
    }

    @Override
    public List<Alquiler> fromDto(List<AlquilerDTO> dtos) {
        return super.fromDto(dtos); 
    }
    
    

}
