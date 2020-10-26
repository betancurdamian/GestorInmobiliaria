/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.ContratoVentaDTO;
import com.betancur.gestorinmobiliario.model.entity.ContratoVenta;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ContratoVentaConverter extends AbstractConverter<ContratoVenta, ContratoVentaDTO>{

    @Override
    public ContratoVenta fromDto(ContratoVentaDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ContratoVentaDTO fromEntity(ContratoVenta entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ContratoVentaDTO> fromEntity(List<ContratoVenta> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ContratoVenta> fromDto(List<ContratoVentaDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }
    
}
