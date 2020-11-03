/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.time.LocalDate;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Ariel
 */
@Entity
@DiscriminatorValue("CONTRATO ALQUILER")
public class ContratoAlquiler extends Contrato {

    @ManyToOne
    @JoinColumn(name = "fk_garante")
    private Garante unGarante;

    @ManyToOne
    @JoinColumn(name = "fk_alquiler")
    private Alquiler unAlquiler;

    public ContratoAlquiler() {
    }

    public ContratoAlquiler(Garante unGarante, Alquiler unAlquiler) {
        this.unGarante = unGarante;
        this.unAlquiler = unAlquiler;
    }

    public ContratoAlquiler(Garante unGarante, Alquiler unAlquiler, LocalDate unaFechaPrimerVencimiento, LocalDate unaFechaSegundoVencimiento, Float montoTotal, Integer cantidadDeCuotas, RecargoPorMora unRecargoPorMora, Locador unLocador, Locatario unLocatario) {
        super(unaFechaPrimerVencimiento, unaFechaSegundoVencimiento, montoTotal, cantidadDeCuotas, unRecargoPorMora, unLocador, unLocatario);
        this.unGarante = unGarante;
        this.unAlquiler = unAlquiler;
    }

    public Garante getUnGarante() {
        return unGarante;
    }

    public void setUnGarante(Garante unGarante) {
        this.unGarante = unGarante;
    }

    public Alquiler getUnAlquiler() {
        return unAlquiler;
    }

    public void setUnAlquiler(Alquiler unAlquiler) {
        this.unAlquiler = unAlquiler;
    }

    
}
