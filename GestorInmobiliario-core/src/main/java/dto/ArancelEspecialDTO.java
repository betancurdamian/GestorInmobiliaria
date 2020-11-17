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
public abstract class ArancelEspecialDTO {
    
    private Long id;
    private String unaFechaArancel;    
    private String descripcion;    
    private Float monto;    
    private InmobiliariaDTO unaInmobiliariaArancelEspecial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnaFechaArancel() {
        return unaFechaArancel;
    }

    public void setUnaFechaArancel(String unaFechaArancel) {
        this.unaFechaArancel = unaFechaArancel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public InmobiliariaDTO getUnaInmobiliariaArancelEspecial() {
        return unaInmobiliariaArancelEspecial;
    }

    public void setUnaInmobiliariaArancelEspecial(InmobiliariaDTO unaInmobiliariaArancelEspecial) {
        this.unaInmobiliariaArancelEspecial = unaInmobiliariaArancelEspecial;
    }
    
    
}
