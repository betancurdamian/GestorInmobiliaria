package com.betancur.gestorinmobiliario.model.entity;

import com.betancur.gestorinmobiliario.model.entity.ContratoVenta;
import com.betancur.gestorinmobiliario.model.entity.Inmobiliaria;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-18T13:31:33")
@StaticMetamodel(Venta.class)
public class Venta_ { 

    public static volatile SingularAttribute<Venta, Boolean> completa;
    public static volatile SingularAttribute<Venta, ContratoVenta> unContratoVenta;
    public static volatile SingularAttribute<Venta, Date> unaFechaVenta;
    public static volatile SingularAttribute<Venta, Long> id;
    public static volatile SingularAttribute<Venta, Inmobiliaria> unaInmobiliariaVenta;

}