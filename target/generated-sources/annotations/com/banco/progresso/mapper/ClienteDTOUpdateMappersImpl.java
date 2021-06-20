package com.banco.progresso.mapper;

import com.banco.progresso.entidade.Cliente;
import com.banco.progresso.entidade.dto.ClienteDTOUpdate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-19T21:45:53-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Amazon.com Inc.)"
)
@Component
public class ClienteDTOUpdateMappersImpl implements ClienteDTOUpdateMappers {

    @Override
    public Cliente toModel(ClienteDTOUpdate clienteDTOUpdate) {
        if ( clienteDTOUpdate == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setId( clienteDTOUpdate.getId() );
        cliente.setNomeCompleto( clienteDTOUpdate.getNomeCompleto() );
        cliente.setTelefone( clienteDTOUpdate.getTelefone() );
        cliente.setEndereco( clienteDTOUpdate.getEndereco() );

        return cliente;
    }

    @Override
    public ClienteDTOUpdate fromDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTOUpdate clienteDTOUpdate = new ClienteDTOUpdate();

        clienteDTOUpdate.setId( cliente.getId() );
        clienteDTOUpdate.setNomeCompleto( cliente.getNomeCompleto() );
        clienteDTOUpdate.setTelefone( cliente.getTelefone() );
        clienteDTOUpdate.setEndereco( cliente.getEndereco() );

        return clienteDTOUpdate;
    }
}
