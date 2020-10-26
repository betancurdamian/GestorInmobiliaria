/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.PersonaDTO;
import com.betancur.gestorinmobiliario.model.entity.Persona;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class PersonaConverter extends AbstractConverter<Persona, PersonaDTO> {

    @Override
    public Persona fromDto(PersonaDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonaDTO fromEntity(Persona entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PersonaDTO> fromEntity(List<Persona> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Persona> fromDto(List<PersonaDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
