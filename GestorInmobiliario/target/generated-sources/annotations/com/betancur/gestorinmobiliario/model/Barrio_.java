package com.betancur.gestorinmobiliario.model;

import com.betancur.gestorinmobiliario.model.Localidad;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-16T17:16:53")
@StaticMetamodel(Barrio.class)
public class Barrio_ { 

    public static volatile SingularAttribute<Barrio, String> codigoPostal;
    public static volatile SingularAttribute<Barrio, Long> id;
    public static volatile SingularAttribute<Barrio, String> nombre;
    public static volatile SingularAttribute<Barrio, Localidad> unaLocalidad;

}