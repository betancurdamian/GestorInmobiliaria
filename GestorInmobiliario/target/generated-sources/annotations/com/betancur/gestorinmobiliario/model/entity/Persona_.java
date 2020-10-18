package com.betancur.gestorinmobiliario.model.entity;

import com.betancur.gestorinmobiliario.model.entity.Barrio;
import com.betancur.gestorinmobiliario.model.entity.EstadoCivil;
import com.betancur.gestorinmobiliario.model.entity.Localidad;
import com.betancur.gestorinmobiliario.model.entity.Provincia;
import com.betancur.gestorinmobiliario.model.entity.TipoDNI;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-18T13:31:33")
@StaticMetamodel(Persona.class)
public abstract class Persona_ { 

    public static volatile SingularAttribute<Persona, Localidad> direccionLocalidad;
    public static volatile SingularAttribute<Persona, String> direccionCalle;
    public static volatile SingularAttribute<Persona, TipoDNI> unTipoDNI;
    public static volatile SingularAttribute<Persona, String> nombre;
    public static volatile SingularAttribute<Persona, Barrio> direccionBarrio;
    public static volatile SingularAttribute<Persona, String> apellido;
    public static volatile SingularAttribute<Persona, Long> id;
    public static volatile SingularAttribute<Persona, Provincia> direccionProvincia;
    public static volatile SingularAttribute<Persona, EstadoCivil> unEstadoCivil;
    public static volatile SingularAttribute<Persona, String> telefono;
    public static volatile SingularAttribute<Persona, String> correoElectronico;
    public static volatile SingularAttribute<Persona, String> dni;
    public static volatile SingularAttribute<Persona, String> direccionNumero;

}