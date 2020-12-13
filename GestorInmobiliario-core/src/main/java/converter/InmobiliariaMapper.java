package converter;

import dto.ActividadDTO;
import dto.AlquilerDTO;
import dto.ArancelEspecialDTO;
import dto.ArancelEspecialExpensaDTO;
import dto.ArancelEspecialServicioDTO;
import dto.BoletaDePagoDTO;
import dto.CasaDTO;
import dto.ClienteDTO;
import dto.ComisionDTO;
import dto.ComprobanteDeIngresoDTO;
import dto.ComprobanteMonotributoDTO;
import dto.ContratoAlquilerDTO;
import dto.ContratoDTO;
import dto.ContratoVentaDTO;
import dto.CuotaVentaDTO;
import dto.DepartamentoDTO;
import dto.DocumentoDeIngresoDTO;
import dto.EstadoCivilDTO;
import dto.GaranteDTO;
import dto.GaranteDependienteDTO;
import dto.GaranteIndependienteDTO;
import dto.InmobiliariaDTO;
import dto.InmuebleDTO;
import dto.LineaDeComisionDTO;
import dto.LocadorDTO;
import dto.LocalComercialDTO;
import dto.LocatarioDTO;
import dto.LocatarioDependienteDTO;
import dto.LocatarioEstudianteDTO;
import dto.LocatarioIndependienteDTO;
import dto.RecargoPorMoraDTO;
import dto.ReciboDeSueldoDTO;
import dto.TerrenoDTO;
import dto.TipoDNIDTO;
import dto.TipoUsuarioDTO;
import dto.UsuarioClienteDTO;
import dto.UsuarioDTO;
import dto.UsuarioEmpresaDTO;
import dto.VentaDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import model.entity.Actividad;
import model.entity.Alquiler;
import model.entity.ArancelEspecial;
import model.entity.ArancelEspecialExpensa;
import model.entity.ArancelEspecialServicio;
import model.entity.BoletaDePago;
import model.entity.Casa;
import model.entity.Cliente;
import model.entity.Comision;
import model.entity.ComprobanteDeIngreso;
import model.entity.ComprobanteMonotributo;
import model.entity.Contrato;
import model.entity.ContratoAlquiler;
import model.entity.ContratoVenta;
import model.entity.CuotaVenta;
import model.entity.Departamento;
import model.entity.DocumentoDeIngreso;
import model.entity.EstadoCivil;
import model.entity.Garante;
import model.entity.GaranteDependiente;
import model.entity.GaranteIndependiente;
import model.entity.Inmobiliaria;
import model.entity.Inmueble;
import model.entity.LineaDeComision;
import model.entity.Locador;
import model.entity.LocalComercial;
import model.entity.Locatario;
import model.entity.LocatarioDependiente;
import model.entity.LocatarioEstudiante;
import model.entity.LocatarioIndependiente;
import model.entity.RecargoPorMora;
import model.entity.ReciboDeSueldo;
import model.entity.Terreno;
import model.entity.TipoDNI;
import model.entity.TipoUsuario;
import model.entity.Usuario;
import model.entity.UsuarioCliente;
import model.entity.UsuarioEmpresa;
import model.entity.Venta;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface InmobiliariaMapper {

    InmobiliariaMapper INSTANCE = Mappers.getMapper(InmobiliariaMapper.class);

    @Mapping(target = "clientes", ignore = true)
    @Mapping(target = "garantes", ignore = true)
    @Mapping(target = "inmuebles", ignore = true)
    @Mapping(target = "alquileres", ignore = true)
    @Mapping(target = "ventas", ignore = true)
    @Mapping(target = "recargosPorMoras", ignore = true)
    @Mapping(target = "arancelesEspeciales", ignore = true)
    @Mapping(target = "usuarios", ignore = true)
    InmobiliariaDTO toInmobiliariaDTO(Inmobiliaria entity);

    Inmobiliaria toInmobiliariaEntity(InmobiliariaDTO dto);

    List<InmobiliariaDTO> toInmobiliariaDTOList(List<Inmobiliaria> entities);

    default ClienteDTO toClienteDTO(Cliente entity) {

        if (entity == null) {
            return null;
        } else {
            ClienteDTO dtoAux = null;
            if (entity instanceof Locador) {
                dtoAux = toLocadorDTO((Locador) entity);
            }
            if (entity instanceof LocatarioDependiente) {
                dtoAux = toLocatatioDependienteDTO((LocatarioDependiente) entity);
            }
            if (entity instanceof LocatarioIndependiente) {
                dtoAux = toLocatarioIndependienteDTO((LocatarioIndependiente) entity);
            }
            if (entity instanceof LocatarioEstudiante) {
                dtoAux = toLocatarioEstudianteDTO((LocatarioEstudiante) entity);
            }
            return dtoAux;
        }

    }

    @Mapping(target = "unUsuarioCliente", ignore = true)
    @Mapping(target = "inmuebles", ignore = true)
    LocadorDTO toLocadorDTO(Locador entity);

    default LocatarioDTO toLocatarioDTO(Locatario entity) {
        LocatarioDTO dtoAux = null;
        if (entity == null) {
            return null;
        }
        if (entity instanceof LocatarioDependiente) {
            dtoAux = toLocatatioDependienteDTO((LocatarioDependiente) entity);
        }
        if (entity instanceof LocatarioIndependiente) {
            dtoAux = toLocatarioIndependienteDTO((LocatarioIndependiente) entity);
        }
        if (entity instanceof LocatarioEstudiante) {
            dtoAux = toLocatarioEstudianteDTO((LocatarioEstudiante) entity);
        }
        return dtoAux;
    }

    @Mapping(target = "inmuebles", ignore = true)
    @Mapping(target = "unGarante", ignore = true)
    @Mapping(target = "unUsuarioCliente", ignore = true)
    LocatarioDependienteDTO toLocatatioDependienteDTO(LocatarioDependiente entity);

    @Mapping(target = "inmuebles", ignore = true)
    @Mapping(target = "unGarante", ignore = true)
    @Mapping(target = "unUsuarioCliente", ignore = true)
    LocatarioIndependienteDTO toLocatarioIndependienteDTO(LocatarioIndependiente entity);

    @Mapping(target = "inmuebles", ignore = true)
    @Mapping(target = "unGarante", ignore = true)
    @Mapping(target = "unUsuarioCliente", ignore = true)
    LocatarioEstudianteDTO toLocatarioEstudianteDTO(LocatarioEstudiante entity);

    default GaranteDTO toGaranteDTO(Garante entity) {
        GaranteDTO dtoAux = null;
        if (entity == null) {
            return null;
        } else {
            if (entity instanceof GaranteDependiente) {
                dtoAux = toGaranteDependienteDTO((GaranteDependiente) entity);
            }
            if (entity instanceof GaranteIndependiente) {
                dtoAux = toGaranteIndependienteDTO((GaranteIndependiente) entity);
            }
        }

        return dtoAux;
    }

    GaranteDependienteDTO toGaranteDependienteDTO(GaranteDependiente entity);

    GaranteIndependienteDTO toGaranteIndependienteDTO(GaranteIndependiente entity);

    default InmuebleDTO toInmuebleDTO(Inmueble entity) {
        InmuebleDTO dtoAux = null;
        if (entity == null) {
            return null;
        } else {
            if (entity instanceof Terreno) {
                dtoAux = toTerrenoDTO((Terreno) entity);
            }
            if (entity instanceof Casa) {
                dtoAux = toCasaDTO((Casa) entity);
            }
            if (entity instanceof Departamento) {
                dtoAux = toDepartamentoDTO((Departamento) entity);
            }
            if (entity instanceof LocalComercial) {
                dtoAux = toLocalComercialDTO((LocalComercial) entity);
            }
        }

        return dtoAux;
    }

    TerrenoDTO toTerrenoDTO(Terreno entity);

    CasaDTO toCasaDTO(Casa entity);

    DepartamentoDTO toDepartamentoDTO(Departamento entity);

    LocalComercialDTO toLocalComercialDTO(LocalComercial entity);

    default ComprobanteDeIngresoDTO toComprobanteDTO(ComprobanteDeIngreso entity) {
        ComprobanteDeIngresoDTO dtoAux = null;
        if (entity == null) {
            return null;
        }
        if (entity instanceof ReciboDeSueldo) {
            dtoAux = toReciboDeSueldoDTO((ReciboDeSueldo) entity);
        }
        if (entity instanceof ComprobanteMonotributo) {
            dtoAux = toComprobanteMonotributoDTO((ComprobanteMonotributo) entity);
        }
        if (entity instanceof DocumentoDeIngreso) {
            dtoAux = toDocumentoDeIngresoDTO((DocumentoDeIngreso) entity);
        }
        return dtoAux;
    }

    ReciboDeSueldoDTO toReciboDeSueldoDTO(ReciboDeSueldo entity);

    ComprobanteMonotributoDTO toComprobanteMonotributoDTO(ComprobanteMonotributo entity);

    DocumentoDeIngresoDTO toDocumentoDeIngresoDTO(DocumentoDeIngreso entity);

    default ArancelEspecialDTO toArancelEspecialDTO(ArancelEspecial entity) {
        ArancelEspecialDTO dtoAux = null;
        if (entity == null) {
            return null;
        }
        if (entity instanceof ArancelEspecialExpensa) {
            dtoAux = toArancelEspecialExpensaDTO((ArancelEspecialExpensa) entity);
        }
        if (entity instanceof ArancelEspecialServicio) {
            dtoAux = toArancelEspecialServicioDTO((ArancelEspecialServicio) entity);
        }
        return dtoAux;
    }

    @Mapping(source = "unaFechaArancel", target = "unaFechaArancel", qualifiedByName = "localDateString")
    ArancelEspecialExpensaDTO toArancelEspecialExpensaDTO(ArancelEspecialExpensa entity);

    @Mapping(source = "unaFechaArancel", target = "unaFechaArancel", qualifiedByName = "localDateString")
    ArancelEspecialServicioDTO toArancelEspecialServicioDTO(ArancelEspecialServicio entity);

    default UsuarioDTO toUsuarioDTO(Usuario entity) {
        UsuarioDTO dtoAux = null;
        if (entity == null) {
            return null;
        }
        if (entity instanceof UsuarioEmpresa) {
            dtoAux = toUsuarioEmpresaDTO((UsuarioEmpresa) entity);
        }
        if (entity instanceof UsuarioCliente) {
            dtoAux = toUsuarioClienteDTO((UsuarioCliente) entity);
        }
        return dtoAux;
    }

    UsuarioEmpresaDTO toUsuarioEmpresaDTO(UsuarioEmpresa entity);

    UsuarioClienteDTO toUsuarioClienteDTO(UsuarioCliente entity);

    default ContratoDTO toContratoDTO(Contrato entity) {
        ContratoDTO dtoAux = null;
        if (entity == null) {
            return null;
        }
        if (entity instanceof ContratoVenta) {
            dtoAux = toContratoVentaDTO((ContratoVenta) entity);
        }
        if (entity instanceof ContratoAlquiler) {
            dtoAux = toContratoAlquilerDTO((ContratoAlquiler) entity);
        }
        return dtoAux;
    }

    @Mapping(target = "cuotasVenta", ignore = true)
    @Mapping(target = "unaComision", ignore = true)
    ContratoVentaDTO toContratoVentaDTO(ContratoVenta entity);

    @Mapping(target = "unaComision", ignore = true)
    ContratoAlquilerDTO toContratoAlquilerDTO(ContratoAlquiler entity);

    default Cliente toClienteEntity(ClienteDTO dto) {
        Cliente entityAux = null;
        if (dto == null) {
            return null;
        }
        if (dto instanceof LocadorDTO) {
            entityAux = toLocadorEntity((LocadorDTO) dto);
        }
        if (dto instanceof LocatarioDependienteDTO) {
            entityAux = toLocatatioDependienteEntity((LocatarioDependienteDTO) dto);
        }
        if (dto instanceof LocatarioIndependienteDTO) {
            entityAux = toLocatarioIndependienteEntity((LocatarioIndependienteDTO) dto);
        }
        if (dto instanceof LocatarioEstudianteDTO) {
            entityAux = toLocatarioEstudianteEntity((LocatarioEstudianteDTO) dto);
        }
        return entityAux;
    }

    Locador toLocadorEntity(LocadorDTO dto);

    default Locatario toLocatarioEntity(LocatarioDTO dto) {
        Locatario entityAux = null;
        if (dto == null) {
            return null;
        }
        if (dto instanceof LocatarioDependienteDTO) {
            entityAux = toLocatatioDependienteEntity((LocatarioDependienteDTO) dto);
        }
        if (dto instanceof LocatarioIndependienteDTO) {
            entityAux = toLocatarioIndependienteEntity((LocatarioIndependienteDTO) dto);
        }
        if (dto instanceof LocatarioEstudianteDTO) {
            entityAux = toLocatarioEstudianteEntity((LocatarioEstudianteDTO) dto);
        }
        return entityAux;
    }

    LocatarioDependiente toLocatatioDependienteEntity(LocatarioDependienteDTO dto);

    LocatarioIndependiente toLocatarioIndependienteEntity(LocatarioIndependienteDTO dto);

    LocatarioEstudiante toLocatarioEstudianteEntity(LocatarioEstudianteDTO dto);

    default Garante toGaranteEntity(GaranteDTO dto) {
        Garante entityAux = null;
        if (dto == null) {
            return null;
        }
        if (dto instanceof GaranteDependienteDTO) {
            entityAux = toGaranteDependienteEntity((GaranteDependienteDTO) dto);
        }
        if (dto instanceof GaranteIndependienteDTO) {
            entityAux = toGaranteIndependienteEntity((GaranteIndependienteDTO) dto);
        }
        return entityAux;
    }

    GaranteDependiente toGaranteDependienteEntity(GaranteDependienteDTO dto);

    GaranteIndependiente toGaranteIndependienteEntity(GaranteIndependienteDTO dto);

    default Inmueble toInmuebleEntity(InmuebleDTO dto) {
        Inmueble entityAux = null;
        if (dto == null) {
            return null;
        }
        if (dto instanceof TerrenoDTO) {
            entityAux = toTerrenoEntity((TerrenoDTO) dto);
        }
        if (dto instanceof CasaDTO) {
            entityAux = toCasaEntity((CasaDTO) dto);
        }
        if (dto instanceof DepartamentoDTO) {
            entityAux = toDepartamentoEntity((DepartamentoDTO) dto);
        }
        if (dto instanceof LocalComercialDTO) {
            entityAux = toLocalComercialEntity((LocalComercialDTO) dto);
        }
        return entityAux;
    }

    Terreno toTerrenoEntity(TerrenoDTO dto);

    Casa toCasaEntity(CasaDTO dto);

    Departamento toDepartamentoEntity(DepartamentoDTO dto);

    LocalComercial toLocalComercialEntity(LocalComercialDTO dto);

    default ComprobanteDeIngreso toComprobanteDeIngresoEntity(ComprobanteDeIngresoDTO dto) {
        ComprobanteDeIngreso entityAux = null;
        if (dto == null) {
            return null;
        }
        if (dto instanceof ReciboDeSueldoDTO) {
            entityAux = toReciboDeSueldoEntity((ReciboDeSueldoDTO) dto);
        }
        if (dto instanceof ComprobanteMonotributoDTO) {
            entityAux = toComprobanteMonotributoEntity((ComprobanteMonotributoDTO) dto);
        }
        if (dto instanceof DocumentoDeIngresoDTO) {
            entityAux = toDocumentoDeIngresoEntity((DocumentoDeIngresoDTO) dto);
        }
        return entityAux;
    }

    ReciboDeSueldo toReciboDeSueldoEntity(ReciboDeSueldoDTO dto);

    ComprobanteMonotributo toComprobanteMonotributoEntity(ComprobanteMonotributoDTO dto);

    DocumentoDeIngreso toDocumentoDeIngresoEntity(DocumentoDeIngresoDTO dto);

    default ArancelEspecial toArancelEspecialEntity(ArancelEspecialDTO dto) {
        ArancelEspecial entityAux = null;
        if (dto == null) {
            return null;
        } else {
            if (dto instanceof ArancelEspecialExpensaDTO) {
                entityAux = toArancelEspecialExpensaEntity((ArancelEspecialExpensaDTO) dto);
            }
            if (dto instanceof ArancelEspecialServicioDTO) {
                entityAux = toArancelEspecialServicioEntity((ArancelEspecialServicioDTO) dto);
            }
            return entityAux;
        }

    }

    @Mapping(source = "unaFechaArancel", target = "unaFechaArancel", qualifiedByName = "localDate")
    ArancelEspecialExpensa toArancelEspecialExpensaEntity(ArancelEspecialExpensaDTO dto);

    @Mapping(source = "unaFechaArancel", target = "unaFechaArancel", qualifiedByName = "localDate")
    ArancelEspecialServicio toArancelEspecialServicioEntity(ArancelEspecialServicioDTO dto);

    default Usuario toUsuarioEntity(UsuarioDTO dto) {
        Usuario entityAux = null;
        if (dto == null) {
            return null;
        }
        if (dto instanceof UsuarioEmpresaDTO) {
            entityAux = toUsuarioEmpresaEntity((UsuarioEmpresaDTO) dto);
        }
        if (dto instanceof UsuarioClienteDTO) {
            entityAux = toUsuarioClienteEntity((UsuarioClienteDTO) dto);
        }
        return entityAux;
    }

    UsuarioEmpresa toUsuarioEmpresaEntity(UsuarioEmpresaDTO dto);

    UsuarioCliente toUsuarioClienteEntity(UsuarioClienteDTO dto);

    default Contrato toContratoEntity(ContratoDTO dto) {
        Contrato entityAux = null;
        if (dto == null) {
            return null;
        } else {
            if (dto instanceof ContratoVentaDTO) {
                entityAux = toContratoVentaEntity((ContratoVentaDTO) dto);
            }
            if (dto instanceof ContratoAlquilerDTO) {
                entityAux = toContratoAlquilerEntity((ContratoAlquilerDTO) dto);
            }
            return entityAux;
        }

    }

    @Mapping(target = "cuotasVenta", ignore = true)
    @Mapping(target = "unaComision", ignore = true)
    ContratoVenta toContratoVentaEntity(ContratoVentaDTO dto);

    @Mapping(target = "unaComision", ignore = true)
    ContratoAlquiler toContratoAlquilerEntity(ContratoAlquilerDTO dto);

    @Mapping(target = "unContratoAlquiler", ignore = true)
    @Mapping(source = "unaFechaInicio", target = "unaFechaInicio", qualifiedByName = "localDateString")
    @Mapping(source = "unaFechaFin", target = "unaFechaFin", qualifiedByName = "localDateString")
    AlquilerDTO toAlquilerDTO(Alquiler entity);

    @Mapping(target = "unContratoAlquiler", ignore = true)
    @Mapping(source = "unaFechaInicio", target = "unaFechaInicio", qualifiedByName = "localDate")
    @Mapping(source = "unaFechaFin", target = "unaFechaFin", qualifiedByName = "localDate")
    Alquiler toAlquilerEntity(AlquilerDTO dto);

    List<AlquilerDTO> toDTOAlquilerList(List<Alquiler> entities);

    ActividadDTO toActividadDTO(Actividad entity);

    Actividad toActividadEntity(ActividadDTO dto);

    List<ActividadDTO> toDTOActividadList(List<Actividad> entities);

    TipoUsuarioDTO toTipoUsuarioDTO(TipoUsuario entity);

    TipoUsuario toTipoUsuarioEntity(TipoUsuarioDTO dto);

    List<TipoUsuarioDTO> toDTOTipoUsuarioList(List<TipoUsuario> entities);

    @Mapping(source = "unaFechaDeRecargo", target = "unaFechaDeRecargo", qualifiedByName = "localDateString")
    RecargoPorMoraDTO toRecargoPorMoraDTO(RecargoPorMora entity);

    @Mapping(source = "unaFechaDeRecargo", target = "unaFechaDeRecargo", qualifiedByName = "localDate")
    RecargoPorMora toRecargoPorMoraEntity(RecargoPorMoraDTO dto);

    List<RecargoPorMoraDTO> toDTORecargoPorMoraList(List<RecargoPorMora> entities);

    TipoDNIDTO toTipoDNIDTO(TipoDNI entity);

    TipoDNI toTipoDNIEntity(TipoDNIDTO dto);

    List<TipoDNIDTO> toDTOTipoDNIList(List<TipoDNI> entities);

    CuotaVentaDTO toCuotaVentaDTO(CuotaVenta entity);

    CuotaVenta toCuotaVentaEntity(CuotaVentaDTO dto);

    List<CuotaVentaDTO> toDTOCuotaVentaList(List<CuotaVenta> entities);

    @Mapping(target = "unContratoVenta", ignore = true)
    @Mapping(source = "unaFechaVenta", target = "unaFechaVenta", qualifiedByName = "localDateString")
    VentaDTO toVentaDTO(Venta entity);

    @Mapping(target = "unContratoVenta", ignore = true)
    @Mapping(source = "unaFechaVenta", target = "unaFechaVenta", qualifiedByName = "localDate")
    Venta toVentaEntity(VentaDTO dto);

    List<VentaDTO> toDTOVentaList(List<Venta> entities);

    ComisionDTO toComisionDTO(Comision entity);

    @Mapping(target = "lineasDeComisiones", ignore = true)
    Comision toComisionEntity(ComisionDTO dto);

    @Mapping(target = "lineasDeComisiones", ignore = true)
    List<ComisionDTO> toDTOComisionList(List<Comision> entities);

    LineaDeComisionDTO toLineaDeComisionDTO(LineaDeComision entity);

    LineaDeComision toLineaDeComisionEntity(LineaDeComisionDTO dto);

    List<LineaDeComisionDTO> toDTOLineaDeComisionList(List<LineaDeComision> entities);

    BoletaDePagoDTO toBoletaDePagoDTO(BoletaDePago entity);

    BoletaDePago toBoletaDePagoEntity(BoletaDePagoDTO dto);

    List<BoletaDePagoDTO> toDTOBoletaDePagoList(List<BoletaDePago> entities);

    EstadoCivilDTO toEstadoCivilDTO(EstadoCivil entity);

    EstadoCivil toEstadoCivilEntity(EstadoCivilDTO dto);

    List<EstadoCivilDTO> toDTOEstadoCivilList(List<EstadoCivil> entities);

    List<TerrenoDTO> toDTOTerrenoList(List<Terreno> entities);

    List<CasaDTO> toDTOCasaList(List<Casa> entities);

    List<DepartamentoDTO> toDTODepartamentoList(List<Departamento> entities);

    List<LocalComercialDTO> toDTOLocalComercialList(List<LocalComercial> entities);

    List<InmuebleDTO> toDTOInmuebleList(List<Inmueble> entities);

    List<UsuarioDTO> toDTOUsuarioList(List<Usuario> entities);

    List<UsuarioEmpresaDTO> toDTOUsuarioEmpresaList(List<UsuarioEmpresa> entities);

    List<UsuarioClienteDTO> toDTOUsuarioClienteList(List<UsuarioCliente> entities);

    List<ArancelEspecialDTO> toDTOArancelEspecialList(List<ArancelEspecial> entities);

    List<ArancelEspecialExpensaDTO> toDTOArancelEspecialExpensaList(List<ArancelEspecialExpensa> entities);

    List<ArancelEspecialServicioDTO> toDTOArancelEspecialServicioList(List<ArancelEspecialServicio> entities);

    List<ClienteDTO> toDTOClienteList(List<Cliente> entities);

    List<LocadorDTO> toDTOLocadorList(List<Locador> entities);

    List<ContratoDTO> toDTOContratoList(List<Contrato> entities);

    List<ContratoVentaDTO> toDTOContratoVentaList(List<ContratoVenta> entities);

    List<ContratoAlquilerDTO> toDTOContratoAlquilerList(List<ContratoAlquiler> entities);

    List<GaranteDTO> toDTOGaranteList(List<Garante> entities);

    List<GaranteDependienteDTO> toDTOGaranteDependienteList(List<GaranteDependiente> entities);

    List<GaranteIndependienteDTO> toDTOGaranteIndependienteList(List<GaranteIndependiente> entities);

    List<LocatarioDTO> toDTOLocatarioList(List<Locatario> entities);

    List<LocatarioDependienteDTO> toDTOLocatarioDependienteList(List<LocatarioDependiente> entities);

    List<LocatarioIndependienteDTO> toDTOLocatarioIndependienteList(List<LocatarioIndependiente> entities);

    List<LocatarioEstudianteDTO> toDTOLocatarioEstudianteList(List<LocatarioEstudiante> entities);

    List<ComprobanteDeIngresoDTO> toDTOComprobanteDeIngresoList(List<ComprobanteDeIngreso> entities);

    List<ReciboDeSueldoDTO> toDTOReciboDeSueldoList(List<ReciboDeSueldo> entities);

    List<ComprobanteMonotributoDTO> toDTOComprobanteDeMonotributoList(List<ComprobanteMonotributo> entities);

    List<DocumentoDeIngresoDTO> toDTODocumentoDeIngresoList(List<DocumentoDeIngreso> entities);

    @Named("localDate")
    static LocalDate converterStringToLocalDate(String strLocalDate) {
        if (strLocalDate != null) {
            DateTimeFormatter f = new DateTimeFormatterBuilder().parseCaseInsensitive()
                    .append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toFormatter();
            LocalDate datetime = LocalDate.parse(strLocalDate, f);
            return datetime;
        } else {
            return null;
        }

    }

    @Named("localDateString")
    static String converterLocalDateToString(LocalDate ldt) {
        if (ldt != null) {
            String formattedDate = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return formattedDate;
        } else {
            return null;
        }

    }
}
