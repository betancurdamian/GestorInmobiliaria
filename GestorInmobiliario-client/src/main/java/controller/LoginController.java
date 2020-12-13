/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.UsuarioDTO;
import dto.UsuarioEmpresaDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.service.Impl.facade.Login;
import view.JFrameLogin;

/**
 * Controlador de Login de Usuario
 *
 */
public class LoginController implements ActionListener, KeyListener, FocusListener {

    private final Login model;
    private final JFrameLogin view;
    private PrincipalController controllerPrincipal;

    @SuppressWarnings("LeakingThisInConstructor")
    public LoginController() {
        
        this.model = new Login();
        this.view = new JFrameLogin();
        this.view.getJtf_username().addKeyListener(this);
        this.view.getJtf_password().addKeyListener(this);
        this.view.getJtf_username().addFocusListener(this);
        this.view.getJtf_password().addFocusListener(this);
        this.view.getJtf_password().setEnabled(false);
        this.view.getJbtn_login().addActionListener(this);
        this.view.arranca();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.view.getJbtn_login()) {
            iniciarSesion();
        }
    }

    private void iniciarSesion() {
        UsuarioDTO usuarioAuxiliar = null;
        usuarioAuxiliar = new UsuarioEmpresaDTO();

        usuarioAuxiliar.setUserName(this.view.getJtf_username().getText());
        usuarioAuxiliar.setPassword(new String(this.view.getJtf_password().getPassword()));

        usuarioAuxiliar = model.ingresar(usuarioAuxiliar);

        if (usuarioAuxiliar != null) {
            if (usuarioAuxiliar instanceof UsuarioEmpresaDTO) {
                //Crea la pantalla principal del sistema
                this.view.limpiar();
                this.view.dispose();
                this.controllerPrincipal = new PrincipalController();
            } else {
                this.view.getJlbl_mensaje().setText("ERROR: Ingreso no permitido");
                this.view.limpiar();
                this.view.getJtf_username().requestFocus();
            }
        } else {
            this.view.getJlbl_mensaje().setText("ERROR: usuario o Password incorrecto");
            this.view.limpiar();
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
            this.view.limpiar();
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

}
