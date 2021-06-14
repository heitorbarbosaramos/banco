package com.banco.progresso.service;

import com.banco.progresso.entidade.Cliente;
import com.banco.progresso.entidade.ContaCliente;
import com.banco.progresso.entidade.dto.ClienteDTO;
import com.banco.progresso.entidade.dto.ClienteDTONumeroContas;
import com.banco.progresso.entidade.dto.ClienteDTOUpdate;
import com.banco.progresso.exceptions.ClienteNaoEncontrado;
import com.banco.progresso.mapper.ClienteDTOUpdateMappers;
import com.banco.progresso.mapper.ClienteMapper;
import com.banco.progresso.repository.ClienteRepository;
import com.banco.progresso.service.utils.CalculoVenceTaxa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoService serviceEndereco;

    @Autowired
    private ContaClienteService serviceContaCliente;

    @Autowired
    private ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

    private ClienteDTOUpdateMappers clienteDTOUpdateMappers = ClienteDTOUpdateMappers.INSTANCE;

    private Cliente save(Cliente cliente){
        return repo.save(cliente);
    }

    public ClienteDTO save(ClienteDTO clienteDTO){
        serviceEndereco.save(clienteDTO.getEndereco());
        Cliente cliente = clienteMapper.toModel(clienteDTO);
        cliente = repo.save(cliente);
        return clienteMapper.fromDTO(cliente);
    }

    public ClienteDTO insert(ClienteDTO clienteDTO){
        serviceEndereco.save(clienteDTO.getEndereco());
        clienteDTO.setDataCriacao(new Date(System.currentTimeMillis()));
        Cliente cliente = clienteMapper.toModel(clienteDTO);
        calculaDataTaxa(cliente);
        cliente = repo.save(cliente);
        return clienteMapper.fromDTO(cliente);
    }

    public ClienteDTOUpdate update(ClienteDTOUpdate clienteDTOUpdate){
        Cliente cliente = clienteDTOUpdateMappers.toModel(clienteDTOUpdate);
        System.out.println("---->" + cliente);
//        cliente = save(cliente);
//        return clienteDTOUpdateMappers.fromDTO(cliente);
        return clienteDTOUpdate;
    }


    public void calculaDataTaxa(Cliente cliente){
        if(cliente.getDataVenceTaxa() == null){
            cliente.setDataVenceTaxa(new Date(System.currentTimeMillis()));
        }
        cliente.setDataVenceTaxa(CalculoVenceTaxa.CalculoVenceTaxa(cliente.getDataVenceTaxa()));
        repo.save(cliente);
    }

    public ClienteDTO findById(Long idCliente){
        Cliente cliente = repo.findById(idCliente).orElseThrow(()-> new ClienteNaoEncontrado(idCliente));
        ClienteDTO clienteDTO = clienteMapper.fromDTO(cliente);
        return clienteMapper.fromDTO(cliente);
    }

    public ClienteDTONumeroContas numberOfAccounts(Long idCliente){
        Cliente cliente = repo.findById(idCliente).orElseThrow(()-> new ClienteNaoEncontrado(idCliente));
        ClienteDTONumeroContas clienteDTO = new ClienteDTONumeroContas(cliente);
        return clienteDTO;
    }

    public ContaCliente createNewAccount(Long idCliente, Double valorAbertura){
        Cliente cliente = clienteMapper.toModel(findById(idCliente));
        ContaCliente conta = serviceContaCliente.save(cliente, valorAbertura);
        cliente.getContas().add(conta);
        cliente = save(cliente);
        return conta;
    }

}
