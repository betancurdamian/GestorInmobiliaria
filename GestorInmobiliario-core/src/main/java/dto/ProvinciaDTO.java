/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;

/**
 *
 * @author Ariel
 */
public class ProvinciaDTO {
    private Long id;
    private String nombre;
    private String codigoPostal;
    private List<LocalidadDTO> localidades;

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

    public List<LocalidadDTO> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<LocalidadDTO> localidades) {
        this.localidades = localidades;
    }
    
    
}
