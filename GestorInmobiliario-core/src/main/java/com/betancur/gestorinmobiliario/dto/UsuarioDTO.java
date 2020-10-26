/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.dto;

/**
 *
 * @author Ariel
 */
public abstract class UsuarioDTO {
    private Long id;
    private String userName;
    private String password;
    private TipoUsuarioDTO unTipoUsuarioDTO;
    private InmobiliariaDTO unaInmobiliariaUsuarioDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuarioDTO getUnTipoUsuarioDTO() {
        return unTipoUsuarioDTO;
    }

    public void setUnTipoUsuarioDTO(TipoUsuarioDTO unTipoUsuarioDTO) {
        this.unTipoUsuarioDTO = unTipoUsuarioDTO;
    }

    public InmobiliariaDTO getUnaInmobiliariaUsuarioDTO() {
        return unaInmobiliariaUsuarioDTO;
    }

    public void setUnaInmobiliariaUsuarioDTO(InmobiliariaDTO unaInmobiliariaUsuarioDTO) {
        this.unaInmobiliariaUsuarioDTO = unaInmobiliariaUsuarioDTO;
    }
    
    
}
