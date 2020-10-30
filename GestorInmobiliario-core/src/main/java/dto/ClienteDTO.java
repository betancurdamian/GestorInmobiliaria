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
    
    private InmobiliariaDTO unaInmobiliariaClienteDTO;
    private UsuarioClienteDTO unUsuarioClienteDTO;

    public InmobiliariaDTO getUnaInmobiliariaClienteDTO() {
        return unaInmobiliariaClienteDTO;
    }

    public void setUnaInmobiliariaClienteDTO(InmobiliariaDTO unaInmobiliariaClienteDTO) {
        this.unaInmobiliariaClienteDTO = unaInmobiliariaClienteDTO;
    }

    public UsuarioClienteDTO getUnUsuarioClienteDTO() {
        return unUsuarioClienteDTO;
    }

    public void setUnUsuarioClienteDTO(UsuarioClienteDTO unUsuarioClienteDTO) {
        this.unUsuarioClienteDTO = unUsuarioClienteDTO;
    }
    
    
}
