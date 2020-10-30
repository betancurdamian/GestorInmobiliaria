/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.CuotaVentaDTO;
import model.entity.CuotaVenta;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class CuotaVentaConverter extends AbstractConverter<CuotaVenta, CuotaVentaDTO> {

    @Override
    public CuotaVenta fromDto(CuotaVentaDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CuotaVentaDTO fromEntity(CuotaVenta entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CuotaVentaDTO> fromEntity(List<CuotaVenta> entities) {
        return super.fromEntity(entities); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CuotaVenta> fromDto(List<CuotaVentaDTO> dtos) {
        return super.fromDto(dtos); //To change body of generated methods, choose Tools | Templates.
    }

}
