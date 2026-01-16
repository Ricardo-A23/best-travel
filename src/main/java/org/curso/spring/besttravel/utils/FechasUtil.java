package org.curso.spring.besttravel.utils;

import java.time.LocalDateTime;
import java.util.Random;

public class FechasUtil {

    private static final Random random = new Random();

    public static LocalDateTime getRamdomSoon(){
        int hora = random.nextInt(5-2)+2;
        LocalDateTime fechaHoy = LocalDateTime.now();
        return fechaHoy.plusHours(hora);
    }

    public static LocalDateTime getRamdomLatter(){
        int hora = random.nextInt(12-6)+6;
        LocalDateTime fechaHoy = LocalDateTime.now();
        return fechaHoy.plusHours(hora);
    }


}
