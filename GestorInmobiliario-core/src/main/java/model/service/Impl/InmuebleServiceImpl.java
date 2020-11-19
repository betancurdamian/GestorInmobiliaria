/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.InmobiliariaMapper;
import dto.CasaDTO;
import dto.DepartamentoDTO;
import dto.InmuebleDTO;
import dto.LocalComercialDTO;
import dto.TerrenoDTO;
import model.dao.CasaJpaController;
import model.dao.Conexion;
import model.dao.DepartamentoJpaController;
import model.dao.InmuebleJpaController;
import model.dao.LocalComercialJpaController;
import model.dao.TerrenoJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Casa;
import model.entity.Departamento;
import model.entity.LocalComercial;
import model.entity.Terreno;
import model.service.IInmuebleService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.Inmueble;
import org.mapstruct.factory.Mappers;

public class InmuebleServiceImpl implements IInmuebleService {

    private final InmuebleJpaController inmuebleDAO;
    private final TerrenoJpaController terrenoDAO;
    private final CasaJpaController casaDAO;
    private final DepartamentoJpaController departamentoDAO;
    private final LocalComercialJpaController localComercialDAO;
    private final InmobiliariaMapper converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public InmuebleServiceImpl() {
        new Conexion();
        this.terrenoDAO = new TerrenoJpaController(Conexion.getEmf());
        this.casaDAO = new CasaJpaController(Conexion.getEmf());
        this.departamentoDAO = new DepartamentoJpaController(Conexion.getEmf());
        this.localComercialDAO = new LocalComercialJpaController(Conexion.getEmf());
        this.inmuebleDAO = new InmuebleJpaController(Conexion.getEmf());
        this.converter = Mappers.getMapper(InmobiliariaMapper.class);

    }

    @Override
    public InmuebleDTO crear(InmuebleDTO dto) {
        if (dto != null) {
            if (dto instanceof TerrenoDTO) {
                Terreno entity = converter.toTerrenoEntity((TerrenoDTO) dto);
                this.terrenoDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof CasaDTO) {
                Casa entity = converter.toCasaEntity((CasaDTO) dto);
                this.casaDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof DepartamentoDTO) {
                Departamento entity = converter.toDepartamentoEntity((DepartamentoDTO) dto);
                this.departamentoDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof LocalComercialDTO) {
                LocalComercial entity = converter.toLocalComercialEntity((LocalComercialDTO) dto);
                this.localComercialDAO.create(entity);
                dto.setId(entity.getId());
            }
        }

        return dto;
    }

    @Override
    public InmuebleDTO modificar(InmuebleDTO dto) {
        if (dto != null) {
            if (dto instanceof TerrenoDTO) {
                try {
                    Terreno entity = converter.toTerrenoEntity((TerrenoDTO) dto);
                    terrenoDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(InmuebleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (dto instanceof CasaDTO) {
                try {
                    Casa entity = converter.toCasaEntity((CasaDTO) dto);
                    casaDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(InmuebleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (dto instanceof DepartamentoDTO) {
                try {
                    Departamento entity = converter.toDepartamentoEntity((DepartamentoDTO) dto);
                    departamentoDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(InmuebleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (dto instanceof LocalComercialDTO) {
                try {
                    LocalComercial entity = converter.toLocalComercialEntity((LocalComercialDTO) dto);
                    localComercialDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(InmuebleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return dto;
    }

    @Override
    public void eliminar(Long id) {
        if (id != null) {
            if (inmuebleDAO.findInmueble(id) != null) {
                try {
                    inmuebleDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(InmuebleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }

    }

    @Override
    public InmuebleDTO listarID(Long id) {
        InmuebleDTO dto = null;
        Inmueble entity = inmuebleDAO.findInmueble(id);
        if (entity instanceof Terreno) {
            dto = converter.toTerrenoDTO((Terreno) entity);
        }
        if (entity instanceof Casa) {
            dto = converter.toCasaDTO((Casa) entity);
        }
        if (entity instanceof Departamento) {
            dto = converter.toDepartamentoDTO((Departamento) entity);
        }
        if (entity instanceof Departamento) {
            dto = converter.toDepartamentoDTO((Departamento) entity);
        }
        if (entity instanceof LocalComercial) {
            dto = converter.toLocalComercialDTO((LocalComercial) entity);
        }
        return dto;
    }

    @Override
    public List<InmuebleDTO> listarTodos() {
        List<Inmueble> entities = inmuebleDAO.findInmuebleEntities();
        return converter.toDTOInmuebleList(entities);
    }

    @Override
    public TerrenoDTO listarTerrenoID(Long id) {
        Terreno entity = terrenoDAO.findTerreno(id);
        return converter.toTerrenoDTO(entity);
    }

    @Override
    public List<TerrenoDTO> listarTodosTerrenos() {
        List<Terreno> entities = terrenoDAO.findTerrenoEntities();
        return converter.toDTOTerrenoList(entities);
    }

    @Override
    public CasaDTO listarCasaID(Long id) {
        Casa entity = casaDAO.findCasa(id);
        return converter.toCasaDTO(entity);
    }

    @Override
    public List<CasaDTO> listarTodasCasas() {
        List<Casa> entities = casaDAO.findCasaEntities();
        return converter.toDTOCasaList(entities);
    }

    @Override
    public DepartamentoDTO listarDepartamentoID(Long id) {
        Departamento entity = departamentoDAO.findDepartamento(id);
        return converter.toDepartamentoDTO(entity);
    }

    @Override
    public List<DepartamentoDTO> listarTodosDepartamentos() {
        List<Departamento> entities = departamentoDAO.findDepartamentoEntities();
        return converter.toDTODepartamentoList(entities);
    }

    @Override
    public LocalComercialDTO listarLocalComercialID(Long id) {
        LocalComercial entity = localComercialDAO.findLocalComercial(id);
        return converter.toLocalComercialDTO(entity);
    }

    @Override
    public List<LocalComercialDTO> listarTodosLocalesComerciales() {
        List<LocalComercial> entities = localComercialDAO.findLocalComercialEntities();
        return converter.toDTOLocalComercialList(entities);
    }

}
