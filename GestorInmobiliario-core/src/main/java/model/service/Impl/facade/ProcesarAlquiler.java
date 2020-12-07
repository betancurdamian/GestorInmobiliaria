package model.service.Impl.facade;

import dto.AlquilerDTO;
import dto.ArancelEspecialDTO;
import dto.ClienteDTO;
import dto.ComisionDTO;
import dto.ComprobanteDeIngresoDTO;
import dto.ContratoAlquilerDTO;
import dto.GaranteDTO;
import dto.InmuebleDTO;
import dto.LineaDeComisionDTO;
import dto.LocadorDTO;
import dto.LocatarioDTO;
import java.util.ArrayList;
import java.util.List;
import model.service.Impl.AlquilerServiceImpl;
import model.service.Impl.ArancelEspecialServiceImpl;
import model.service.Impl.ClienteServiceImpl;
import model.service.Impl.ComprobanteDeIngresoServiceImpl;
import model.service.Impl.GaranteServiceImpl;
import model.service.Impl.InmobiliariaServiceImpl;
import model.service.Impl.InmuebleServiceImpl;
import model.service.Impl.RecargoPorMoraServiceImpl;

public class ProcesarAlquiler {

    private AlquilerDTO nuevoAlquiler;
    private LocatarioDTO locatarioSeleccionado;

    private final InmobiliariaServiceImpl inmobiliariaService;
    private final ClienteServiceImpl clienteService;
    private final InmuebleServiceImpl inmuebleService;
    private final ArancelEspecialServiceImpl arancelEspecialService;
    private final RecargoPorMoraServiceImpl recargoPorMoraService;
    private final AlquilerServiceImpl alquilerService;
    private final ComprobanteDeIngresoServiceImpl comprobanteIngresoService;
    private final GaranteServiceImpl garanteService;

    public ProcesarAlquiler() {
        this.inmobiliariaService = new InmobiliariaServiceImpl();
        this.clienteService = new ClienteServiceImpl();
        this.inmuebleService = new InmuebleServiceImpl();
        this.arancelEspecialService = new ArancelEspecialServiceImpl();
        this.recargoPorMoraService = new RecargoPorMoraServiceImpl();
        this.alquilerService = new AlquilerServiceImpl();
        this.comprobanteIngresoService = new ComprobanteDeIngresoServiceImpl();
        this.garanteService = new GaranteServiceImpl();
    }

    public void crearNuevoAlquiler(String fechaInicio, String fechaFin) {
        this.nuevoAlquiler = new AlquilerDTO();
        this.nuevoAlquiler.setUnaFechaInicio(fechaInicio);
        this.nuevoAlquiler.setUnaFechaFin(fechaFin);
        this.nuevoAlquiler.setCompleta(Boolean.FALSE);
        this.nuevoAlquiler.setUnaInmobiliariaAlquiler(this.inmobiliariaService.obtenerPrimeraInmobiliaria());
    }

    public List<ClienteDTO> listarClientes() {
        return this.clienteService.listarTodos();
    }
    
    public List<LocatarioDTO> fitrarLocatariosPorDNI(String dniABuscar) {
        List<LocatarioDTO> locatariosFiltrados = new ArrayList();
        boolean resultado = false;
        for (LocatarioDTO cl : this.clienteService.listarTodosLocatarios()) {
            resultado = cl.getDni().startsWith(dniABuscar);
            if (resultado) {
                locatariosFiltrados.add(cl);
            }
        }
        return locatariosFiltrados;
    }

    public void seleccionarLocatario(ClienteDTO unLocatario) {
        if (unLocatario != null) {
            this.locatarioSeleccionado = clienteService.listarLocatarioID(unLocatario.getId());
        } else {
            System.out.println("El locatario no existe");
        }
    }

    public List<ComprobanteDeIngresoDTO> buscarUltimosComprobantesDelLocatario(int cantidadDeComprobantes) {
        List<ComprobanteDeIngresoDTO> comprobantesEncontrados = new ArrayList();
        if (locatarioSeleccionado != null) {
            for (int i = 0; i < cantidadDeComprobantes; i++) {
                if (this.comprobanteIngresoService.listarUltimosComprobantesLocatario(i, locatarioSeleccionado) != null) {
                    comprobantesEncontrados.add(this.comprobanteIngresoService.listarUltimosComprobantesLocatario(i, locatarioSeleccionado));
                }
            }
        }
        return comprobantesEncontrados;
    }

    public GaranteDTO buscarGaranteDelLocatario() {
        if (locatarioSeleccionado != null) {
            return garanteService.verGaranteDelLocatario(this.locatarioSeleccionado.getId());
        }else{
            return null;
        }
    }

