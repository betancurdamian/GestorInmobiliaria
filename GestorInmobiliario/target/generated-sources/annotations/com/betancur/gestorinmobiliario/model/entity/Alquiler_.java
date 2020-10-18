package com.betancur.gestorinmobiliario.model.entity;

import com.betancur.gestorinmobiliario.model.entity.ContratoAlquiler;
import com.betancur.gestorinmobiliario.model.entity.Inmobiliaria;
import com.betancur.gestorinmobiliario.model.entity.Inmueble;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-18T13:31:33")
@StaticMetamodel(Alquiler.class)
public class Alquiler_ { 

    public static volatile SingularAttribute<Alquiler, Date> unaFechaInicio;
    public static volatile SingularAttribute<Alquiler, Inmueble> unInmueble;
    public static volatile SingularAttribute<Alquiler, Date> unaFechaFin;
    public static volatile SingularAttribute<Alquiler, Inmobiliaria> unaInmobiliariaAlquiler;
    public static volatile SingularAttribute<Alquiler, Long> id;
    public static volatile SingularAttribute<Alquiler, ContratoAlquiler> unContratoAlquiler;
    public static volatile SingularAttribute<Alquiler, Boolean> disponible;

}