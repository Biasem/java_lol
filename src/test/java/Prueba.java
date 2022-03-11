import utilidades.UtilidadesFicheros;

import java.io.IOException;
import java.time.LocalDateTime;

public class Prueba {

    public static void main(String[] args) throws IOException {
       // System.out.println(UtilidadesFicheros.leerItems());
        int tiempoinicio;
        tiempoinicio = LocalDateTime.now().getSecond();
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            System.out.println("programa en espera");
        }
        int tiempofin = LocalDateTime.now().getSecond();
        System.out.println(tiempofin-tiempoinicio);
    }

}
