/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ClienteDTO;
import dto.LocadorDTO;
import dto.LocatarioDTO;
import dto.LocatarioDependienteDTO;
import dto.LocatarioEstudianteDTO;
import dto.LocatarioIndependienteDTO;
import model.entity.Cliente;
import model.entity.Locador;
import model.entity.Locatario;
import model.entity.LocatarioDependiente;
import model.entity.LocatarioEstudiante;
import model.entity.LocatarioIndependiente;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ClienteConverter extends AbstractConverter<Cliente, ClienteDTO> {

    @Override
    public Cliente fromDto(ClienteDTO dto) {
        Cliente entity = null;
        if (dto != null) {
            if (dto instanceof LocadorDTO) {
                entity = new Locador();
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

            if (dto instanceof LocatarioDTO) {
                if (dto instanceof LocatarioDependienteDTO) {
                    entity = new LocatarioDependiente();
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
                    if (((LocatarioDependienteDTO) dto).getUnaActividadDTO() != null) {
                        ActividadConverter converter = new ActividadConverter();
                        ((LocatarioDependiente) entity).setUnaActividad(converter.fromDto(((LocatarioDependienteDTO) dto).getUnaActividadDTO()));
                    }
                    if (((LocatarioDependienteDTO) dto).getComprobantesDeIngresosLocatariosDTO() != null) {
                        ComprobanteDeIngresoConverter converter = new ComprobanteDeIngresoConverter();
                        ((LocatarioDependiente) entity).setComprobantesDeIngresosLocatarios(converter.fromDto(((LocatarioDependienteDTO) dto).getComprobantesDeIngresosLocatariosDTO()));
                    }
                }
                if (dto instanceof LocatarioIndependienteDTO) {
                    entity = new LocatarioIndependiente();
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
                    if (((LocatarioIndependienteDTO) dto).getUnaActividadDTO() != null) {
                        ActividadConverter converter = new ActividadConverter();
                        ((LocatarioIndependiente) entity).setUnaActividad(converter.fromDto(((LocatarioIndependienteDTO) dto).getUnaActividadDTO()));
                    }
                    if (((LocatarioDependienteDTO) dto).getComprobantesDeIngresosLocatariosDTO() != null) {
                        ComprobanteDeIngresoConverter converter = new ComprobanteDeIngresoConverter();
                        ((LocatarioIndependiente) entity).setComprobantesDeIngresosLocatarios(converter.fromDto(((LocatarioIndependienteDTO) dto).getComprobantesDeIngresosLocatariosDTO()));
                    }
                }
                if (dto instanceof LocatarioEstudianteDTO) {
                    entity = new LocatarioEstudiante();
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
                    if (((LocatarioEstudianteDTO) dto).getUnaActividadDTO() != null) {
                        ActividadConverter converter = new ActividadConverter();
                        ((LocatarioEstudiante) entity).setUnaActividad(converter.fromDto(((LocatarioEstudianteDTO) dto).getUnaActividadDTO()));
                    }
                    if (((LocatarioDependienteDTO) dto).getComprobantesDeIngresosLocatariosDTO() != null) {
                        ComprobanteDeIngresoConverter converter = new ComprobanteDeIngresoConverter();
                        ((LocatarioEstudiante) entity).setComprobantesDeIngresosLocatarios(converter.fromDto(((LocatarioEstudianteDTO) dto).getComprobantesDeIngresosLocatariosDTO()));
                    }
                }
            }
            return entity;
        } else {
            return entity;
        }
    }

    @Override
    public ClienteDTO fromEntity(Cliente entity) {
        ClienteDTO dto = null;
        if (entity != null) {
            if (entity instanceof Locador) {
                dto = new LocadorDTO();
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

            if (entity instanceof Locatario) {
                if (entity instanceof LocatarioDependiente) {
                    dto = new LocatarioDependienteDTO();
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
                    if (((LocatarioDependiente) entity).getUnaActividad() != null) {
                        ActividadConverter converter = new ActividadConverter();
                        ((LocatarioDependienteDTO) dto).setUnaActividadDTO(converter.fromEntity(((LocatarioDependiente) entity).getUnaActividad()));
                    }
                    if (((LocatarioDependiente) entity).getComprobantesDeIngresosLocatarios() != null) {
                        ComprobanteDeIngresoConverter converter = new ComprobanteDeIngresoConverter();
                        ((LocatarioDependienteDTO) dto).setComprobantesDeIngresosLocatariosDTO(converter.fromEntity(((LocatarioDependiente) entity).getComprobantesDeIngresosLocatarios()));
                    }
                }
                if (entity instanceof LocatarioIndependiente) {
                    dto = new LocatarioIndependienteDTO();
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
                    if (((LocatarioIndependiente) entity).getUnaActividad() != null) {
                        ActividadConverter converter = new ActividadConverter();
                        ((LocatarioIndependienteDTO) dto).setUnaActividadDTO(converter.fromEntity(((LocatarioIndependiente) entity).getUnaActividad()));
                    }
                    if (((LocatarioIndependiente) entity).getComprobantesDeIngresosLocatarios() != null) {
                        ComprobanteDeIngresoConverter converter = new ComprobanteDeIngresoConverter();
                        ((LocatarioIndependienteDTO) dto).setComprobantesDeIngresosLocatariosDTO(converter.fromEntity(((LocatarioIndependiente) entity).getComprobantesDeIngresosLocatarios()));
                    }
                }
                if (entity instanceof LocatarioEstudiante) {
                    dto = new LocatarioEstudianteDTO();
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
                    if (((LocatarioEstudiante) entity).getUnaActividad() != null) {
                        ActividadConverter converter = new ActividadConverter();
                        ((LocatarioEstudianteDTO) dto).setUnaActividadDTO(converter.fromEntity(((LocatarioEstudiante) entity).getUnaActividad()));
                    }
                    if (((LocatarioIndependiente) entity).getComprobantesDeIngresosLocatarios() != null) {
                        ComprobanteDeIngresoConverter converter = new ComprobanteDeIngresoConverter();
                        ((LocatarioEstudianteDTO) dto).setComprobantesDeIngresosLocatariosDTO(converter.fromEntity(((LocatarioEstudiante) entity).getComprobantesDeIngresosLocatarios()));
                    }
                }

            }
            return dto;
        } else {
            return dto;
        }
    }

    @Override
    public List<ClienteDTO> fromEntity(List<Cliente> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> fromDto(List<ClienteDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
