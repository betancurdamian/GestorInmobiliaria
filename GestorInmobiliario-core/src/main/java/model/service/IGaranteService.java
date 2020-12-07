/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import dto.GaranteDTO;
import dto.GaranteDependienteDTO;
import dto.GaranteIndependienteDTO;
import java.util.List;

/**
 *
 * @author Ariel
 */
public interface IGaranteService extends ICRUD<GaranteDTO>{
    GaranteDependienteDTO listarGaranteDependienteID(Long id);
    List<GaranteDependienteDTO> listarTodosGarantesDependientes();
    
    GaranteIndependienteDTO listarGaranteIndependienteID(Long id);
    List<GaranteIndependienteDTO> listarTodosGarantesIndependientes();
    
    GaranteDTO verGaranteDelLocatario(Long id);
}
