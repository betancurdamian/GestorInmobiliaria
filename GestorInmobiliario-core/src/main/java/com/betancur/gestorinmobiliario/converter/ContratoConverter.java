/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.ContratoAlquilerDTO;
import com.betancur.gestorinmobiliario.dto.ContratoDTO;
import com.betancur.gestorinmobiliario.dto.ContratoVentaDTO;
import com.betancur.gestorinmobiliario.model.entity.Contrato;
import com.betancur.gestorinmobiliario.model.entity.ContratoAlquiler;
import com.betancur.gestorinmobiliario.model.entity.ContratoVenta;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ContratoConverter extends AbstractConverter<Contrato, ContratoDTO> {

    @Override
    public Contrato fromDto(ContratoDTO dto) {
        Contrato entity = null;
        if (dto != null) {
            if (dto instanceof ContratoAlquilerDTO) {
                entity = new ContratoAlquiler();

                if (dto.getId() != null) {
                    entity.setId(dto.getId());
                }
                if (dto.getUnaFechaPrimerVencimiento() != null) {
                    entity.setUnaFechaPrimerVencimiento(Converter.converterStringToLocalDate(dto.getUnaFechaPrimerVencimiento()));
                }
                if (dto.getUnaFechaSegundoVencimiento() != null) {
                    entity.setUnaFechaSegundoVencimiento(Converter.converterStringToLocalDate(dto.getUnaFechaSegundoVencimiento()));
                }
                if (dto.getMontoTotal() != null) {
                    entity.setMontoTotal(dto.getMontoTotal());
                }
                if (dto.getCantidadDeCuotas() != null) {
                    entity.setCantidadDeCuotas(dto.getCantidadDeCuotas());
                }
                if (dto.getUnRecargoPorMoraDTO() != null) {
                    RecargoPorMoraConverter converter = new RecargoPorMoraConverter();
                    entity.setUnRecargoPorMora(converter.fromDto(dto.getUnRecargoPorMoraDTO()));
                }
                if (dto.getUnLocadorDTO() != null) {
                    LocadorConverter converter = new LocadorConverter();
                    entity.setUnLocador(converter.fromDto(dto.getUnLocadorDTO()));
                }
                if (dto.getUnLocatarioDTO() != null) {
                    LocatarioConverter converter = new LocatarioConverter();
                    entity.setUnLocatario(converter.fromDto(dto.getUnLocatarioDTO()));
                }
                if (dto.getUnaComisionDTO() != null) {
                    ComisionConverter converter = new ComisionConverter();
                    entity.setUnaComision(converter.fromDto(dto.getUnaComisionDTO()));
                }
                if (!dto.getArancelesEspecialesDTO().isEmpty()) {
                    ArancelEspecialConverter converter = new ArancelEspecialConverter();
                    entity.setArancelesEspeciales(converter.fromDto(dto.getArancelesEspecialesDTO()));
                }
                if (!dto.getBoletasDePagoDTO().isEmpty()) {
                    BoletaDePagoConverter converter = new BoletaDePagoConverter();
                    entity.setBoletasDePago(converter.fromDto(dto.getBoletasDePagoDTO()));
                }
                if ((((ContratoAlquilerDTO) dto).getUnGaranteDTO()) != null) {
                    GaranteConverter converter = new GaranteConverter();
                    ((ContratoAlquiler) entity).setUnGarante(converter.fromDto((((ContratoAlquilerDTO) dto).getUnGaranteDTO())));
                }

            }

            if (dto instanceof ContratoVentaDTO) {
                entity = new ContratoVenta();

                if (dto.getId() != null) {
                    entity.setId(dto.getId());
                }
                if (dto.getUnaFechaPrimerVencimiento() != null) {
                    entity.setUnaFechaPrimerVencimiento(Converter.converterStringToLocalDate(dto.getUnaFechaPrimerVencimiento()));
                }
                if (dto.getUnaFechaSegundoVencimiento() != null) {
                    entity.setUnaFechaSegundoVencimiento(Converter.converterStringToLocalDate(dto.getUnaFechaSegundoVencimiento()));
                }
                if (dto.getMontoTotal() != null) {
                    entity.setMontoTotal(dto.getMontoTotal());
                }
                if (dto.getCantidadDeCuotas() != null) {
                    entity.setCantidadDeCuotas(dto.getCantidadDeCuotas());
                }
                if (dto.getUnRecargoPorMoraDTO() != null) {
                    RecargoPorMoraConverter converter = new RecargoPorMoraConverter();
                    entity.setUnRecargoPorMora(converter.fromDto(dto.getUnRecargoPorMoraDTO()));
                }
                if (dto.getUnLocadorDTO() != null) {
                    LocadorConverter converter = new LocadorConverter();
                    entity.setUnLocador(converter.fromDto(dto.getUnLocadorDTO()));
                }
                if (dto.getUnLocatarioDTO() != null) {
                    LocatarioConverter converter = new LocatarioConverter();
                    entity.setUnLocatario(converter.fromDto(dto.getUnLocatarioDTO()));
                }
                if (dto.getUnaComisionDTO() != null) {
                    ComisionConverter converter = new ComisionConverter();
                    entity.setUnaComision(converter.fromDto(dto.getUnaComisionDTO()));
                }
                if (!dto.getArancelesEspecialesDTO().isEmpty()) {
                    ArancelEspecialConverter converter = new ArancelEspecialConverter();
                    entity.setArancelesEspeciales(converter.fromDto(dto.getArancelesEspecialesDTO()));
                }
                if (!dto.getBoletasDePagoDTO().isEmpty()) {
                    BoletaDePagoConverter converter = new BoletaDePagoConverter();
                    entity.setBoletasDePago(converter.fromDto(dto.getBoletasDePagoDTO()));
                }
                if ((((ContratoVentaDTO) dto).getCuotasVentaDTO()) != null) {
                    CuotaVentaConverter converter = new CuotaVentaConverter();
                    ((ContratoVenta) entity).setCuotasVenta(converter.fromDto((((ContratoVentaDTO) dto).getCuotasVentaDTO())));
                }
            }
            return entity;
        } else {
            return entity;
        }
    }

    @Override
    public ContratoDTO fromEntity(Contrato entity) {
        ContratoDTO dto = null;
        if (entity != null) {
            if (entity instanceof ContratoAlquiler) {
                dto = new ContratoAlquilerDTO();

                if (entity.getId() != null) {
                    dto.setId(entity.getId());
                }
                if (entity.getUnaFechaPrimerVencimiento() != null) {
                    dto.setUnaFechaPrimerVencimiento(Converter.converterLocalDateToString(entity.getUnaFechaPrimerVencimiento()));
                }
                if (entity.getUnaFechaSegundoVencimiento() != null) {
                    dto.setUnaFechaSegundoVencimiento(Converter.converterLocalDateToString(entity.getUnaFechaSegundoVencimiento()));
                }
                if (entity.getMontoTotal() != null) {
                    dto.setMontoTotal(entity.getMontoTotal());
                }
                if (entity.getCantidadDeCuotas() != null) {
                    dto.setCantidadDeCuotas(entity.getCantidadDeCuotas());
                }
                if (entity.getUnRecargoPorMora() != null) {
                    RecargoPorMoraConverter converter = new RecargoPorMoraConverter();
                    dto.setUnRecargoPorMoraDTO(converter.fromEntity(entity.getUnRecargoPorMora()));
                }
                if (entity.getUnLocador() != null) {
                    LocadorConverter converter = new LocadorConverter();
                    dto.setUnLocadorDTO(converter.fromEntity(entity.getUnLocador()));
                }
                if (entity.getUnLocatario() != null) {
                    LocatarioConverter converter = new LocatarioConverter();
                    dto.setUnLocatarioDTO(converter.fromEntity(entity.getUnLocatario()));
                }
                if (entity.getUnaComision() != null) {
                    ComisionConverter converter = new ComisionConverter();
                    dto.setUnaComisionDTO(converter.fromEntity(entity.getUnaComision()));
                }
                if (!entity.getArancelesEspeciales().isEmpty()) {
                    ArancelEspecialConverter converter = new ArancelEspecialConverter();
                    dto.setArancelesEspecialesDTO(converter.fromEntity(entity.getArancelesEspeciales()));
                }
                if (!entity.getBoletasDePago().isEmpty()) {
                    BoletaDePagoConverter converter = new BoletaDePagoConverter();
                    dto.setBoletasDePagoDTO(converter.fromEntity(entity.getBoletasDePago()));
                }
                if ((((ContratoAlquiler) entity).getUnGarante()) != null) {
                    GaranteConverter converter = new GaranteConverter();
                    ((ContratoAlquilerDTO) dto).setUnGaranteDTO(converter.fromEntity(((ContratoAlquiler) entity).getUnGarante()));
                }

            }

            if (dto instanceof ContratoVentaDTO) {

                dto = new ContratoVentaDTO();

                if (entity.getId() != null) {
                    dto.setId(entity.getId());
                }
                if (entity.getUnaFechaPrimerVencimiento() != null) {
                    dto.setUnaFechaPrimerVencimiento(Converter.converterLocalDateToString(entity.getUnaFechaPrimerVencimiento()));
                }
                if (entity.getUnaFechaSegundoVencimiento() != null) {
                    dto.setUnaFechaSegundoVencimiento(Converter.converterLocalDateToString(entity.getUnaFechaSegundoVencimiento()));
                }
                if (entity.getMontoTotal() != null) {
                    dto.setMontoTotal(entity.getMontoTotal());
                }
                if (entity.getCantidadDeCuotas() != null) {
                    dto.setCantidadDeCuotas(entity.getCantidadDeCuotas());
                }
                if (entity.getUnRecargoPorMora() != null) {
                    RecargoPorMoraConverter converter = new RecargoPorMoraConverter();
                    dto.setUnRecargoPorMoraDTO(converter.fromEntity(entity.getUnRecargoPorMora()));
                }
                if (entity.getUnLocador() != null) {
                    LocadorConverter converter = new LocadorConverter();
                    dto.setUnLocadorDTO(converter.fromEntity(entity.getUnLocador()));
                }
                if (entity.getUnLocatario() != null) {
                    LocatarioConverter converter = new LocatarioConverter();
                    dto.setUnLocatarioDTO(converter.fromEntity(entity.getUnLocatario()));
                }
                if (entity.getUnaComision() != null) {
                    ComisionConverter converter = new ComisionConverter();
                    dto.setUnaComisionDTO(converter.fromEntity(entity.getUnaComision()));
                }
                if (!entity.getArancelesEspeciales().isEmpty()) {
                    ArancelEspecialConverter converter = new ArancelEspecialConverter();
                    dto.setArancelesEspecialesDTO(converter.fromEntity(entity.getArancelesEspeciales()));
                }
                if (!entity.getBoletasDePago().isEmpty()) {
                    BoletaDePagoConverter converter = new BoletaDePagoConverter();
                    dto.setBoletasDePagoDTO(converter.fromEntity(entity.getBoletasDePago()));
                }
                if ((((ContratoAlquiler) entity).getUnGarante()) != null) {
                    GaranteConverter converter = new GaranteConverter();
                    ((ContratoAlquilerDTO) dto).setUnGaranteDTO(converter.fromEntity(((ContratoAlquiler) entity).getUnGarante()));
                }
            }
            return dto;
        } else {
            return dto;
        }
    }

    @Override
    public List<ContratoDTO> fromEntity(List<Contrato> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Contrato> fromDto(List<ContratoDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
