/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import dto.ClienteDTO;
import dto.VentaDTO;
import java.util.List;

/**
 *
 * @author Ariel
 */
public interface IVentaService extends ICRUD<VentaDTO>{
    List<VentaDTO> listarVentasDeCliente(ClienteDTO unLocatario);
}
