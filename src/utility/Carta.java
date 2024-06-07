package utility;

import java.util.ArrayList;

public class Carta {
    private String nome;
    private String descrizione;
    private int copie;
    private ArrayList<CoppiaValoreSeme> valoreSemeArray;
    private CoppiaValoreSeme valoreSeme;

    public Carta(String nome, String descrizione, int copie, ArrayList<CoppiaValoreSeme> valoreSemeArray) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.copie = copie;
        this.valoreSemeArray = valoreSemeArray;
    }

    public Carta(String nome, String descrizione, CoppiaValoreSeme valoreSeme) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.valoreSeme = valoreSeme;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getCopie() {
        return copie;
    }

    public void setCopie(int copie) {
        this.copie = copie;
    }

    public ArrayList<CoppiaValoreSeme> getValoreSemeArray() {
        return valoreSemeArray;
    }

    public void setValoreSemeArray(ArrayList<CoppiaValoreSeme> valoreSemeArray) {
        this.valoreSemeArray = valoreSemeArray;
    }
}
