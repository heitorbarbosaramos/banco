package com.banco.progresso.mapper;

import com.banco.progresso.entidade.Cliente;
import com.banco.progresso.entidade.dto.ClienteDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-19T21:45:52-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Amazon.com Inc.)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toModel(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setId( clienteDTO.getId() );
        cliente.setNomeCompleto( clienteDTO.getNomeCompleto() );
        cliente.setCpfOuCnpj( clienteDTO.getCpfOuCnpj() );
        cliente.setTipoCliente( clienteDTO.getTipoCliente() );
        cliente.setTelefone( clienteDTO.getTelefone() );
        cliente.setDataCriacao( clienteDTO.getDataCriacao() );
        cliente.setDataVenceTaxa( clienteDTO.getDataVenceTaxa() );
        cliente.setEndereco( clienteDTO.getEndereco() );

        return cliente;
    }

    @Override
    public ClienteDTO fromDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setId( cliente.getId() );
        clienteDTO.setNomeCompleto( cliente.getNomeCompleto() );
        clienteDTO.setCpfOuCnpj( cliente.getCpfOuCnpj() );
        clienteDTO.setTipoCliente( cliente.getTipoCliente() );
        clienteDTO.setTelefone( cliente.getTelefone() );
        clienteDTO.setDataCriacao( cliente.getDataCriacao() );
        clienteDTO.setDataVenceTaxa( cliente.getDataVenceTaxa() );
        clienteDTO.setEndereco( cliente.getEndereco() );

        return clienteDTO;
    }
}
