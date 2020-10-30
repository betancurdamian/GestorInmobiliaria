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
public class InmobiliariaDTO {
    private Long id;
    private String razonSocial;
    private String cuit;
    private String direccionCalle;
    private String direccionNumero;
    private ProvinciaDTO direccionProvinciaDTO;
    private LocalidadDTO direccionLocalidadDTO;
    private BarrioDTO direccionBarrioDTO;
    private String telefono;
    private String correoElectronico;
    private List<ClienteDTO> clientesDTO;
    private List<GaranteDTO> garantesDTO;
    private List<InmuebleDTO> inmueblesDTO;
    private List<AlquilerDTO> alquileresDTO;
    private List<VentaDTO> ventasDTO;
    private List<RecargoPorMoraDTO> recargosPorMorasDTO;    
    private List<ArancelEspecialDTO> arancelesEspecialesDTO;    
    private List<UsuarioDTO> usuariosDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
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

    public ProvinciaDTO getDireccionProvinciaDTO() {
        return direccionProvinciaDTO;
    }

    public void setDireccionProvinciaDTO(ProvinciaDTO direccionProvinciaDTO) {
        this.direccionProvinciaDTO = direccionProvinciaDTO;
    }

    public LocalidadDTO getDireccionLocalidadDTO() {
        return direccionLocalidadDTO;
    }

    public void setDireccionLocalidadDTO(LocalidadDTO direccionLocalidadDTO) {
        this.direccionLocalidadDTO = direccionLocalidadDTO;
    }

    public BarrioDTO getDireccionBarrioDTO() {
        return direccionBarrioDTO;
    }

    public void setDireccionBarrioDTO(BarrioDTO direccionBarrioDTO) {
        this.direccionBarrioDTO = direccionBarrioDTO;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public List<ClienteDTO> getClientesDTO() {
        return clientesDTO;
    }

    public void setClientesDTO(List<ClienteDTO> clientesDTO) {
        this.clientesDTO = clientesDTO;
    }

    public List<GaranteDTO> getGarantesDTO() {
        return garantesDTO;
    }

    public void setGarantesDTO(List<GaranteDTO> garantesDTO) {
        this.garantesDTO = garantesDTO;
    }

    public List<InmuebleDTO> getInmueblesDTO() {
        return inmueblesDTO;
    }

    public void setInmueblesDTO(List<InmuebleDTO> inmueblesDTO) {
        this.inmueblesDTO = inmueblesDTO;
    }

    public List<AlquilerDTO> getAlquileresDTO() {
        return alquileresDTO;
    }

    public void setAlquileresDTO(List<AlquilerDTO> alquileresDTO) {
        this.alquileresDTO = alquileresDTO;
    }

    public List<VentaDTO> getVentasDTO() {
        return ventasDTO;
    }

    public void setVentasDTO(List<VentaDTO> ventasDTO) {
        this.ventasDTO = ventasDTO;
    }

    public List<RecargoPorMoraDTO> getRecargosPorMorasDTO() {
        return recargosPorMorasDTO;
    }

    public void setRecargosPorMorasDTO(List<RecargoPorMoraDTO> recargosPorMorasDTO) {
        this.recargosPorMorasDTO = recargosPorMorasDTO;
    }

    public List<ArancelEspecialDTO> getArancelesEspecialesDTO() {
        return arancelesEspecialesDTO;
    }

    public void setArancelesEspecialesDTO(List<ArancelEspecialDTO> arancelesEspecialesDTO) {
        this.arancelesEspecialesDTO = arancelesEspecialesDTO;
    }

    public List<UsuarioDTO> getUsuariosDTO() {
        return usuariosDTO;
    }

    public void setUsuariosDTO(List<UsuarioDTO> usuariosDTO) {
        this.usuariosDTO = usuariosDTO;
    }
    
    
}
