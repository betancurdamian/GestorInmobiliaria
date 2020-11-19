/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import dto.ContratoAlquilerDTO;
import dto.ContratoDTO;
import dto.ContratoVentaDTO;
import java.util.List;

/**
 *
 * @author Ariel
 */
public interface IContratoService extends ICRUD<ContratoDTO>{
    ContratoVentaDTO listarContratoVentaID(Long id);
    List<ContratoVentaDTO> listarTodosContratosVentas();
    
    ContratoAlquilerDTO listarContratoAlquilerID(Long id);
    List<ContratoAlquilerDTO> listarTodosContratosAlquileres();
}
