package com.banco.progresso.entidade.dto;

import com.banco.progresso.entidade.ContaCliente;

public class ClienteDTOSaldoConta {

    private String nomeCompleto;
    private String cpfOuCnpj;
    private String tipoCliente;
    private String telefone;
    private Integer numeroConta;
    private Double saldo;

    public ClienteDTOSaldoConta(ContaCliente conta) {
        this.nomeCompleto = conta.getCliente().getNomeCompleto();
        this.cpfOuCnpj = conta.getCliente().getCpfOuCnpj();
        this.tipoCliente = conta.getCliente().getTipoCliente();
        this.telefone = conta.getCliente().getTelefone();
        this.numeroConta = conta.getNumeroConta();
        this.saldo = conta.getSaldo();
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public Double getSaldo() {
        return saldo;
    }
}
