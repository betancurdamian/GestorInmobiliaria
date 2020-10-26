/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.controller;

import com.betancur.gestorinmobiliario.dto.AlquilerDTO;
import com.betancur.gestorinmobiliario.model.service.IAlquilerService;
import com.betancur.gestorinmobiliario.model.service.Impl.AlquilerServiceImpl;

/**
 *
 * @author Ariel
 */
public class AlquilerController {

    private final IAlquilerService service;

    public AlquilerController() {
        service = new AlquilerServiceImpl();
    }

    public void registrar(AlquilerDTO t) {
        System.out.println("ID: "+service.crear(t).getId());
        
    }

    public void listarAlquileres() {
        for (AlquilerDTO alqdto : service.listarTodos()) {
            System.out.println("ID: " + alqdto.getId());
            System.out.println("Contratos " + alqdto.getUnContratoAlquilerDTO());

        }
    }
    
    public void listarID(){
        System.out.println("por id"+service.listarID(3l).getId());
    System.out.println("por id"+service.listarID(3l).getUnaFechaFin());
    }

}
