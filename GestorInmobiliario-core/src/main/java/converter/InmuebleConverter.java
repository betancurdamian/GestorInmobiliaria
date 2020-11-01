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
import java.util.List;

/**
 *
 * @author Ariel
 */
public class InmuebleConverter extends AbstractConverter<Inmueble, InmuebleDTO> {

    @Override
    public Inmueble fromDto(InmuebleDTO dto) {
        Inmueble entity = null;
        if (dto != null) {
            if (dto instanceof TerrenoDTO) {
                entity = new Terreno();

                if (dto.getId() != null) {
                    entity.setId(dto.getId());
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
                if (dto.getSuperficieTotal() != null) {
                    entity.setSuperficieTotal(dto.getSuperficieTotal());
                }
                if (dto.getDisponible() != null) {
                    entity.setDisponible(dto.getDisponible());
                }
                if (dto.getDescripcion() != null) {
                    entity.setDescripcion(dto.getDescripcion());
                }
                if (dto.getUnaInmobiliariaInmuebleDTO() != null) {
                    InmobiliariaConverter converter = new InmobiliariaConverter();
                    entity.setUnaInmobiliariaInmueble(converter.fromDto(dto.getUnaInmobiliariaInmuebleDTO()));
                }
                if (((TerrenoDTO) dto).getAncho() != null) {
                    ((Terreno) entity).setAncho(((TerrenoDTO) dto).getAncho());
                }
                if (((TerrenoDTO) dto).getAncho() != null) {
                    ((Terreno) entity).setLargo(((TerrenoDTO) dto).getLargo());
                }
            }
            if (dto instanceof CasaDTO) {
                entity = new Casa();

                if (dto.getId() != null) {
                    entity.setId(dto.getId());
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
                if (dto.getSuperficieTotal() != null) {
                    entity.setSuperficieTotal(dto.getSuperficieTotal());
                }
                if (dto.getDisponible() != null) {
                    entity.setDisponible(dto.getDisponible());
                }
                if (dto.getDescripcion() != null) {
                    entity.setDescripcion(dto.getDescripcion());
                }
                if (dto.getUnaInmobiliariaInmuebleDTO() != null) {
                    InmobiliariaConverter converter = new InmobiliariaConverter();
                    entity.setUnaInmobiliariaInmueble(converter.fromDto(dto.getUnaInmobiliariaInmuebleDTO()));
                }
                if (((CasaDTO) dto).getCantidadDeDormitorios() != null) {
                    ((Casa) entity).setCantidadDeDormitorios(((CasaDTO) dto).getCantidadDeDormitorios());
                }
                if (((CasaDTO) dto).getCantidadDeBanio() != null) {
                    ((Casa) entity).setCantidadDeBanio(((CasaDTO) dto).getCantidadDeBanio());
                }
                if (((CasaDTO) dto).getNumeroDePisos() != null) {
                    ((Casa) entity).setNumeroDePisos(((CasaDTO) dto).getNumeroDePisos());
                }
                if (((CasaDTO) dto).getCochera() != null) {
                    ((Casa) entity).setCochera(((CasaDTO) dto).getCochera());
                }
                if (((CasaDTO) dto).getJardin() != null) {
                    ((Casa) entity).setJardin(((CasaDTO) dto).getJardin());
                }
            }
            if (dto instanceof DepartamentoDTO) {
                entity = new Departamento();

                if (dto.getId() != null) {
                    entity.setId(dto.getId());
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
                if (dto.getSuperficieTotal() != null) {
                    entity.setSuperficieTotal(dto.getSuperficieTotal());
                }
                if (dto.getDisponible() != null) {
                    entity.setDisponible(dto.getDisponible());
                }
                if (dto.getDescripcion() != null) {
                    entity.setDescripcion(dto.getDescripcion());
                }
                if (dto.getUnaInmobiliariaInmuebleDTO() != null) {
                    InmobiliariaConverter converter = new InmobiliariaConverter();
                    entity.setUnaInmobiliariaInmueble(converter.fromDto(dto.getUnaInmobiliariaInmuebleDTO()));
                }
                if (((DepartamentoDTO) dto).getCantidadDeDormitorios() != null) {
                    ((Departamento) entity).setCantidadDeDormitorios(((DepartamentoDTO) dto).getCantidadDeDormitorios());
                }
                if (((DepartamentoDTO) dto).getCantidadDeBanio() != null) {
                    ((Departamento) entity).setCantidadDeBanio(((DepartamentoDTO) dto).getCantidadDeBanio());
                }
                if (((DepartamentoDTO) dto).getBalcon() != null) {
                    ((Departamento) entity).setBalcon(((DepartamentoDTO) dto).getBalcon());
                }
            }
            if (dto instanceof LocalComercialDTO) {
                entity = new LocalComercial();

                if (dto.getId() != null) {
                    entity.setId(dto.getId());
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
                if (dto.getSuperficieTotal() != null) {
                    entity.setSuperficieTotal(dto.getSuperficieTotal());
                }
                if (dto.getDisponible() != null) {
                    entity.setDisponible(dto.getDisponible());
                }
                if (dto.getDescripcion() != null) {
                    entity.setDescripcion(dto.getDescripcion());
                }
                if (dto.getUnaInmobiliariaInmuebleDTO() != null) {
                    InmobiliariaConverter converter = new InmobiliariaConverter();
                    entity.setUnaInmobiliariaInmueble(converter.fromDto(dto.getUnaInmobiliariaInmuebleDTO()));
                }
                if (((LocalComercialDTO) dto).getCantidadDeBanio() != null) {
                    ((LocalComercial) entity).setCantidadDeBanio(((LocalComercialDTO) dto).getCantidadDeBanio());
                }
                if (((LocalComercialDTO) dto).getCochera() != null) {
                    ((LocalComercial) entity).setCochera(((LocalComercialDTO) dto).getCochera());
                }
            }
            return entity;
        } else {
            return entity;
        }
    }

    @Override
    public InmuebleDTO fromEntity(Inmueble entity) {
        InmuebleDTO dto = null;
        if (entity != null) {
            if (entity instanceof Terreno) {
                dto = new TerrenoDTO();

                if (entity.getId() != null) {
                    dto.setId(entity.getId());
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
                if (entity.getSuperficieTotal() != null) {
                    dto.setSuperficieTotal(entity.getSuperficieTotal());
                }
                if (entity.getDisponible() != null) {
                    dto.setDisponible(entity.getDisponible());
                }
                if (entity.getDescripcion() != null) {
                    dto.setDescripcion(entity.getDescripcion());
                }
                if (entity.getUnaInmobiliariaInmueble() != null) {
                    InmobiliariaConverter converter = new InmobiliariaConverter();
                    dto.setUnaInmobiliariaInmuebleDTO(converter.fromEntity(entity.getUnaInmobiliariaInmueble()));
                }
                if (((Terreno) entity).getAncho() != null) {
                    ((TerrenoDTO) dto).setAncho(((Terreno) entity).getAncho());
                }
                if (((Terreno) entity).getAncho() != null) {
                    ((TerrenoDTO) dto).setLargo(((Terreno) entity).getLargo());
                }
            }
            if (entity instanceof Casa) {
                dto = new CasaDTO();

                if (entity.getId() != null) {
                    dto.setId(entity.getId());
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
                if (entity.getSuperficieTotal() != null) {
                    dto.setSuperficieTotal(entity.getSuperficieTotal());
                }
                if (entity.getDisponible() != null) {
                    dto.setDisponible(entity.getDisponible());
                }
                if (entity.getDescripcion() != null) {
                    dto.setDescripcion(entity.getDescripcion());
                }
                if (entity.getUnaInmobiliariaInmueble() != null) {
                    InmobiliariaConverter converter = new InmobiliariaConverter();
                    dto.setUnaInmobiliariaInmuebleDTO(converter.fromEntity(entity.getUnaInmobiliariaInmueble()));
                }
                if (((Casa) entity).getCantidadDeDormitorios() != null) {
                    ((CasaDTO) dto).setCantidadDeDormitorios(((Casa) entity).getCantidadDeDormitorios());
                }
                if (((Casa) entity).getCantidadDeBanio() != null) {
                    ((CasaDTO) dto).setCantidadDeBanio(((Casa) entity).getCantidadDeBanio());
                }
                if (((Casa) entity).getNumeroDePisos() != null) {
                    ((CasaDTO) dto).setNumeroDePisos(((Casa) entity).getNumeroDePisos());
                }
                if (((Casa) entity).getCochera() != null) {
                    ((CasaDTO) dto).setCochera(((Casa) entity).getCochera());
                }
                if (((Casa) entity).getJardin() != null) {
                    ((CasaDTO) dto).setJardin(((Casa) entity).getJardin());
                }
            }
            if (entity instanceof Departamento) {
                dto = new DepartamentoDTO();

                if (entity.getId() != null) {
                    dto.setId(entity.getId());
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
                if (entity.getSuperficieTotal() != null) {
                    dto.setSuperficieTotal(entity.getSuperficieTotal());
                }
                if (entity.getDisponible() != null) {
                    dto.setDisponible(entity.getDisponible());
                }
                if (entity.getDescripcion() != null) {
                    dto.setDescripcion(entity.getDescripcion());
                }
                if (entity.getUnaInmobiliariaInmueble() != null) {
                    InmobiliariaConverter converter = new InmobiliariaConverter();
                    dto.setUnaInmobiliariaInmuebleDTO(converter.fromEntity(entity.getUnaInmobiliariaInmueble()));
                }
                if (((Departamento) entity).getCantidadDeDormitorios() != null) {
                    ((DepartamentoDTO) dto).setCantidadDeDormitorios(((Departamento) entity).getCantidadDeDormitorios());
                }
                if (((Departamento) entity).getCantidadDeBanio() != null) {
                    ((DepartamentoDTO) dto).setCantidadDeBanio(((Departamento) entity).getCantidadDeBanio());
                }

                if (((Departamento) entity).getBalcon() != null) {
                    ((DepartamentoDTO) dto).setBalcon(((Departamento) entity).getBalcon());
                }
            }
            if (entity instanceof LocalComercial) {
                dto = new LocalComercialDTO();

                if (entity.getId() != null) {
                    dto.setId(entity.getId());
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
                if (entity.getSuperficieTotal() != null) {
                    dto.setSuperficieTotal(entity.getSuperficieTotal());
                }
                if (entity.getDisponible() != null) {
                    dto.setDisponible(entity.getDisponible());
                }
                if (entity.getDescripcion() != null) {
                    dto.setDescripcion(entity.getDescripcion());
                }
                if (entity.getUnaInmobiliariaInmueble() != null) {
                    InmobiliariaConverter converter = new InmobiliariaConverter();
                    dto.setUnaInmobiliariaInmuebleDTO(converter.fromEntity(entity.getUnaInmobiliariaInmueble()));
                }
                if (((LocalComercial) entity).getCantidadDeBanio() != null) {
                    ((LocalComercialDTO) dto).setCantidadDeBanio(((LocalComercial) entity).getCantidadDeBanio());
                }
                if (((LocalComercial) entity).getCochera() != null) {
                    ((LocalComercialDTO) dto).setCochera(((LocalComercial) entity).getCochera());
                }
            }
            return dto;
        } else {
            return dto;
        }
    }

    @Override
    public List<InmuebleDTO> fromEntity(List<Inmueble> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Inmueble> fromDto(List<InmuebleDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
