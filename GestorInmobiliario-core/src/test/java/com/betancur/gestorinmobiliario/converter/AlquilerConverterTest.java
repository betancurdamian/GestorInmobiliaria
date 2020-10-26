/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;

import com.betancur.gestorinmobiliario.dto.AlquilerDTO;
import com.betancur.gestorinmobiliario.model.entity.Alquiler;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ariel
 */
public class AlquilerConverterTest {
    
    public AlquilerConverterTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of fromDto method, of class AlquilerConverter.
     */
    @Test
    public void testFromDto_AlquilerDTO() {
        System.out.println("fromDto");
        AlquilerDTO dto = null;
        AlquilerConverter instance = new AlquilerConverter();
        Alquiler expResult = null;
        Alquiler result = instance.fromDto(dto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fromEntity method, of class AlquilerConverter.
     */
    @Test
    public void testFromEntity_Alquiler() {
        System.out.println("fromEntity");
        Alquiler entity = null;
        AlquilerConverter instance = new AlquilerConverter();
        AlquilerDTO expResult = null;
        AlquilerDTO result = instance.fromEntity(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fromEntity method, of class AlquilerConverter.
     */
    @Test
    public void testFromEntity_List() {
        System.out.println("fromEntity");
        List<Alquiler> entities = null;
        AlquilerConverter instance = new AlquilerConverter();
        List<AlquilerDTO> expResult = null;
        List<AlquilerDTO> result = instance.fromEntity(entities);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fromDto method, of class AlquilerConverter.
     */
    @Test
    public void testFromDto_List() {
        System.out.println("fromDto");
        List<AlquilerDTO> dtos = null;
        AlquilerConverter instance = new AlquilerConverter();
        List<Alquiler> expResult = null;
        List<Alquiler> result = instance.fromDto(dtos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
