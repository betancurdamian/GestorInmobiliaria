/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import util.Converter;
import dto.ArancelEspecialDTO;
import dto.ArancelEspecialExpensaDTO;
import dto.ArancelEspecialServicioDTO;
import model.entity.ArancelEspecial;
import model.entity.ArancelEspecialExpensa;
import model.entity.ArancelEspecialServicio;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ArancelEspecialConverter extends AbstractConverter<ArancelEspecial, ArancelEspecialDTO> {

    @Override
    public ArancelEspecial fromDto(ArancelEspecialDTO dto) {
        ArancelEspecial entity = null;
        if (dto != null) {
            if (dto instanceof ArancelEspecialExpensaDTO) {
                entity = new ArancelEspecialExpensa();
                if (dto.getId() != null) {
                    entity.setId(dto.getId());
                }
                if (dto.getUnaFechaDeRecargo() != null) {
                    entity.setUnaFechaDeRecargo(Converter.converterStringToLocalDate(dto.getUnaFechaDeRecargo()));
                }
                if (dto.getDescripcion() != null) {
                    entity.setDescripcion(dto.getDescripcion());
                }
                if (dto.getMonto() != null) {
                    entity.setMonto(dto.getMonto());
                }
                if (dto.getUnaInmobiliariaArancelEspecialDTO() != null) {
                    InmobiliariaConverter converter = new InmobiliariaConverter();
                    entity.setUnaInmobiliariaArancelEspecial(converter.fromDto(dto.getUnaInmobiliariaArancelEspecialDTO()));
                }
            }

            if (dto instanceof ArancelEspecialServicioDTO) {
                entity = new ArancelEspecialServicio();
                if (dto.getId() != null) {
                    entity.setId(dto.getId());
                }
                if (dto.getUnaFechaDeRecargo() != null) {
                    entity.setUnaFechaDeRecargo(Converter.converterStringToLocalDate(dto.getUnaFechaDeRecargo()));
                }
                if (dto.getDescripcion() != null) {
                    entity.setDescripcion(dto.getDescripcion());
                }
                if (dto.getMonto() != null) {
                    entity.setMonto(dto.getMonto());
                }
                if (dto.getUnaInmobiliariaArancelEspecialDTO() != null) {
                    InmobiliariaConverter converter = new InmobiliariaConverter();
                    entity.setUnaInmobiliariaArancelEspecial(converter.fromDto(dto.getUnaInmobiliariaArancelEspecialDTO()));
                }
            }
            return entity;
        } else {
            return entity;
        }
    }

    @Override
    @SuppressWarnings("null")
    public ArancelEspecialDTO fromEntity(ArancelEspecial entity) {
        ArancelEspecialDTO dto = null;
        if (entity != null) {
            if (entity instanceof ArancelEspecialExpensa) {
                dto = new ArancelEspecialExpensaDTO();
                if (entity.getId() != null) {
                    dto.setId(entity.getId());
                }
                if (entity.getUnaFechaDeRecargo() != null) {
                    dto.setUnaFechaDeRecargo(Converter.converterLocalDateToString(entity.getUnaFechaDeRecargo()));
                }
                if (entity.getDescripcion() != null) {
                    dto.setDescripcion(entity.getDescripcion());
                }
                if (entity.getMonto() != null) {
                    dto.setMonto(entity.getMonto());
                }
                if (entity.getUnaInmobiliariaArancelEspecial() != null) {
                    InmobiliariaConverter converter = new InmobiliariaConverter();
                    dto.setUnaInmobiliariaArancelEspecialDTO(converter.fromEntity(entity.getUnaInmobiliariaArancelEspecial()));
                }
            }

            if (entity instanceof ArancelEspecialServicio) {
                entity = new ArancelEspecialServicio();
                if (entity.getId() != null) {
                    dto.setId(entity.getId());
                }

                if (entity.getUnaFechaDeRecargo() != null) {
                    dto.setUnaFechaDeRecargo(Converter.converterLocalDateToString(entity.getUnaFechaDeRecargo()));
                }
                if (entity.getDescripcion() != null) {
                    dto.setDescripcion(entity.getDescripcion());
                }
                if (entity.getMonto() != null) {
                    dto.setMonto(entity.getMonto());
                }
                if (entity.getUnaInmobiliariaArancelEspecial() != null) {
                    InmobiliariaConverter converter = new InmobiliariaConverter();
                    dto.setUnaInmobiliariaArancelEspecialDTO(converter.fromEntity(entity.getUnaInmobiliariaArancelEspecial()));
                }
            }
            return dto;
        } else {
            return dto;
        }
    }

    @Override
    public List<ArancelEspecialDTO> fromEntity(List<ArancelEspecial> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ArancelEspecial> fromDto(List<ArancelEspecialDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
