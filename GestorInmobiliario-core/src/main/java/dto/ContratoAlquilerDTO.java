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
public class ContratoAlquilerDTO extends ContratoDTO{
    private GaranteDTO unGarante;
    private AlquilerDTO unAlquiler;

    public GaranteDTO getUnGarante() {
        return unGarante;
    }

    public void setUnGarante(GaranteDTO unGarante) {
        this.unGarante = unGarante;
    }

    public AlquilerDTO getUnAlquiler() {
        return unAlquiler;
    }

    public void setUnAlquiler(AlquilerDTO unAlquiler) {
        this.unAlquiler = unAlquiler;
    }
    
    
}
