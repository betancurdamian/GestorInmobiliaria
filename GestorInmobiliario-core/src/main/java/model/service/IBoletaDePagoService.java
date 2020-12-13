/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import dto.BoletaDePagoDTO;
import dto.ContratoDTO;
import java.util.List;

/**
 *
 * @author Ariel
 */
public interface IBoletaDePagoService extends ICRUD<BoletaDePagoDTO>{
     List<BoletaDePagoDTO> listarBoletasDeContrato(ContratoDTO unContrato);
}
