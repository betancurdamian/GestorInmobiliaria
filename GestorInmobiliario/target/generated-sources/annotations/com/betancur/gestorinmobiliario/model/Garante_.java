package com.betancur.gestorinmobiliario.model;

import com.betancur.gestorinmobiliario.model.Actividad;
import com.betancur.gestorinmobiliario.model.ComprobanteDeIngreso;
import com.betancur.gestorinmobiliario.model.ContratoAlquiler;
import com.betancur.gestorinmobiliario.model.Inmobiliaria;
import com.betancur.gestorinmobiliario.model.Locatario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-16T17:16:53")
@StaticMetamodel(Garante.class)
public abstract class Garante_ extends Persona_ {

    public static volatile ListAttribute<Garante, ComprobanteDeIngreso> comprobantesDeIngresosGarantes;
    public static volatile SingularAttribute<Garante, Locatario> unLocatario;
    public static volatile SingularAttribute<Garante, Actividad> unaActividad;
    public static volatile SingularAttribute<Garante, Inmobiliaria> unaInmobiliariaGarante;
    public static volatile SingularAttribute<Garante, ContratoAlquiler> unContratoAlquiler;

}