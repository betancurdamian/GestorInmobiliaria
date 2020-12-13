/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.JFramePrincipal;

/**
 *
 * @author Ariel
 */
public class PrincipalController {
    
    private final JFramePrincipal view;

    public PrincipalController() {
        this.view = new JFramePrincipal();
        this.view.arranca();
    }
    
    
}
