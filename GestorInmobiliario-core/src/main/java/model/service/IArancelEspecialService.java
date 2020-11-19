/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import dto.ArancelEspecialDTO;
import dto.ArancelEspecialExpensaDTO;
import dto.ArancelEspecialServicioDTO;
import java.util.List;

/**
 *
 * @author Ariel
 */
public interface IArancelEspecialService extends ICRUD<ArancelEspecialDTO>{
    ArancelEspecialExpensaDTO listarArancelEspecialExpensaID(Long id);
    List<ArancelEspecialExpensaDTO> listarTodosArancelEspecialesExpensas();
    
    ArancelEspecialServicioDTO listarArancelEspecialServicioID(Long id);
    List<ArancelEspecialServicioDTO> listarTodosArancelEspecialesServicios();
}
