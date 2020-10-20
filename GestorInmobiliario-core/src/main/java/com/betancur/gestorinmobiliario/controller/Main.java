/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.controller;

import com.betancur.gestorinmobiliario.dto.AlquilerDTO;
import com.betancur.gestorinmobiliario.dto.BarrioDTO;
import com.betancur.gestorinmobiliario.model.dao.Conexion;

/**
 *
 * @author Ariel
 */
public class Main {

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
        new Conexion();
        AlquilerController alquiler = new AlquilerController();

        AlquilerDTO c1 = new AlquilerDTO();
        c1.setDisponible(true);
        c1.setUnaFechaInicio("2020-11-29");
        c1.setUnaFechaFin("2020-11-29");
        c1.setUnInmuebleID(null);
        c1.setUnaInmobiliariaAlquilerID(null);

        alquiler.registrar(c1);

        alquiler.listarAlquileres();
        alquiler.listarID();

        BarrioController barrio = new BarrioController();
        
        BarrioDTO b = new BarrioDTO();
        b.setNombre("barrito");
        b.setCodigoPostal("3300");
        
        barrio.registrar(b);
        barrio.listarBarrios();
    }
}
