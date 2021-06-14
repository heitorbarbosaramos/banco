package com.banco.progresso.controller;

import com.banco.progresso.entidade.ContaCliente;
import com.banco.progresso.entidade.Extrato;
import com.banco.progresso.entidade.dto.*;
import com.banco.progresso.service.ClienteService;
import com.banco.progresso.service.ContaClienteService;
import com.banco.progresso.service.ExtratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteControllerRest {

    @Autowired
    private ClienteService serviceCliente;

    @Autowired
    private ContaClienteService serviceContaCliente;

    @Autowired
    private ExtratoService serviceExtrato;


    @PostMapping(value = "/insert/cliente")
    private ResponseEntity<?> insert(@Valid @RequestBody ClienteDTO clienteDTO){
        clienteDTO = serviceCliente.insert(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/update")
    public ResponseEntity update(@RequestBody ClienteDTOUpdate clienteDTOUpdate){
        clienteDTOUpdate = serviceCliente.update(clienteDTOUpdate);
        return ResponseEntity.ok(clienteDTOUpdate);
    }

    @GetMapping(value = "/findById/{idCliente}")
    public ResponseEntity<?> findById(@PathVariable(value = "idCliente") Long idCliente){
        ClienteDTO clienteDTO = serviceCliente.findById(idCliente);
        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping(value = "/createAccount/{idCliente}/{valorAbertura}")
    public ResponseEntity<?> createAccount(@PathVariable(value = "idCliente") Long idCliente, @PathVariable(value = "valorAbertura")  Double valorAbertura){
        ContaCliente conta = serviceCliente.createNewAccount(idCliente, valorAbertura);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(conta.getNumeroConta()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/numberOfAccounts/{idCliente}")
    public ResponseEntity<?> numberOfAccounts (@PathVariable(value = "idCliente") Long idCliente){
        ClienteDTONumeroContas clienteDTONumeroContas = serviceCliente.numberOfAccounts(idCliente);
        return ResponseEntity.ok(clienteDTONumeroContas);
    }

    @GetMapping(value = "/saldo/{numeroConta}")
    public ResponseEntity<?> saldoPorConta(@PathVariable(value = "numeroConta") Integer numeroConta){
        ClienteDTOSaldoConta conta = serviceContaCliente.saldo(numeroConta);
        return ResponseEntity.ok(conta);
    }

    @PostMapping(value = "/deposito/{numeroConta}/{deposito}")
    public ResponseEntity<?> deposito(@PathVariable(value = "numeroConta") Integer numeroConta, @PathVariable(value = "deposito")  Double deposito){
        serviceContaCliente.deposito(numeroConta, deposito);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/saque/{numeroConta}/{deposito}")
    public ResponseEntity<?> saque(@PathVariable(value = "numeroConta") Integer numeroConta, @PathVariable(value = "deposito")  Double deposito){
        serviceContaCliente.saque(numeroConta, deposito);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/transferenciaEntrada/{numeroContaOrigem}/{numeroContaDestino}/{valor}")
    public ResponseEntity<?> transferenciaEntrada(@PathVariable(value = "numeroContaDestino") Integer numeroContaDestino, @PathVariable(value = "numeroContaOrigem") Integer numeroContaOrigem, @PathVariable(value = "valor")  Double valor){
        serviceContaCliente.transferenciaEntrada(numeroContaDestino,numeroContaOrigem, valor);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/transferenciaSaida/{numeroContaOrigem}/{numeroContaDestino}/{valor}")
    public ResponseEntity<?> transferenciaSaida(@PathVariable(value = "numeroContaDestino") Integer numeroContaDestino, @PathVariable(value = "numeroContaOrigem") Integer numeroContaOrigem, @PathVariable(value = "valor")  Double valor){
        serviceContaCliente.transferenciaSaida(numeroContaDestino,numeroContaOrigem, valor);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/extrato/{numeroContaDestino}/{dataInicio}/{dataFim}")
    public ResponseEntity<?> extrato(@PathVariable(value = "numeroContaDestino") Integer numeroContaDestino, @PathVariable(value = "dataInicio") String dataInicio, @PathVariable(value = "dataFim")  String dataFim){
        ExtratoDTO extrato = serviceExtrato.extratos(numeroContaDestino, dataInicio, dataFim);
        return ResponseEntity.ok(extrato);
    }
}
