package com.banco.progresso.entidade.dto;

import com.banco.progresso.entidade.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDTONumeroContas {

    private String nomeCompleto;
    private String cpfOuCnpj;
    private String tipoCliente;
    private String telefone;

    private List<Integer> numeroContas = new ArrayList<>();

    public ClienteDTONumeroContas(Cliente cliente) {
        this.nomeCompleto = cliente.getNomeCompleto();
        this.cpfOuCnpj = cliente.getCpfOuCnpj();
        this.tipoCliente = cliente.getTipoCliente();
        this.telefone = cliente.getTelefone();
        cliente.getContas().forEach(l->{
            numeroContas.add(l.getNumeroConta());
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

    public List<Integer> getNumeroContas() {
        return numeroContas;
    }
}
