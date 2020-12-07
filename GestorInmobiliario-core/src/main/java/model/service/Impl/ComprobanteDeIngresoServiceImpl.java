/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.ComprobanteDeIngresoDTO;
import dto.ComprobanteMonotributoDTO;
import dto.DocumentoDeIngresoDTO;
import dto.GaranteDTO;
import dto.LocatarioDTO;
import dto.ReciboDeSueldoDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.ComprobanteDeIngresoJpaController;
import model.dao.ComprobanteMonotributoJpaController;
import model.dao.Conexion;
import model.dao.DocumentoDeIngresoJpaController;
import model.dao.ReciboDeSueldoJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.ComprobanteDeIngreso;
import model.entity.ComprobanteMonotributo;
import model.entity.DocumentoDeIngreso;
import model.entity.ReciboDeSueldo;
import model.service.IComprobanteDeIngresoService;
import org.mapstruct.factory.Mappers;
import util.ConverterDate;

/**
 *
 * @author Ariel
 */
public class ComprobanteDeIngresoServiceImpl implements IComprobanteDeIngresoService {

    private final ComprobanteDeIngresoJpaController comprobanteDeIngresoDAO;
    private final ReciboDeSueldoJpaController reciboDeSueldoDAO;
    private final ComprobanteMonotributoJpaController comprobanteMonotributoDAO;
    private final DocumentoDeIngresoJpaController documentoDeIngresoDAO;
    private final InmobiliariaMapper converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public ComprobanteDeIngresoServiceImpl() {
        new Conexion();
        this.comprobanteDeIngresoDAO = new ComprobanteDeIngresoJpaController(Conexion.getEmf());
        this.reciboDeSueldoDAO = new ReciboDeSueldoJpaController(Conexion.getEmf());
        this.comprobanteMonotributoDAO = new ComprobanteMonotributoJpaController(Conexion.getEmf());
        this.documentoDeIngresoDAO = new DocumentoDeIngresoJpaController(Conexion.getEmf());
        this.converter = Mappers.getMapper(InmobiliariaMapper.class);
    }

