/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dto.ClienteDTO;
import dto.LocadorDTO;
import dto.LocatarioDependienteDTO;
import dto.LocatarioEstudianteDTO;
import dto.LocatarioIndependienteDTO;
import model.entity.Cliente;
import model.entity.Locador;
import model.entity.LocatarioDependiente;
import model.entity.LocatarioEstudiante;
import model.entity.LocatarioIndependiente;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

/**
 *
 * @author Ariel
 */
public class ClienteConverter implements Converter<Cliente, ClienteDTO> {

    @Override
    public Cliente fomDTO(ClienteDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);

        Cliente entity = null;
        if (dto instanceof LocadorDTO) {
            entity = modelMapper.map(dto, Locador.class);
        }
        if (dto instanceof LocatarioDependienteDTO) {
            entity = modelMapper.map(dto, LocatarioDependiente.class);
        }
        if (dto instanceof LocatarioIndependienteDTO) {
            entity = modelMapper.map(dto, LocatarioIndependiente.class);
        }
        if (dto instanceof LocatarioEstudianteDTO) {
            entity = modelMapper.map(dto, LocatarioEstudiante.class);
        }

        return entity;
    }

    @Override
    public ClienteDTO fromDTO(Cliente entity) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        ClienteDTO dto = null;
        if (entity instanceof Locador) {
            dto = modelMapper.map(dto, LocadorDTO.class);
        }
        if (entity instanceof LocatarioDependiente) {
            dto = modelMapper.map(dto, LocatarioDependienteDTO.class);
        }
        if (entity instanceof LocatarioIndependiente) {
            dto = modelMapper.map(dto, LocatarioIndependienteDTO.class);
        }
        if (entity instanceof LocatarioEstudiante) {
            dto = modelMapper.map(dto, LocatarioEstudianteDTO.class);
        }

        return dto;
    }

}
