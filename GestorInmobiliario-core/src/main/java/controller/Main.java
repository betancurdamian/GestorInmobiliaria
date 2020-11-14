/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.ArancelEspecialExpensaDTO;
import dto.ArancelEspecialServicioDTO;
import dto.ComisionDTO;
import dto.LineaDeComisionDTO;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import model.dao.Conexion;
import model.service.Impl.ArancelEspecialServiceImpl;
import model.service.Impl.ComisionServiceImpl;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Ariel
 */
public class Main {

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) throws SQLException, JRException, JRException, FileNotFoundException {

        new Conexion();

        ArancelEspecialServiceImpl servArancel = new ArancelEspecialServiceImpl();
        
        ArancelEspecialServicioDTO aesdto = new ArancelEspecialServicioDTO();
        aesdto.setDescripcion("usura servicio");
        aesdto.setMonto(120f);
        aesdto.setUnaFechaDeRecargo("2020-11-14");
        
        servArancel.crear(aesdto);
        
        ArancelEspecialExpensaDTO aeedto = new ArancelEspecialExpensaDTO();
        aeedto.setDescripcion("usura servicio");
        aeedto.setMonto(120f);
        aeedto.setUnaFechaDeRecargo("2020-11-14");
        servArancel.crear(aeedto);
    }

}
