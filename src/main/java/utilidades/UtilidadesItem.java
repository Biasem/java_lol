package utilidades;

import modelos.Item;
import modelos.Personaje;

import java.util.List;

public class UtilidadesItem {

    public void equiparItem(Personaje personaje, Item item){
        List<Item> listaequipada = personaje.getEquipamiento();
        listaequipada.add(item);
        personaje.setDefensa(personaje.getDefensa()+ item.getAumentoDefensa());
        personaje.setAtaque(personaje.getAtaque()+ item.getAumentoDanio());
        personaje.setVida(personaje.getVida()+ item.getAumentoSalud());
        personaje.setMana(personaje.getMana()+ item.getAumentoMana());
        personaje.setEquipamiento(listaequipada);
    }




}
