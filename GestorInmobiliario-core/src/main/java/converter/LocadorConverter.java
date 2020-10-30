/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.LocadorDTO;
import model.entity.Locador;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class LocadorConverter extends AbstractConverter<Locador, LocadorDTO> {

    @Override
    public Locador fromDto(LocadorDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocadorDTO fromEntity(Locador entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LocadorDTO> fromEntity(List<Locador> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Locador> fromDto(List<LocadorDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
