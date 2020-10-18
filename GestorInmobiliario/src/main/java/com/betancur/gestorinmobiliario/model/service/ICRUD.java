/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.model.service;

import java.util.List;

/**
 *
 * @author Ariel
 * @param <T>
 */
public interface ICRUD<T> {
    void crear(T t);
    void modificar(T t);
    void eliminar(Long id);
    T listarID(Long id);
    List<T> listarTodos();
}
