/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.controller;

import com.betancur.gestorinmobiliario.dto.AlquilerDTO;
import com.betancur.gestorinmobiliario.dto.ArancelEspecialExpensaDTO;
import com.betancur.gestorinmobiliario.dto.ArancelEspecialServicioDTO;
import com.betancur.gestorinmobiliario.dto.BarrioDTO;
import com.betancur.gestorinmobiliario.dto.CasaDTO;
import com.betancur.gestorinmobiliario.dto.DepartamentoDTO;
import com.betancur.gestorinmobiliario.dto.LocalComercialDTO;
import com.betancur.gestorinmobiliario.dto.TerrenoDTO;
import com.betancur.gestorinmobiliario.model.dao.Conexion;

/**
 *
 * @author Ariel
 */
public class Main {

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
        new Conexion();
        InmuebleController inmuebleController = new InmuebleController();
        
        TerrenoDTO t1 = new TerrenoDTO();
        
        CasaDTO c1 = new CasaDTO();
        
        DepartamentoDTO d1 = new DepartamentoDTO();
        
        LocalComercialDTO l1 = new LocalComercialDTO();
        
        inmuebleController.listarID();
        
        
//        AlquilerController alquiler = new AlquilerController();
//
//        ArancelEspecialController arancelEspecial = new ArancelEspecialController();
//
//        ArancelEspecialServicioDTO aesDTO = new ArancelEspecialServicioDTO();
//        aesDTO.setUnaFechaDeRecargo("2020-11-29");
//
//        ArancelEspecialExpensaDTO aeeDTO = new ArancelEspecialExpensaDTO();
//        aeeDTO.setUnaFechaDeRecargo("2020-11-29");
//        
//        arancelEspecial.registrar(aesDTO);
//        arancelEspecial.registrar(aeeDTO);
//        
//
//        AlquilerDTO c1 = new AlquilerDTO();
//        c1.setDisponible(true);
//        c1.setUnaFechaInicio("2020-11-29");
//        c1.setUnaFechaFin("2020-11-29");
//        c1.setUnInmuebleDTO(null);
//        c1.setUnaInmobiliariaAlquilerDTO(null);
//
//        alquiler.registrar(c1);
//
//        alquiler.listarAlquileres();
//        alquiler.listarID();
//
//        BarrioController barrio = new BarrioController();
//
//        BarrioDTO b = new BarrioDTO();
//        b.setNombre("barrito");
//        b.setCodigoPostal("3300");
//
//        barrio.registrar(b);
//        barrio.listarBarrios();
    }
}
