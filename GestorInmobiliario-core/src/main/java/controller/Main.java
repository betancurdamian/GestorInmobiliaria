/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.LocadorDTO;
import dto.LocatarioDependienteDTO;
import dto.LocatarioEstudianteDTO;
import dto.LocatarioIndependienteDTO;
import model.service.Impl.ClienteServiceImpl;

/**
 *
 * @author Ariel
 */
public class Main {
    public static void main(String[] args) {

        ClienteServiceImpl service = new ClienteServiceImpl();
        
        LocadorDTO l = new LocadorDTO();
        LocatarioDependienteDTO ld = new LocatarioDependienteDTO();
        LocatarioIndependienteDTO li = new LocatarioIndependienteDTO();
        LocatarioEstudianteDTO le = new LocatarioEstudianteDTO();
        
        service.crear(l);
        service.crear(ld);
        service.crear(li);
        service.crear(le);
    }

}
