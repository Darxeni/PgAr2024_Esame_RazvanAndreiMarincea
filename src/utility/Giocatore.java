package utility;

import java.util.ArrayList;

public class Giocatore {
    private String nome;
    private int PF;
    private ArrayList<Carta> mano;
    private Ruolo tipoRuolo;
    private Personaggio personaggio;

    public void setPersonaggio(Personaggio personaggio) {
        this.personaggio = personaggio;
    }

    public Giocatore(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPF() {
        return PF;
    }

    public void setPF(int PF, int i) {
        if (i == 0){
            this.PF = PF + 1;
        }
        else {
            this.PF = PF;
        }
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }

    public Ruolo getTipoRuolo() {
        return tipoRuolo;
    }

    public void setTipoRuolo(Ruolo tipoRuolo) {
        this.tipoRuolo = tipoRuolo;
    }
}
