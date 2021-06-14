package com.banco.progresso.mapper;

import com.banco.progresso.entidade.Cliente;
import com.banco.progresso.entidade.dto.ClienteDTOUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteDTOUpdateMappers {

    ClienteDTOUpdateMappers INSTANCE = Mappers.getMapper(ClienteDTOUpdateMappers.class);

    Cliente toModel(ClienteDTOUpdate clienteDTOUpdate);
    ClienteDTOUpdate fromDTO(Cliente cliente);
}
