/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Ariel
 */
public class ExporLocalBoletaDePago extends ExportLocal {

    public ExporLocalBoletaDePago() {

    }

    @SuppressWarnings("empty-statement")
    public void guardarBoletaDePagoPDF() throws JRException, SQLException, FileNotFoundException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inmobiliaria?serverTimezone=UTC", "root", "admin")) {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("txt_cliente", "asdasdasd");
            String rutaCarpeta = "jaspers";
            String nombreArchivo = "boleta_pago.jrxml";

            crearInforme(connection, rutaCarpeta, nombreArchivo, parameters);
        }
    }

    public void verBoletaDePagoPDF() throws JRException, SQLException, FileNotFoundException {
        verVisorPDFLocal();
    }

    public void exportarBoletaDePagoPDF() throws JRException, SQLException, FileNotFoundException {
        
        String rutaCarpeta = "reports";
        String nombreArchivo = "boleta_pago.pdf";
        exportarPDFLocal(rutaCarpeta,nombreArchivo);
    }

}
