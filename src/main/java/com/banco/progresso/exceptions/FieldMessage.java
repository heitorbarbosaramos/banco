package com.banco.progresso.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   //para gerar os getteres e seteres
@Builder                //Builder vai nos prover uma maneira de criar objetos sem precisarmos de construtores e sem métodos setters em nossas classes
@AllArgsConstructor     //insere os construtores automaticamente
@NoArgsConstructor      //insere os construtores automaticamente
public class FieldMessage {

    private String fieldName;
    private String message;
}
