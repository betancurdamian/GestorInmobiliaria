/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ariel
 */
public abstract class Export {

    Path rutaRelativa;
    Path rutaAbsoluta;
    
    private  JasperReport report;
    private  JasperPrint print;

    protected void crearInforme(Connection conexion, String ruta,String nombreArchivo, Map parametros) throws JRException, FileNotFoundException {        
        
        rutaRelativa = Paths.get(ruta+"/"+nombreArchivo);
        rutaAbsoluta = rutaRelativa.toAbsolutePath();
        
        report = JasperCompileManager.compileReport(rutaAbsoluta.toString());
        print = JasperFillManager.fillReport(report, parametros, conexion);
    }

    protected void verVisorPDFLocal() {
        JasperViewer.viewReport(print, false);
    }

    protected void exportarPDFLocal(String ruta, String nombreArchivo) throws JRException, FileNotFoundException {
        rutaRelativa = Paths.get(ruta+"/"+nombreArchivo); 
        rutaAbsoluta = rutaRelativa.toAbsolutePath();
        JasperExportManager.exportReportToPdfFile(print, rutaAbsoluta.toString());
    }

}
