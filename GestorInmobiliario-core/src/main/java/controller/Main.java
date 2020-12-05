/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.ArancelEspecialDTO;
import dto.ArancelEspecialExpensaDTO;
import dto.ArancelEspecialServicioDTO;
import dto.CasaDTO;
import dto.ClienteDTO;
import dto.CuotaVentaDTO;
import dto.InmuebleDTO;
import dto.LineaDeComisionDTO;
import dto.LocatarioDTO;
import dto.RecargoPorMoraDTO;
import model.service.Impl.ArancelEspecialServiceImpl;
import model.service.Impl.RecargoPorMoraServiceImpl;
import model.service.Impl.facade.ProcesarVenta;

/**
 *
 * @author Ariel
 */
public class Main {

    public static void main(String[] args) {

        ProcesarVenta ventaService = new ProcesarVenta();

        ventaService.crearNuevaVenta("2020-12-03");
        //InmuebleServiceImpl service = new InmuebleServiceImpl();
        //service.listarTodasCasas();
        
        ClienteDTO clienteCompradorAux = null;
        for (ClienteDTO l : ventaService.listarClientes()) {
//            System.out.println(""+l.getId());
            if (l.getId()==21l) {
                clienteCompradorAux = l;
            }
        }
        
        ventaService.seleccionarClienteComprador(clienteCompradorAux);
        
        
        CasaDTO casaAux = null;
        for (InmuebleDTO i : ventaService.listarInmueblesDisponibles()) {
//            System.out.println(""+i.getId());
            if (i.getId()==1l) {
                casaAux = (CasaDTO) i;
            }
        }        
        
        ventaService.crearContrato(0, 0);
        
        ventaService.agregarInmueble(casaAux);        
        
        ventaService.agregarCuotas(0);
        
        ventaService.agregarComision(10000f, 10);
        
        
        System.out.println("------------VENTA------------");
        System.out.println("fecha: "+ ventaService.getNuevaVenta().getUnaFechaVenta());
        System.out.println("Es Completa: "+ ventaService.getNuevaVenta().getCompleta());
        
        System.out.println("------------Locatario------------");
        System.out.println("id: "+ ventaService.getNuevaVenta().getUnContratoVenta().getUnLocatario().getId());
        
        System.out.println("------------Inmueble------------");
        System.out.println("id: "+ ventaService.getNuevaVenta().getUnInmuebleVenta().getId());
        
        System.out.println("------------Locador------------");
        System.out.println("id: "+ ventaService.getNuevaVenta().getUnContratoVenta().getUnLocador().getId());
        
        System.out.println("------------Comision------------");
        System.out.println("monto Total: "+ ventaService.getNuevaVenta().getUnContratoVenta().getUnaComision().getMontoTotal());
        System.out.println("cantidad De Cuotas: "+ ventaService.getNuevaVenta().getUnContratoVenta().getUnaComision().getCantidadDeCuotas());
        
        System.out.println("------------Lineas de Comision------------");
        for (LineaDeComisionDTO lc : ventaService.getNuevaVenta().getUnContratoVenta().getUnaComision().getLineasDeComisiones()) {
            System.out.println("numero Cuota: "+lc.getNumeroCuota());
            System.out.println("monto Cuota: "+lc.getMonto());
            System.out.println("---------------------------------");
        }
        
        System.out.println("------------Cuota Venta------------");
        for (CuotaVentaDTO cv : ventaService.getNuevaVenta().getUnContratoVenta().getCuotasVenta()) {
            System.out.println("numero Cuota: "+cv.getNumeroCuota());
            System.out.println("monto Cuota: "+cv.getMontoCuota());
            System.out.println("---------------------------------");
        }
        
        System.out.println("------------Aranceles Especiales------------");
        for (ArancelEspecialDTO cv : ventaService.listarArancelesEspeciales()) {
            System.out.println("id : "+cv.getId());
            System.out.println("Descripcion: "+cv.getDescripcion());
            System.out.println("---------------------------------");
        }
        
        ventaService.agregarArancelesEspeciales(ventaService.listarArancelesEspeciales());
        
        ventaService.finalizarVenta();
        
        
       
    }

}
