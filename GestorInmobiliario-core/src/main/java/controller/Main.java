/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.ActividadDTO;
import dto.BarrioDTO;
import dto.CasaDTO;
import dto.EstadoCivilDTO;
import dto.InmuebleDTO;
import dto.LocalidadDTO;
import dto.TipoDNIDTO;
import model.service.Impl.ActividadServiceImpl;
import model.service.Impl.EstadoCivilServiceImpl;
import model.service.Impl.InmuebleServiceImpl;
import model.service.Impl.LocalidadServiceImpl;
import model.service.Impl.TipoDNIServiceImpl;

/**
 *
 * @author Ariel
 */
public class Main {
    public static void main(String[] args) {
        
       InmuebleServiceImpl service = new InmuebleServiceImpl();
        
        
        //System.out.println("id: "+service.listarID(651l).getId());
        
        CasaDTO dto = new CasaDTO();
        
       service.crear(dto);
        
        for (InmuebleDTO l : service.listarTodos()) {
            System.out.println("id: "+l.getId());
        }
        
    }

}
