package utility;

import java.util.ArrayList;
import java.util.Map;

public class Equipaggiabile extends Carta{
    private TipoEquipaggiabile tipoEquipaggiabile;

    public Equipaggiabile(String nome, String descrizione, int copie, ArrayList<CoppiaValoreSeme> semeValore, TipoEquipaggiabile tipoEquipaggiabile) {
        super(nome, descrizione, copie, semeValore);
        this.tipoEquipaggiabile = tipoEquipaggiabile;
    }
}
