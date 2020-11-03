/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import dto.ClienteDTO;
import dto.LocadorDTO;
import dto.LocatarioDTO;
import dto.LocatarioDependienteDTO;
import dto.LocatarioEstudianteDTO;
import dto.LocatarioIndependienteDTO;
import java.util.List;

/**
 *
 * @author Ariel
 */
public interface IClienteService extends ICRUD<ClienteDTO>{
    LocadorDTO listarLocadorID(Long id);
    List<LocadorDTO> listarTodosLocadores();
    
    LocatarioDTO listarLocatarioID(Long id);
    List<LocatarioDTO> listarTodosLocatarios();
    
    LocatarioDependienteDTO listarLocatarioDependienteID(Long id);
    List<LocatarioDependienteDTO> listarTodosLocatariosDependientes();
    
    LocatarioIndependienteDTO listarLocatarioIndependienteID(Long id);
    List<LocatarioIndependienteDTO> listarTodosLocatariosIndependientes();
    
    LocatarioEstudianteDTO listarLocatarioEstudianteID(Long id);
    List<LocatarioEstudianteDTO> listarTodosLocatariosEstudiantes();
}