    @Override
    public ComprobanteDeIngresoDTO crear(ComprobanteDeIngresoDTO dto) {
        if (dto != null) {
            if (dto instanceof ReciboDeSueldoDTO) {
                ReciboDeSueldo entity = converter.toReciboDeSueldoEntity((ReciboDeSueldoDTO) dto);
                this.reciboDeSueldoDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof ComprobanteMonotributoDTO) {
                ComprobanteMonotributo entity = converter.toComprobanteMonotributoEntity((ComprobanteMonotributoDTO) dto);
                this.comprobanteMonotributoDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof DocumentoDeIngresoDTO) {
                DocumentoDeIngreso entity = converter.toDocumentoDeIngresoEntity((DocumentoDeIngresoDTO) dto);
                this.documentoDeIngresoDAO.create(entity);
                dto.setId(entity.getId());
            }
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public ComprobanteDeIngresoDTO modificar(ComprobanteDeIngresoDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                if (dto instanceof ReciboDeSueldoDTO) {
                    try {
                        ReciboDeSueldo entity = converter.toReciboDeSueldoEntity((ReciboDeSueldoDTO) dto);
                        this.reciboDeSueldoDAO.edit(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ComprobanteDeIngresoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof ComprobanteMonotributoDTO) {
                    try {
                        ComprobanteMonotributo entity = converter.toComprobanteMonotributoEntity((ComprobanteMonotributoDTO) dto);
                        this.comprobanteMonotributoDAO.create(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ComprobanteDeIngresoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dto instanceof DocumentoDeIngresoDTO) {
                    try {
                        DocumentoDeIngreso entity = converter.toDocumentoDeIngresoEntity((DocumentoDeIngresoDTO) dto);
                        this.documentoDeIngresoDAO.create(entity);
                        dto.setId(entity.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ComprobanteDeIngresoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                System.out.println("ID  DTO is null");
            }
        } else {
            System.out.println("DTO is null");
        }
        return dto;
    }

    @Override
    public void eliminar(Long id) {
        if (id != null) {
            if (comprobanteDeIngresoDAO.findComprobanteDeIngreso(id) != null) {
                try {
                    comprobanteDeIngresoDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(ComprobanteDeIngresoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public ComprobanteDeIngresoDTO listarID(Long id) {
        ComprobanteDeIngreso entity = comprobanteDeIngresoDAO.findComprobanteDeIngreso(id);
        ComprobanteDeIngresoDTO dto = null;
        if (entity instanceof ReciboDeSueldo) {
            dto = converter.toReciboDeSueldoDTO((ReciboDeSueldo) entity);
        }
        if (entity instanceof ComprobanteMonotributo) {
            dto = converter.toComprobanteMonotributoDTO((ComprobanteMonotributo) entity);
        }
        if (entity instanceof DocumentoDeIngreso) {
            dto = converter.toDocumentoDeIngresoDTO((DocumentoDeIngreso) entity);
        }
        return dto;
    }

    @Override
    public List<ComprobanteDeIngresoDTO> listarTodos() {
        List<ComprobanteDeIngreso> entities = comprobanteDeIngresoDAO.findComprobanteDeIngresoEntities();
        return converter.toDTOComprobanteDeIngresoList(entities);
    }

    @Override
    public ReciboDeSueldoDTO listarReciboDeSueldoID(Long id) {
        ReciboDeSueldo entity = reciboDeSueldoDAO.findReciboDeSueldo(id);
        return converter.toReciboDeSueldoDTO(entity);
    }

    @Override
    public List<ReciboDeSueldoDTO> listarTodosReciboDeSueldos() {
        List<ReciboDeSueldo> entities = reciboDeSueldoDAO.findReciboDeSueldoEntities();
        return converter.toDTOReciboDeSueldoList(entities);
    }

    @Override
    public ComprobanteMonotributoDTO listarComprobanteMonotributoID(Long id) {
        ComprobanteMonotributo entity = comprobanteMonotributoDAO.findComprobanteMonotributo(id);
        return converter.toComprobanteMonotributoDTO(entity);
    }

    @Override
    public List<ComprobanteMonotributoDTO> listarTodosComprobanteMonotributos() {
        List<ComprobanteMonotributo> entities = comprobanteMonotributoDAO.findComprobanteMonotributoEntities();
        return converter.toDTOComprobanteDeMonotributoList(entities);
    }

    @Override
    public DocumentoDeIngresoDTO listarDocumentoDeIngresoID(Long id) {
        DocumentoDeIngreso entity = documentoDeIngresoDAO.findDocumentoDeIngreso(id);
        return converter.toDocumentoDeIngresoDTO(entity);
    }

    @Override
    public List<DocumentoDeIngresoDTO> listarTodosDocumentoDeIngresos() {
        List<DocumentoDeIngreso> entities = documentoDeIngresoDAO.findDocumentoDeIngresoEntities();
        return converter.toDTODocumentoDeIngresoList(entities);
    }

    @Override
    public ComprobanteDeIngresoDTO listarUltimosComprobantesLocatario(int mesARestar, LocatarioDTO unLocatario) {
        LocalDate fechaAuxDate;
        LocalDate fechaActualDate = LocalDate.now();
        fechaActualDate=fechaActualDate.minusMonths(mesARestar);
        ComprobanteDeIngresoDTO comprobante=null;
        for (ComprobanteDeIngreso ci : this.comprobanteDeIngresoDAO.findComprobanteDeIngresoEntities()) {
            if (ci.getUnLocatario() != null) {
                if (ci.getUnLocatario().getId().equals(unLocatario.getId())) {
                    if (ci.getMes() >= 1 && ci.getMes() <= 9) {
                        fechaAuxDate = ConverterDate.converterStringToLocalDate(ci.getAnio().toString() + "-0" + ci.getMes().toString() + "-01");
                    } else {
                        fechaAuxDate = ConverterDate.converterStringToLocalDate(ci.getAnio().toString() + "-" + ci.getMes().toString() + "-01");
                    }
                    if (fechaActualDate.getYear() == fechaAuxDate.getYear()) {
                        if (fechaActualDate.getMonthValue() == fechaAuxDate.getMonthValue()) {                            
                            comprobante = converter.toComprobanteDTO(ci);
                        }
                    }
                }
            }

        }
        return comprobante;
    }

    @Override
    public ComprobanteDeIngresoDTO listarUltimosComprobantesGarante(int mesARestar, GaranteDTO unGarante) {
        LocalDate fechaAuxDate;
        LocalDate fechaActualDate = LocalDate.now();
        fechaActualDate=fechaActualDate.minusMonths(mesARestar);
        ComprobanteDeIngresoDTO comprobante=null;
        for (ComprobanteDeIngreso ci : this.comprobanteDeIngresoDAO.findComprobanteDeIngresoEntities()) {
            if (ci.getUnGarante()!= null) {
                if (ci.getUnGarante().getId().equals(unGarante.getId())) {
                    if (ci.getMes() >= 1 && ci.getMes() <= 9) {
                        fechaAuxDate = ConverterDate.converterStringToLocalDate(ci.getAnio().toString() + "-0" + ci.getMes().toString() + "-01");
                    } else {
                        fechaAuxDate = ConverterDate.converterStringToLocalDate(ci.getAnio().toString() + "-" + ci.getMes().toString() + "-01");
                    }
                    if (fechaActualDate.getYear() == fechaAuxDate.getYear()) {
                        if (fechaActualDate.getMonthValue() == fechaAuxDate.getMonthValue()) {                            
                            comprobante = converter.toComprobanteDTO(ci);
                        }
                    }
                }
            }

        }
        return comprobante;
    }

}
