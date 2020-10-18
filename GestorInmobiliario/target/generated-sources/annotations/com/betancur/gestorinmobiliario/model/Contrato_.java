package com.betancur.gestorinmobiliario.model;

import com.betancur.gestorinmobiliario.model.ArancelEspecial;
import com.betancur.gestorinmobiliario.model.BoletaDePago;
import com.betancur.gestorinmobiliario.model.Comision;
import com.betancur.gestorinmobiliario.model.Locador;
import com.betancur.gestorinmobiliario.model.Locatario;
import com.betancur.gestorinmobiliario.model.RecargoPorMora;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-16T17:16:53")
@StaticMetamodel(Contrato.class)
public abstract class Contrato_ { 

    public static volatile SingularAttribute<Contrato, Date> unaFechaPrimerVencimiento;
    public static volatile SingularAttribute<Contrato, Locador> unLocador;
    public static volatile SingularAttribute<Contrato, Integer> cantidadDeCuotas;
    public static volatile SingularAttribute<Contrato, Locatario> unLocatario;
    public static volatile SingularAttribute<Contrato, Comision> unaComision;
    public static volatile ListAttribute<Contrato, ArancelEspecial> arancelesEspeciales;
    public static volatile ListAttribute<Contrato, BoletaDePago> boletasDePago;
    public static volatile SingularAttribute<Contrato, Long> id;
    public static volatile SingularAttribute<Contrato, RecargoPorMora> unRecargoPorMora;
    public static volatile SingularAttribute<Contrato, Date> unaFechaSegundoVencimiento;
    public static volatile SingularAttribute<Contrato, Float> montoTotal;

}