/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import javax.swing.event.ListSelectionListener;
import view.resources.ValidadorDeCampos;

/**
 *
 * @author Ariel
 */
public abstract class Controller implements ActionListener, KeyListener, FocusListener, ListSelectionListener{
    private final ValidadorDeCampos validador;

    public Controller() {
        this.validador = new ValidadorDeCampos();
    }
    
    abstract void addListener();
    abstract void formatearCampos();
    abstract void limpiarCampos();
    abstract void habilitarBotones(boolean estado);
    abstract void habilitarCampos(boolean estado);
    abstract void habilitarTabla(boolean estado);

    public ValidadorDeCampos getValidador() {
        return validador;
    }
}
