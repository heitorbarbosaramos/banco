package com.banco.progresso.entidade.dto;

import com.banco.progresso.entidade.Extrato;

import java.util.ArrayList;
import java.util.List;

public class ExtratoDTO {

    private String nomeCompleto;
    private String cpfOuCnpj;
    private String tipoCliente;
    private String telefone;

    private Integer numeroConta;
    private Double saldo;

    private List<ExtratoDTOHistorico> historicos = new ArrayList<>();

    public ExtratoDTO(List<Extrato>  extrato) {
        this.nomeCompleto = extrato.get(0).getContaCliente().getCliente().getNomeCompleto();
        this.cpfOuCnpj = extrato.get(0).getContaCliente().getCliente().getCpfOuCnpj();
        this.tipoCliente = extrato.get(0).getContaCliente().getCliente().getTipoCliente();
        this.telefone = extrato.get(0).getContaCliente().getCliente().getTelefone();
        this.numeroConta = extrato.get(0).getContaCliente().getNumeroConta();
        this.saldo = extrato.get(0).getContaCliente().getSaldo();

        extrato.forEach(l->{
            historicos.add(new ExtratoDTOHistorico(l.getNaturezaOperacao(), l.getDataOperacao(), l.getValor(), l.getSaldo()));
        });
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

    public List<ExtratoDTOHistorico> getHistoricos() {
        return historicos;
    }
}
