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
public abstract class InmuebleDTO {

    private Long id;
    private String direccionCalle;
    private String direccionNumero;
    private ProvinciaDTO direccionProvincia;
    private LocalidadDTO direccionLocalidad;
    private BarrioDTO direccionBarrio;
    private Integer superficieTotal;
    private Boolean disponible;
    private String descripcion;
    private InmobiliariaDTO unaInmobiliariaInmueble;
    private LocadorDTO unaLocador;

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

    public ProvinciaDTO getDireccionProvincia() {
        return direccionProvincia;
    }

    public void setDireccionProvincia(ProvinciaDTO direccionProvincia) {
        this.direccionProvincia = direccionProvincia;
    }

    public LocalidadDTO getDireccionLocalidad() {
        return direccionLocalidad;
    }

    public void setDireccionLocalidad(LocalidadDTO direccionLocalidad) {
        this.direccionLocalidad = direccionLocalidad;
    }

    public BarrioDTO getDireccionBarrio() {
        return direccionBarrio;
    }

    public void setDireccionBarrio(BarrioDTO direccionBarrio) {
        this.direccionBarrio = direccionBarrio;
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

    public InmobiliariaDTO getUnaInmobiliariaInmueble() {
        return unaInmobiliariaInmueble;
    }

    public void setUnaInmobiliariaInmueble(InmobiliariaDTO unaInmobiliariaInmueble) {
        this.unaInmobiliariaInmueble = unaInmobiliariaInmueble;
    }

    public LocadorDTO getUnaLocador() {
        return unaLocador;
    }

    public void setUnaLocador(LocadorDTO unaLocador) {
        this.unaLocador = unaLocador;
    }

    
}
