package utility;

import java.util.ArrayList;
import java.util.Map;

public class Equipaggiabile extends Carta{
    private TipoEquipaggiabile tipoEquipaggiabile;

    public Equipaggiabile(String nome, String descrizione, int copie, ArrayList<CoppiaValoreSeme> semeValore, TipoEquipaggiabile tipoEquipaggiabile) {
        super(nome, descrizione, copie, semeValore);
        this.tipoEquipaggiabile = tipoEquipaggiabile;
    }

    public Equipaggiabile(String nome, String descrizione, CoppiaValoreSeme valoreSeme) {
        super(nome, descrizione, valoreSeme);
    }

    public TipoEquipaggiabile getTipoEquipaggiabile() {
        return tipoEquipaggiabile;
    }

    public void setTipoEquipaggiabile(TipoEquipaggiabile tipoEquipaggiabile) {
        this.tipoEquipaggiabile = tipoEquipaggiabile;
    }
}
