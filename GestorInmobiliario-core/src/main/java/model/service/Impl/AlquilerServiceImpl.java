/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl;

import converter.AlquilerConverter;
import converter.ContratoConverter;
import converter.InmobiliariaConverter;
import converter.InmuebleConverter;
import dto.AlquilerDTO;
import model.dao.AlquilerJpaController;
import model.dao.Conexion;
import model.dao.ContratoAlquilerJpaController;
import model.dao.InmobiliariaJpaController;
import model.dao.InmuebleJpaController;
import model.dao.exceptions.NonexistentEntityException;
import model.entity.Alquiler;
import model.service.IAlquilerService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ariel
 */
public class AlquilerServiceImpl implements IAlquilerService {

    private final AlquilerJpaController alquilerDAO;
    private final InmobiliariaJpaController inmobiliariaDAO;
    private final InmuebleJpaController inmuebleDAO;
    private final ContratoAlquilerJpaController contratoAlquilerDAO;

    private final AlquilerConverter converter;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public AlquilerServiceImpl() {
        new Conexion();
        this.alquilerDAO = new AlquilerJpaController(Conexion.getEmf());
        this.inmobiliariaDAO = new InmobiliariaJpaController(Conexion.getEmf());
        this.inmuebleDAO = new InmuebleJpaController(Conexion.getEmf());
        this.contratoAlquilerDAO = new ContratoAlquilerJpaController(Conexion.getEmf());

        this.converter = new AlquilerConverter();
    }

    @Override
    public AlquilerDTO crear(AlquilerDTO dto) {
        if (dto != null) {
            Alquiler entity = this.converter.fromDto(dto);

            if (dto.getUnInmuebleDTO() != null) {
                InmuebleConverter converterInmueble = new InmuebleConverter();
                entity.setUnInmuebleAlquiler(inmuebleDAO.findInmueble(converterInmueble.fromDto(dto.getUnInmuebleDTO()).getId()));
            }
            if (dto.getUnaInmobiliariaAlquilerDTO() != null) {
                InmobiliariaConverter converterInmobiliaria = new InmobiliariaConverter();
                entity.setUnaInmobiliariaAlquiler(inmobiliariaDAO.findInmobiliaria(converterInmobiliaria.fromDto(dto.getUnaInmobiliariaAlquilerDTO()).getId()));
            }

            if (dto.getUnContratoAlquilerDTO() != null) {
                ContratoConverter converterContratoAlquiler = new ContratoConverter();
                entity.setUnContratoAlquiler(contratoAlquilerDAO.findContratoAlquiler(converterContratoAlquiler.fromDto(dto.getUnContratoAlquilerDTO()).getId()));
            }
            this.alquilerDAO.create(entity);
            dto.setId(entity.getId());
        } else {
            System.out.println("El DTO es null");
        }
        return dto;
    }

    @Override
    public AlquilerDTO modificar(AlquilerDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                Alquiler entity = this.converter.fromDto(dto);
                try {
                    alquilerDAO.edit(entity);
                } catch (Exception ex) {
                    Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            if (alquilerDAO.findAlquiler(id) != null) {
                try {
                    alquilerDAO.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("NO EXIST Entity to Delete");
            }
        } else {
            System.out.println("ID is null");
        }
    }

    @Override
    public AlquilerDTO listarID(Long id) {
        Alquiler entity = alquilerDAO.findAlquiler(id);
        return this.converter.fromEntity(entity);
    }

    @Override
    public List<AlquilerDTO> listarTodos() {
        List<Alquiler> entities = alquilerDAO.findAlquilerEntities();
        return this.converter.fromEntity(entities);
    }

}
