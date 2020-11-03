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
public class UsuarioClienteDTO extends UsuarioDTO{
    private ClienteDTO unCliente;

    public ClienteDTO getUnCliente() {
        return unCliente;
    }

    public void setUnCliente(ClienteDTO unCliente) {
        this.unCliente = unCliente;
    }
    
    
}
