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
import javax.persistence.OneToMany;

/**
 *
 * @author Ariel
 */
@Entity
@DiscriminatorValue("LOCADOR")
public class Locador extends Cliente {
    
    @OneToMany(mappedBy = "unLocador", cascade = CascadeType.ALL)
    private List<Inmueble> inmuebles;
    
    public Locador() {
        this.inmuebles = new ArrayList<>();
    }

    public Locador(Inmobiliaria unaInmobiliariaCliente, UsuarioCliente unUsuarioCliente) {
        super(unaInmobiliariaCliente, unUsuarioCliente);
        this.inmuebles = new ArrayList<>();
    }

    public Locador(Inmobiliaria unaInmobiliariaCliente, UsuarioCliente unUsuarioCliente, String nombre, String apellido, TipoDNI unTipoDNI, String dni, EstadoCivil unEstadoCivil, String direccionCalle, String direccionNumero, Provincia direccionProvincia, Localidad direccionLocalidad, Barrio direccionBarrio, String telefono, String correoElectronico) {
        super(unaInmobiliariaCliente, unUsuarioCliente, nombre, apellido, unTipoDNI, dni, unEstadoCivil, direccionCalle, direccionNumero, direccionProvincia, direccionLocalidad, direccionBarrio, telefono, correoElectronico);
        this.inmuebles = new ArrayList<>();
    }

    public List<Inmueble> getInmuebles() {
        return inmuebles;
    }

    public void setInmuebles(List<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
    }

    
}
