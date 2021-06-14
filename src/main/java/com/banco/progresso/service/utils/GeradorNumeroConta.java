package com.banco.progresso.service.utils;

import java.util.Random;

public class GeradorNumeroConta {

    public static Integer geradorNumero(){
        Random gerador = new Random();
        return gerador.nextInt(10000);
    }
}
