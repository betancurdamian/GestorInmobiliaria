/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import dto.ComprobanteDeIngresoDTO;
import dto.ComprobanteMonotributoDTO;
import dto.DocumentoDeIngresoDTO;
import dto.ReciboDeSueldoDTO;
import java.util.List;

/**
 *
 * @author Ariel
 */
public interface IComprobanteDeIngresoService extends ICRUD<ComprobanteDeIngresoDTO>{
     
    ReciboDeSueldoDTO listarReciboDeSueldoID(Long id);
    List<ReciboDeSueldoDTO> listarTodosReciboDeSueldos();
    
    ComprobanteMonotributoDTO listarComprobanteMonotributoID(Long id);
    List<ComprobanteMonotributoDTO> listarTodosComprobanteMonotributos();
    
    DocumentoDeIngresoDTO listarDocumentoDeIngresoID(Long id);
    List<DocumentoDeIngresoDTO> listarTodosDocumentoDeIngresos();
}
