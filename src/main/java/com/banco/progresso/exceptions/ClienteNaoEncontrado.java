package com.banco.progresso.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNaoEncontrado extends RuntimeException{

    public ClienteNaoEncontrado(Long idCliente){
        super("Cliente nao encontrado, id: " + idCliente);
    }
}
