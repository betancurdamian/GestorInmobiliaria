/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.service.Impl;

import com.betancur.gestorinmobiliario.model.Alquiler;
import com.betancur.gestorinmobiliario.model.dao.AlquilerJpaController;
import com.betancur.gestorinmobiliario.model.dao.Conexion;
import com.betancur.gestorinmobiliario.model.dao.exceptions.NonexistentEntityException;
import com.betancur.gestorinmobiliario.model.service.IAlquilerService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ariel
 */
public class AlquilerServiceImpl implements IAlquilerService{

    private final AlquilerJpaController AlquilerDAO;

    public AlquilerServiceImpl() {
        this.AlquilerDAO = new AlquilerJpaController(Conexion.getEmf());
    }

    @Override
    public void crear(Alquiler t) {
        AlquilerDAO.create(t);
    }

    @Override
    public void modificar(Alquiler t) {
        try {
            AlquilerDAO.edit(t);
        } catch (Exception ex) {
            Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            AlquilerDAO.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AlquilerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Alquiler listarID(Long id) {
        return AlquilerDAO.findAlquiler(id);
    }

    @Override
    public List<Alquiler> listarTodos() {
        return AlquilerDAO.findAlquilerEntities();
    }
    
    
    
    
}
