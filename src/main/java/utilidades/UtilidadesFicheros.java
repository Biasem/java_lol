package utilidades;

import com.opencsv.CSVReader;
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

    public static final char SEPARATOR=';';
    public static final char QUOTE='"';

    public static List<Personaje>  leerFicheroPersonajes() throws IOException {

        List<Personaje> personajes = new ArrayList<>();
        CSVReader reader = null;                          //"D:\\Descargas Utorrent\\estudios\\DAW 1\\java proyect lol\\src\\main\\java\\coche.csv"
        try {                                            //"E:\\java proyect 2\\src\\main\\java\\Coche.csv"
            reader = new CSVReader(new FileReader("D:\\Descargas Utorrent\\estudios\\DAW 1\\java proyect lol\\src\\main\\java\\coche.csv"),SEPARATOR,QUOTE);
            String[] nextLine= null ;
            int count = 0;

            while ((nextLine = reader.readNext()) != null) {

                if(count >0) {
                    String[] valores = nextLine[0].toString().split(",");
                    Personaje p = new Personaje();
                    p.setId(Integer.parseInt(valores[0]));
                    p.setNombre(valores[1]);
                    p.setFechaCreacion(LocalDate.parse(valores[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    p.setAtaqueBase( Double.parseDouble(valores[3]));
                    p.setDefensaBase( Double.parseDouble(valores[4]));
                    p.setVidaBase( Double.parseDouble(valores[5]));
                    p.setManaBase( Double.parseDouble(valores[6]));
                    p.setRegion( Region.valueOf(valores[7]));
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
