/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Ariel
 */
@MappedSuperclass
public abstract class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "fk_tipo_dni")
    private TipoDNI unTipoDNI;

    @Column(name = "dni")
    private String dni;

    @ManyToOne
    @JoinColumn(name = "fk_estado_civil")
    private EstadoCivil unEstadoCivil;

    @Column(name = "direccion_calle")
    private String direccionCalle;

    @Column(name = "direccion_numero")
    private String direccionNumero;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    public Persona() {
    }
    
    public Persona(String nombre, String apellido, TipoDNI unTipoDNI, String dni, EstadoCivil unEstadoCivil, String direccionCalle, String direccionNumero, String telefono, String correoElectronico) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.unTipoDNI = unTipoDNI;
        this.dni = dni;
        this.unEstadoCivil = unEstadoCivil;
        this.direccionCalle = direccionCalle;
        this.direccionNumero = direccionNumero;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
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
        if (!(object instanceof Inmobiliaria)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betancur.gestorinmobiliario.dao.Inmobiliaria[ id=" + id + " ]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public TipoDNI getUnTipoDNI() {
        return unTipoDNI;
    }

    public void setUnTipoDNI(TipoDNI unTipoDNI) {
        this.unTipoDNI = unTipoDNI;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public EstadoCivil getUnEstadoCivil() {
        return unEstadoCivil;
    }

    public void setUnEstadoCivil(EstadoCivil unEstadoCivil) {
        this.unEstadoCivil = unEstadoCivil;
    }

    public String getDireccionCalle() {
        return direccionCalle;
    }

    public void setDireccionCalle(String direccionCalle) {
        this.direccionCalle = direccionCalle;
    }

    public String getDireccionNumero() {
        return direccionNumero;
    }

    public void setDireccionNumero(String direccionNumero) {
        this.direccionNumero = direccionNumero;
    }   

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

}