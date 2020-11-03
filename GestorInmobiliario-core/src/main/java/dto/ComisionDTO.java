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
public class ComisionDTO {

    private Long id;
    private ContratoDTO unContrato;
    private Integer cantidadDeCuotas;
    private Float montoTotal;
    private List<LineaDeComisionDTO> linesasDeComisiones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContratoDTO getUnContrato() {
        return unContrato;
    }

    public void setUnContrato(ContratoDTO unContrato) {
        this.unContrato = unContrato;
    }

    public Integer getCantidadDeCuotas() {
        return cantidadDeCuotas;
    }

    public void setCantidadDeCuotas(Integer cantidadDeCuotas) {
        this.cantidadDeCuotas = cantidadDeCuotas;
    }

    public Float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<LineaDeComisionDTO> getLinesasDeComisiones() {
        return linesasDeComisiones;
    }

    public void setLinesasDeComisiones(List<LineaDeComisionDTO> linesasDeComisiones) {
        this.linesasDeComisiones = linesasDeComisiones;
    }

}
