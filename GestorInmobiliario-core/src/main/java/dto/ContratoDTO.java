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
public class ContratoDTO {
    private Long id;
    private Integer unDiaPrimerVencimiento;
    private Integer unDiaSegundoVencimiento;
    private Float montoTotal;
    private Integer cantidadDeCuotas;
    private RecargoPorMoraDTO unRecargoPorMora;
    private ClienteDTO unLocador;
    private ClienteDTO unLocatario;
    private ComisionDTO unaComision;
    private List<ArancelEspecialDTO> arancelesEspeciales;
    private List<BoletaDePagoDTO> boletasDePago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUnDiaPrimerVencimiento() {
        return unDiaPrimerVencimiento;
    }

    public void setUnDiaPrimerVencimiento(Integer unDiaPrimerVencimiento) {
        this.unDiaPrimerVencimiento = unDiaPrimerVencimiento;
    }

    public Integer getUnDiaSegundoVencimiento() {
        return unDiaSegundoVencimiento;
    }

    public void setUnDiaSegundoVencimiento(Integer unDiaSegundoVencimiento) {
        this.unDiaSegundoVencimiento = unDiaSegundoVencimiento;
    }

    public Float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getCantidadDeCuotas() {
        return cantidadDeCuotas;
    }

    public void setCantidadDeCuotas(Integer cantidadDeCuotas) {
        this.cantidadDeCuotas = cantidadDeCuotas;
    }

    public RecargoPorMoraDTO getUnRecargoPorMora() {
        return unRecargoPorMora;
    }

    public void setUnRecargoPorMora(RecargoPorMoraDTO unRecargoPorMora) {
        this.unRecargoPorMora = unRecargoPorMora;
    }

    public ClienteDTO getUnLocador() {
        return unLocador;
    }

    public void setUnLocador(ClienteDTO unLocador) {
        this.unLocador = unLocador;
    }

    public ClienteDTO getUnLocatario() {
        return unLocatario;
    }

    public void setUnLocatario(ClienteDTO unLocatario) {
        this.unLocatario = unLocatario;
    }

    public ComisionDTO getUnaComision() {
        return unaComision;
    }

    public void setUnaComision(ComisionDTO unaComision) {
        this.unaComision = unaComision;
    }

    public List<ArancelEspecialDTO> getArancelesEspeciales() {
        return arancelesEspeciales;
    }

    public void setArancelesEspeciales(List<ArancelEspecialDTO> arancelesEspeciales) {
        this.arancelesEspeciales = arancelesEspeciales;
    }

    public List<BoletaDePagoDTO> getBoletasDePago() {
        return boletasDePago;
    }

    public void setBoletasDePago(List<BoletaDePagoDTO> boletasDePago) {
        this.boletasDePago = boletasDePago;
    }
    
    
}
