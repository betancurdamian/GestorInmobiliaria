/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.CasaDTO;
import dto.ClienteDTO;
import dto.InmuebleDTO;
import dto.LocadorDTO;
import dto.LocatarioDTO;
import dto.LocatarioDependienteDTO;
import model.service.Impl.InmuebleServiceImpl;
import model.service.Impl.facade.ProcesarVenta;



/**
 *
 * @author Ariel
 */
public class Main {
    public static void main(String[] args) {
        
       ProcesarVenta procesarVenta = new ProcesarVenta();
        
       procesarVenta.crearNuevaVenta();
       
       //procesarVenta.listarLocatarios();
       
//        for (CasaDTO i : procesarVenta.listarInmuebles()) {
//            System.out.println(""+i.getId());
//        }
       
        
        LocatarioDependienteDTO unLocatarioDependiente = new LocatarioDependienteDTO();
        unLocatarioDependiente.setId(21l);
        
        procesarVenta.agregarLocatario(unLocatarioDependiente);
//        
//        for (InmuebleDTO inmueble : procesarVenta.listarInmuebles()) {
//            System.out.println(""+inmueble.getId());
//        }
        
//        CasaDTO unInmueble = new CasaDTO();
//        unInmueble.setId(1l);
//        
//        procesarVenta.agregarInmueble(unInmueble);
        
       
       
    }

}
