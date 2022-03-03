package utilidades;

import modelos.Escalabilidad;
import modelos.Personaje;
import modelos.Region;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UtilidadesPersonaje {

    public UtilidadesPersonaje(){}

    //Que recibiendo un personaje aumente su nivel en 1, actualizando
    //todas sus estadísticas según su escalabilidad.
    public static Personaje levelUp(Personaje personaje){
        if (personaje.getNivel()<18) {
            personaje.setNivel(personaje.getNivel() + 1);
            personaje.setAtaque(personaje.getAtaqueBase() + personaje.getEscalabilidad().getIncrementoDanioNivel() * personaje.getNivel());
            personaje.setDefensa(personaje.getDefensaBase() + personaje.getEscalabilidad().getIncrementoDefensaNivel() * personaje.getNivel());
            personaje.setMana(personaje.getManaBase() + personaje.getEscalabilidad().getIncrementoManaNivel() * personaje.getNivel());
            personaje.setVida(personaje.getVidaBase() + personaje.getEscalabilidad().getIncrementoSaludNivel() * personaje.getNivel());
        }
        return personaje;
    }

    //Que a partir de un listado de personajes devuelve un mapa de los
    //personajes agrupados por región.
    public static Map<Region,List<Personaje>> getPersonajesPorRegion(List<Personaje> personajes){

        return personajes.stream().collect(Collectors.groupingBy(p-> p.getRegion()));
    }

    //A partir de una lista de personajes, devuelve el personaje más
    //poderoso.
    public static Personaje getMasPoderoso(List<Personaje> personaje){
        Personaje poderoso = new Personaje();
        for (Personaje perso :personaje){
            while(perso.getNivel()<18){
                levelUp(perso);
            }
            if (poderoso.getNivel()==null){
                poderoso = perso;
            }
            if((perso.getAtaque()+ perso.getDefensa()+perso.getMana()+perso.getVida())>
                    (perso.getAtaque())+poderoso.getDefensa()+ poderoso.getMana()+ poderoso.getVida()){
                poderoso = perso;
            }
        }
        return poderoso;
    }

    //Que devuelve el personaje más poderoso de cada región.

    public static Map<Region, Personaje> getMasPoderosoPorRegion(List<Personaje> personajes){
        Map<Region,Personaje> mapa = new HashMap<>();

        mapa.put(Region.DEMACIA,getMasPoderoso(personajes.stream().filter(p-> p.getRegion().equals(Region.DEMACIA)).collect(Collectors.toList())));
        mapa.put(Region.NOXUS,getMasPoderoso(personajes.stream().filter(p-> p.getRegion().equals(Region.NOXUS)).collect(Collectors.toList())));
        mapa.put(Region.JONIA,getMasPoderoso(personajes.stream().filter(p-> p.getRegion().equals(Region.JONIA)).collect(Collectors.toList())));
        mapa.put(Region.SHURIMA,getMasPoderoso(personajes.stream().filter(p-> p.getRegion().equals(Region.SHURIMA)).collect(Collectors.toList())));
        mapa.put(Region.TARGON,getMasPoderoso(personajes.stream().filter(p-> p.getRegion().equals(Region.TARGON)).collect(Collectors.toList())));
        mapa.put(Region.FREIJORD,getMasPoderoso(personajes.stream().filter(p-> p.getRegion().equals(Region.FREIJORD)).collect(Collectors.toList())));
        mapa.put(Region.PILTOVER,getMasPoderoso(personajes.stream().filter(p-> p.getRegion().equals(Region.PILTOVER)).collect(Collectors.toList())));
        mapa.put(Region.ZAUN,getMasPoderoso(personajes.stream().filter(p-> p.getRegion().equals(Region.ZAUN)).collect(Collectors.toList())));
        mapa.put(Region.BANDLE,getMasPoderoso(personajes.stream().filter(p-> p.getRegion().equals(Region.BANDLE)).collect(Collectors.toList())));
        mapa.put(Region.AGUAS_ESTANCADAS,getMasPoderoso(personajes.stream().filter(p-> p.getRegion().equals(Region.AGUAS_ESTANCADAS)).collect(Collectors.toList())));
        mapa.put(Region.DESCONOCIDA,getMasPoderoso(personajes.stream().filter(p-> p.getRegion().equals(Region.DESCONOCIDA)).collect(Collectors.toList())));

        return mapa;
    }


}
