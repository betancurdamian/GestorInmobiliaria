package com.betancur.gestorinmobiliario.model;

import com.betancur.gestorinmobiliario.model.Inmobiliaria;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-16T17:16:53")
@StaticMetamodel(RecargoPorMora.class)
public class RecargoPorMora_ { 

    public static volatile SingularAttribute<RecargoPorMora, Float> monto;
    public static volatile SingularAttribute<RecargoPorMora, Date> unaFechaDeRecargo;
    public static volatile SingularAttribute<RecargoPorMora, Long> id;
    public static volatile SingularAttribute<RecargoPorMora, Inmobiliaria> unaInmobiliariaRecargoPorMora;

}