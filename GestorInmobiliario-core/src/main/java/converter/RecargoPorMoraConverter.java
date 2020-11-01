/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.RecargoPorMoraDTO;
import model.entity.RecargoPorMora;
import java.util.List;
import util.Converter;

/**
 *
 * @author Ariel
 */
public class RecargoPorMoraConverter extends AbstractConverter<RecargoPorMora, RecargoPorMoraDTO> {

    @Override
    public RecargoPorMora fromDto(RecargoPorMoraDTO dto) {
        RecargoPorMora entity = null;
        if (dto != null) {
            entity = new RecargoPorMora();
            if (dto.getId() != null) {
                entity.setId(dto.getId());
            }
            if (dto.getUnaFechaDeRecargo() != null) {
                entity.setUnaFechaDeRecargo(Converter.converterStringToLocalDate(dto.getUnaFechaDeRecargo()));
            }
            if (dto.getMonto() != null) {
                entity.setMonto(dto.getMonto());
            }
            if (dto.getUnaInmobiliariaRecargoPorMoraDTO() != null) {
                InmobiliariaConverter converter = new InmobiliariaConverter();
                entity.setUnaInmobiliariaRecargoPorMora(converter.fromDto(dto.getUnaInmobiliariaRecargoPorMoraDTO()));
            }
            return entity;
        } else {
            return entity;
        }
    }

    @Override
    public RecargoPorMoraDTO fromEntity(RecargoPorMora entity) {
        RecargoPorMoraDTO dto = null;
        if (entity != null) {

            dto = new RecargoPorMoraDTO();
            if (entity.getId() != null) {
                dto.setId(entity.getId());
            }
            if (entity.getUnaFechaDeRecargo() != null) {
                dto.setUnaFechaDeRecargo(Converter.converterLocalDateToString(entity.getUnaFechaDeRecargo()));
            }
            if (entity.getMonto() != null) {
                dto.setMonto(entity.getMonto());
            }
            if (entity.getUnaInmobiliariaRecargoPorMora() != null) {
                InmobiliariaConverter converter = new InmobiliariaConverter();
                dto.setUnaInmobiliariaRecargoPorMoraDTO(converter.fromEntity(entity.getUnaInmobiliariaRecargoPorMora()));
            }

            return dto;
        } else {
            return dto;
        }
    }

    @Override
    public List<RecargoPorMoraDTO> fromEntity(List<RecargoPorMora> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecargoPorMora> fromDto(List<RecargoPorMoraDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
