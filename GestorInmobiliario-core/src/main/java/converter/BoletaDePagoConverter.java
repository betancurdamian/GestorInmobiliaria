/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import util.Converter;
import dto.BoletaDePagoDTO;
import model.entity.BoletaDePago;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class BoletaDePagoConverter extends AbstractConverter<BoletaDePago, BoletaDePagoDTO> {

    @Override
    public BoletaDePago fromDto(BoletaDePagoDTO dto) {
        BoletaDePago entity = null;
        if (dto != null) {
            entity = new BoletaDePago();
            if (dto.getId() != null) {
                entity.setId(dto.getId());
            }
            if (dto.getNumeroBoleta() != null) {
                entity.setNumeroBoleta(dto.getNumeroBoleta());
            }
            if (dto.getFechaPago() != null) {
                entity.setFechaPago(Converter.converterStringToLocalDate(dto.getFechaPago()));
            }
            if (dto.getNumeroCuota() != null) {
                entity.setNumeroCuota(dto.getNumeroCuota());
            }
            if (dto.getMonto() != null) {
                entity.setMonto(dto.getMonto());
            }
            if (dto.getPagado() != null) {
                entity.setPagado(dto.getPagado());
            }
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
        BoletaDePagoDTO dto = null;
        if (entity != null) {
            dto = new BoletaDePagoDTO();
            if (entity.getId() != null) {
                dto.setId(entity.getId());
            }
            if (entity.getNumeroBoleta() != null) {
                dto.setNumeroBoleta(entity.getNumeroBoleta());
            }
            if (entity.getFechaPago() != null) {
                dto.setFechaPago(Converter.converterLocalDateToString(entity.getFechaPago()));
            }
            if (entity.getNumeroCuota() != null) {
                dto.setNumeroCuota(entity.getNumeroCuota());
            }
            if (entity.getMonto() != null) {
                dto.setMonto(entity.getMonto());
            }
            if (entity.getPagado() != null) {
                dto.setPagado(entity.getPagado());
            }
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
