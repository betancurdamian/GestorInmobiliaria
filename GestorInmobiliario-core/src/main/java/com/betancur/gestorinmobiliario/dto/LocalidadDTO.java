/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.dto;

import java.util.List;

/**
 *
 * @author Ariel
 */
public class LocalidadDTO {
    private Long id;
    private String nombre;
    private String codigoPostal;
    private ProvinciaDTO unaProvinciaDTO;
    private List<BarrioDTO> barrios;

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

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public ProvinciaDTO getUnaProvinciaDTO() {
        return unaProvinciaDTO;
    }

    public void setUnaProvinciaDTO(ProvinciaDTO unaProvinciaDTO) {
        this.unaProvinciaDTO = unaProvinciaDTO;
    }

    public List<BarrioDTO> getBarrios() {
        return barrios;
    }

    public void setBarrios(List<BarrioDTO> barrios) {
        this.barrios = barrios;
    }
    
    
}