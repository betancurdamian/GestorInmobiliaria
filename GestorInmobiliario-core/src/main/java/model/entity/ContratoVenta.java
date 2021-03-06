/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Ariel
 */
@Entity
@DiscriminatorValue("CONTRATO VENTA")
public class ContratoVenta extends Contrato {

    @OneToMany(mappedBy = "unContratoVenta", cascade = CascadeType.ALL)
    private List<CuotaVenta> cuotasVenta;
    
    @ManyToOne
    @JoinColumn(name = "fk_venta")
    private Venta unaVenta;

    public ContratoVenta() {
        this.cuotasVenta = new ArrayList<>();
    }

    public ContratoVenta(Venta unaVenta) {
        this.unaVenta = unaVenta;
        this.cuotasVenta = new ArrayList<>();
    }

    public ContratoVenta(Venta unaVenta, Integer unaFechaPrimerVencimiento, Integer unaFechaSegundoVencimiento, Float montoTotal, Integer cantidadDeCuotas, RecargoPorMora unRecargoPorMora, Locador unLocador, Locatario unLocatario) {
        super(unaFechaPrimerVencimiento, unaFechaSegundoVencimiento, montoTotal, cantidadDeCuotas, unRecargoPorMora, unLocador, unLocatario);
        this.unaVenta = unaVenta;
        this.cuotasVenta = new ArrayList<>();
    }

    public List<CuotaVenta> getCuotasVenta() {
        return cuotasVenta;
    }

    public void setCuotasVenta(List<CuotaVenta> cuotasVenta) {
        this.cuotasVenta = cuotasVenta;
    }

    public Venta getUnaVenta() {
        return unaVenta;
    }

    public void setUnaVenta(Venta unaVenta) {
        this.unaVenta = unaVenta;
    }
    
    
}