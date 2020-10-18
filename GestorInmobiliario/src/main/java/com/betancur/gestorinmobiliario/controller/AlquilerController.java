/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.controller;

import com.betancur.gestorinmobiliario.model.Alquiler;
import com.betancur.gestorinmobiliario.model.service.Impl.AlquilerServiceImpl;

/**
 *
 * @author Ariel
 */
public class AlquilerController {

    private AlquilerServiceImpl service;

    public AlquilerController() {
        service = new AlquilerServiceImpl();
    }
    
    public void registrar(Alquiler t) {        
        service.crear(t);
    }

}
