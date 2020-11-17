/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ActividadDTO;
import java.util.List;
import model.entity.Actividad;
import org.mapstruct.Mapper;


@Mapper
public interface ActividadMapper extends IMapper<Actividad, ActividadDTO>{
    
    @Override
    ActividadDTO toDTO(Actividad entity);
    
    @Override
    Actividad toEntity(ActividadDTO dto);    
    
    @Override
    List<ActividadDTO> toDTOList(List<Actividad> entities);
}
