
package model.service.Impl.facade;

import dto.InmuebleDTO;
import dto.LocadorDTO;
import dto.LocatarioDTO;
import dto.VentaDTO;
import java.util.List;
import model.service.Impl.ClienteServiceImpl;
import model.service.Impl.InmuebleServiceImpl;


public class ProcesarVenta {
    private VentaDTO nuevaVenta;
    private LocatarioDTO locatarioSeleccionado;
    private InmuebleDTO inmuebleSeleccionado;
    private LocadorDTO locadorSeleccionado;
    
    
    private final ClienteServiceImpl clienteService;
    private final InmuebleServiceImpl inmuebleService;

    public ProcesarVenta() {
        this.clienteService = new ClienteServiceImpl();
        this.inmuebleService = new InmuebleServiceImpl();
    }
    
    public void crearNuevaVenta(){
        this.nuevaVenta = new VentaDTO();
    }
    
    public List<LocatarioDTO> listarLocatarios(){
        return this.clienteService.listarTodosLocatarios();
    }
    
    public void agregarLocatario(LocatarioDTO unLocatario){
        if (unLocatario!=null) {
            this.locatarioSeleccionado = clienteService.listarLocatarioID(unLocatario.getId());
        }
    }
    
    public List<InmuebleDTO> listarInmuebles(){
        return this.inmuebleService.listarTodos();
    }
    
    public void agregarInmueble(InmuebleDTO unInmueble){
        if (unInmueble!=null) {
            this.inmuebleSeleccionado = inmuebleService.listarID(unInmueble.getId());
        }
    }
    
    public void agregarLocador(){
    }
    
}
