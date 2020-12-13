/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.Impl.facade;

import dto.UsuarioClienteDTO;
import dto.UsuarioDTO;
import dto.UsuarioEmpresaDTO;
import model.service.Impl.UsuarioServiceImpl;

public class Login {

    private final UsuarioServiceImpl usuarioService;

    public Login() {
        this.usuarioService = new UsuarioServiceImpl();
    }

    public UsuarioDTO ingresar(UsuarioDTO unUsuario) {
        UsuarioDTO usuarioLogin = null;
        if (unUsuario != null) {
            if (unUsuario.getUserName() != null) {
                if (unUsuario.getPassword() != null) {
                    for (UsuarioDTO u : usuarioService.listarTodos()) {
                        if (u.getUserName().equals(unUsuario.getUserName())) {
                            if (u.getPassword().equals(unUsuario.getPassword())) {
                                if (u instanceof UsuarioEmpresaDTO) {
                                    usuarioLogin = usuarioService.listarUsuarioEmpresaID(u.getId());
                                }
                                if (u instanceof UsuarioClienteDTO) {
                                    usuarioLogin = usuarioService.listarUsuarioClienteID(u.getId());
                                }
                            }
                        }
                    }
                }
            }
        }
        return usuarioLogin;
    }
}
