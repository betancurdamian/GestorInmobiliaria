/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.InmobiliariaDTO;
import model.entity.Inmobiliaria;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class InmobiliariaConverter extends AbstractConverter<Inmobiliaria, InmobiliariaDTO> {

    @Override
    public Inmobiliaria fromDto(InmobiliariaDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InmobiliariaDTO fromEntity(Inmobiliaria entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InmobiliariaDTO> fromEntity(List<Inmobiliaria> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Inmobiliaria> fromDto(List<InmobiliariaDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
