/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.CasaDTO;
import dto.DepartamentoDTO;
import dto.InmuebleDTO;
import dto.LocalComercialDTO;
import dto.TerrenoDTO;
import model.entity.Casa;
import model.entity.Departamento;
import model.entity.Inmueble;
import model.entity.LocalComercial;
import model.entity.Terreno;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class InmuebleConverter implements Converter<Inmueble, InmuebleDTO> {

    @Override
    public Inmueble fomDTO(InmuebleDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);

        Inmueble entity = null;
        if (dto instanceof TerrenoDTO) {
            entity = modelMapper.map(dto, Terreno.class);
        }
        if (dto instanceof CasaDTO) {
            entity = modelMapper.map(dto, Casa.class);
        }
        if (dto instanceof DepartamentoDTO) {
            entity = modelMapper.map(dto, Departamento.class);
        }
        if (dto instanceof LocalComercialDTO) {
            entity = modelMapper.map(dto, LocalComercial.class);
        }
        return entity;
    }

    @Override
    public InmuebleDTO fromDTO(Inmueble entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);

        InmuebleDTO dto = null;
        if (entity instanceof Terreno) {
            dto = modelMapper.map(entity, TerrenoDTO.class);
        }
        if (entity instanceof Casa) {
            dto = modelMapper.map(entity, CasaDTO.class);
        }
        if (entity instanceof Departamento) {
            dto = modelMapper.map(entity, DepartamentoDTO.class);
        }
        if (entity instanceof LocalComercial) {
            dto = modelMapper.map(entity, LocalComercialDTO.class);
        }
        return dto;
    }
    
}
