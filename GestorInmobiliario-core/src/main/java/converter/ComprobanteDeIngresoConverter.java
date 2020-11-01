/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ComprobanteDeIngresoDTO;
import dto.ComprobanteMonotributoDTO;
import dto.DocumentoDeIngresoDTO;
import dto.LocatarioDTO;
import dto.ReciboDeSueldoDTO;
import model.entity.ComprobanteDeIngreso;
import java.util.List;
import model.entity.ComprobanteMonotributo;
import model.entity.DocumentoDeIngreso;
import model.entity.Locatario;
import model.entity.ReciboDeSueldo;

/**
 *
 * @author Ariel
 */
public class ComprobanteDeIngresoConverter extends AbstractConverter<ComprobanteDeIngreso, ComprobanteDeIngresoDTO> {

    @Override
    public ComprobanteDeIngreso fromDto(ComprobanteDeIngresoDTO dto) {
        ComprobanteDeIngreso entity = null;
        if (dto != null) {
            if (dto instanceof ReciboDeSueldoDTO) {
                entity = new ReciboDeSueldo();

                if (dto.getId() != null) {
                    entity.setId(dto.getId());
                }
                if (dto.getMes() != null) {
                    entity.setMes(dto.getMes());
                }
                if (dto.getAnio() != null) {
                    entity.setAnio(dto.getAnio());
                }
                if (dto.getImporteBruto() != null) {
                    entity.setImporteBruto(dto.getImporteBruto());
                }
                if (dto.getImporteNeto() != null) {
                    entity.setImporteNeto(dto.getImporteNeto());
                }
                if (dto.getUnLocatarioDTO() != null) {
                    ClienteConverter converter = new ClienteConverter();
                    entity.setUnLocatario((Locatario) converter.fromDto(dto.getUnLocatarioDTO()));
                }
                if (dto.getUnGaranteDTo() != null) {
                    GaranteConverter converter = new GaranteConverter();
                    entity.setUnGarante(converter.fromDto(dto.getUnGaranteDTo()));
                }
                if (((ReciboDeSueldoDTO) dto).getNombreEmpresa() != null) {
                    ((ReciboDeSueldo) entity).setNombreEmpresa(((ReciboDeSueldoDTO) dto).getNombreEmpresa());
                }
                if (((ReciboDeSueldoDTO) dto).getAntiguedad() != null) {
                    ((ReciboDeSueldo) entity).setAntiguedad(((ReciboDeSueldoDTO) dto).getAntiguedad());
                }
            }
            if (dto instanceof ComprobanteMonotributoDTO) {
                entity = new ComprobanteMonotributo();

                if (dto.getId() != null) {
                    entity.setId(dto.getId());
                }
                if (dto.getMes() != null) {
                    entity.setMes(dto.getMes());
                }
                if (dto.getAnio() != null) {
                    entity.setAnio(dto.getAnio());
                }
                if (dto.getImporteBruto() != null) {
                    entity.setImporteBruto(dto.getImporteBruto());
                }
                if (dto.getImporteNeto() != null) {
                    entity.setImporteNeto(dto.getImporteNeto());
                }
                if (dto.getUnLocatarioDTO() != null) {
                    ClienteConverter converter = new ClienteConverter();
                    entity.setUnLocatario((Locatario) converter.fromDto(dto.getUnLocatarioDTO()));
                }
                if (dto.getUnGaranteDTo() != null) {
                    GaranteConverter converter = new GaranteConverter();
                    entity.setUnGarante(converter.fromDto(dto.getUnGaranteDTo()));
                }
                if (((ComprobanteMonotributoDTO) dto).getCuit() != null) {
                    ((ComprobanteMonotributo) entity).setCuit(((ComprobanteMonotributoDTO) dto).getCuit());
                }
                if (((ComprobanteMonotributoDTO) dto).getEstadoMonotributo() != null) {
                    ((ComprobanteMonotributo) entity).setEstadoMonotributo(((ComprobanteMonotributoDTO) dto).getEstadoMonotributo());
                }
            }
            if (dto instanceof DocumentoDeIngresoDTO) {
                entity = new DocumentoDeIngreso();

                if (dto.getId() != null) {
                    entity.setId(dto.getId());
                }
                if (dto.getMes() != null) {
                    entity.setMes(dto.getMes());
                }
                if (dto.getAnio() != null) {
                    entity.setAnio(dto.getAnio());
                }
                if (dto.getImporteBruto() != null) {
                    entity.setImporteBruto(dto.getImporteBruto());
                }
                if (dto.getImporteNeto() != null) {
                    entity.setImporteNeto(dto.getImporteNeto());
                }
                if (dto.getUnLocatarioDTO() != null) {
                    ClienteConverter converter = new ClienteConverter();
                    entity.setUnLocatario((Locatario) converter.fromDto(dto.getUnLocatarioDTO()));
                }
                if (dto.getUnGaranteDTo() != null) {
                    GaranteConverter converter = new GaranteConverter();
                    entity.setUnGarante(converter.fromDto(dto.getUnGaranteDTo()));
                }
                if (((DocumentoDeIngresoDTO) dto).getValidado() != null) {
                    ((DocumentoDeIngreso) entity).setValidado(((DocumentoDeIngresoDTO) dto).getValidado());
                }
            }
            return entity;
        } else {
            return entity;
        }
    }

