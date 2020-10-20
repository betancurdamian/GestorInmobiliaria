/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.controller;

import com.betancur.gestorinmobiliario.dto.BarrioDTO;
import com.betancur.gestorinmobiliario.model.service.IBarrioService;
import com.betancur.gestorinmobiliario.model.service.Impl.BarrioServiceImpl;

/**
 *
 * @author Ariel
 */
public class BarrioController {
    private final IBarrioService service;

    public BarrioController() {
        service = new BarrioServiceImpl();
    }
    
    public void registrar(BarrioDTO t) {
        System.out.println("ID: "+service.crear(t).getId());
        
    }

    public void listarBarrios() {
        for (BarrioDTO barrio : service.listarTodos()) {
            System.out.println("ID: " + barrio.getId());
            System.out.println("nombre " + barrio.getNombre());

        }
    }
    
    public void listarID(){
        System.out.println("por id"+service.listarID(1l).getId());
    System.out.println("por id"+service.listarID(1l).getCodigoPostal());
    }
    
}
