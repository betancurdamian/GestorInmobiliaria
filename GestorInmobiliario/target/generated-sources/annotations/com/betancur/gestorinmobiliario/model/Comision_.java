package com.betancur.gestorinmobiliario.model;

import com.betancur.gestorinmobiliario.model.Contrato;
import com.betancur.gestorinmobiliario.model.LineaDeComision;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-16T17:16:53")
@StaticMetamodel(Comision.class)
public class Comision_ { 

    public static volatile SingularAttribute<Comision, Contrato> unContrato;
    public static volatile SingularAttribute<Comision, Integer> cantidadDeCuotas;
    public static volatile ListAttribute<Comision, LineaDeComision> linesasDecomisiones;
    public static volatile SingularAttribute<Comision, Long> id;
    public static volatile SingularAttribute<Comision, Float> montoTotal;

}