/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.AlquilerDTO;
import model.service.IAlquilerService;
import model.service.Impl.AlquilerServiceImpl;

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
            System.out.println("Contratos " + alqdto.getUnContratoAlquiler());

        }
    }
    
    public void listarID(){
        System.out.println("por id"+service.listarID(53l).getId());
    System.out.println("por id"+service.listarID(53l).getUnaFechaFin());
    }

}
