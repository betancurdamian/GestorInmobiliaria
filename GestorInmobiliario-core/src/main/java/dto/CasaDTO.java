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
public class CasaDTO extends InmuebleDTO{
    private Integer cantidadDeDormitorios;    
    private Integer cantidadDeBanio;    
    private Integer numeroDePisos;    
    private Boolean cochera;    
    private Boolean jardin;

    public Integer getCantidadDeDormitorios() {
        return cantidadDeDormitorios;
    }

    public void setCantidadDeDormitorios(Integer cantidadDeDormitorios) {
        this.cantidadDeDormitorios = cantidadDeDormitorios;
    }

    public Integer getCantidadDeBanio() {
        return cantidadDeBanio;
    }

    public void setCantidadDeBanio(Integer cantidadDeBanio) {
        this.cantidadDeBanio = cantidadDeBanio;
    }

    public Integer getNumeroDePisos() {
        return numeroDePisos;
    }

    public void setNumeroDePisos(Integer numeroDePisos) {
        this.numeroDePisos = numeroDePisos;
    }

    public Boolean getCochera() {
        return cochera;
    }

    public void setCochera(Boolean cochera) {
        this.cochera = cochera;
    }

    public Boolean getJardin() {
        return jardin;
    }

    public void setJardin(Boolean jardin) {
        this.jardin = jardin;
    }

    
    
}
