/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import dto.UsuarioClienteDTO;
import dto.UsuarioDTO;
import dto.UsuarioEmpresaDTO;
import java.util.List;

/**
 *
 * @author Ariel
 */
public interface IUsuarioService extends ICRUD<UsuarioDTO>{
    UsuarioEmpresaDTO listarUsuarioEmpresaID(Long id);
    List<UsuarioEmpresaDTO> listarTodosUsuariosEmpresas();
    
    UsuarioClienteDTO listarUsuarioClienteID(Long id);
    List<UsuarioClienteDTO> listarTodosUsuariosClientes();
}
