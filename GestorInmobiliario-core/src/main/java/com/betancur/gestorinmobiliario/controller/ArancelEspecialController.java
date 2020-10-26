/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.controller;

import com.betancur.gestorinmobiliario.dto.ArancelEspecialDTO;
import com.betancur.gestorinmobiliario.model.service.IArancelEspecialService;
import com.betancur.gestorinmobiliario.model.service.Impl.ArancelEspecialServiceImpl;

/**
 *
 * @author Ariel
 */
public class ArancelEspecialController {
    private final IArancelEspecialService service;

    public ArancelEspecialController() {
        service = new ArancelEspecialServiceImpl();
    }

    

    public void registrar(ArancelEspecialDTO t) {
        System.out.println("ID: "+service.crear(t).getId());
        
    }
}
