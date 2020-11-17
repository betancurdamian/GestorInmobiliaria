/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ActividadDTO;
import model.entity.Actividad;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Ariel
 */
public class ActividadMapperTest {
    
    @Test
    public void testToDTO() {
        System.out.println("toDTO");
        Actividad entity = new Actividad("una");
        entity.setId(1l);
        ActividadMapper converter = Mappers.getMapper(ActividadMapper.class);
        
        ActividadDTO expResult = new ActividadDTO();
        expResult.setNombre("una");
        expResult.setId(1l);
        ActividadDTO result = converter.toDTO(entity);
        assertEquals(expResult.getId(), result.getId());
        
    }

    
}