    @Override
    public ComprobanteDeIngresoDTO fromEntity(ComprobanteDeIngreso entity) {
        ComprobanteDeIngresoDTO dto = null;
        if (entity != null) {
            if (entity instanceof ReciboDeSueldo) {
                dto = new ReciboDeSueldoDTO();

                if (entity.getId() != null) {
                    dto.setId(entity.getId());
                }
                if (entity.getMes() != null) {
                    dto.setMes(entity.getMes());
                }
                if (entity.getAnio() != null) {
                    dto.setAnio(entity.getAnio());
                }
                if (entity.getImporteBruto() != null) {
                    dto.setImporteBruto(entity.getImporteBruto());
                }
                if (entity.getImporteNeto() != null) {
                    dto.setImporteNeto(entity.getImporteNeto());
                }
                if (entity.getUnLocatario() != null) {
                    ClienteConverter converter = new ClienteConverter();
                    dto.setUnLocatarioDTO((LocatarioDTO) converter.fromEntity(entity.getUnLocatario()));
                }
                if (entity.getUnGarante() != null) {
                    GaranteConverter converter = new GaranteConverter();
                    dto.setUnGaranteDTo(converter.fromEntity(entity.getUnGarante()));
                }
                if (((ReciboDeSueldo) entity).getNombreEmpresa() != null) {
                    ((ReciboDeSueldoDTO) dto).setNombreEmpresa(((ReciboDeSueldo) entity).getNombreEmpresa());
                }
                if (((ReciboDeSueldo) entity).getAntiguedad() != null) {
                    ((ReciboDeSueldoDTO) dto).setAntiguedad(((ReciboDeSueldo) entity).getAntiguedad());
                }
            }
            if (entity instanceof ComprobanteMonotributo) {
                dto = new ComprobanteMonotributoDTO();

                if (entity.getId() != null) {
                    dto.setId(entity.getId());
                }
                if (entity.getMes() != null) {
                    dto.setMes(entity.getMes());
                }
                if (entity.getAnio() != null) {
                    dto.setAnio(entity.getAnio());
                }
                if (entity.getImporteBruto() != null) {
                    dto.setImporteBruto(entity.getImporteBruto());
                }
                if (entity.getImporteNeto() != null) {
                    dto.setImporteNeto(entity.getImporteNeto());
                }
                if (entity.getUnLocatario() != null) {
                    ClienteConverter converter = new ClienteConverter();
                    dto.setUnLocatarioDTO((LocatarioDTO) converter.fromEntity(entity.getUnLocatario()));
                }
                if (entity.getUnGarante() != null) {
                    GaranteConverter converter = new GaranteConverter();
                    dto.setUnGaranteDTo(converter.fromEntity(entity.getUnGarante()));
                }
                if (((ComprobanteMonotributo) entity).getCuit() != null) {
                    ((ComprobanteMonotributoDTO) dto).setCuit(((ComprobanteMonotributo) entity).getCuit());
                }
                if (((ComprobanteMonotributo) entity).getEstadoMonotributo() != null) {
                    ((ComprobanteMonotributoDTO) dto).setEstadoMonotributo(((ComprobanteMonotributo) entity).getEstadoMonotributo());
                }
            }
            if (entity instanceof DocumentoDeIngreso) {
                dto = new DocumentoDeIngresoDTO();

                if (entity.getId() != null) {
                    dto.setId(entity.getId());
                }
                if (entity.getMes() != null) {
                    dto.setMes(entity.getMes());
                }
                if (entity.getAnio() != null) {
                    dto.setAnio(entity.getAnio());
                }
                if (entity.getImporteBruto() != null) {
                    dto.setImporteBruto(entity.getImporteBruto());
                }
                if (entity.getImporteNeto() != null) {
                    dto.setImporteNeto(entity.getImporteNeto());
                }
                if (entity.getUnLocatario() != null) {
                    ClienteConverter converter = new ClienteConverter();
                    dto.setUnLocatarioDTO((LocatarioDTO) converter.fromEntity(entity.getUnLocatario()));
                }
                if (entity.getUnGarante() != null) {
                    GaranteConverter converter = new GaranteConverter();
                    dto.setUnGaranteDTo(converter.fromEntity(entity.getUnGarante()));
                }
                if (((DocumentoDeIngreso) entity).getValidado() != null) {
                    ((DocumentoDeIngresoDTO) dto).setValidado(((DocumentoDeIngreso) entity).getValidado());
                }
            }
            return dto;
        } else {
            return dto;
        }
    }

    @Override
    public List<ComprobanteDeIngresoDTO> fromEntity(List<ComprobanteDeIngreso> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ComprobanteDeIngreso> fromDto(List<ComprobanteDeIngresoDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
