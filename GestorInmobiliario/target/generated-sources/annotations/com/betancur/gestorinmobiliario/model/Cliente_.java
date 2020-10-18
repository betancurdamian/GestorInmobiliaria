package com.betancur.gestorinmobiliario.model;

import com.betancur.gestorinmobiliario.model.Inmobiliaria;
import com.betancur.gestorinmobiliario.model.UsuarioCliente;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-16T17:16:53")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ extends Persona_ {

    public static volatile SingularAttribute<Cliente, Inmobiliaria> unaInmobiliariaCliente;
    public static volatile SingularAttribute<Cliente, UsuarioCliente> unUsuarioCliente;

}