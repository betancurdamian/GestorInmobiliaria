package com.betancur.gestorinmobiliario.model;

import com.betancur.gestorinmobiliario.model.Localidad;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-16T17:16:53")
@StaticMetamodel(Provincia.class)
public class Provincia_ { 

    public static volatile SingularAttribute<Provincia, String> codigoPostal;
    public static volatile SingularAttribute<Provincia, Long> id;
    public static volatile SingularAttribute<Provincia, String> nombre;
    public static volatile ListAttribute<Provincia, Localidad> localidades;

}