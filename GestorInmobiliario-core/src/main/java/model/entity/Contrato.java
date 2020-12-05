/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ariel
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_contrato")
@Table(name = "contratos")
public abstract class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "dia_primer_vencimiento")
    private Integer unDiaPrimerVencimiento;

    @Column(name = "dia_segundo_vencimiento")
    private Integer unDiaSegundoVencimiento;

    @Column(name = "monto_total")
    private Float montoTotal;

    @Column(name = "cantidad_cuota")
    private Integer cantidadDeCuotas;

    @ManyToOne
    @JoinColumn(name = "fk_recargo_mora")
    private RecargoPorMora unRecargoPorMora;

    @ManyToOne
    @JoinColumn(name = "fk_locador")
    private Cliente unLocador;

    @ManyToOne
    @JoinColumn(name = "fk_locatario")
    private Cliente unLocatario;

    @ManyToOne
    @JoinColumn(name = "fk_comision")
    private Comision unaComision;

    @OneToMany
    private List<ArancelEspecial> arancelesEspeciales;
    
    @OneToMany(mappedBy = "unContrato", cascade = CascadeType.ALL)
    private List<BoletaDePago> boletasDePago;

    public Contrato() {
        this.arancelesEspeciales = new ArrayList<>();
        this.boletasDePago = new ArrayList<>();
    }

    public Contrato(Integer unaFechaPrimerVencimiento, Integer unaFechaSegundoVencimiento, Float montoTotal, Integer cantidadDeCuotas, RecargoPorMora unRecargoPorMora, Locador unLocador, Locatario unLocatario) {
        this.unDiaPrimerVencimiento = unaFechaPrimerVencimiento;
        this.unDiaSegundoVencimiento = unaFechaSegundoVencimiento;
        this.montoTotal = montoTotal;
        this.cantidadDeCuotas = cantidadDeCuotas;
        this.unRecargoPorMora = unRecargoPorMora;
        this.unLocador = unLocador;
        this.unLocatario = unLocatario;
        this.arancelesEspeciales = new ArrayList<>();
        this.boletasDePago = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUnDiaPrimerVencimiento() {
        return unDiaPrimerVencimiento;
    }

    public void setUnDiaPrimerVencimiento(Integer unDiaPrimerVencimiento) {
        this.unDiaPrimerVencimiento = unDiaPrimerVencimiento;
    }

    public Integer getUnDiaSegundoVencimiento() {
        return unDiaSegundoVencimiento;
    }

    public void setUnDiaSegundoVencimiento(Integer unDiaSegundoVencimiento) {
        this.unDiaSegundoVencimiento = unDiaSegundoVencimiento;
    }

    public Float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getCantidadDeCuotas() {
        return cantidadDeCuotas;
    }

    public void setCantidadDeCuotas(Integer cantidadDeCuotas) {
        this.cantidadDeCuotas = cantidadDeCuotas;
    }

    public RecargoPorMora getUnRecargoPorMora() {
        return unRecargoPorMora;
    }

    public void setUnRecargoPorMora(RecargoPorMora unRecargoPorMora) {
        this.unRecargoPorMora = unRecargoPorMora;
    }

    public Cliente getUnLocador() {
        return unLocador;
    }

    public void setUnLocador(Cliente unLocador) {
        this.unLocador = unLocador;
    }

    public Cliente getUnLocatario() {
        return unLocatario;
    }

    public void setUnLocatario(Cliente unLocatario) {
        this.unLocatario = unLocatario;
    }

    public Comision getUnaComision() {
        return unaComision;
    }

    public void setUnaComision(Comision unaComision) {
        this.unaComision = unaComision;
    }

    public List<ArancelEspecial> getArancelesEspeciales() {
        return arancelesEspeciales;
    }

    public void setArancelesEspeciales(List<ArancelEspecial> arancelesEspeciales) {
        this.arancelesEspeciales = arancelesEspeciales;
    }

    public List<BoletaDePago> getBoletasDePago() {
        return boletasDePago;
    }

    public void setBoletasDePago(List<BoletaDePago> boletasDePago) {
        this.boletasDePago = boletasDePago;
    }

    
}
