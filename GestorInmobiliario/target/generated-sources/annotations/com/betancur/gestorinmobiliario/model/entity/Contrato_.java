package com.betancur.gestorinmobiliario.model.entity;

import com.betancur.gestorinmobiliario.model.entity.ArancelEspecial;
import com.betancur.gestorinmobiliario.model.entity.BoletaDePago;
import com.betancur.gestorinmobiliario.model.entity.Comision;
import com.betancur.gestorinmobiliario.model.entity.Locador;
import com.betancur.gestorinmobiliario.model.entity.Locatario;
import com.betancur.gestorinmobiliario.model.entity.RecargoPorMora;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-18T13:31:33")
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