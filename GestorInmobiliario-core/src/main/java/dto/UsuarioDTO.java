/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Ariel
 */
public abstract class UsuarioDTO {
    private Long id;
    private String userName;
    private String password;
    private TipoUsuarioDTO unTipoUsuario;
    private InmobiliariaDTO unaInmobiliariaUsuario;

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

    public TipoUsuarioDTO getUnTipoUsuario() {
        return unTipoUsuario;
    }

    public void setUnTipoUsuario(TipoUsuarioDTO unTipoUsuario) {
        this.unTipoUsuario = unTipoUsuario;
    }

    public InmobiliariaDTO getUnaInmobiliariaUsuario() {
        return unaInmobiliariaUsuario;
    }

    public void setUnaInmobiliariaUsuario(InmobiliariaDTO unaInmobiliariaUsuario) {
        this.unaInmobiliariaUsuario = unaInmobiliariaUsuario;
    }
    
    
}
