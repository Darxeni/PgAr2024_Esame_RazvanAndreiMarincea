package utility;

import java.util.ArrayList;

public class Giocatore {
    private String nome;
    private int PF;
    private ArrayList<Carta> mano;
    private Ruolo tipoRuolo;

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

    public void setPF(int PF) {
        this.PF = PF;
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
        if(tipoRuolo.equals(Ruolo.Sceriffo)){
            this.setPF(5);
        }
        else{
            this.setPF(4);
        }
    }
}
