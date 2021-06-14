package com.banco.progresso.service;

import com.banco.progresso.entidade.Endereco;
import com.banco.progresso.repository.EnderecoRepository;
import com.banco.progresso.service.utils.BuscaEnderecoPorCep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private EnderecoRepository repo;

    @Autowired
    public EnderecoService(EnderecoRepository repo){
        this.repo = repo;
    }

    public Endereco save(Endereco endereco){
        return  repo.save(endereco);
    }

    public Endereco save(String cep, String logradouro, String complemento, String bairro, String localidade, String numero, String uf){
        Endereco endereco = BuscaEnderecoPorCep.buscarCep(cep);
        endereco.setCep(cep);
        endereco.setLocalidade(logradouro);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setLocalidade(localidade);
        endereco.setNumero(numero);
        endereco.setUf(uf);
        endereco = save(endereco);
        return endereco;
    }
}
