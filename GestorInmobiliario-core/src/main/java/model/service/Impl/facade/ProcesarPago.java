/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl.facade;

import dto.AlquilerDTO;
import dto.BoletaDePagoDTO;
import dto.ClienteDTO;
import dto.VentaDTO;
import java.util.ArrayList;
import java.util.List;
import model.service.Impl.AlquilerServiceImpl;
import model.service.Impl.BoletaDePagoServiceImpl;
import model.service.Impl.ContratoServiceImpl;
import model.service.Impl.VentaServiceImpl;

/**
 *
 * @author Ariel
 */
public class ProcesarPago {

    private final BoletaDePagoServiceImpl boletaDePagoService;
    private final VentaServiceImpl ventaService;
    private final AlquilerServiceImpl alquilerService;
    private final ContratoServiceImpl contratoService;

    public ProcesarPago() {
        this.ventaService = new VentaServiceImpl();
        this.alquilerService = new AlquilerServiceImpl();
        this.contratoService = new ContratoServiceImpl();
        this.boletaDePagoService = new BoletaDePagoServiceImpl();
    }

    public List<BoletaDePagoDTO> buscarBoletasDePagoVenta(ClienteDTO unLocatario) {
        List<BoletaDePagoDTO> boletasDePago = new ArrayList<>();
        if (unLocatario != null && unLocatario.getId() > 0) {
            for (VentaDTO v : ventaService.listarVentasDeCliente(unLocatario)) {
                for (BoletaDePagoDTO bp : boletaDePagoService.listarBoletasDeContrato(contratoService.ObtenerContratoDeUnaVenta(v))) {
                    boletasDePago.add(bp);
                }
            }
        }
        return boletasDePago;
    }

    public List<BoletaDePagoDTO> buscarBoletasDePagoAlquiler(ClienteDTO unLocatario) {
        List<BoletaDePagoDTO> boletasDePago = new ArrayList<>();
        if (unLocatario != null && unLocatario.getId() > 0) {
            for (AlquilerDTO a : alquilerService.listarAlquileresDeCliente(unLocatario)) {
                for (BoletaDePagoDTO bp : boletaDePagoService.listarBoletasDeContrato(contratoService.ObtenerContratoDeUnAlquiler(a))) {
                    boletasDePago.add(bp);
                }
            }
        }
        return boletasDePago;
    }

    public BoletaDePagoDTO crearBoletaDePagoDeVenta(VentaDTO unaVenta) {
        BoletaDePagoDTO nuevaBoletaDePago = null;
        if (unaVenta != null) {
            if (!unaVenta.getCompleta()) {
                if (boletaDePagoService.listarBoletasDeContrato(contratoService.ObtenerContratoDeUnaVenta(unaVenta)).isEmpty()) {
                    System.out.println("Primera Boleta de pago");
                    nuevaBoletaDePago = new BoletaDePagoDTO();
                    nuevaBoletaDePago.setNumeroBoleta("1");
                    nuevaBoletaDePago.setUnContrato(contratoService.ObtenerContratoDeUnaVenta(unaVenta));
                } else {
                    System.out.println("Crear siguiente boleta");
                }

            }
        }
        return nuevaBoletaDePago;
    }
    
    public BoletaDePagoDTO pagarBoletaDePago(BoletaDePagoDTO unaBoletaDePago){
        return boletaDePagoService.crear(unaBoletaDePago);
    }
}
