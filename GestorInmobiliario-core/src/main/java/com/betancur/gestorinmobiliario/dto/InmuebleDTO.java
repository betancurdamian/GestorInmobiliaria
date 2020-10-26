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
public abstract class InmuebleDTO {

    private Long id;
    private String direccionCalle;
    private String direccionNumero;
    private ProvinciaDTO direccionProvinciaDTO;
    private LocalidadDTO direccionLocalidadDTO;
    private BarrioDTO direccionBarrioDTO;
    private Integer superficieTotal;
    private Boolean disponible;
    private String descripcion;
    private InmobiliariaDTO unaInmobiliariaInmuebleDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getSuperficieTotal() {
        return superficieTotal;
    }

    public void setSuperficieTotal(Integer superficieTotal) {
        this.superficieTotal = superficieTotal;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public InmobiliariaDTO getUnaInmobiliariaInmuebleDTO() {
        return unaInmobiliariaInmuebleDTO;
    }

    public void setUnaInmobiliariaInmuebleDTO(InmobiliariaDTO unaInmobiliariaInmuebleDTO) {
        this.unaInmobiliariaInmuebleDTO = unaInmobiliariaInmuebleDTO;
    }

    
}