    public List<ComprobanteDeIngresoDTO> buscarUltimosComprobantesDelGarante(int cantidadDeComprobantes) {
        List<ComprobanteDeIngresoDTO> comprobantesEncontrados = new ArrayList();
        if (locatarioSeleccionado != null) {
            for (int i = 0; i < cantidadDeComprobantes; i++) {
                if (this.comprobanteIngresoService.listarUltimosComprobantesGarante(i, garanteService.verGaranteDelLocatario(this.locatarioSeleccionado.getId())) != null) {
                    comprobantesEncontrados.add(this.comprobanteIngresoService.listarUltimosComprobantesGarante(i, garanteService.verGaranteDelLocatario(this.locatarioSeleccionado.getId())));
                }
            }
        }
        return comprobantesEncontrados;
    }

    public List<InmuebleDTO> listarInmueblesDisponibles() {
        List<InmuebleDTO> inmueblesDisponibles = new ArrayList();
        for (InmuebleDTO i : this.inmuebleService.listarTodos()) {
            if (i.getDisponible()) {
                if (!i.getUnCliente().equals(this.locatarioSeleccionado)) {
                    inmueblesDisponibles.add(i);
                }
            }
        }
        return inmueblesDisponibles;
    }

    public void crearContrato(int diaPrimerVencimiento, int diaSegundoVencimiento) {
        ContratoAlquilerDTO contratoAlquiler = new ContratoAlquilerDTO();
        contratoAlquiler.setUnDiaPrimerVencimiento(diaPrimerVencimiento);
        contratoAlquiler.setUnDiaSegundoVencimiento(diaSegundoVencimiento);
        this.nuevoAlquiler.setUnContratoAlquiler(contratoAlquiler);
        this.nuevoAlquiler.getUnContratoAlquiler().setUnLocatario(this.locatarioSeleccionado);
        this.nuevoAlquiler.getUnContratoAlquiler().setUnRecargoPorMora(this.recargoPorMoraService.obtenerUltimoRecargoPorMora());
    }

    public void agregarInmueble(InmuebleDTO unInmueble) {
        if (unInmueble != null) {
            this.nuevoAlquiler.setUnInmueble(inmuebleService.listarID(unInmueble.getId()));
            this.nuevoAlquiler.getUnContratoAlquiler().setMontoTotal(this.nuevoAlquiler.getUnInmueble().getPrecioBaseVenta());
            this.nuevoAlquiler.getUnContratoAlquiler().setUnLocador((LocadorDTO) this.nuevoAlquiler.getUnInmueble().getUnCliente());
        } else {
            System.out.println("El Inmueble no existe");
        }
    }

    public void agregarCuotas(int cantidadCuota) {
        if (cantidadCuota > 1) {
            this.nuevoAlquiler.getUnContratoAlquiler().setCantidadDeCuotas(cantidadCuota);
        } else {
            this.nuevoAlquiler.getUnContratoAlquiler().setCantidadDeCuotas(1);
        }
    }

    public void agregarComision(float montoTotal, int cantidadDeCuotas) {
        ComisionDTO comision = new ComisionDTO();
        comision.setMontoTotal(montoTotal);
        comision.setCantidadDeCuotas(cantidadDeCuotas);
        comision.setUnContrato(nuevoAlquiler.getUnContratoAlquiler());

        List<LineaDeComisionDTO> lineasDeComision = new ArrayList<>();

        for (int i = 1; i <= cantidadDeCuotas; i++) {
            LineaDeComisionDTO lcAux = new LineaDeComisionDTO();
            lcAux.setUnaComision(comision);
            lcAux.setNumeroCuota(i);
            lcAux.setMonto(montoTotal / cantidadDeCuotas);
            lineasDeComision.add(lcAux);
        }
        comision.setLineasDeComisiones(lineasDeComision);
        nuevoAlquiler.getUnContratoAlquiler().setUnaComision(comision);
    }

    public List<ArancelEspecialDTO> listarArancelesEspeciales() {
        return this.arancelEspecialService.listarTodos();
    }

    public void agregarArancelesEspeciales(List<ArancelEspecialDTO> arancelesEspeciales) {
        nuevoAlquiler.getUnContratoAlquiler().setArancelesEspeciales(arancelesEspeciales);
    }

    public void finalizarAlquiler() {
        this.nuevoAlquiler.setId(this.alquilerService.crear(nuevoAlquiler).getId());
    }

    public AlquilerDTO getNuevoAlquiler() {
        return nuevoAlquiler;
    }

}
