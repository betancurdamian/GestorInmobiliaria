package com.betancur.gestorinmobiliario.model.entity;

import com.betancur.gestorinmobiliario.model.entity.CuotaVenta;
import com.betancur.gestorinmobiliario.model.entity.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-18T13:31:33")
@StaticMetamodel(ContratoVenta.class)
public class ContratoVenta_ extends Contrato_ {

    public static volatile ListAttribute<ContratoVenta, CuotaVenta> cuotasVenta;
    public static volatile SingularAttribute<ContratoVenta, Venta> unaVenta;

}