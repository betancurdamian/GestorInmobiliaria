package com.betancur.gestorinmobiliario.model.entity;

import com.betancur.gestorinmobiliario.model.entity.Alquiler;
import com.betancur.gestorinmobiliario.model.entity.ArancelEspecial;
import com.betancur.gestorinmobiliario.model.entity.Barrio;
import com.betancur.gestorinmobiliario.model.entity.Cliente;
import com.betancur.gestorinmobiliario.model.entity.Garante;
import com.betancur.gestorinmobiliario.model.entity.Inmueble;
import com.betancur.gestorinmobiliario.model.entity.Localidad;
import com.betancur.gestorinmobiliario.model.entity.Provincia;
import com.betancur.gestorinmobiliario.model.entity.RecargoPorMora;
import com.betancur.gestorinmobiliario.model.entity.Usuario;
import com.betancur.gestorinmobiliario.model.entity.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-18T13:26:19")
@StaticMetamodel(Inmobiliaria.class)
public class Inmobiliaria_ { 

    public static volatile SingularAttribute<Inmobiliaria, Localidad> direccionLocalidad;
    public static volatile SingularAttribute<Inmobiliaria, String> direccionCalle;
    public static volatile ListAttribute<Inmobiliaria, RecargoPorMora> recargosPorMoras;
    public static volatile ListAttribute<Inmobiliaria, Usuario> usuarios;
    public static volatile ListAttribute<Inmobiliaria, Inmueble> inmuebles;
    public static volatile SingularAttribute<Inmobiliaria, String> razonSocial;
    public static volatile SingularAttribute<Inmobiliaria, String> cuit;
    public static volatile ListAttribute<Inmobiliaria, Alquiler> alquileres;
    public static volatile SingularAttribute<Inmobiliaria, Barrio> direccionBarrio;
    public static volatile ListAttribute<Inmobiliaria, Garante> garantes;
    public static volatile ListAttribute<Inmobiliaria, ArancelEspecial> arancelesEspeciales;
    public static volatile ListAttribute<Inmobiliaria, Venta> ventas;
    public static volatile SingularAttribute<Inmobiliaria, Long> id;
    public static volatile SingularAttribute<Inmobiliaria, Provincia> direccionProvincia;
    public static volatile SingularAttribute<Inmobiliaria, String> telefono;
    public static volatile ListAttribute<Inmobiliaria, Cliente> clientes;
    public static volatile SingularAttribute<Inmobiliaria, String> correoElectronico;
    public static volatile SingularAttribute<Inmobiliaria, String> direccionNumero;

}