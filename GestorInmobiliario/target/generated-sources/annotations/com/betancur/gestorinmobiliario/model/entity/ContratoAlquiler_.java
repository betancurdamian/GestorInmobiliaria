package com.betancur.gestorinmobiliario.model.entity;

import com.betancur.gestorinmobiliario.model.entity.Alquiler;
import com.betancur.gestorinmobiliario.model.entity.Garante;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-18T13:26:19")
@StaticMetamodel(ContratoAlquiler.class)
public class ContratoAlquiler_ extends Contrato_ {

    public static volatile SingularAttribute<ContratoAlquiler, Garante> unGarante;
    public static volatile SingularAttribute<ContratoAlquiler, Alquiler> unAlquiler;

}