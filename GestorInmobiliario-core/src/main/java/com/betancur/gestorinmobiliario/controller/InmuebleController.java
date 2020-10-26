/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.controller;

import com.betancur.gestorinmobiliario.dto.CasaDTO;
import com.betancur.gestorinmobiliario.dto.DepartamentoDTO;
import com.betancur.gestorinmobiliario.dto.InmuebleDTO;
import com.betancur.gestorinmobiliario.dto.LocalComercialDTO;
import com.betancur.gestorinmobiliario.dto.TerrenoDTO;
import com.betancur.gestorinmobiliario.model.entity.Casa;
import com.betancur.gestorinmobiliario.model.entity.Departamento;
import com.betancur.gestorinmobiliario.model.entity.LocalComercial;
import com.betancur.gestorinmobiliario.model.entity.Terreno;
import com.betancur.gestorinmobiliario.model.service.IInmuebleService;
import com.betancur.gestorinmobiliario.model.service.Impl.InmuebleServiceImpl;

/**
 *
 * @author Ariel
 */
public class InmuebleController {

    private final IInmuebleService service;

    public InmuebleController() {
        service = new InmuebleServiceImpl();
    }

    public void registrar(InmuebleDTO t) {
        System.out.println("ID: " + service.crear(t).getId());

    }

    public void listarInmuebles() {
        for (InmuebleDTO listarTodo : service.listarTodos()) {
            if (listarTodo instanceof TerrenoDTO) {
                System.out.println(""+((TerrenoDTO) listarTodo).getAncho());
            }
            if (listarTodo instanceof CasaDTO) {
                
            }
            if (listarTodo instanceof DepartamentoDTO) {
               
            }
            if (listarTodo instanceof LocalComercialDTO) {
                
            }
        }
        
    }

    public void listarID() {
        System.out.println("por id" + service.listarID(3l).getId());
    }

}
