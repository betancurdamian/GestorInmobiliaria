/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ContratoVentaDTO;
import dto.CuotaVentaDTO;
import model.entity.CuotaVenta;
import java.util.List;
import model.entity.ContratoVenta;

/**
 *
 * @author Ariel
 */
public class CuotaVentaConverter extends AbstractConverter<CuotaVenta, CuotaVentaDTO> {

    @Override
    public CuotaVenta fromDto(CuotaVentaDTO dto) {
        CuotaVenta entity = null;
        if (dto != null) {
            entity = new CuotaVenta();
            if (dto.getId() != null) {
                entity.setId(dto.getId());
            }
            if (dto.getNumeroCuota() != null) {
                entity.setNumeroCuota(dto.getNumeroCuota());
            }
            if (dto.getMontoCuota() != null) {
                entity.setMontoCuota(dto.getMontoCuota());
            }
            if (dto.getUnContratoVentaDTO() != null) {
                ContratoConverter converter = new ContratoConverter();
                entity.setUnContratoVenta((ContratoVenta) converter.fromDto(dto.getUnContratoVentaDTO()));
            }
            return entity;
        } else {
            return entity;
        }
    }

    @Override
    public CuotaVentaDTO fromEntity(CuotaVenta entity) {
        CuotaVentaDTO dto = null;
        if (entity != null) {
            dto = new CuotaVentaDTO();
            
            if (entity.getId() != null) {
                dto.setId(entity.getId());
            }
            if (entity.getNumeroCuota() != null) {
                dto.setNumeroCuota(entity.getNumeroCuota());
            }
            if (entity.getMontoCuota() != null) {
                dto.setMontoCuota(entity.getMontoCuota());
            }
            if (entity.getUnContratoVenta() != null) {
                ContratoConverter converter = new ContratoConverter();
                dto.setUnContratoVentaDTO((ContratoVentaDTO) converter.fromEntity(entity.getUnContratoVenta()));
            }
            return dto;
        } else {
            return dto;
        }
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
