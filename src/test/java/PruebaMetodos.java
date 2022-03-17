import modelos.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import utilidades.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class PruebaMetodos {

    List<Personaje> personajes = new ArrayList<>();
    List<Jugador> jugadores = new ArrayList<>();
    List<Item>  items = new ArrayList<>();
    Partida partida = new Partida();


    @Before
    public void inicializadoDeDatos() throws Exception {

        //----------------------- PERSONAJES -------------------------------------
        Personaje p = new Personaje(1,"Lux", "Campeona de luz", LocalDate.now(), 0, 100D, 100D,
                100D, 100D, 100D, 100D, 100D, 100D, Region.DEMACIA, new ArrayList<>(), new ArrayList<>(), null);
        Personaje p2 = new Personaje(2,"Ezreal", "El Explorador", LocalDate.now(), 0, 0D, 0D,
                0D, 0D, 105D, 105D, 105D, 105D, Region.PILTOVER, new ArrayList<>(), new ArrayList<>(), null);
        Personaje p3 = new Personaje(3,"Sion", "Titán", LocalDate.now(), 0, 0D, 0D,
                0D, 0D, 140D, 140D, 140D, 140D, Region.NOXUS, new ArrayList<>(), new ArrayList<>(), null);
        Personaje p4 = new Personaje(4,"Miss Fortune", "Pistolas", LocalDate.now(), 0, 0D, 0D,
                0D, 0D, 130D, 130D, 130D, 130D, Region.AGUAS_ESTANCADAS, new ArrayList<>(), new ArrayList<>(), null);
        Personaje p5 = new Personaje(5,"Gankplank", "Pirata", LocalDate.now(), 0, 0D, 0D,
                0D, 0D, 120D, 120D, 120D, 120D, Region.AGUAS_ESTANCADAS, new ArrayList<>(), new ArrayList<>(), null);


        //---------------------- JUGADORES -----------------------------------------
        Jugador j = new Jugador(1,"Ibai", new HashSet<>(), new HashMap<>());
        Jugador j1 = new Jugador(2,"Reven", new HashSet<>(), new HashMap<>());
        Jugador j2 = new Jugador(3,"Barbe", new HashSet<>(), new HashMap<>());
        Jugador j3 = new Jugador(4,"Knekro", new HashSet<>(), new HashMap<>());



        //---------------------- ITEMS -----------------------------------------
        Item i = new Item(1,"Filo Infinito", 100D,0D,0D, 0D);
        Item i2 = new Item(2,"Hidra Titánica", 50D,0D,100D, 0D);


        //---------------------- ESCALABILIDAD -----------------------------------------
        Escalabilidad e = new Escalabilidad(1, 5D, 5D, 5D, 5D);
        Escalabilidad e2 = new Escalabilidad(2, 1D, 1D, 1D, 1D);

        //---------------------- HABILIDADES -----------------------------------------
        Habilidad h = new Habilidad(1, "Disparo Arcano", 20D, 0D, 10D, TipoHabilidad.DAÑO);
        Habilidad h1 = new Habilidad(2, "Singularidad Brillante", 30D, 0D, 20D, TipoHabilidad.DAÑO);


        p2.getHabilidades().add(h);
        p.getHabilidades().add(h1);

        p.setEscalabilidad(e);
        p2.setEscalabilidad(e);
        p3.setEscalabilidad(e);
        p4.setEscalabilidad(e2);
        p5.setEscalabilidad(e2);


        personajes.add(p);
        personajes.add(p2);
        personajes.add(p3);
        personajes.add(p4);
        personajes.add(p5);

        jugadores.add(j);
        jugadores.add(j1);
        jugadores.add(j2);
        jugadores.add(j3);

        j.getPersonajesFavoritos().add(p);
        j1.getPersonajesFavoritos().add(p2);
        j2.getPersonajesFavoritos().add(p3);
        j3.getPersonajesFavoritos().add(p4);

        j.getPartidasGanadas().put(p,1);
        j1.getPartidasGanadas().put(p2,1);
        j2.getPartidasGanadas().put(p3,1);
        j3.getPartidasGanadas().put(p4,1);

        items.add(i);
        items.add(i2);




    }




    //---------------------------------------------- V5 --------------------------------------------------------------//

    @Test
    public void testLevelUp() {
        Personaje lux = personajes.get(0);
        UtilidadesPersonaje.levelUp(lux);
        Integer nivel = 1;
        Double statEsperada = 105D;
        assertEquals(lux.getNivel(), nivel);
        assertEquals(lux.getAtaque(), statEsperada);
        assertEquals(lux.getVida(), statEsperada);
    }

    @Test
    public void testPersonajesPorRegion() {

        Map<Region, List<Personaje>> personajesPorRegion =  UtilidadesPersonaje.getPersonajesPorRegion(personajes);
        Integer regionesDelMapa = personajesPorRegion.keySet().size();
        Integer regionesEsperadas = 4;
        Integer personajesEnAguasEstancadas = personajesPorRegion.get(Region.AGUAS_ESTANCADAS).size();
        Integer personajesEnAguasEstancadasEsperados = 2;

        assertEquals(regionesDelMapa, regionesEsperadas);
        assertEquals(personajesEnAguasEstancadas,personajesEnAguasEstancadasEsperados);
    }


    @Test
    public void testMasPoderoso() {

        Personaje masPoderosoEsperado = personajes.stream().filter(p-> p.getNombre().equals("Sion")).findFirst().get();
        Personaje masPoderosoObtenido = UtilidadesPersonaje.getMasPoderoso(personajes);
        assertEquals(masPoderosoEsperado, masPoderosoObtenido);

    }


    @Test
    public void testPersonajesMasPoderosoPorRegion() {

        Map<Region,Personaje> personajesPoderososPorRegion =  UtilidadesPersonaje.getMasPoderosoPorRegion(personajes);
        Integer regionesDelMapa = personajesPoderososPorRegion.keySet().size();
        Integer regionesEsperadas = 4;
        Personaje masPoderosoEsperado = personajes.stream().filter(p-> p.getNombre().equals("Miss Fortune")).findFirst().get();
        Personaje masPoderosoObtenido = personajesPoderososPorRegion.get(Region.AGUAS_ESTANCADAS);

        assertEquals(regionesDelMapa, regionesEsperadas);
        assertEquals(masPoderosoEsperado,masPoderosoObtenido);
    }


    //---------------------------------------------- V6 --------------------------------------------------------------//

    @Test
    public void testLeerPersonajesFichero() throws IOException {

        List<Personaje> personajesLeidos = UtilidadesFicheros.leerPersonajes();
        Integer tamanyoLista = personajesLeidos.size();
        Integer tamanyoListaEsperado = 10;

        assertEquals(tamanyoLista, tamanyoListaEsperado);
        personajesLeidos.forEach(p->{
            assertNotNull(p.getNombre());
            assertNotNull(p.getFechaCreacion());
            assertNotNull(p.getAtaqueBase());
            assertNotNull(p.getDefensaBase());
            assertNotNull(p.getVidaBase());
            assertNotNull(p.getManaBase());
            assertNotNull(p.getRegion());
        });
    }


    @Test
    public void testLeerItemsFichero() throws IOException {

        List<Item> itemsLeidos = UtilidadesFicheros.leerItems();
        Integer tamanyoLista = itemsLeidos.size();
        Integer tamanyoListaEsperado = 7;

        assertEquals(tamanyoLista, tamanyoListaEsperado);
        itemsLeidos.forEach(i->{
            assertNotNull(i.getNombre());
            assertNotNull(i.getAumentoDanio());
            assertNotNull(i.getAumentoDefensa());
            assertNotNull(i.getAumentoMana());
            assertNotNull(i.getAumentoSalud());
        });
    }

    @Test
    public void testLeerPersonajesFicheroConHabilidad() throws IOException {

        List<Personaje> personajesLeidos = UtilidadesFicheros.leerYAprenderHabilidades();
        Integer tamanyoLista = personajesLeidos.size();
        Integer tamanyoListaEsperado = 10;
        List<Habilidad> habilidades = new ArrayList<>();
        personajesLeidos.stream().filter(p->p.getHabilidades()!= null).forEach(p->habilidades.addAll(p.getHabilidades()));
        Integer tamanyoListaHabilidades = habilidades.size();
        Integer tamanyoListaEsperadoHabilidades = 6;

        //Comprobacion de personajes
        assertEquals(tamanyoLista, tamanyoListaEsperado);
        personajesLeidos.forEach(p->{
            assertNotNull(p.getNombre());
            assertNotNull(p.getFechaCreacion());
            assertNotNull(p.getAtaqueBase());
            assertNotNull(p.getDefensaBase());
            assertNotNull(p.getVidaBase());
            assertNotNull(p.getManaBase());
            assertNotNull(p.getRegion());
        });

        //Comprobacion de habilidades
        assertEquals(tamanyoListaHabilidades,tamanyoListaEsperadoHabilidades);
        List <Integer> idsDeseados = new ArrayList<>(Arrays.asList(1,2,10));
        Integer tamanyoListaHabilidadesPersonaje = 2;
        List<Personaje> personajesConHabilidades = personajesLeidos.stream().filter(p-> idsDeseados.contains(p.getId())).collect(Collectors.toList());
        personajesConHabilidades.stream().map(p->p.getHabilidades()).forEach(h->{
            Integer tamanyohabilidades = h.size();
            assertEquals(tamanyohabilidades,tamanyoListaHabilidadesPersonaje);
        });

    }

    @Test
    public void testEquiparItem() {

        Personaje personajeDePrueba = personajes.get(0);
        Item itemDePrueba = items.get(1);
        List<Double> statsEsperadas = new ArrayList<>(Arrays.asList(150D,100D,200D,100D));
        UtilidadesItem.equiparItem(personajeDePrueba,itemDePrueba);
        List<Double> statsObtenidas = new ArrayList<>();
        statsObtenidas.add(personajeDePrueba.getAtaque());
        statsObtenidas.add(personajeDePrueba.getDefensa());
        statsObtenidas.add(personajeDePrueba.getVida());
        statsObtenidas.add(personajeDePrueba.getMana());

        assertEquals(statsEsperadas,statsObtenidas);
    }

    @Test
    public void testGolpearConHabilidad() {
        Personaje emisor = personajes.get(0);
        Personaje receptor = personajes.get(1);
        emisor.getEquipamiento().add(items.get(0));
        Habilidad habilidad = emisor.getHabilidades().get(0);
        UtilidadesHabilidad.golpearConHabilidad(emisor,receptor,habilidad);
        Double manaEsperadoEmisor = 170D;
        Double vidaEsperadaReceptor = 126.5;
        assertEquals(emisor.getMana(),manaEsperadoEmisor);
        assertEquals(receptor.getVida(),vidaEsperadaReceptor);
    }


    //---------------------------------------------- V7 --------------------------------------------------------------//

    @Test
    public void testIniciarPartida() {

        Map<Jugador,Personaje> eleccionesEsperadas = new HashMap<>();
        eleccionesEsperadas.put(jugadores.get(0),personajes.get(0));
        eleccionesEsperadas.put(jugadores.get(1),personajes.get(1));
        eleccionesEsperadas.put(jugadores.get(2),personajes.get(2));
        eleccionesEsperadas.put(jugadores.get(3),personajes.get(3));

        UtilidadesPartida.inicializarPartida(partida,jugadores,personajes);

        Map<Jugador,Personaje> eleccionesObtenidas = partida.getElecciones();

        Integer tamanyoEquipos = 2;
        Map<Integer, Set<Jugador>> equipos = partida.getJugadoresPorEquipo();
        Integer equiposEnPartida = equipos.keySet().size();
        Integer tamanyoEquipo1 = equipos.get(1).size();
        Integer tamanyoEquipo2 = equipos.get(1).size();

        assertEquals(eleccionesEsperadas,eleccionesObtenidas);
        assertNotNull(partida.getInicioPartida());
        assertEquals(equiposEnPartida,tamanyoEquipos);
        assertEquals(tamanyoEquipo1,tamanyoEquipo2);

    }

    @Test
    public void testFinalizarPartida() {


        UtilidadesPartida.inicializarPartida(partida,jugadores,personajes);
        UtilidadesPartida.finalizarPartida(partida,1);

        Integer duracionPartidaEsperada= Math.toIntExact(partida.getInicioPartida().until(partida.getFinPartida(), ChronoUnit.SECONDS));
        Integer duracion = partida.getDuracionPartida();

        Integer numPrueba = 2;
        Integer numObtenido = Math.toIntExact(jugadores.stream().filter(p -> p.getPartidasGanadas().containsValue(2)).count());

        assertEquals(duracionPartidaEsperada,duracion);
        assertNotNull(partida.getFinPartida());
        assertNotNull(partida.getEquipoVencedor());
        assertEquals(numObtenido,numPrueba);


    }


}