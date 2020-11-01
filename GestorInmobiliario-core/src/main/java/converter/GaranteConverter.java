/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.GaranteDTO;
import dto.GaranteDependienteDTO;
import dto.GaranteIndependienteDTO;
import dto.LocatarioDTO;
import model.entity.Garante;
import model.entity.GaranteDependiente;
import model.entity.GaranteIndependiente;
import java.util.List;
import model.entity.Locatario;

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

                if (((GaranteDependienteDTO) dto).getUnaActividadDTO() != null) {
                    ActividadConverter converter = new ActividadConverter();
                    ((GaranteDependiente) entity).setUnaActividad(converter.fromDto(((GaranteDependienteDTO) dto).getUnaActividadDTO()));
                }
                if (((GaranteDependienteDTO) dto).getUnLocatarioDTO() != null) {
                    ClienteConverter converter = new ClienteConverter();
                    ((GaranteDependiente) entity).setUnLocatario((Locatario) converter.fromDto(((GaranteDependienteDTO) dto).getUnLocatarioDTO()));
                }
                if (((GaranteDependienteDTO) dto).getComprobantesDeIngresosGarantesDTO() != null) {
                    ComprobanteDeIngresoConverter converter = new ComprobanteDeIngresoConverter();
                    ((GaranteDependiente) entity).setComprobantesDeIngresosGarantes(converter.fromDto(((GaranteDependienteDTO) dto).getComprobantesDeIngresosGarantesDTO()));
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
                if (((GaranteIndependienteDTO) dto).getUnaActividadDTO() != null) {
                    ActividadConverter converter = new ActividadConverter();
                    ((GaranteIndependiente) entity).setUnaActividad(converter.fromDto(((GaranteIndependienteDTO) dto).getUnaActividadDTO()));
                }
                if (((GaranteIndependienteDTO) dto).getUnLocatarioDTO() != null) {
                    ClienteConverter converter = new ClienteConverter();
                    ((GaranteIndependiente) entity).setUnLocatario((Locatario) converter.fromDto(((GaranteIndependienteDTO) dto).getUnLocatarioDTO()));
                }
                if (((GaranteIndependienteDTO) dto).getComprobantesDeIngresosGarantesDTO() != null) {
                    ComprobanteDeIngresoConverter converter = new ComprobanteDeIngresoConverter();
                    ((GaranteIndependiente) entity).setComprobantesDeIngresosGarantes(converter.fromDto(((GaranteIndependienteDTO) dto).getComprobantesDeIngresosGarantesDTO()));
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

                if (((GaranteDependiente) entity).getUnaActividad() != null) {
                    ActividadConverter converter = new ActividadConverter();
                    ((GaranteDependienteDTO) dto).setUnaActividadDTO(converter.fromEntity(((GaranteDependiente) entity).getUnaActividad()));
                }
                if (((GaranteDependiente) entity).getUnLocatario() != null) {
                    ClienteConverter converter = new ClienteConverter();
                    ((GaranteDependienteDTO) dto).setUnLocatarioDTO((LocatarioDTO) converter.fromEntity(((GaranteDependiente) entity).getUnLocatario()));
                }
                if (((GaranteDependiente) entity).getComprobantesDeIngresosGarantes() != null) {
                    ComprobanteDeIngresoConverter converter = new ComprobanteDeIngresoConverter();
                    ((GaranteDependienteDTO) dto).setComprobantesDeIngresosGarantesDTO(converter.fromEntity(((GaranteDependiente) entity).getComprobantesDeIngresosGarantes()));
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
                if (((GaranteIndependiente) entity).getUnaActividad() != null) {
                    ActividadConverter converter = new ActividadConverter();
                    ((GaranteIndependienteDTO) dto).setUnaActividadDTO(converter.fromEntity(((GaranteIndependiente) entity).getUnaActividad()));
                }
                if (((GaranteIndependiente) entity).getUnLocatario() != null) {
                    ClienteConverter converter = new ClienteConverter();
                    ((GaranteIndependienteDTO) dto).setUnLocatarioDTO((LocatarioDTO) converter.fromEntity(((GaranteIndependiente) entity).getUnLocatario()));
                }
                if (((GaranteIndependiente) entity).getComprobantesDeIngresosGarantes() != null) {
                    ComprobanteDeIngresoConverter converter = new ComprobanteDeIngresoConverter();
                    ((GaranteIndependienteDTO) dto).setComprobantesDeIngresosGarantesDTO(converter.fromEntity(((GaranteIndependiente) entity).getComprobantesDeIngresosGarantes()));
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
