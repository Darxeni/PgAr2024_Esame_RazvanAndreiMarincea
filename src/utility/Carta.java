package utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Carta {
    private String nome;
    private String descrizione;
    private int copie;
    private ArrayList<CoppiaValoreSeme> valoreSeme;

    public Carta(String nome, String descrizione, int copie, ArrayList<CoppiaValoreSeme> valoreSeme) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.copie = copie;
        this.valoreSeme = valoreSeme;
    }
}
