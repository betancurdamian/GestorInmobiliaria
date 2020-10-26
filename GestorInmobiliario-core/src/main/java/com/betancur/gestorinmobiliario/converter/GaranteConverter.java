/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.GaranteDTO;
import com.betancur.gestorinmobiliario.dto.GaranteDependienteDTO;
import com.betancur.gestorinmobiliario.dto.GaranteIndependienteDTO;
import com.betancur.gestorinmobiliario.model.entity.Garante;
import com.betancur.gestorinmobiliario.model.entity.GaranteDependiente;
import com.betancur.gestorinmobiliario.model.entity.GaranteIndependiente;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class GaranteConverter extends AbstractConverter<Garante, GaranteDTO> {

    @Override
    public Garante fromDto(GaranteDTO dto) {
        Garante entity = null;
        if (dto != null) {
            if (dto instanceof GaranteDependienteDTO) {
                entity = new GaranteDependiente();
                if (dto.getId() != null) {
                    entity.setId(dto.getId());
                }
                if (dto.getNombre() != null) {
                    entity.setNombre(dto.getNombre());
                }
                if (dto.getApellido() != null) {
                    entity.setApellido(dto.getApellido());
                }
                if (dto.getUnTipoDNIDTO() != null) {
                    TipoDNIConverter converter = new TipoDNIConverter();
                    entity.setUnTipoDNI(converter.fromDto(dto.getUnTipoDNIDTO()));
                }
                if (dto.getDni() != null) {
                    entity.setDni(dto.getDni());
                }
                if (dto.getUnEstadoCivilDTO() != null) {
                    EstadoCivilConverter converter = new EstadoCivilConverter();
                    entity.setUnEstadoCivil(converter.fromDto(dto.getUnEstadoCivilDTO()));
                }
                if (dto.getDireccionCalle() != null) {
                    entity.setDireccionCalle(dto.getDireccionCalle());
                }
                if (dto.getDireccionNumero() != null) {
                    entity.setDireccionNumero(dto.getDireccionNumero());
                }
                if (dto.getDireccionProvinciaDTO() != null) {
                    ProvinciaConverter converter = new ProvinciaConverter();
                    entity.setDireccionProvincia(converter.fromDto(dto.getDireccionProvinciaDTO()));
                }
                if (dto.getDireccionLocalidadDTO() != null) {
                    LocalidadConverter converter = new LocalidadConverter();
                    entity.setDireccionLocalidad(converter.fromDto(dto.getDireccionLocalidadDTO()));
                }
                if (dto.getDireccionBarrioDTO() != null) {
                    BarrioConverter converter = new BarrioConverter();
                    entity.setDireccionBarrio(converter.fromDto(dto.getDireccionBarrioDTO()));
                }
                if (dto.getTelefono() != null) {
                    entity.setTelefono(dto.getTelefono());
                }
                if (dto.getCorreoElectronico() != null) {
                    entity.setCorreoElectronico(dto.getCorreoElectronico());
                }

            }

            if (dto instanceof GaranteIndependienteDTO) {
                entity = new GaranteIndependiente();
                if (dto.getId() != null) {
                    entity.setId(dto.getId());
                }
                if (dto.getNombre() != null) {
                    entity.setNombre(dto.getNombre());
                }
                if (dto.getApellido() != null) {
                    entity.setApellido(dto.getApellido());
                }
                if (dto.getUnTipoDNIDTO() != null) {
                    TipoDNIConverter converter = new TipoDNIConverter();
                    entity.setUnTipoDNI(converter.fromDto(dto.getUnTipoDNIDTO()));
                }
                if (dto.getDni() != null) {
                    entity.setDni(dto.getDni());
                }
                if (dto.getUnEstadoCivilDTO() != null) {
                    EstadoCivilConverter converter = new EstadoCivilConverter();
                    entity.setUnEstadoCivil(converter.fromDto(dto.getUnEstadoCivilDTO()));
                }
                if (dto.getDireccionCalle() != null) {
                    entity.setDireccionCalle(dto.getDireccionCalle());
                }
                if (dto.getDireccionNumero() != null) {
                    entity.setDireccionNumero(dto.getDireccionNumero());
                }
                if (dto.getDireccionProvinciaDTO() != null) {
                    ProvinciaConverter converter = new ProvinciaConverter();
                    entity.setDireccionProvincia(converter.fromDto(dto.getDireccionProvinciaDTO()));
                }
                if (dto.getDireccionLocalidadDTO() != null) {
                    LocalidadConverter converter = new LocalidadConverter();
                    entity.setDireccionLocalidad(converter.fromDto(dto.getDireccionLocalidadDTO()));
                }
                if (dto.getDireccionBarrioDTO() != null) {
                    BarrioConverter converter = new BarrioConverter();
                    entity.setDireccionBarrio(converter.fromDto(dto.getDireccionBarrioDTO()));
                }
                if (dto.getTelefono() != null) {
                    entity.setTelefono(dto.getTelefono());
                }
                if (dto.getCorreoElectronico() != null) {
                    entity.setCorreoElectronico(dto.getCorreoElectronico());
                }
            }
            return entity;
        } else {
            return entity;
        }

    }

    @Override
    public GaranteDTO fromEntity(Garante entity) {
        GaranteDTO dto = null;
        if (entity != null) {
            if (entity instanceof GaranteDependiente) {
                dto = new GaranteDependienteDTO();
                if (entity.getId() != null) {
                    dto.setId(entity.getId());
                }
                if (entity.getNombre() != null) {
                    dto.setNombre(entity.getNombre());
                }
                if (entity.getApellido() != null) {
                    dto.setApellido(entity.getApellido());
                }
                if (entity.getUnTipoDNI() != null) {
                    TipoDNIConverter converter = new TipoDNIConverter();
                    dto.setUnTipoDNIDTO(converter.fromEntity(entity.getUnTipoDNI()));
                }
                if (entity.getDni() != null) {
                    dto.setDni(entity.getDni());
                }
                if (entity.getUnEstadoCivil() != null) {
                    EstadoCivilConverter converter = new EstadoCivilConverter();
                    dto.setUnEstadoCivilDTO(converter.fromEntity(entity.getUnEstadoCivil()));
                }
                if (entity.getDireccionCalle() != null) {
                    dto.setDireccionCalle(entity.getDireccionCalle());
                }
                if (entity.getDireccionNumero() != null) {
                    dto.setDireccionNumero(entity.getDireccionNumero());
                }
                if (entity.getDireccionProvincia() != null) {
                    ProvinciaConverter converter = new ProvinciaConverter();
                    dto.setDireccionProvinciaDTO(converter.fromEntity(entity.getDireccionProvincia()));
                }
                if (entity.getDireccionLocalidad() != null) {
                    LocalidadConverter converter = new LocalidadConverter();
                    dto.setDireccionLocalidadDTO(converter.fromEntity(entity.getDireccionLocalidad()));
                }
                if (entity.getDireccionBarrio() != null) {
                    BarrioConverter converter = new BarrioConverter();
                    dto.setDireccionBarrioDTO(converter.fromEntity(entity.getDireccionBarrio()));
                }
                if (entity.getTelefono() != null) {
                    dto.setTelefono(entity.getTelefono());
                }
                if (entity.getCorreoElectronico() != null) {
                    dto.setCorreoElectronico(entity.getCorreoElectronico());
                }

            }

            if (entity instanceof GaranteIndependiente) {
                dto = new GaranteIndependienteDTO();
                if (entity.getId() != null) {
                    dto.setId(entity.getId());
                }
                if (entity.getNombre() != null) {
                    dto.setNombre(entity.getNombre());
                }
                if (entity.getApellido() != null) {
                    dto.setApellido(entity.getApellido());
                }
                if (entity.getUnTipoDNI() != null) {
                    TipoDNIConverter converter = new TipoDNIConverter();
                    dto.setUnTipoDNIDTO(converter.fromEntity(entity.getUnTipoDNI()));
                }
                if (entity.getDni() != null) {
                    dto.setDni(entity.getDni());
                }
                if (entity.getUnEstadoCivil() != null) {
                    EstadoCivilConverter converter = new EstadoCivilConverter();
                    dto.setUnEstadoCivilDTO(converter.fromEntity(entity.getUnEstadoCivil()));
                }
                if (entity.getDireccionCalle() != null) {
                    dto.setDireccionCalle(entity.getDireccionCalle());
                }
                if (entity.getDireccionNumero() != null) {
                    dto.setDireccionNumero(entity.getDireccionNumero());
                }
                if (entity.getDireccionProvincia() != null) {
                    ProvinciaConverter converter = new ProvinciaConverter();
                    dto.setDireccionProvinciaDTO(converter.fromEntity(entity.getDireccionProvincia()));
                }
                if (entity.getDireccionLocalidad() != null) {
                    LocalidadConverter converter = new LocalidadConverter();
                    dto.setDireccionLocalidadDTO(converter.fromEntity(entity.getDireccionLocalidad()));
                }
                if (entity.getDireccionBarrio() != null) {
                    BarrioConverter converter = new BarrioConverter();
                    dto.setDireccionBarrioDTO(converter.fromEntity(entity.getDireccionBarrio()));
                }
                if (entity.getTelefono() != null) {
                    dto.setTelefono(entity.getTelefono());
                }
                if (entity.getCorreoElectronico() != null) {
                    dto.setCorreoElectronico(entity.getCorreoElectronico());
                }
            }
            return dto;
        } else {
            return dto;
        }
    }

    @Override
    public List<GaranteDTO> fromEntity(List<Garante> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Garante> fromDto(List<GaranteDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
