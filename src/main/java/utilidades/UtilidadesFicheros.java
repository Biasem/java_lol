package utilidades;

import com.opencsv.CSVReader;
import modelos.*;


import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;


public class UtilidadesFicheros {


    public UtilidadesFicheros() {
    }

    public static final char SEPARATOR=',';
    public static final char QUOTE='"';

    public static List<Personaje>  leerPersonajes() throws IOException {
        UtilidadesPersonaje up = new UtilidadesPersonaje();

        List<Personaje> personajes = new ArrayList<>();
        CSVReader reader = null;                          //"D:\\javalol\\src\\main\\java\\documentos\\personajes.csv"
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
                    p.setAtaqueBase( Double.valueOf(valores[3]));
                    p.setDefensaBase( Double.valueOf(valores[4]));
                    p.setVidaBase( Double.valueOf(valores[5]));
                    p.setManaBase( Double.valueOf(valores[6]));
                    p.setRegion( Region.valueOf(valores[7]));
                    p.setNivel(0);
                    p.setEscalabilidad(new Escalabilidad(1,1.1,1.1,1.1,1.1));
                    up.levelUp(p);
                    List<Habilidad> h = new ArrayList<>();
                    List<Item> i = new ArrayList<>();
                    p.setHabilidades(h);
                    p.setEquipamiento(i);
                    p.setDescripcion("");
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
    public static List<Personaje>  leerYAprenderHabilidades() throws IOException {
    List<Personaje> listapersonajes = leerPersonajes();

    int id_personaje;

    CSVReader reader = null;
        try {
        reader = new CSVReader(new FileReader("E:\\java_lol\\src\\main\\java\\documentos\\habilidades.csv"),SEPARATOR,QUOTE);
        String[] nextLine = null;

        int count = 0;

           for (Personaje p:listapersonajes){
               List<Habilidad> lista = new ArrayList<>();
               while ((nextLine = reader.readNext()) != null) {
                   if (count > 0) {
                       String[] valores = nextLine;
                       Habilidad h = new Habilidad();
                       h.setId(Integer.parseInt(valores[0]));
                       id_personaje = Integer.parseInt(valores[1]);
                       h.setNombre(valores[2]);
                       h.setDanioBase(Double.valueOf(valores[3]));
                       h.setCosteMana(Double.valueOf(valores[4]));
                       h.setTipoHabilidad(TipoHabilidad.valueOf(valores[5]));
                       if (p.getId()==id_personaje){
                           lista.add(h);
                       }
                   }
                   count++;
               }
               p.setHabilidades(lista);
           }


        }
        catch (Exception e){
            throw e;
        }
        return listapersonajes;
        }




}
