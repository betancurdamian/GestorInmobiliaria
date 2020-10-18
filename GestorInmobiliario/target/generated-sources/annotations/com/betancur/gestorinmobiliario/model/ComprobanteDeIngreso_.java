package com.betancur.gestorinmobiliario.model;

import com.betancur.gestorinmobiliario.model.Garante;
import com.betancur.gestorinmobiliario.model.Locatario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-16T17:16:53")
@StaticMetamodel(ComprobanteDeIngreso.class)
public abstract class ComprobanteDeIngreso_ { 

    public static volatile SingularAttribute<ComprobanteDeIngreso, Float> importeNeto;
    public static volatile SingularAttribute<ComprobanteDeIngreso, Float> importeBruto;
    public static volatile ListAttribute<ComprobanteDeIngreso, Garante> garantes;
    public static volatile SingularAttribute<ComprobanteDeIngreso, Integer> mes;
    public static volatile SingularAttribute<ComprobanteDeIngreso, Long> id;
    public static volatile SingularAttribute<ComprobanteDeIngreso, Integer> anio;
    public static volatile ListAttribute<ComprobanteDeIngreso, Locatario> locatarios;

}