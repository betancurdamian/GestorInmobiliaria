/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import dto.CasaDTO;
import dto.DepartamentoDTO;
import dto.InmuebleDTO;
import dto.LocalComercialDTO;
import dto.TerrenoDTO;
import java.util.ArrayList;
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
import org.modelmapper.ModelMapper;

/**
 *
 * @author Ariel
 */
public class InmuebleServiceImpl implements IInmuebleService {

    private final InmuebleJpaController inmuebleDAO;
    private final TerrenoJpaController terrenoDAO;
    private final CasaJpaController casaDAO;
    private final DepartamentoJpaController departamentoDAO;
    private final LocalComercialJpaController localComercialDAO;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public InmuebleServiceImpl() {
        new Conexion();
        this.terrenoDAO = new TerrenoJpaController(Conexion.getEmf());
        this.casaDAO = new CasaJpaController(Conexion.getEmf());
        this.departamentoDAO = new DepartamentoJpaController(Conexion.getEmf());
        this.localComercialDAO = new LocalComercialJpaController(Conexion.getEmf());
        this.inmuebleDAO = new InmuebleJpaController(Conexion.getEmf());

    }

    @Override
    public InmuebleDTO crear(InmuebleDTO dto) {
        if (dto != null) {
            if (dto instanceof TerrenoDTO) {
                ModelMapper modelMapper = new ModelMapper();
                Terreno entity = modelMapper.map(dto, Terreno.class);
                this.terrenoDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof CasaDTO) {
                ModelMapper modelMapper = new ModelMapper();
                Casa entity = modelMapper.map(dto, Casa.class);
                this.casaDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof DepartamentoDTO) {
                ModelMapper modelMapper = new ModelMapper();
                Departamento entity = modelMapper.map(dto, Departamento.class);
                this.departamentoDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof LocalComercialDTO) {
                ModelMapper modelMapper = new ModelMapper();
                LocalComercial entity = modelMapper.map(dto, LocalComercial.class);
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
                    ModelMapper modelMapper = new ModelMapper();
                    Terreno entity = modelMapper.map(dto, Terreno.class);

                    terrenoDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(InmuebleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (dto instanceof CasaDTO) {
                try {
                    ModelMapper modelMapper = new ModelMapper();
                    Casa entity = modelMapper.map(dto, Casa.class);

                    casaDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(InmuebleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (dto instanceof DepartamentoDTO) {
                try {
                    ModelMapper modelMapper = new ModelMapper();
                    Departamento entity = modelMapper.map(dto, Departamento.class);

                    departamentoDAO.edit(entity);
                    dto.setId(entity.getId());
                } catch (Exception ex) {
                    Logger.getLogger(InmuebleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (dto instanceof LocalComercialDTO) {
                try {
                    ModelMapper modelMapper = new ModelMapper();
                    LocalComercial entity = modelMapper.map(dto, LocalComercial.class);

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
        try {
            inmuebleDAO.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(InmuebleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public InmuebleDTO listarID(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Inmueble entity = inmuebleDAO.findInmueble(id);
        InmuebleDTO dto = modelMapper.map(entity, InmuebleDTO.class);

        return dto;
    }

    @Override
    public List<InmuebleDTO> listarTodos() {
        ModelMapper modelMapper = new ModelMapper();
        InmuebleDTO dtoAux = null;
        List<InmuebleDTO> dtos = new ArrayList<>();

        for (Inmueble entitiy : inmuebleDAO.findInmuebleEntities()) {
            dtoAux = modelMapper.map(entitiy, InmuebleDTO.class);
            dtos.add(dtoAux);
        }

        return dtos;
    }

}
