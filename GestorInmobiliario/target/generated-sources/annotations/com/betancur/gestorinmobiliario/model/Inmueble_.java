package com.betancur.gestorinmobiliario.model;

import com.betancur.gestorinmobiliario.model.Alquiler;
import com.betancur.gestorinmobiliario.model.Barrio;
import com.betancur.gestorinmobiliario.model.Inmobiliaria;
import com.betancur.gestorinmobiliario.model.Localidad;
import com.betancur.gestorinmobiliario.model.Provincia;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-16T17:16:53")
@StaticMetamodel(Inmueble.class)
public abstract class Inmueble_ { 

    public static volatile SingularAttribute<Inmueble, Localidad> direccionLocalidad;
    public static volatile SingularAttribute<Inmueble, Integer> superficieTotal;
    public static volatile SingularAttribute<Inmueble, String> descripcion;
    public static volatile SingularAttribute<Inmueble, String> direccionCalle;
    public static volatile SingularAttribute<Inmueble, Alquiler> unAlquiler;
    public static volatile SingularAttribute<Inmueble, Barrio> direccionBarrio;
    public static volatile SingularAttribute<Inmueble, Long> id;
    public static volatile SingularAttribute<Inmueble, Provincia> direccionProvincia;
    public static volatile SingularAttribute<Inmueble, Inmobiliaria> unaInmobiliariaInmueble;
    public static volatile SingularAttribute<Inmueble, String> direccionNumero;
    public static volatile SingularAttribute<Inmueble, Boolean> disponible;

}