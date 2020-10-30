/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.VentaDTO;
import model.entity.Venta;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class VentaConverter extends AbstractConverter<Venta, VentaDTO>{

    @Override
    public Venta fromDto(VentaDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VentaDTO fromEntity(Venta entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VentaDTO> fromEntity(List<Venta> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venta> fromDto(List<VentaDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }
    
}
