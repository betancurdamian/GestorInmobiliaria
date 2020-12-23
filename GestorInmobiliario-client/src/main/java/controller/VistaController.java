/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.JFramePrincipal;
import view.JPanelLocador;

/**
 *
 * @author Ariel
 */
public class VistaController implements ActionListener {

    private final JFramePrincipal view;

    public VistaController() {
        this.view = new JFramePrincipal();
        this.view.getjMenuItem_locador().addActionListener(this);
        this.view.getjMenuItem_locatario().addActionListener(this);
        this.view.arranca();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.view.getjMenuItem_locador())) {
            this.view.getjPanel_contenido().removeAll();
            GestorCliente controllerCliente = new GestorCliente();
            this.view.getjPanel_contenido().add(controllerCliente.getView(), BorderLayout.CENTER);            
            this.view.getjPanel_contenido().revalidate();
            this.view.getjPanel_contenido().repaint();
        }
        if (e.getSource().equals(this.view.getjMenuItem_locatario())) {
            this.view.getjPanel_contenido().removeAll();
            JPanelLocador panelLocador = new JPanelLocador();
            panelLocador.setSize(400, 400);
            this.view.getjPanel_contenido().add(panelLocador, BorderLayout.CENTER);
            this.view.getjPanel_contenido().revalidate();
            this.view.getjPanel_contenido().repaint();
        }
    }

}
