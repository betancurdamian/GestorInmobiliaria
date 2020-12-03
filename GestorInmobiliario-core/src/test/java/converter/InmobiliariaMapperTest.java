/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.InmobiliariaDTO;
import model.entity.Inmobiliaria;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mapstruct.factory.Mappers;

public class InmobiliariaMapperTest {
    @Test
    public void testToLocalidadDTO() {
        System.out.println("toDTO");
        Inmobiliaria entity = new Inmobiliaria();
        entity.setId(1l);
        InmobiliariaMapper converter = Mappers.getMapper(InmobiliariaMapper.class);
        
        InmobiliariaDTO expResult = new InmobiliariaDTO();
        expResult.setId(1l);
        InmobiliariaDTO result = converter.toDTO(entity);
        assertEquals(expResult.getId(), result.getId());
        
    }
}
