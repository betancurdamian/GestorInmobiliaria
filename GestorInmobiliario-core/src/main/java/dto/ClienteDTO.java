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
public abstract class ClienteDTO extends PersonaDTO{
    
    private InmobiliariaDTO unaInmobiliariaCliente;
    private UsuarioClienteDTO unUsuarioCliente;

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
    
    
}
