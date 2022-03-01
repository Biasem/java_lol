package utilidades;

import com.opencsv.CSVReader;
import modelos.Escalabilidad;
import modelos.Personaje;
import modelos.Region;


import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UtilidadesFicheros {


    public UtilidadesFicheros() {
    }

    public static final char SEPARATOR=',';
    public static final char QUOTE='"';

    public static List<Personaje>  leerPersonajes() throws IOException {
    UtilidadesPersonaje up = new UtilidadesPersonaje();

        List<Personaje> personajes = new ArrayList<>();
        CSVReader reader = null;                          //"D:\\javalol\\src\\main\\java\\archivos\\personajes.csv"
        try {                                            //"E:\java_lol\src\main\java\documentos\personajes.csv"
            reader = new CSVReader(new FileReader("E:\\java_lol\\src\\main\\java\\documentos\\personajes.csv"),SEPARATOR,QUOTE);
            String[] nextLine= null ;
            int count = 0;

            while ((nextLine = reader.readNext()) != null) {

                if(count >0) {

                    String[] valores = nextLine;
                    Personaje p = new Personaje();
                    p.setId(Integer.parseInt(valores[0]));
                    p.setNombre(valores[1]);
                    p.setFechaCreacion(LocalDate.parse(valores[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    p.setAtaqueBase( Double.parseDouble(valores[3]));
                    p.setDefensaBase( Double.parseDouble(valores[4]));
                    p.setVidaBase( Double.parseDouble(valores[5]));
                    p.setManaBase( Double.parseDouble(valores[6]));
                    p.setRegion( Region.valueOf(valores[7]));
                    p.setNivel(0);
                    p.setEscalabilidad(new Escalabilidad(1,1.1,1.1,1.1,1.1));
                    up.levelUp(p);
                    personajes.add(p);
                }
                count++;
            }

        } catch (Exception e) {
            throw e;
        }

        reader.close();

        return  personajes;
    }

}
