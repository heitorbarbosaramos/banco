package com.banco.progresso.service;

import com.banco.progresso.entidade.Cliente;
import com.banco.progresso.entidade.ContaCliente;
import com.banco.progresso.entidade.dto.ClienteDTOSaldoConta;
import com.banco.progresso.enums.NaturezaOperacaoConta;
import com.banco.progresso.repository.ContaClienteRepository;
import com.banco.progresso.service.utils.GeradorNumeroConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ContaClienteService {

    @Autowired
    private ContaClienteRepository repo;

    @Autowired
    private ExtratoService serviceExtrato;

    public ContaCliente save(ContaCliente contaCliente){
        return repo.save(contaCliente);
    }

    @Transactional
    public ContaCliente save(Cliente cliente, Double valor){
        ContaCliente contaCliente = new ContaCliente();
        contaCliente.setNumeroConta(GeradorNumeroConta.geradorNumero());
        contaCliente.setCliente(cliente);
        contaCliente = save(contaCliente);
        if(valor > 0){
            deposito(contaCliente.getNumeroConta(), valor);
        }
        return contaCliente;
    }

    public ContaCliente findByNumeroConta(Integer numeroConta){
        return repo.findByNumeroConta(numeroConta);
    }

    public ClienteDTOSaldoConta saldo(Integer numeroConta){
        ClienteDTOSaldoConta saldo = new ClienteDTOSaldoConta(findByNumeroConta(numeroConta));
        return saldo;
    }

    public void deposito(Integer numeroConta, Double valor){
        ContaCliente contaCliente = findByNumeroConta(numeroConta);
        Double resgataSaldo = (contaCliente.getSaldo() == null) ? 0 : contaCliente.getSaldo();
        contaCliente.setSaldo( resgataSaldo + valor);
        save(contaCliente);
        serviceExtrato.save(contaCliente, NaturezaOperacaoConta.DEPOSITO.getDescricao(), valor);
    }

    public void saque(Integer numeroConta, Double valor){
        ContaCliente contaCliente = findByNumeroConta(numeroConta);
        Double resgataSaldo = (contaCliente.getSaldo() == null) ? 0 : contaCliente.getSaldo();
        verificaSaldoParaTransacao(resgataSaldo , valor);
        contaCliente.setSaldo( resgataSaldo - valor);
        save(contaCliente);
        serviceExtrato.save(contaCliente, NaturezaOperacaoConta.SAQUE.getDescricao(), valor);
    }

    public void taxa(Integer numeroConta, Double valor){
        ContaCliente contaCliente = findByNumeroConta(numeroConta);
        Double resgataSaldo = (contaCliente.getSaldo() == null) ? 0 : contaCliente.getSaldo();
        verificaSaldoParaTransacao(resgataSaldo , valor);
        contaCliente.setSaldo( resgataSaldo - valor);
        save(contaCliente);
        serviceExtrato.save(contaCliente, NaturezaOperacaoConta.TAXA.getDescricao(), valor);
    }

    public void transferenciaEntrada(Integer numeroContaDestino, Integer numeroContaOrigem, Double valor){
        ContaCliente contaClienteDestino = findByNumeroConta(numeroContaDestino);
        ContaCliente contaClienteOrigem = findByNumeroConta(numeroContaOrigem);

        Double resgataSaldo = (contaClienteOrigem.getSaldo() == null) ? 0 : contaClienteOrigem.getSaldo();
        verificaSaldoParaTransacao(resgataSaldo , valor);

        contaClienteDestino.setSaldo( resgataSaldo + valor);
        save(contaClienteDestino);
        serviceExtrato.save(contaClienteDestino, NaturezaOperacaoConta.TRANSFERENCIA_ENTRADA.getDescricao(), valor);

        contaClienteOrigem.setSaldo( resgataSaldo - valor);
        save(contaClienteOrigem);
        serviceExtrato.save(contaClienteOrigem, NaturezaOperacaoConta.TRANSFERENCIA_SAIDA.getDescricao(), valor);
    }

    public void transferenciaSaida(Integer numeroContaDestino, Integer numeroContaOrigem, Double valor){
        ContaCliente contaClienteDestino = findByNumeroConta(numeroContaDestino);
        ContaCliente contaClienteOrigem = findByNumeroConta(numeroContaOrigem);

        Double resgataSaldo = (contaClienteOrigem.getSaldo() == null) ? 0 : contaClienteOrigem.getSaldo();
        verificaSaldoParaTransacao(resgataSaldo , valor);

        contaClienteDestino.setSaldo( resgataSaldo - valor);
        save(contaClienteDestino);
        serviceExtrato.save(contaClienteDestino, NaturezaOperacaoConta.TRANSFERENCIA_SAIDA.getDescricao(), valor);

        contaClienteOrigem.setSaldo( resgataSaldo + valor);
        save(contaClienteOrigem);
        serviceExtrato.save(contaClienteOrigem, NaturezaOperacaoConta.TRANSFERENCIA_ENTRADA.getDescricao(), valor);
    }


    private void verificaSaldoParaTransacao(Double resgataSaldo, Double valor){
        if(resgataSaldo < valor){
            throw new RuntimeException("Valor em conta insulficiente para saque");
        }
    }
}
