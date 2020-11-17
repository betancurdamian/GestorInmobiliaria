/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.ActividadDTO;
import model.service.Impl.ActividadServiceImpl;

/**
 *
 * @author Ariel
 */
public class Main {
    public static void main(String[] args) {
        
        ActividadServiceImpl service = new ActividadServiceImpl();
        
        
        System.out.println("id: "+service.listarID(651l).getId());
        
        ActividadDTO dto = new ActividadDTO();
        dto.setNombre("prosti");
        
       service.crear(dto);
        
        for (ActividadDTO l : service.listarTodos()) {
            System.out.println("id: "+l.getId());
            System.out.println("nombre: "+l.getNombre());
        }
        
    }

}
