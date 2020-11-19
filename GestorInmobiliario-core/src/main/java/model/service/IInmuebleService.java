/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import dto.CasaDTO;
import dto.DepartamentoDTO;
import dto.InmuebleDTO;
import dto.LocalComercialDTO;
import dto.TerrenoDTO;
import java.util.List;

/**
 *
 * @author Ariel
 */
public interface IInmuebleService extends ICRUD<InmuebleDTO>{
    TerrenoDTO listarTerrenoID(Long id);
    List<TerrenoDTO> listarTodosTerrenos();
    
    CasaDTO listarCasaID(Long id);
    List<CasaDTO> listarTodasCasas();
    
    DepartamentoDTO listarDepartamentoID(Long id);
    List<DepartamentoDTO> listarTodosDepartamentos();
    
    LocalComercialDTO listarLocalComercialID(Long id);
    List<LocalComercialDTO> listarTodosLocalesComerciales();
}
