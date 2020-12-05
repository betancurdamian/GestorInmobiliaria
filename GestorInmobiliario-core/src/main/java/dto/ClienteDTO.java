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
public abstract class ClienteDTO extends PersonaDTO{
    
    private InmobiliariaDTO unaInmobiliariaCliente;
    private UsuarioClienteDTO unUsuarioCliente;
    private List<InmuebleDTO> inmuebles;

    public InmobiliariaDTO getUnaInmobiliariaCliente() {
        return unaInmobiliariaCliente;
    }

    public void setUnaInmobiliariaCliente(InmobiliariaDTO unaInmobiliariaCliente) {
        this.unaInmobiliariaCliente = unaInmobiliariaCliente;
    }

    public UsuarioClienteDTO getUnUsuarioCliente() {
        return unUsuarioCliente;
    }

    public void setUnUsuarioCliente(UsuarioClienteDTO unUsuarioCliente) {
        this.unUsuarioCliente = unUsuarioCliente;
    }

    public List<InmuebleDTO> getInmuebles() {
        return inmuebles;
    }

    public void setInmuebles(List<InmuebleDTO> inmuebles) {
        this.inmuebles = inmuebles;
    }
    
    
}
