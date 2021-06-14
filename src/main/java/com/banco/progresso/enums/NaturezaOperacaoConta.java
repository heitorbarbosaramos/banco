package com.banco.progresso.enums;

public enum NaturezaOperacaoConta {

    DEPOSITO(1, "Depósito"),
    SAQUE(2, "Saque"),
    TRANSFERENCIA_ENTRADA(3, "Transferência entrada"),
    TRANSFERENCIA_SAIDA(4, "Transferência saída"),
    TAXA(5, "Taxa");

    private int cod;
    private String descricao;

    private NaturezaOperacaoConta(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao () {
        return descricao;
    }

    public static NaturezaOperacaoConta toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (NaturezaOperacaoConta x : NaturezaOperacaoConta.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}