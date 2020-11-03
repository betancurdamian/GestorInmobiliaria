/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Ariel
 */
@Entity
@Table(name = "clientes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cliente")
public abstract class Cliente extends Persona {

    @ManyToOne
    @JoinColumn(name = "fk_inmobiliaria")
    private Inmobiliaria unaInmobiliariaCliente;

    @OneToOne(mappedBy = "unCliente", cascade = CascadeType.ALL)
    private UsuarioCliente unUsuarioCliente;

    public Cliente() {
    }

    public Cliente(Inmobiliaria unaInmobiliariaCliente, UsuarioCliente unUsuarioCliente) {
        this.unaInmobiliariaCliente = unaInmobiliariaCliente;
        this.unUsuarioCliente = unUsuarioCliente;
    }

    public Cliente(Inmobiliaria unaInmobiliariaCliente, UsuarioCliente unUsuarioCliente, String nombre, String apellido, TipoDNI unTipoDNI, String dni, EstadoCivil unEstadoCivil, String direccionCalle, String direccionNumero, Provincia direccionProvincia, Localidad direccionLocalidad, Barrio direccionBarrio, String telefono, String correoElectronico) {
        super(nombre, apellido, unTipoDNI, dni, unEstadoCivil, direccionCalle, direccionNumero, direccionProvincia, direccionLocalidad, direccionBarrio, telefono, correoElectronico);
        this.unaInmobiliariaCliente = unaInmobiliariaCliente;
        this.unUsuarioCliente = unUsuarioCliente;
    }

    

    public void setUnaInmobiliariaCliente(Inmobiliaria unaInmobiliariaCliente) {
        this.unaInmobiliariaCliente = unaInmobiliariaCliente;
    }

    public Inmobiliaria getUnaInmobiliariaCliente() {
        return unaInmobiliariaCliente;
    }

    public UsuarioCliente getUnUsuarioCliente() {
        return unUsuarioCliente;
    }

    public void setUnUsuarioCliente(UsuarioCliente unUsuarioCliente) {
        this.unUsuarioCliente = unUsuarioCliente;
    }

}
