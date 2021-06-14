package com.banco.progresso.entidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data                   //para gerar os getteres e seteres
@Builder                //Builder vai nos prover uma maneira de criar objetos sem precisarmos de construtores e sem métodos setters em nossas classes
@AllArgsConstructor     //insere os construtores automaticamente
@NoArgsConstructor      //insere os construtores automaticamente
public class Extrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naturezaOperacao;
    private Date dataOperacao;
    private Double valor;
    private Double saldo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_conta")
    private ContaCliente contaCliente;

}
