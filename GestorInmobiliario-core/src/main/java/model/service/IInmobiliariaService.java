/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import dto.InmobiliariaDTO;

/**
 *
 * @author Ariel
 */
public interface IInmobiliariaService extends ICRUD<InmobiliariaDTO>{
    InmobiliariaDTO obtenerPrimeraInmobiliaria();
}
