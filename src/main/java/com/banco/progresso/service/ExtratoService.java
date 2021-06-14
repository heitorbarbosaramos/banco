package com.banco.progresso.service;

import com.banco.progresso.entidade.ContaCliente;
import com.banco.progresso.entidade.Extrato;
import com.banco.progresso.entidade.dto.ExtratoDTO;
import com.banco.progresso.repository.ExtratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ExtratoService {

    @Autowired
    private ExtratoRepository repo;

    @Autowired
    private ContaClienteService serviceConta;

    @Autowired
    private ClienteService serviceCliente;

    public Extrato save(Extrato extrato){
        return repo.save(extrato);
    }

    public Extrato save(ContaCliente contaCliente, String naturezaOperacao, Double valor){
        Extrato extrato = new Extrato();
        extrato.setContaCliente(contaCliente);
        extrato.setDataOperacao(new Date(System.currentTimeMillis()));
        extrato.setNaturezaOperacao(naturezaOperacao);
        extrato.setValor(valor);
        extrato.setSaldo(contaCliente.getSaldo());
        return save(extrato);
    }

    public ExtratoDTO extratos(Integer numeroConta, String dataInicio, String dataFim) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date data1 = null;
        Date data2 = null;

        try {
            data1 = sdf.parse(dataInicio);
            data2 = sdf.parse(dataFim);
        } catch (ParseException e) {
           new RuntimeException(e.getMessage());
        }
        List<Extrato> extrato = repo.findAccountTimeCourse(numeroConta, data1, data2);

        if(extrato.get(0).getContaCliente().getCliente().getDataVenceTaxa().before(new Date(System.currentTimeMillis()))){

            Integer contaOperacoes = extrato.size();
            Double valorOperacoes = 0.0;
           if(contaOperacoes <= 10){
               valorOperacoes = 1.0;
           }else if(contaOperacoes > 10 && contaOperacoes <= 20 ){
               valorOperacoes = 0.75;
           }else{
               valorOperacoes = 0.50;
           }

           valorOperacoes *= contaOperacoes;

            serviceConta.taxa(numeroConta, valorOperacoes);
            serviceCliente.calculaDataTaxa(extrato.get(0).getContaCliente().getCliente());

        }

        return new ExtratoDTO(extrato);
    }
}
