/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.LineaDeComisionDTO;
import model.entity.LineaDeComision;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class LineaDeComisionConverter implements Converter<LineaDeComision, LineaDeComisionDTO>{

    @Override
    public LineaDeComision fomDTO(LineaDeComisionDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        LineaDeComision entity = modelMapper.map(dto, LineaDeComision.class);
        
        return entity;
    }

    @Override
    public LineaDeComisionDTO fromDTO(LineaDeComision entity) {
         ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        LineaDeComisionDTO dto = modelMapper.map(entity, LineaDeComisionDTO.class);
        
        return dto;
    }
    
}
