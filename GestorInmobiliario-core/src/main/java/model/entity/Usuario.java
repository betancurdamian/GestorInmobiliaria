/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Ariel
 */
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario")
public abstract class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "fk_tipo_usuario")
    private TipoUsuario unTipoUsuario;

    @ManyToOne
    @JoinColumn(name = "fk_inmobiliaria")
    private Inmobiliaria unaInmobiliariaUsuario;

    public Usuario() {
    }

    public Usuario(String userName, String password, TipoUsuario unTipoUsuario, Inmobiliaria unaInmobiliariaUsuario) {
        this.userName = userName;
        this.password = password;
        this.unTipoUsuario = unTipoUsuario;
        this.unaInmobiliariaUsuario = unaInmobiliariaUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betancur.gestorinmobiliario.model.Usuario[ id=" + id + " ]";
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

    public TipoUsuario getUnTipoUsuario() {
        return unTipoUsuario;
    }

    public void setUnTipoUsuario(TipoUsuario unTipoUsuario) {
        this.unTipoUsuario = unTipoUsuario;
    }

    public Inmobiliaria getUnaInmobiliariaUsuario() {
        return unaInmobiliariaUsuario;
    }

    public void setUnaInmobiliariaUsuario(Inmobiliaria unaInmobiliariaUsuario) {
        this.unaInmobiliariaUsuario = unaInmobiliariaUsuario;
    }

}
