package utilidades;

import modelos.Jugador;
import modelos.Partida;
import modelos.Personaje;

import java.time.LocalDateTime;
import java.util.*;

public class UtilidadesPartida implements IUtilidadesPartida{

    public void inicializarPartida(Partida partida, List<Jugador> participantes, List<Personaje> personajesDisponibles){
       Map<Jugador,Personaje> mapaJugadorPersonaje = new HashMap<>();
       Map<Integer, Set<Jugador>> mapaEquipos = new HashMap<>();
       for (Jugador j: participantes){
           List<Personaje> eleccion = new ArrayList<>(personajesDisponibles);
           eleccion.retainAll(j.getPersonajesFavoritos());
           if (!eleccion.isEmpty()){
               int rnd = (int) (Math.random()*( eleccion.size()-1));
               mapaJugadorPersonaje.put(j, eleccion.get(rnd));
           }else{
               int rnd = (int) (Math.random()*( personajesDisponibles.size()-1));
               mapaJugadorPersonaje.put(j, eleccion.get(rnd));
           }
       }



       partida.setElecciones(mapaJugadorPersonaje);
       partida.setJugadoresPorEquipo(mapaEquipos);
       partida.setInicioPartida(LocalDateTime.now());
    }

    public void finalizarPartida(Partida partida, Integer equipoVencedor){


    }
}
