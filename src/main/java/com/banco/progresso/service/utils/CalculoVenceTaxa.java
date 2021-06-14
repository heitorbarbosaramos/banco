package com.banco.progresso.service.utils;

import java.util.Calendar;
import java.util.Date;

public class CalculoVenceTaxa {

    public static Date CalculoVenceTaxa(Date data){

        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.DAY_OF_MONTH, 30);
        data = cal.getTime();

        return data;

    }
}
