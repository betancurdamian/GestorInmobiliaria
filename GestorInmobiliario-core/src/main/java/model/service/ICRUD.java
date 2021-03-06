/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.util.List;

/**
 *
 * @author Ariel
 * @param <DTO>
 */
public interface ICRUD<DTO> {
    DTO crear(DTO dto);
    DTO modificar(DTO dto);
    void eliminar(Long id);
    DTO listarID(Long id);
    List<DTO> listarTodos();
}
