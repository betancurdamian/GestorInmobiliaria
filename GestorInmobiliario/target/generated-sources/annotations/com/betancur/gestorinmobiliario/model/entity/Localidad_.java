package com.betancur.gestorinmobiliario.model.entity;

import com.betancur.gestorinmobiliario.model.entity.Barrio;
import com.betancur.gestorinmobiliario.model.entity.Provincia;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-18T13:26:19")
@StaticMetamodel(Localidad.class)
public class Localidad_ { 

    public static volatile SingularAttribute<Localidad, String> codigoPostal;
    public static volatile SingularAttribute<Localidad, Provincia> unaProvincia;
    public static volatile ListAttribute<Localidad, Barrio> barrios;
    public static volatile SingularAttribute<Localidad, Long> id;
    public static volatile SingularAttribute<Localidad, String> nombre;

}