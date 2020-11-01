/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.InmobiliariaDTO;
import model.entity.Inmobiliaria;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class InmobiliariaConverter extends AbstractConverter<Inmobiliaria, InmobiliariaDTO> {

    @Override
    public Inmobiliaria fromDto(InmobiliariaDTO dto) {
        Inmobiliaria entity = null;
        if (dto != null) {
            entity = new Inmobiliaria();

            if (dto.getId() != null) {
                entity.setId(dto.getId());
            }
            if (dto.getRazonSocial() != null) {
                entity.setRazonSocial(dto.getRazonSocial());
            }
            if (dto.getCuit() != null) {
                entity.setCuit(dto.getCuit());
            }
            if (dto.getDireccionCalle() != null) {
                entity.setDireccionCalle(dto.getDireccionCalle());
            }
            if (dto.getDireccionNumero() != null) {
                entity.setDireccionNumero(dto.getDireccionNumero());
            }
            if (dto.getTelefono() != null) {
                entity.setTelefono(dto.getTelefono());
            }
            if (dto.getCorreoElectronico() != null) {
                entity.setCorreoElectronico(dto.getCorreoElectronico());
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
            if (!dto.getUsuariosDTO().isEmpty()) {
                UsuarioConverter converter = new UsuarioConverter();
                entity.setUsuarios(converter.fromDto(dto.getUsuariosDTO()));
            }
            if (!dto.getClientesDTO().isEmpty()) {
                ClienteConverter converter = new ClienteConverter();
                entity.setClientes(converter.fromDto(dto.getClientesDTO()));
            }
            if (!dto.getRecargosPorMorasDTO().isEmpty()) {
                RecargoPorMoraConverter converter = new RecargoPorMoraConverter();
                entity.setRecargosPorMoras(converter.fromDto(dto.getRecargosPorMorasDTO()));
            }
            if (!dto.getArancelesEspecialesDTO().isEmpty()) {
                ArancelEspecialConverter converter = new ArancelEspecialConverter();
                entity.setArancelesEspeciales(converter.fromDto(dto.getArancelesEspecialesDTO()));
            }
            if (!dto.getVentasDTO().isEmpty()) {
                VentaConverter converter = new VentaConverter();
                entity.setVentas(converter.fromDto(dto.getVentasDTO()));
            }
            if (!dto.getInmueblesDTO().isEmpty()) {
                InmuebleConverter converter = new InmuebleConverter();
                entity.setInmuebles(converter.fromDto(dto.getInmueblesDTO()));
            }
            if (!dto.getAlquileresDTO().isEmpty()) {
                AlquilerConverter converter = new AlquilerConverter();
                entity.setAlquileres(converter.fromDto(dto.getAlquileresDTO()));
            }
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public InmobiliariaDTO fromEntity(Inmobiliaria entity) {
        InmobiliariaDTO dto = null;
        if (entity != null) {
            dto = new InmobiliariaDTO();

            if (entity.getId() != null) {
                dto.setId(entity.getId());
            }
            if (entity.getRazonSocial() != null) {
                dto.setRazonSocial(entity.getRazonSocial());
            }
            if (entity.getCuit() != null) {
                dto.setCuit(entity.getCuit());
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
            if (!entity.getClientes().isEmpty()) {
                ClienteConverter converter = new ClienteConverter();
                dto.setClientesDTO(converter.fromEntity(entity.getClientes()));
            }
            if (!entity.getUsuarios().isEmpty()) {
                UsuarioConverter converter = new UsuarioConverter();
                dto.setUsuariosDTO(converter.fromEntity(entity.getUsuarios()));
            }
            if (!entity.getRecargosPorMoras().isEmpty()) {
                RecargoPorMoraConverter converter = new RecargoPorMoraConverter();
                dto.setRecargosPorMorasDTO(converter.fromEntity(entity.getRecargosPorMoras()));
            }
            if (!entity.getArancelesEspeciales().isEmpty()) {
                ArancelEspecialConverter converter = new ArancelEspecialConverter();
                dto.setArancelesEspecialesDTO(converter.fromEntity(entity.getArancelesEspeciales()));
            }
            if (!entity.getVentas().isEmpty()) {
                VentaConverter converter = new VentaConverter();
                dto.setVentasDTO(converter.fromEntity(entity.getVentas()));
            }
            if (!entity.getInmuebles().isEmpty()) {
                InmuebleConverter converter = new InmuebleConverter();
                dto.setInmueblesDTO(converter.fromEntity(entity.getInmuebles()));
            }
            if (!entity.getAlquileres().isEmpty()) {
                AlquilerConverter converter = new AlquilerConverter();
                dto.setAlquileresDTO(converter.fromEntity(entity.getAlquileres()));
            }
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public List<InmobiliariaDTO> fromEntity(List<Inmobiliaria> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Inmobiliaria> fromDto(List<InmobiliariaDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
