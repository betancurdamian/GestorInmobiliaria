package model.service.Impl.facade;

import dto.CasaDTO;
import dto.ContratoVentaDTO;
import dto.InmuebleDTO;
import dto.LocadorDTO;
import dto.LocatarioDTO;
import dto.VentaDTO;
import java.util.List;
import model.service.Impl.ClienteServiceImpl;
import model.service.Impl.InmuebleServiceImpl;

public class ProcesarVenta {

    private VentaDTO nuevaVenta;

    private final ClienteServiceImpl clienteService;
    private final InmuebleServiceImpl inmuebleService;

    public ProcesarVenta() {
        this.clienteService = new ClienteServiceImpl();
        this.inmuebleService = new InmuebleServiceImpl();
    }

    public void crearNuevaVenta() {
        this.nuevaVenta = new VentaDTO();
        this.nuevaVenta.setCompleta(Boolean.FALSE);
        ContratoVentaDTO contratoVenta = new ContratoVentaDTO();
        this.nuevaVenta.setUnContratoVenta(contratoVenta);
    }

    public List<LocatarioDTO> listarLocatarios() {
        return this.clienteService.listarTodosLocatarios();
    }

    public void agregarLocatario(LocatarioDTO unLocatario) {
        if (unLocatario != null) {
            this.nuevaVenta.getUnContratoVenta().setUnLocatario(clienteService.listarLocatarioID(unLocatario.getId()));
        }
    }

    public List<CasaDTO> listarInmuebles() {
        return this.inmuebleService.listarTodasCasas();
    }

    public void agregarInmueble(InmuebleDTO unInmueble) {
        if (unInmueble != null) {
            this.nuevaVenta.setUnInmueble(inmuebleService.listarID(unInmueble.getId()));
        } else {
            System.out.println("El Inmueble no existe");
        }
    }

    public void agregarLocador() {
        this.nuevaVenta.getUnContratoVenta().setUnLocador(this.nuevaVenta.getUnInmueble().getUnLocador());
    }

    public void crearComision(float montoTotal, int cantidadDeCuotas) {
        
    }
}
