package com.banco.progresso.entidade.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ExtratoDTOHistorico {

    private String naturezaOperacao;
    private Date dataOperacao;
    private Double valor;
    private Double saldo;

    public ExtratoDTOHistorico(String naturezaOperacao, Date dataOperacao, Double valor, Double saldo) {
        this.naturezaOperacao = naturezaOperacao;
        this.dataOperacao = dataOperacao;
        this.valor = valor;
        this.saldo = saldo;
    }
}
