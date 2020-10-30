/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import util.ExportImpl;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Ariel
 */
public class Main {

    public static void main(String[] args) throws SQLException, JRException, JRException, FileNotFoundException {

        //new Conexion();
//        InmuebleController inmuebleController = new InmuebleController();
//        
//        TerrenoDTO t1 = new TerrenoDTO();
//        
//        CasaDTO c1 = new CasaDTO();
//        
//        DepartamentoDTO d1 = new DepartamentoDTO();
//        
//        LocalComercialDTO l1 = new LocalComercialDTO();
//        
//        inmuebleController.listarID();
//
//        
//        AlquilerController alquiler = new AlquilerController();
//
//        ArancelEspecialController arancelEspecial = new ArancelEspecialController();
//
//        ArancelEspecialServicioDTO aesDTO = new ArancelEspecialServicioDTO();
//        aesDTO.setUnaFechaDeRecargo("2020-11-29");
//
//        ArancelEspecialExpensaDTO aeeDTO = new ArancelEspecialExpensaDTO();
//        aeeDTO.setUnaFechaDeRecargo("2020-11-29");
//        
//        arancelEspecial.registrar(aesDTO);
//        arancelEspecial.registrar(aeeDTO);
//
//
//        AlquilerDTO c12 = new AlquilerDTO();
//        c12.setDisponible(true);
//        c12.setUnaFechaInicio("2020-11-29");
//        c12.setUnaFechaFin("2020-11-29");
//        c12.setUnInmuebleDTO(null);
//        c12.setUnaInmobiliariaAlquilerDTO(null);
//
//        alquiler.registrar(c12);
//
//        alquiler.listarAlquileres();
//        alquiler.listarID();
//
//        BarrioController barrio = new BarrioController();
//
//        BarrioDTO b = new BarrioDTO();
//        b.setNombre("barrito");
//        b.setCodigoPostal("3300");
//
//        barrio.registrar(b);
//        barrio.listarBarrios();
// creo la conexión a la BBDD
        //ConexionBBDD conn = new ConexionBBDD();
      
        ExportImpl ex = new ExportImpl();
        ex.guardarBoletaDePagoPDF();
        
        ex.verBoletaDePagoPDF();
        ex.exportarBoletaDePagoPDF();
    }
}
