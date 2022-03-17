package utilidades;

import modelos.Habilidad;
import modelos.Item;
import modelos.Personaje;
import modelos.TipoHabilidad;

public class UtilidadesHabilidad {

    public static void golpearConHabilidad(Personaje emisor, Personaje receptor, Habilidad habilidadEmisor){
        UtilidadesPersonaje up = new UtilidadesPersonaje();
        UtilidadesItem ui = new UtilidadesItem();
        double daniohabilidad;
    if(habilidadEmisor.getTipoHabilidad()== TipoHabilidad.DAÑO){
        while((emisor.getNivel()<18)&&(receptor.getNivel()<18)){
        up.levelUp(emisor);
        up.levelUp(receptor);
        }
        if (emisor.getEquipamiento().isEmpty()==false){
            for(Item i :emisor.getEquipamiento()){
                ui.equiparItem(emisor,i);
            }
        }
        if (receptor.getEquipamiento().isEmpty()==false){
            for(Item i :receptor.getEquipamiento()){
                ui.equiparItem(receptor,i);
            }
        }
        daniohabilidad = habilidadEmisor.getDanioBase()+(0.2* emisor.getAtaque())-(0.1* receptor.getDefensa());
        emisor.setMana(emisor.getMana()-habilidadEmisor.getCosteMana());
        receptor.setVida(receptor.getVida()-daniohabilidad);
    }
    }





}
