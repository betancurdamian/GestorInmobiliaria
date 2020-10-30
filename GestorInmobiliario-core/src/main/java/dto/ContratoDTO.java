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
    private String unaFechaPrimerVencimiento;
    private String unaFechaSegundoVencimiento;
    private Float montoTotal;
    private Integer cantidadDeCuotas;
    private RecargoPorMoraDTO unRecargoPorMoraDTO;
    private LocadorDTO unLocadorDTO;
    private LocatarioDTO unLocatarioDTO;
    private ComisionDTO unaComisionDTO;
    private List<ArancelEspecialDTO> arancelesEspecialesDTO;
    private List<BoletaDePagoDTO> boletasDePagoDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnaFechaPrimerVencimiento() {
        return unaFechaPrimerVencimiento;
    }

    public void setUnaFechaPrimerVencimiento(String unaFechaPrimerVencimiento) {
        this.unaFechaPrimerVencimiento = unaFechaPrimerVencimiento;
    }

    public String getUnaFechaSegundoVencimiento() {
        return unaFechaSegundoVencimiento;
    }

    public void setUnaFechaSegundoVencimiento(String unaFechaSegundoVencimiento) {
        this.unaFechaSegundoVencimiento = unaFechaSegundoVencimiento;
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

    public RecargoPorMoraDTO getUnRecargoPorMoraDTO() {
        return unRecargoPorMoraDTO;
    }

    public void setUnRecargoPorMoraDTO(RecargoPorMoraDTO unRecargoPorMoraDTO) {
        this.unRecargoPorMoraDTO = unRecargoPorMoraDTO;
    }

    public LocadorDTO getUnLocadorDTO() {
        return unLocadorDTO;
    }

    public void setUnLocadorDTO(LocadorDTO unLocadorDTO) {
        this.unLocadorDTO = unLocadorDTO;
    }

    public LocatarioDTO getUnLocatarioDTO() {
        return unLocatarioDTO;
    }

    public void setUnLocatarioDTO(LocatarioDTO unLocatarioDTO) {
        this.unLocatarioDTO = unLocatarioDTO;
    }

    public ComisionDTO getUnaComisionDTO() {
        return unaComisionDTO;
    }

    public void setUnaComisionDTO(ComisionDTO unaComisionDTO) {
        this.unaComisionDTO = unaComisionDTO;
    }

    public List<ArancelEspecialDTO> getArancelesEspecialesDTO() {
        return arancelesEspecialesDTO;
    }

    public void setArancelesEspecialesDTO(List<ArancelEspecialDTO> arancelesEspecialesDTO) {
        this.arancelesEspecialesDTO = arancelesEspecialesDTO;
    }

    public List<BoletaDePagoDTO> getBoletasDePagoDTO() {
        return boletasDePagoDTO;
    }

    public void setBoletasDePagoDTO(List<BoletaDePagoDTO> boletasDePagoDTO) {
        this.boletasDePagoDTO = boletasDePagoDTO;
    }
    
    
}
