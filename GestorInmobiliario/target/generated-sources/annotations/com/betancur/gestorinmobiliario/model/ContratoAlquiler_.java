package com.betancur.gestorinmobiliario.model;

import com.betancur.gestorinmobiliario.model.Alquiler;
import com.betancur.gestorinmobiliario.model.Garante;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-16T17:16:53")
@StaticMetamodel(ContratoAlquiler.class)
public class ContratoAlquiler_ extends Contrato_ {

    public static volatile SingularAttribute<ContratoAlquiler, Garante> unGarante;
    public static volatile SingularAttribute<ContratoAlquiler, Alquiler> unAlquiler;

}