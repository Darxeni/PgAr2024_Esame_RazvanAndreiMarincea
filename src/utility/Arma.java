package utility;

import java.util.ArrayList;
import java.util.Map;

public class Arma extends Equipaggiabile{
    private int distanza;

    public Arma(String nome, String descrizione, int copie, ArrayList<CoppiaValoreSeme> semeValore, TipoEquipaggiabile tipoEquipaggiabile, int distanza) {
        super(nome, descrizione, copie, semeValore, tipoEquipaggiabile);
        this.distanza = distanza;
    }
}
