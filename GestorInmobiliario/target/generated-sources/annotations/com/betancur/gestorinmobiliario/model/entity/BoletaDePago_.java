package com.betancur.gestorinmobiliario.model.entity;

import com.betancur.gestorinmobiliario.model.entity.Contrato;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-18T13:26:19")
@StaticMetamodel(BoletaDePago.class)
public class BoletaDePago_ { 

    public static volatile SingularAttribute<BoletaDePago, Contrato> unContrato;
    public static volatile SingularAttribute<BoletaDePago, Float> monto;
    public static volatile SingularAttribute<BoletaDePago, Boolean> pagado;
    public static volatile SingularAttribute<BoletaDePago, String> numeroBoleta;
    public static volatile SingularAttribute<BoletaDePago, Integer> numeroCuota;
    public static volatile SingularAttribute<BoletaDePago, Long> id;
    public static volatile SingularAttribute<BoletaDePago, Date> fechaPago;

}