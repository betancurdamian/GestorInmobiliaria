/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.RecargoPorMoraDTO;
import model.entity.RecargoPorMora;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class RecargoPorMoraConverter extends AbstractConverter<RecargoPorMora, RecargoPorMoraDTO> {

    @Override
    public RecargoPorMora fromDto(RecargoPorMoraDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecargoPorMoraDTO fromEntity(RecargoPorMora entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecargoPorMoraDTO> fromEntity(List<RecargoPorMora> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecargoPorMora> fromDto(List<RecargoPorMoraDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
