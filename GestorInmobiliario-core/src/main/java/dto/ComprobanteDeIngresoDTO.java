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
public abstract class ComprobanteDeIngresoDTO {
    private Long id;
    private Integer mes;
    private Integer anio;
    private Float importeBruto;
    private Float importeNeto;

    //Puede ser locatario o garante el comprobante de ingreso
    private LocatarioDTO unLocatarioDTO;
    private GaranteDTO unGaranteDTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Float getImporteBruto() {
        return importeBruto;
    }

    public void setImporteBruto(Float importeBruto) {
        this.importeBruto = importeBruto;
    }

    public Float getImporteNeto() {
        return importeNeto;
    }

    public void setImporteNeto(Float importeNeto) {
        this.importeNeto = importeNeto;
    }

    public LocatarioDTO getUnLocatarioDTO() {
        return unLocatarioDTO;
    }

    public void setUnLocatarioDTO(LocatarioDTO unLocatarioDTO) {
        this.unLocatarioDTO = unLocatarioDTO;
    }

    public GaranteDTO getUnGaranteDTo() {
        return unGaranteDTo;
    }

    public void setUnGaranteDTo(GaranteDTO unGaranteDTo) {
        this.unGaranteDTo = unGaranteDTo;
    }

    
}
