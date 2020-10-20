/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.client.controller;


import com.betancur.gestorinmobiliario.model.entity.Alquiler;
import com.betancur.gestorinmobiliario.model.service.Impl.AlquilerServiceImpl;

/**
 *
 * @author Ariel
 */
public class Main {
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
        
        AlquilerServiceImpl service = new AlquilerServiceImpl();
        
        Alquiler a = new Alquiler(null, null, null, null, true);
        service.crear(a);
    }
}
