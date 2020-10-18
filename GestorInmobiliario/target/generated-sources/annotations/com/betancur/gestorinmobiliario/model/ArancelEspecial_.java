package com.betancur.gestorinmobiliario.model;

import com.betancur.gestorinmobiliario.model.Inmobiliaria;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-16T17:16:53")
@StaticMetamodel(ArancelEspecial.class)
public abstract class ArancelEspecial_ { 

    public static volatile SingularAttribute<ArancelEspecial, String> descripcion;
    public static volatile SingularAttribute<ArancelEspecial, Float> monto;
    public static volatile SingularAttribute<ArancelEspecial, Date> unaFechaDeRecargo;
    public static volatile SingularAttribute<ArancelEspecial, Inmobiliaria> unaInmobiliariaArancelEspecial;
    public static volatile SingularAttribute<ArancelEspecial, Long> id;

}