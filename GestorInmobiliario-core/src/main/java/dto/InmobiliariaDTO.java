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
    private ProvinciaDTO direccionProvincia;
    private LocalidadDTO direccionLocalidad;
    private BarrioDTO direccionBarrio;
    private String telefono;
    private String correoElectronico;
    private List<ClienteDTO> clientes;
    private List<GaranteDTO> garantes;
    private List<InmuebleDTO> inmuebles;
    private List<AlquilerDTO> alquileres;
    private List<VentaDTO> ventas;
    private List<RecargoPorMoraDTO> recargosPorMoras;    
    private List<ArancelEspecialDTO> arancelesEspeciales;    
    private List<UsuarioDTO> usuarios;

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

    public List<ClienteDTO> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteDTO> clientes) {
        this.clientes = clientes;
    }

    public List<GaranteDTO> getGarantes() {
        return garantes;
    }

    public void setGarantes(List<GaranteDTO> garantes) {
        this.garantes = garantes;
    }

    public List<InmuebleDTO> getInmuebles() {
        return inmuebles;
    }

    public void setInmuebles(List<InmuebleDTO> inmuebles) {
        this.inmuebles = inmuebles;
    }

    public List<AlquilerDTO> getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(List<AlquilerDTO> alquileres) {
        this.alquileres = alquileres;
    }

    public List<VentaDTO> getVentas() {
        return ventas;
    }

    public void setVentas(List<VentaDTO> ventas) {
        this.ventas = ventas;
    }

    public List<RecargoPorMoraDTO> getRecargosPorMoras() {
        return recargosPorMoras;
    }

    public void setRecargosPorMoras(List<RecargoPorMoraDTO> recargosPorMoras) {
        this.recargosPorMoras = recargosPorMoras;
    }

    public List<ArancelEspecialDTO> getArancelesEspeciales() {
        return arancelesEspeciales;
    }

    public void setArancelesEspeciales(List<ArancelEspecialDTO> arancelesEspeciales) {
        this.arancelesEspeciales = arancelesEspeciales;
    }

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }
    
    
}
