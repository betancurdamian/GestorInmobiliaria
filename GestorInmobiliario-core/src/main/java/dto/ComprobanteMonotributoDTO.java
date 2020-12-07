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
public class ComprobanteMonotributoDTO extends ComprobanteDeIngresoDTO{
    
    private Boolean estadoMonotributo;    

    public Boolean getEstadoMonotributo() {
        return estadoMonotributo;
    }

    public void setEstadoMonotributo(Boolean estadoMonotributo) {
        this.estadoMonotributo = estadoMonotributo;
    }

    
    
}
