/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ComprobanteDeIngresoDTO;
import model.entity.ComprobanteDeIngreso;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ComprobanteDeIngresoConverter extends AbstractConverter<ComprobanteDeIngreso, ComprobanteDeIngresoDTO> {

    @Override
    public ComprobanteDeIngreso fromDto(ComprobanteDeIngresoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComprobanteDeIngresoDTO fromEntity(ComprobanteDeIngreso entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ComprobanteDeIngresoDTO> fromEntity(List<ComprobanteDeIngreso> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ComprobanteDeIngreso> fromDto(List<ComprobanteDeIngresoDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
