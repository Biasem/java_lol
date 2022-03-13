import utilidades.UtilidadesFicheros;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Prueba {

    public static void main(String[] args) throws IOException {
       // System.out.println(UtilidadesFicheros.leerItems());
        /*int tiempoinicio;
        tiempoinicio = LocalDateTime.now().getSecond();
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            System.out.println("programa en espera");
        }
        int tiempofin = LocalDateTime.now().getSecond();
        System.out.println(tiempofin-tiempoinicio);*/
        Map<Integer,Set<Integer>> prueba = new HashMap<>();

        Set<Integer> s1 = new HashSet<>();
        s1.add(4);
        s1.add(2);
        s1.add(3);
        Set<Integer> s2 = s1;

        s2.add(3);
        s2.add(2);
        s2.add(4);
        prueba.put(1, s1);
        prueba.put(2, s2);
        System.out.println(s2.size());


    }

}
