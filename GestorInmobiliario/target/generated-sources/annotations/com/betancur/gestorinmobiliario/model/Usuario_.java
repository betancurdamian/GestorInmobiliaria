package com.betancur.gestorinmobiliario.model;

import com.betancur.gestorinmobiliario.model.Inmobiliaria;
import com.betancur.gestorinmobiliario.model.TipoUsuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-16T17:16:53")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ { 

    public static volatile SingularAttribute<Usuario, TipoUsuario> unTipoUsuario;
    public static volatile SingularAttribute<Usuario, String> password;
    public static volatile SingularAttribute<Usuario, Inmobiliaria> unaInmobiliariaUsuario;
    public static volatile SingularAttribute<Usuario, Long> id;
    public static volatile SingularAttribute<Usuario, String> userName;

}