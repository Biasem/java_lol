package utilidades;

import modelos.Jugador;
import modelos.Partida;
import modelos.Personaje;

import java.time.LocalDateTime;
import java.util.*;

public class UtilidadesPartida implements IUtilidadesPartida{

    public void inicializarPartida(Partida partida, List<Jugador> participantes, List<Personaje> personajesDisponibles){
       Map<Jugador,Personaje> mapaJugadorPersonaje = new HashMap<>();
       Set<Jugador> eq1 = new HashSet<>();
       Set<Jugador> eq2 = new HashSet<>();
       Map<Integer, Set<Jugador>> mapaEquipos = new HashMap<>();
       List<Personaje> copiapersonajes = personajesDisponibles;

       // Elecciones de personajes--------------------------------------------------------
       for (Jugador j: participantes){
           List<Personaje> eleccion = new ArrayList<>(personajesDisponibles);
           eleccion.retainAll(j.getPersonajesFavoritos());
           if (!eleccion.isEmpty()){
               int rnd = (int) (Math.random()*( eleccion.size()+1));
               mapaJugadorPersonaje.put(j, eleccion.get(rnd));
           }else{
               int rnd = (int) (Math.random()*( personajesDisponibles.size()+1));
               mapaJugadorPersonaje.put(j, eleccion.get(rnd));
           }

           //Eleccion de equipos----------------------------------------------------------
           if (eq1.size()== participantes.size()/2){
               eq2.add(j);
           }else if(eq2.size()== participantes.size()/2){
               eq1.add(j);
           }else{
               int rnd = (int) (Math.random()*2);
               if (rnd ==0){
                   eq1.add(j);
               }else{
                   eq2.add(j);
               }
           }
       }
       //Final del método
       mapaEquipos.put(1,eq1);
       mapaEquipos.put(2,eq2);
       partida.setElecciones(mapaJugadorPersonaje);
       partida.setJugadoresPorEquipo(mapaEquipos);
       partida.setInicioPartida(LocalDateTime.now());
    }

    public void finalizarPartida(Partida partida, Integer equipoVencedor){
    int tiempo = 0;
    partida.setFinPartida(LocalDateTime.now());
    tiempo = partida.getFinPartida().getSecond()-partida.getInicioPartida().getSecond();
    partida.setDuracionPartida(tiempo);
    partida.setEquipoVencedor(equipoVencedor);

    for(Jugador j:partida.getJugadoresPorEquipo().get(equipoVencedor)){
        if (j.getPartidasGanadas().equals(null) || j.getPartidasGanadas().size()==0){
            Map<Personaje,Integer> mapa = new HashMap<>();
            mapa.put(partida.getElecciones().get(j),1);
        }
        else if(j.getPartidasGanadas().keySet().contains(partida.getElecciones().get(j))== true){
        j.getPartidasGanadas().put(partida.getElecciones().get(j),j.getPartidasGanadas().get(partida.getElecciones().get(j))+1);
        }
        else if(j.getPartidasGanadas().keySet().contains(partida.getElecciones().get(j))== false){
            j.getPartidasGanadas().put(partida.getElecciones().get(j),1);

        }
    }

    }
}
