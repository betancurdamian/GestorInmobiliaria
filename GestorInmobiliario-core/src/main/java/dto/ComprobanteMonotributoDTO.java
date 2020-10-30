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
    private String cuit;
    private boolean estadoMonotributo;

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public boolean isEstadoMonotributo() {
        return estadoMonotributo;
    }

    public void setEstadoMonotributo(boolean estadoMonotributo) {
        this.estadoMonotributo = estadoMonotributo;
    }
    
}
