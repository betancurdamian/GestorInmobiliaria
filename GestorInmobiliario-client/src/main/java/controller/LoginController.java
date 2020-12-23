/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.UsuarioDTO;
import dto.UsuarioEmpresaDTO;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import javax.swing.event.ListSelectionEvent;
import model.service.Impl.facade.Login;
import view.JFrameLogin;

/**
 * Controlador de Login de Usuario
 *
 */
public class LoginController extends Controller {

    private final Login model;
    private final JFrameLogin view;

    @SuppressWarnings("LeakingThisInConstructor")
    public LoginController() {
        this.model = new Login();
        this.view = new JFrameLogin();
        addListener();
        habilitarCampos(false);
        this.view.arranca();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.view.getJbtn_login()) {
            iniciarSesion();
        }
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    private void iniciarSesion() {
        UsuarioDTO usuarioAuxiliar = null;
        usuarioAuxiliar = new UsuarioEmpresaDTO();

        usuarioAuxiliar.setUserName(this.view.getJtf_username().getText());
        usuarioAuxiliar.setPassword(new String(this.view.getJtf_password().getPassword()));

        usuarioAuxiliar = model.ingresar(usuarioAuxiliar);

        if (usuarioAuxiliar != null) {
            if (usuarioAuxiliar instanceof UsuarioEmpresaDTO) {
                //Crea la pantalla principal del sistema
                limpiarCampos();
                this.view.dispose();
                new VistaController();
            } else {
                this.view.getJlbl_mensaje().setText("ERROR: Ingreso no permitido");
                limpiarCampos();
                this.view.getJtf_username().requestFocus();
            }
        } else {
            this.view.getJlbl_mensaje().setText("ERROR: usuario o Password incorrecto");
            limpiarCampos();
            this.view.getJtf_username().requestFocus();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            iniciarSesion();
            limpiarCampos();
        }

        if (e.getSource() == this.view.getJtf_username()) {
            if (this.view.getJtf_username().getText().isEmpty() || this.view.getJtf_username().getText().equals("")) {
                this.view.getJtf_password().setEnabled(false);
            } else {
                this.view.getJtf_password().setEnabled(true);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == this.view.getJtf_username()) {
            if (this.view.getJtf_username().getText().isEmpty() || this.view.getJtf_username().getText().equals("")) {
                this.view.getJtf_password().setEnabled(false);
            } else {
                this.view.getJtf_password().setEnabled(true);
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    void addListener() {
        this.view.getJtf_username().addKeyListener(this);
        this.view.getJtf_password().addKeyListener(this);
        this.view.getJtf_username().addFocusListener(this);
        this.view.getJtf_password().addFocusListener(this);
        this.view.getJbtn_login().addActionListener(this);
    }

    @Override
    void formatearCampos() {

    }

    @Override
    void limpiarCampos() {
        this.getValidador().limpiarCampo(this.view.getJtf_username());
        this.getValidador().limpiarCampo(this.view.getJtf_password());
    }

    @Override
    void habilitarBotones(boolean estado) {

    }

    @Override
    void habilitarCampos(boolean estado) {
        this.view.getJtf_password().setEnabled(estado);
    }

    @Override
    void habilitarTabla(boolean estado) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

}
