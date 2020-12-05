/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import dto.RecargoPorMoraDTO;

/**
 *
 * @author Ariel
 */
public interface IRecargoPorMoraService extends ICRUD<RecargoPorMoraDTO>{
    RecargoPorMoraDTO obtenerUltimoRecargoPorMora();
}
