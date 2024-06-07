package utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GiocaEScarta extends Carta{
    private TipoGiocaEScarta tipoGiocaEScarta;

    public GiocaEScarta(String nome, String descrizione, int copie, ArrayList<CoppiaValoreSeme> valoreSeme, TipoGiocaEScarta tipoGiocaEScarta) {
        super(nome, descrizione, copie, valoreSeme);
        this.tipoGiocaEScarta = tipoGiocaEScarta;
    }

    public GiocaEScarta(String nome, String descrizione, CoppiaValoreSeme coppiaValoreSeme, TipoGiocaEScarta tipoGiocaEScarta) {
        super(nome, descrizione, coppiaValoreSeme);
        this.tipoGiocaEScarta = tipoGiocaEScarta;
    }

    public TipoGiocaEScarta getTipoGiocaEScarta() {
        return tipoGiocaEScarta;
    }

    public void setTipoGiocaEScarta(TipoGiocaEScarta tipoGiocaEScarta) {
        this.tipoGiocaEScarta = tipoGiocaEScarta;
    }
}
