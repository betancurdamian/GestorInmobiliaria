/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import dto.AlquilerDTO;
import dto.ClienteDTO;
import java.util.List;


/**
 *
 * @author Ariel
 */
public interface IAlquilerService extends ICRUD<AlquilerDTO>{
    List<AlquilerDTO> listarAlquileresDeCliente(ClienteDTO unLocatario);
}
