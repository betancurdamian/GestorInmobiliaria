package com.betancur.gestorinmobiliario.model;

import com.betancur.gestorinmobiliario.model.Actividad;
import com.betancur.gestorinmobiliario.model.ComprobanteDeIngreso;
import com.betancur.gestorinmobiliario.model.Garante;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-16T17:16:53")
@StaticMetamodel(Locatario.class)
public abstract class Locatario_ extends Cliente_ {

    public static volatile SingularAttribute<Locatario, Garante> unGarante;
    public static volatile SingularAttribute<Locatario, Actividad> unaActividad;
    public static volatile ListAttribute<Locatario, ComprobanteDeIngreso> comprobantesDeIngresosLocatarios;

}