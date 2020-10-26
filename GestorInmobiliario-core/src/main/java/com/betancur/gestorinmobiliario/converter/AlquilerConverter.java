/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.AlquilerDTO;
import com.betancur.gestorinmobiliario.dto.ContratoAlquilerDTO;
import com.betancur.gestorinmobiliario.model.entity.Alquiler;
import com.betancur.gestorinmobiliario.model.entity.ContratoAlquiler;
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

            if (dto.getId() != null) {
                entity.setId(dto.getId());
            }

            if (dto.getUnaFechaInicio() != null) {
                entity.setUnaFechaInicio(Converter.converterStringToLocalDate(dto.getUnaFechaInicio()));
            }

            if (dto.getUnaFechaFin() != null) {
                entity.setUnaFechaFin(Converter.converterStringToLocalDate(dto.getUnaFechaFin()));
            }

            if (dto.getUnContratoAlquilerDTO() != null) {
                ContratoConverter converter = new ContratoConverter();
                entity.setUnContratoAlquiler((ContratoAlquiler) converter.fromDto(dto.getUnContratoAlquilerDTO()));
            }

            if (dto.getUnInmuebleDTO() != null) {
                InmuebleConverter converter = new InmuebleConverter();
                entity.setUnInmuebleAlquiler(converter.fromDto(dto.getUnInmuebleDTO()));
            }

            if (dto.getUnaInmobiliariaAlquilerDTO() != null) {
                InmobiliariaConverter converter = new InmobiliariaConverter();
                entity.setUnaInmobiliariaAlquiler(converter.fromDto(dto.getUnaInmobiliariaAlquilerDTO()));
            }

            if (dto.isDisponible() != null) {
                entity.setDisponible(dto.isDisponible());
            }

            return entity;
        } else {
            return null;
        }
    }

    @Override
    public AlquilerDTO fromEntity(Alquiler entity) {
        if (entity != null) {
            AlquilerDTO dto = new AlquilerDTO();

            if (entity.getId() != null) {
                dto.setId(entity.getId());
            }

            if (entity.getUnaFechaInicio() != null) {
                dto.setUnaFechaInicio(Converter.converterLocalDateToString(entity.getUnaFechaInicio()));
            }

            if (entity.getUnaFechaFin() != null) {
                dto.setUnaFechaFin(Converter.converterLocalDateToString(entity.getUnaFechaFin()));
            }

            if (entity.getUnContratoAlquiler() != null) {
                ContratoConverter converter = new ContratoConverter();
                dto.setUnContratoAlquilerDTO((ContratoAlquilerDTO) converter.fromEntity(entity.getUnContratoAlquiler()));
            }

            if (entity.getUnInmuebleAlquiler() != null) {
                InmuebleConverter converter = new InmuebleConverter();
                dto.setUnInmuebleDTO(converter.fromEntity(entity.getUnInmuebleAlquiler()));
            }

            if (entity.getUnaInmobiliariaAlquiler() != null) {
                InmobiliariaConverter converter = new InmobiliariaConverter();
                dto.setUnaInmobiliariaAlquilerDTO(converter.fromEntity(entity.getUnaInmobiliariaAlquiler()));
            }

            if (entity.isDisponible() != null) {
                dto.setDisponible(entity.isDisponible());
            }

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
