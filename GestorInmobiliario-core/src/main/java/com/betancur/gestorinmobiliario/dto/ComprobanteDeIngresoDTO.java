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
public abstract class ComprobanteDeIngresoDTO {
    private Long id;
    private int mes;
    private int anio;
    private float importeBruto;
    private float importeNeto;

    //Puede ser locatario o garante el comprobante de ingreso
    private LocatarioDTO locatarioDTO;
    private GaranteDTO garanteDTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public float getImporteBruto() {
        return importeBruto;
    }

    public void setImporteBruto(float importeBruto) {
        this.importeBruto = importeBruto;
    }

    public float getImporteNeto() {
        return importeNeto;
    }

    public void setImporteNeto(float importeNeto) {
        this.importeNeto = importeNeto;
    }

    public LocatarioDTO getLocatarioDTO() {
        return locatarioDTO;
    }

    public void setLocatarioDTO(LocatarioDTO locatarioDTO) {
        this.locatarioDTO = locatarioDTO;
    }

    public GaranteDTO getGaranteDTo() {
        return garanteDTo;
    }

    public void setGaranteDTo(GaranteDTO garanteDTo) {
        this.garanteDTo = garanteDTo;
    }
    
    
}
