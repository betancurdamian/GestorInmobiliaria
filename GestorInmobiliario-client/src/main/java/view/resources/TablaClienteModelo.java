/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.resources;

import dto.ClienteDTO;
import dto.LocadorDTO;
import dto.LocatarioDependienteDTO;
import dto.LocatarioEstudianteDTO;
import dto.LocatarioIndependienteDTO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entity.Cliente;

/**
 *
 * @author Ariel
 */
public class TablaClienteModelo extends AbstractTableModel {

    private static final String[] COLUMNAS = {"N°", "Nombre", "Apellido", "DNI", "TIPO CLIENTE"};
    private List<ClienteDTO> clientes;

    public TablaClienteModelo() {
        clientes = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return clientes == null ? 0 : clientes.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object retorno = null;
        ClienteDTO cliente = clientes.get(rowIndex);

        switch (columnIndex) {
            case 0:
                retorno = rowIndex + 1;
                break;
            case 1:
                retorno = cliente.getNombre();
                break;
            case 2:
                retorno = cliente.getApellido();
                break;
            case 3:
                retorno = cliente.getDni();
                break;
            case 4:
                if (cliente instanceof LocadorDTO) {
                    retorno = "LOCADOR";
                }
                if (cliente instanceof LocatarioDependienteDTO) {
                    retorno = "LOCATARIO DEPENDIENTE";
                }
                if (cliente instanceof LocatarioIndependienteDTO) {
                    retorno = "LOCATARIO INDEPENDIENTE";
                }
                if (cliente instanceof LocatarioEstudianteDTO) {
                    retorno = "LOCATARIO ESTUDIANTE";
                }
                
                break;
           
        }

        return retorno;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNAS[column];
    }

    public void setClientes(List<ClienteDTO> clientes) {
        this.clientes = clientes;
    }
    
    
    public ClienteDTO obtenerClienteEn (int fila) {
        return clientes.get(fila);
    }
    
    public int buscarFilaCliente(Cliente clienteBuscado){
        int fila = 0;
        int contador = 0;
        for (ClienteDTO cliente : clientes) {
            contador = contador +1;
            if (clienteBuscado.getId()==cliente.getId()) {
                fila = contador;
            }
        }
        return fila;
    }

}
