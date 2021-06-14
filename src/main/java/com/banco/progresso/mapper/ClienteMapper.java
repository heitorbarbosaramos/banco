package com.banco.progresso.mapper;

import com.banco.progresso.entidade.Cliente;
import com.banco.progresso.entidade.dto.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente toModel(ClienteDTO clienteDTO);
    ClienteDTO fromDTO(Cliente cliente);
}
