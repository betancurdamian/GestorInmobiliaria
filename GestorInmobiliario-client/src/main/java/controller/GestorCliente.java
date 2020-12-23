/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.ClienteDTO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.event.ListSelectionEvent;
import model.service.Impl.ClienteServiceImpl;
import view.JPanelCliente;
import view.resources.TablaClienteModelo;

/**
 *
 * @author Ariel
 */
public class GestorCliente extends Controller {

    private final JPanelCliente view;
    private final ClienteServiceImpl model;
    private final TablaClienteModelo tablaClienteModelo;

    public GestorCliente() {
        this.model = new ClienteServiceImpl();
        this.view = new JPanelCliente();
        this.view.setSize(400, 400);
        addListener();
        limpiarCampos();
        formatearCampos();
        habilitarBotones(false);
        getValidador().habilitarBoton(true, this.view.getJbtn_agregar(), Color.lightGray, Color.black, Color.orange, Color.lightGray);
        getValidador().habilitarBoton(true, this.view.getJbtn_listar(), Color.lightGray, Color.black, Color.orange, Color.lightGray);
        habilitarCampos(false);
        getValidador().habilitarCampo(true, this.view.getJtf_listar());
        habilitarTabla(false);

        this.tablaClienteModelo = new TablaClienteModelo();
        this.view.getjTableCliente().setModel(this.tablaClienteModelo);
        this.view.getjTableCliente().getColumnModel().getColumn(0).setPreferredWidth(5);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.view.getJbtn_listar()) {
            habilitarTabla(true);
            this.tablaClienteModelo.setClientes(this.model.listarTodos());
            //Refrescar el modelo en la tabla
            this.tablaClienteModelo.fireTableDataChanged();
            getValidador().limpiarCampo(this.view.getJtf_listar());
        }
    }

    /**
     * Agrega los componentes de la view a ser escuchados
     */
    @Override
    public void addListener() {
        this.view.getJbtn_listar().addActionListener(this);
        this.view.getJbtn_agregar().addActionListener(this);
        this.view.getJbtn_modificar().addActionListener(this);
        this.view.getJbtn_eliminar().addActionListener(this);
        this.view.getJbtn_aceptar().addActionListener(this);
        this.view.getJbtn_cancelar().addActionListener(this);

        this.view.getJcb_estadoCivil().addActionListener(this);
        this.view.getJcb_tipoCliente().addActionListener(this);
        this.view.getJcb_tipoDNI().addActionListener(this);

        this.view.getJtf_listar().addKeyListener(this);

        //agrega escuchadores de las tablas
        this.view.getjTableCliente().getSelectionModel().addListSelectionListener(this);

    }

    /**
     * Da formato a los componentes de la view
     */
    @Override
    public void formatearCampos() {
        //Limitar Caracteres de campos
        this.getValidador().LimitarCaracteres(this.view.getJtf_nombre(), 20);
        this.getValidador().LimitarCaracteres(this.view.getJtf_apellido(), 20);
        this.getValidador().LimitarCaracteres(this.view.getJtf_dni(), 8);
        this.getValidador().LimitarCaracteres(this.view.getJtf_calle(), 20);
        this.getValidador().LimitarCaracteres(this.view.getJtf_numero(), 5);
        this.getValidador().LimitarCaracteres(this.view.getJtf_telefono(), 10);
        this.getValidador().LimitarCaracteres(this.view.getJtf_correo(), 30);
        this.getValidador().LimitarCaracteres(this.view.getJtf_listar(), 8);

        //tipos de datos admitidos
        this.getValidador().validarSoloLetras(this.view.getJtf_nombre());
        this.getValidador().validarSoloLetras(this.view.getJtf_apellido());
        this.getValidador().validarSoloNumero(this.view.getJtf_dni());
        this.getValidador().validarSoloLetras(this.view.getJtf_calle());
        this.getValidador().validarSoloNumero(this.view.getJtf_numero());
        this.getValidador().validarSoloNumero(this.view.getJtf_telefono());
        this.getValidador().validarSoloNumero(this.view.getJtf_listar());
    }

    @Override
    public void limpiarCampos() {
        //Limpiar campos
        this.getValidador().limpiarCampo(this.view.getJtf_nombre());
        this.getValidador().limpiarCampo(this.view.getJtf_apellido());
        this.getValidador().limpiarCampo(this.view.getJtf_dni());
        this.getValidador().limpiarCampo(this.view.getJtf_calle());
        this.getValidador().limpiarCampo(this.view.getJtf_numero());
        this.getValidador().limpiarCampo(this.view.getJtf_telefono());
        this.getValidador().limpiarCampo(this.view.getJtf_correo());
        this.getValidador().limpiarCampo(this.view.getJtf_listar());

        this.getValidador().limpiarCombobox(this.view.getJcb_estadoCivil());
        this.getValidador().limpiarCombobox(this.view.getJcb_tipoCliente());
        this.getValidador().limpiarCombobox(this.view.getJcb_tipoDNI());
    }

    @Override
    public void habilitarBotones(boolean estado) {
        this.getValidador().habilitarBoton(estado, this.view.getJbtn_listar(), Color.lightGray, Color.black, Color.orange, Color.lightGray);
        this.getValidador().habilitarBoton(estado, this.view.getJbtn_agregar(), Color.lightGray, Color.black, Color.orange, Color.lightGray);

        this.getValidador().habilitarBoton(estado, this.view.getJbtn_modificar(), Color.lightGray, Color.black, Color.orange, Color.lightGray);
        this.getValidador().habilitarBoton(estado, this.view.getJbtn_eliminar(), Color.lightGray, Color.black, Color.orange, Color.lightGray);
        this.getValidador().habilitarBoton(estado, this.view.getJbtn_aceptar(), Color.lightGray, Color.black, Color.orange, Color.lightGray);
        this.getValidador().habilitarBoton(estado, this.view.getJbtn_cancelar(), Color.lightGray, Color.black, Color.orange, Color.lightGray);
    }

    @Override
    public void habilitarCampos(boolean estado) {
        this.getValidador().habilitarCampo(estado, this.view.getJtf_nombre());
        this.getValidador().habilitarCampo(estado, this.view.getJtf_apellido());
        this.getValidador().habilitarCampo(estado, this.view.getJtf_dni());
        this.getValidador().habilitarCampo(estado, this.view.getJtf_calle());
        this.getValidador().habilitarCampo(estado, this.view.getJtf_numero());
        this.getValidador().habilitarCampo(estado, this.view.getJtf_telefono());
        this.getValidador().habilitarCampo(estado, this.view.getJtf_correo());
        this.getValidador().habilitarCampo(estado, this.view.getJtf_listar());

        this.getValidador().habilitarCombobox(estado, this.view.getJcb_estadoCivil());
        this.getValidador().habilitarCombobox(estado, this.view.getJcb_tipoCliente());
        this.getValidador().habilitarCombobox(estado, this.view.getJcb_tipoDNI());
    }

    @Override
    public void habilitarTabla(boolean estado) {
        this.view.getjTableCliente().setEnabled(estado);
    }

    public JPanelCliente getView() {
        return view;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String dni = this.view.getJtf_listar().getText();
        List<ClienteDTO> filteredCliente = this.model.listarTodos().stream().filter(c->c.getDni().contains(dni)).collect(Collectors.toList());
        this.tablaClienteModelo.setClientes(filteredCliente);
        //Refrescar el modelo en la tabla
        this.tablaClienteModelo.fireTableDataChanged();
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

}
