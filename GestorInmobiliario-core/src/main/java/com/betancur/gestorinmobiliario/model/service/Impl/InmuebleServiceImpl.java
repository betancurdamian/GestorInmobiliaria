/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.service.Impl;

import com.betancur.gestorinmobiliario.converter.InmuebleConverter;
import com.betancur.gestorinmobiliario.dto.CasaDTO;
import com.betancur.gestorinmobiliario.dto.DepartamentoDTO;
import com.betancur.gestorinmobiliario.dto.InmuebleDTO;
import com.betancur.gestorinmobiliario.dto.LocalComercialDTO;
import com.betancur.gestorinmobiliario.dto.TerrenoDTO;
import com.betancur.gestorinmobiliario.model.dao.CasaJpaController;
import com.betancur.gestorinmobiliario.model.dao.Conexion;
import com.betancur.gestorinmobiliario.model.dao.DepartamentoJpaController;
import com.betancur.gestorinmobiliario.model.dao.InmuebleJpaController;
import com.betancur.gestorinmobiliario.model.dao.LocalComercialJpaController;
import com.betancur.gestorinmobiliario.model.dao.TerrenoJpaController;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import com.betancur.gestorinmobiliario.model.entity.Casa;
import com.betancur.gestorinmobiliario.model.entity.Departamento;
import com.betancur.gestorinmobiliario.model.entity.LocalComercial;
import com.betancur.gestorinmobiliario.model.entity.Terreno;
import com.betancur.gestorinmobiliario.model.service.IInmuebleService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private final InmuebleConverter converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public InmuebleServiceImpl() {
        new Conexion();
        this.terrenoDAO = new TerrenoJpaController(Conexion.getEmf());
        this.casaDAO = new CasaJpaController(Conexion.getEmf());
        this.departamentoDAO = new DepartamentoJpaController(Conexion.getEmf());
        this.localComercialDAO = new LocalComercialJpaController(Conexion.getEmf());
        this.inmuebleDAO = new InmuebleJpaController(Conexion.getEmf());
        this.converter = new InmuebleConverter();
    }

    @Override
    public InmuebleDTO crear(InmuebleDTO dto) {
        if (dto != null) {
            if (dto instanceof TerrenoDTO) {
                Terreno entity = (Terreno) this.converter.fromDto(dto);
                this.terrenoDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof CasaDTO) {
                Casa entity = (Casa) this.converter.fromDto(dto);
                this.casaDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof DepartamentoDTO) {
                Departamento entity = (Departamento) this.converter.fromDto(dto);
                this.departamentoDAO.create(entity);
                dto.setId(entity.getId());
            }
            if (dto instanceof LocalComercialDTO) {
                LocalComercial entity = (LocalComercial) this.converter.fromDto(dto);
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
                Terreno entity = (Terreno) this.converter.fromDto(dto);
                try {
                    this.terrenoDAO.edit(entity);
                } catch (Exception ex) {
                    Logger.getLogger(InmuebleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                dto.setId(entity.getId());
            }
            if (dto instanceof CasaDTO) {
                Casa entity = (Casa) this.converter.fromDto(dto);
                try {
                    this.casaDAO.edit(entity);
                } catch (Exception ex) {
                    Logger.getLogger(InmuebleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                dto.setId(entity.getId());
            }
            if (dto instanceof DepartamentoDTO) {
                Departamento entity = (Departamento) this.converter.fromDto(dto);
                try {
                    this.departamentoDAO.edit(entity);
                } catch (Exception ex) {
                    Logger.getLogger(InmuebleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                dto.setId(entity.getId());
            }
            if (dto instanceof LocalComercialDTO) {
                LocalComercial entity = (LocalComercial) this.converter.fromDto(dto);
                try {
                    this.localComercialDAO.edit(entity);
                } catch (Exception ex) {
                    Logger.getLogger(InmuebleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                dto.setId(entity.getId());
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
        return this.converter.fromEntity(inmuebleDAO.findInmueble(id));
    }

    @Override
    public List<InmuebleDTO> listarTodos() {
        return converter.fromEntity(inmuebleDAO.findInmuebleEntities());
    }

}
