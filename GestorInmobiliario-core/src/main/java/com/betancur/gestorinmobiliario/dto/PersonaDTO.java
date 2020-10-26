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
public abstract class PersonaDTO {
    private Long id;
    private String nombre;    
    private String apellido;
    private TipoDNIDTO unTipoDNIDTO;
    private String dni;
    private EstadoCivilDTO unEstadoCivilDTO;
    private String direccionCalle;
    private String direccionNumero;
    private ProvinciaDTO direccionProvinciaDTO;
    private LocalidadDTO direccionLocalidadDTO;
    private BarrioDTO direccionBarrioDTO;
    private String telefono;
    private String correoElectronico;

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

    public TipoDNIDTO getUnTipoDNIDTO() {
        return unTipoDNIDTO;
    }

    public void setUnTipoDNIDTO(TipoDNIDTO unTipoDNIDTO) {
        this.unTipoDNIDTO = unTipoDNIDTO;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public EstadoCivilDTO getUnEstadoCivilDTO() {
        return unEstadoCivilDTO;
    }

    public void setUnEstadoCivilDTO(EstadoCivilDTO unEstadoCivilDTO) {
        this.unEstadoCivilDTO = unEstadoCivilDTO;
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

    public ProvinciaDTO getDireccionProvinciaDTO() {
        return direccionProvinciaDTO;
    }

    public void setDireccionProvinciaDTO(ProvinciaDTO direccionProvinciaDTO) {
        this.direccionProvinciaDTO = direccionProvinciaDTO;
    }

    public LocalidadDTO getDireccionLocalidadDTO() {
        return direccionLocalidadDTO;
    }

    public void setDireccionLocalidadDTO(LocalidadDTO direccionLocalidadDTO) {
        this.direccionLocalidadDTO = direccionLocalidadDTO;
    }

    public BarrioDTO getDireccionBarrioDTO() {
        return direccionBarrioDTO;
    }

    public void setDireccionBarrioDTO(BarrioDTO direccionBarrioDTO) {
        this.direccionBarrioDTO = direccionBarrioDTO;
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
