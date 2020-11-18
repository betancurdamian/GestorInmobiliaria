/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.ActividadDTO;
import dto.BarrioDTO;
import dto.CasaDTO;
import dto.ClienteDTO;
import dto.EstadoCivilDTO;
import dto.InmuebleDTO;
import dto.LocadorDTO;
import dto.LocalidadDTO;
import dto.TipoDNIDTO;
import model.service.Impl.ActividadServiceImpl;
import model.service.Impl.ClienteServiceImpl;
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
        
       ClienteServiceImpl service = new ClienteServiceImpl();
        
        
        //System.out.println("id: "+service.listarID(651l).getId());
        
        LocadorDTO dto = new LocadorDTO();
        
       service.crear(dto);
        
        for (ClienteDTO l : service.listarTodos()) {
            System.out.println("id: "+l.getId());
        }
        
    }

}
