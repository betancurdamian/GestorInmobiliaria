/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.BarrioDTO;
import model.entity.Barrio;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class BarrioMapperTest {
    
    @Test
    public void testToDTO() {
        System.out.println("toDTO");
        Barrio entity = new Barrio("una");
        entity.setId(1l);
        BarrioMapper converter = Mappers.getMapper(BarrioMapper.class);
        
        BarrioDTO expResult = new BarrioDTO();
        expResult.setNombre("una");
        expResult.setId(1l);
        BarrioDTO result = converter.toDTO(entity);
        assertEquals(expResult.getId(), result.getId());
        
    }
    
}
