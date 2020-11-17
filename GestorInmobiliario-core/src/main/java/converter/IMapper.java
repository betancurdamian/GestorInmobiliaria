/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.util.List;

/**
 *
 * @author Ariel
 * @param <ENTITY>
 * @param <DTO>
 */
public interface IMapper <ENTITY, DTO>{
    DTO toDTO(ENTITY entity);
    
    ENTITY toEntity(DTO dto);    
    
    List<DTO> toDTOList(List<ENTITY> entities);
}
