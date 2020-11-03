/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Ariel
 */
public abstract class PersonaDTO {
    private Long id;
    private String nombre;    
    private String apellido;
    private TipoDNIDTO unTipoDNI;
    private String dni;
    private EstadoCivilDTO unEstadoCivil;
    private String direccionCalle;
    private String direccionNumero;
    private ProvinciaDTO direccionProvincia;
    private LocalidadDTO direccionLocalidad;
    private BarrioDTO direccionBarrio;
    private String telefono;
    private String correoElectronico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public TipoDNIDTO getUnTipoDNI() {
        return unTipoDNI;
    }

    public void setUnTipoDNI(TipoDNIDTO unTipoDNI) {
        this.unTipoDNI = unTipoDNI;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public EstadoCivilDTO getUnEstadoCivil() {
        return unEstadoCivil;
    }

    public void setUnEstadoCivil(EstadoCivilDTO unEstadoCivil) {
        this.unEstadoCivil = unEstadoCivil;
    }

    public String getDireccionCalle() {
        return direccionCalle;
    }

    public void setDireccionCalle(String direccionCalle) {
        this.direccionCalle = direccionCalle;
    }

    public String getDireccionNumero() {
        return direccionNumero;
    }

    public void setDireccionNumero(String direccionNumero) {
        this.direccionNumero = direccionNumero;
    }

    public ProvinciaDTO getDireccionProvincia() {
        return direccionProvincia;
    }

    public void setDireccionProvincia(ProvinciaDTO direccionProvincia) {
        this.direccionProvincia = direccionProvincia;
    }

    public LocalidadDTO getDireccionLocalidad() {
        return direccionLocalidad;
    }

    public void setDireccionLocalidad(LocalidadDTO direccionLocalidad) {
        this.direccionLocalidad = direccionLocalidad;
    }

    public BarrioDTO getDireccionBarrio() {
        return direccionBarrio;
    }

    public void setDireccionBarrio(BarrioDTO direccionBarrio) {
        this.direccionBarrio = direccionBarrio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
    
}
