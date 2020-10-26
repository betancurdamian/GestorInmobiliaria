/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.BoletaDePagoDTO;
import com.betancur.gestorinmobiliario.model.entity.BoletaDePago;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class BoletaDePagoConverter extends AbstractConverter<BoletaDePago, BoletaDePagoDTO> {

    @Override
    public BoletaDePago fromDto(BoletaDePagoDTO dto) {
        if (dto != null) {
            BoletaDePago entity = new BoletaDePago();

            if (dto.getId() != null) {
                entity.setId(dto.getId());
            }

            entity.setNumeroBoleta(dto.getNumeroBoleta());

            entity.setFechaPago(Converter.converterStringToLocalDate(dto.getFechaPago()));
            entity.setNumeroCuota(dto.getNumeroCuota());
            entity.setMonto(dto.getMonto());
            entity.setPagado(dto.isPagado());

            if (dto.getUnContratoDTO() != null) {
                ContratoConverter converter = new ContratoConverter();
                entity.setUnContrato(converter.fromDto(dto.getUnContratoDTO()));
            }
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public BoletaDePagoDTO fromEntity(BoletaDePago entity) {
        if (entity != null) {
            BoletaDePagoDTO dto = new BoletaDePagoDTO();
            dto.setId(entity.getId());

            dto.setNumeroBoleta(entity.getNumeroBoleta());
            dto.setFechaPago(Converter.converterLocalDateToString(entity.getFechaPago()));
            dto.setNumeroCuota(entity.getNumeroCuota());
            dto.setMonto(entity.getMonto());
            dto.setPagado(entity.isPagado());

            if (entity.getUnContrato() != null) {
                ContratoConverter converter = new ContratoConverter();
                dto.setUnContratoDTO(converter.fromEntity(entity.getUnContrato()));
            }
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public List<BoletaDePagoDTO> fromEntity(List<BoletaDePago> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BoletaDePago> fromDto(List<BoletaDePagoDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
