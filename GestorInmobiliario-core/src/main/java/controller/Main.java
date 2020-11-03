/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.ComisionDTO;
import dto.LineaDeComisionDTO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import dto.LocadorDTO;
import dto.LocatarioDependienteDTO;
import dto.LocatarioEstudianteDTO;
import dto.LocatarioIndependienteDTO;
import util.ExporLocalBoletaDePago;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import model.dao.Conexion;
import model.entity.LineaDeComision;
import model.entity.Locador;
import model.service.Impl.ClienteServiceImpl;
import model.service.Impl.ComisionServiceImpl;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Ariel
 */
public class Main {

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) throws SQLException, JRException, JRException, FileNotFoundException {

        new Conexion();
//        InmuebleController inmuebleController = new InmuebleController();
//        inmuebleController.listarInmuebles();
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

//        ExporLocalBoletaDePago ex = new ExporLocalBoletaDePago();
//        ex.guardarBoletaDePagoPDF();
//        
//        ex.verBoletaDePagoPDF();
//        ex.exportarBoletaDePagoPDF();
//        
//        ClienteServiceImpl serviceCliente = new ClienteServiceImpl();
//        
//        LocadorDTO l = new LocadorDTO();
//        LocatarioDependienteDTO ld = new LocatarioDependienteDTO();
//        LocatarioIndependienteDTO lind = new LocatarioIndependienteDTO();
//        LocatarioEstudianteDTO le = new LocatarioEstudianteDTO();
//        
//        serviceCliente.crear(l);
//        serviceCliente.crear(ld);
//        serviceCliente.crear(lind);
//        serviceCliente.crear(le);
//        
//        for (LocadorDTO ll : serviceCliente.listarTodosLocadores()) {
//            System.out.println("" + ll.getId());
//        }
//        
        ComisionServiceImpl comSer = new ComisionServiceImpl();

        ComisionDTO comision = new ComisionDTO();
        comision.setCantidadDeCuotas(12);
        comision.setMontoTotal(12000f);

        LineaDeComisionDTO l1 = new LineaDeComisionDTO();
        l1.setNumeroCuota(1);
        l1.setMonto(200f);
        l1.setUnaComision(comision);

        LineaDeComisionDTO l2 = new LineaDeComisionDTO();
        l2.setNumeroCuota(2);
        l2.setMonto(200f);
        l2.setUnaComision(comision);

        comision.setLinesasDeComisiones(new ArrayList<>());
        comision.getLinesasDeComisiones().add(l1);
        comision.getLinesasDeComisiones().add(l2);

        

        System.out.println("TODOOOOO");
        for (ComisionDTO listarTodo : comSer.listarTodos()) {
            System.out.println("id: " + listarTodo.getId());
            System.out.println("total: " + listarTodo.getMontoTotal());
            System.out.println("cantidad de cuota: " + listarTodo.getCantidadDeCuotas());
            System.out.println("------------------------------");
        }
    }

}
