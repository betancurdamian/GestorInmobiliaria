package model.service.Impl.facade;

import dto.ArancelEspecialDTO;
import dto.ClienteDTO;
import dto.ComisionDTO;
import dto.ContratoVentaDTO;
import dto.CuotaVentaDTO;
import dto.InmuebleDTO;
import dto.LineaDeComisionDTO;
import dto.LocadorDTO;
import dto.LocatarioDTO;
import dto.VentaDTO;
import java.util.ArrayList;
import java.util.List;
import model.service.Impl.ArancelEspecialServiceImpl;
import model.service.Impl.ClienteServiceImpl;
import model.service.Impl.InmobiliariaServiceImpl;
import model.service.Impl.InmuebleServiceImpl;
import model.service.Impl.RecargoPorMoraServiceImpl;
import model.service.Impl.VentaServiceImpl;

public class ProcesarVenta {

    private VentaDTO nuevaVenta;
    private ClienteDTO clienteCompradorSeleccionado;

    private final InmobiliariaServiceImpl inmobiliariaService;
    private final ClienteServiceImpl clienteService;
    private final InmuebleServiceImpl inmuebleService;
    private final ArancelEspecialServiceImpl arancelEspecialService;
    private final RecargoPorMoraServiceImpl recargoPorMoraService;
    private final VentaServiceImpl ventaService;

    public ProcesarVenta() {
        this.inmobiliariaService = new InmobiliariaServiceImpl();
        this.clienteService = new ClienteServiceImpl();
        this.inmuebleService = new InmuebleServiceImpl();
        this.arancelEspecialService = new ArancelEspecialServiceImpl();
        this.recargoPorMoraService = new RecargoPorMoraServiceImpl();
        this.ventaService = new VentaServiceImpl();
    }

    public void crearNuevaVenta(String fechaActual) {
        this.nuevaVenta = new VentaDTO();
        this.nuevaVenta.setUnaFechaVenta(fechaActual);
        this.nuevaVenta.setCompleta(Boolean.FALSE);
        this.nuevaVenta.setUnaInmobiliariaVenta(this.inmobiliariaService.obtenerPrimeraInmobiliaria());
    }

    public List<ClienteDTO> listarClientes() {
        return this.clienteService.listarTodos();
    }

    public void seleccionarClienteComprador(ClienteDTO unClienteComprador) {
        if (unClienteComprador != null) {
            this.clienteCompradorSeleccionado = clienteService.listarID(unClienteComprador.getId());
        } else {
            System.out.println("El locatario no existe");
        }
    }

    public List<InmuebleDTO> listarInmueblesDisponibles() {
        List<InmuebleDTO> inmueblesDisponibles = new ArrayList();
        for (InmuebleDTO i : this.inmuebleService.listarTodos()) {
            if (i.getDisponible()) {
                if (i.getUnCliente().equals(this.clienteCompradorSeleccionado)) {
                    inmueblesDisponibles.add(i);
                }                
            }
        }
        return inmueblesDisponibles;
    }

    public void crearContrato(int diaPrimerVencimiento, int diaSegundoVencimiento) {
        ContratoVentaDTO contratoVenta = new ContratoVentaDTO();
        contratoVenta.setUnDiaPrimerVencimiento(diaPrimerVencimiento);
        contratoVenta.setUnDiaSegundoVencimiento(diaSegundoVencimiento);
        this.nuevaVenta.setUnContratoVenta(contratoVenta);
        this.nuevaVenta.getUnContratoVenta().setUnLocatario(this.clienteCompradorSeleccionado);
        this.nuevaVenta.getUnContratoVenta().setUnRecargoPorMora(this.recargoPorMoraService.obtenerUltimoRecargoPorMora());
    }

    public void agregarInmueble(InmuebleDTO unInmueble) {
        if (unInmueble != null) {
            this.nuevaVenta.setUnInmuebleVenta(inmuebleService.listarID(unInmueble.getId()));
            this.nuevaVenta.getUnContratoVenta().setMontoTotal(this.nuevaVenta.getUnInmuebleVenta().getPrecioBaseVenta());
            this.nuevaVenta.getUnContratoVenta().setUnLocador((LocadorDTO) this.nuevaVenta.getUnInmuebleVenta().getUnCliente());
        } else {
            System.out.println("El Inmueble no existe");
        }
    }

    public void agregarCuotas(int cantidadCuota) {
        List<CuotaVentaDTO> cuotasVentas = new ArrayList();
        if (cantidadCuota > 1) {
            this.nuevaVenta.getUnContratoVenta().setCantidadDeCuotas(cantidadCuota);
            for (int i = 1; i <= cantidadCuota; i++) {
                CuotaVentaDTO cuotaVentaAux = new CuotaVentaDTO();
                cuotaVentaAux.setNumeroCuota(i);
                cuotaVentaAux.setMontoCuota(this.nuevaVenta.getUnContratoVenta().getMontoTotal() / cantidadCuota);
                cuotasVentas.add(cuotaVentaAux);
            }
        } else {
            this.nuevaVenta.getUnContratoVenta().setCantidadDeCuotas(1);
            CuotaVentaDTO cuotaVentaAux = new CuotaVentaDTO();
            cuotaVentaAux.setNumeroCuota(1);
            cuotaVentaAux.setMontoCuota(this.nuevaVenta.getUnContratoVenta().getMontoTotal());
            cuotasVentas.add(cuotaVentaAux);
        }
        this.nuevaVenta.getUnContratoVenta().setCuotasVenta(cuotasVentas);
    }

    public void agregarComision(float montoTotal, int cantidadDeCuotas) {
        ComisionDTO comision = new ComisionDTO();
        comision.setMontoTotal(montoTotal);
        comision.setCantidadDeCuotas(cantidadDeCuotas);
        comision.setUnContrato(nuevaVenta.getUnContratoVenta());

        List<LineaDeComisionDTO> lineasDeComision = new ArrayList<>();

        for (int i = 1; i <= cantidadDeCuotas; i++) {
            LineaDeComisionDTO lcAux = new LineaDeComisionDTO();
            lcAux.setUnaComision(comision);
            lcAux.setNumeroCuota(i);
            lcAux.setMonto(montoTotal / cantidadDeCuotas);
            lineasDeComision.add(lcAux);
        }
        comision.setLineasDeComisiones(lineasDeComision);
        nuevaVenta.getUnContratoVenta().setUnaComision(comision);
    }

    public List<ArancelEspecialDTO> listarArancelesEspeciales() {
        return this.arancelEspecialService.listarTodos();
    }

    public void agregarArancelesEspeciales(List<ArancelEspecialDTO> arancelesEspeciales) {
        nuevaVenta.getUnContratoVenta().setArancelesEspeciales(arancelesEspeciales);
    }

    public void finalizarVenta() {
        this.nuevaVenta.setId(this.ventaService.crear(nuevaVenta).getId());
        nuevaVenta.getUnInmuebleVenta().setUnCliente(clienteCompradorSeleccionado);
        this.inmuebleService.modificar(nuevaVenta.getUnInmuebleVenta());
    }

    public VentaDTO getNuevaVenta() {
        return nuevaVenta;
    }

}
