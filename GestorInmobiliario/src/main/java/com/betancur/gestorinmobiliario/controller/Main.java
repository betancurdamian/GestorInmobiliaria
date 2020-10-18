/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.controller;

import com.betancur.gestorinmobiliario.model.Alquiler;
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
        
        Alquiler c1 = new Alquiler(null, null, null, null, true);
        
        alquiler.registrar(c1);
    }
}
