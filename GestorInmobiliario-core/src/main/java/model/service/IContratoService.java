/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import dto.AlquilerDTO;
import dto.ContratoAlquilerDTO;
import dto.ContratoDTO;
import dto.ContratoVentaDTO;
import dto.VentaDTO;
import java.util.List;

/**
 *
 * @author Ariel
 */
public interface IContratoService extends ICRUD<ContratoDTO>{
    ContratoVentaDTO listarContratoVentaID(Long id);
    ContratoVentaDTO ObtenerContratoDeUnaVenta(VentaDTO unaVenta);
    List<ContratoVentaDTO> listarTodosContratosVentas();
    
     ContratoAlquilerDTO listarContratoAlquilerID(Long id);
    ContratoAlquilerDTO ObtenerContratoDeUnAlquiler(AlquilerDTO unAlquiler);
    List<ContratoAlquilerDTO> listarTodosContratosAlquileres();
}
