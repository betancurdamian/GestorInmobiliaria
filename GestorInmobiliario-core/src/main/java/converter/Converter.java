/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

/**
 *
 * @author Ariel
 * @param <ENTITY>
 * @param <DTO>
 */
public interface Converter <ENTITY,DTO>{
    ENTITY fomDTO(DTO dto);
    DTO fromDTO(ENTITY entity);
}
