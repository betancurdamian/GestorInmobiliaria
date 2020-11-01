/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import dto.ClienteDTO;
import dto.LocadorDTO;
import java.util.List;

/**
 *
 * @author Ariel
 */
public interface IClienteService extends ICRUD<ClienteDTO>{
    LocadorDTO listarLocadorID(Long id);
    public List<LocadorDTO> listarTodosLocadores();
}
