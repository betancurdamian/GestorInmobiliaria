package com.betancur.gestorinmobiliario.model;

import com.betancur.gestorinmobiliario.model.CuotaVenta;
import com.betancur.gestorinmobiliario.model.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-16T17:16:53")
@StaticMetamodel(ContratoVenta.class)
public class ContratoVenta_ extends Contrato_ {

    public static volatile ListAttribute<ContratoVenta, CuotaVenta> cuotasVenta;
    public static volatile SingularAttribute<ContratoVenta, Venta> unaVenta;

}