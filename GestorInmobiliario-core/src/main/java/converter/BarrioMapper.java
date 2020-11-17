/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.BarrioDTO;
import java.util.List;
import model.entity.Barrio;
import org.mapstruct.Mapper;


@Mapper
public interface BarrioMapper extends IMapper<Barrio, BarrioDTO>{
    @Override
    BarrioDTO toDTO(Barrio entity);
    
    @Override
    Barrio toEntity(BarrioDTO dto);    
    
    @Override
    List<BarrioDTO> toDTOList(List<Barrio> entities);
}
