/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.CasaDTO;
import dto.InmuebleDTO;
import dto.LocadorDTO;
import dto.LocatarioDTO;
import dto.LocatarioDependienteDTO;
import model.service.Impl.facade.ProcesarVenta;



/**
 *
 * @author Ariel
 */
public class Main {
    public static void main(String[] args) {
        
       ProcesarVenta procesarVenta = new ProcesarVenta();
        
       procesarVenta.crearNuevaVenta();
       
        for (LocatarioDTO locatario : procesarVenta.listarLocatarios()) {
            System.out.println(""+locatario.getId());
        }
        
        LocatarioDependienteDTO unLocatarioDependiente = new LocatarioDependienteDTO();
        unLocatarioDependiente.setId(251l);
        
        procesarVenta.agregarLocatario(unLocador);
        
        for (InmuebleDTO inmueble : procesarVenta.listarInmuebles()) {
            System.out.println(""+inmueble.getId());
        }
        
        CasaDTO unInmueble = new CasaDTO();
        unInmueble.setId(1901l);
        
        procesarVenta.agregarInmueble(unInmueble);
        
        
       
    }

}
