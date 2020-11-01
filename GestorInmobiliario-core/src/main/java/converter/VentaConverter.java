/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ContratoVentaDTO;
import dto.VentaDTO;
import model.entity.Venta;
import java.util.List;
import model.entity.ContratoVenta;
import util.Converter;

/**
 *
 * @author Ariel
 */
public class VentaConverter extends AbstractConverter<Venta, VentaDTO> {

    @Override
    public Venta fromDto(VentaDTO dto) {
        Venta entity = null;
        if (dto != null) {
            entity = new Venta();
            if (dto.getId() != null) {
                entity.setId(dto.getId());
            }
            if (dto.getUnaFechaVenta() != null) {
                entity.setUnaFechaVenta(Converter.converterStringToLocalDate(dto.getUnaFechaVenta()));
            }
            if (dto.getCompleta() != null) {
                entity.setCompleta(dto.getCompleta());
            }
            if (dto.getUnContratoVentaDTO() != null) {
                ContratoConverter converter = new ContratoConverter();
                entity.setUnContratoVenta((ContratoVenta) converter.fromDto(dto.getUnContratoVentaDTO()));
            }
            if (dto.getUnInmuebleDTO() != null) {
                InmuebleConverter converter = new InmuebleConverter();
                entity.setUnInmuebleVenta(converter.fromDto(dto.getUnInmuebleDTO()));
            }
            if (dto.getUnaInmobiliariaVentaDTO() != null) {
                InmobiliariaConverter converter = new InmobiliariaConverter();
                entity.setUnaInmobiliariaVenta(converter.fromDto(dto.getUnaInmobiliariaVentaDTO()));
            }
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public VentaDTO fromEntity(Venta entity) {
        VentaDTO dto = null;
        if (entity != null) {
            dto = new VentaDTO();
            if (entity.getId() != null) {
                dto.setId(entity.getId());
            }
            if (entity.getUnaFechaVenta() != null) {
                dto.setUnaFechaVenta(Converter.converterLocalDateToString(entity.getUnaFechaVenta()));
            }
            if (entity.getCompleta() != null) {
                dto.setCompleta(entity.getCompleta());
            }
            if (entity.getUnContratoVenta() != null) {
                ContratoConverter converter = new ContratoConverter();
                dto.setUnContratoVentaDTO((ContratoVentaDTO) converter.fromEntity(entity.getUnContratoVenta()));
            }
            if (entity.getUnInmuebleVenta() != null) {
                InmuebleConverter converter = new InmuebleConverter();
                dto.setUnInmuebleDTO(converter.fromEntity(entity.getUnInmuebleVenta()));
            }
            if (entity.getUnaInmobiliariaVenta() != null) {
                InmobiliariaConverter converter = new InmobiliariaConverter();
                dto.setUnaInmobiliariaVentaDTO(converter.fromEntity(entity.getUnaInmobiliariaVenta()));
            }
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public List<VentaDTO> fromEntity(List<Venta> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venta> fromDto(List<VentaDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
