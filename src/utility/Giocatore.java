package utility;

import java.util.ArrayList;

public class Giocatore {
    private String nome;
    private int PF;
    private ArrayList<Carta> mano =  new ArrayList<>();
    private ArrayList<Carta> carteEquipaggiate;
    private Ruolo tipoRuolo;
    private Personaggio personaggio;
    private int distanza = 1;



    public void setPF(int PF) {
        this.PF = PF;
    }

    public void setPersonaggio(Personaggio personaggio) {
        this.personaggio = personaggio;
    }

    public Giocatore(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
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

    public void setTipoRuolo(Ruolo tipoRuolo) {
        this.tipoRuolo = tipoRuolo;
    }

    public int getDistanza() {
        return distanza;
    }

    public void aggiungiCarteAllaMano(Carta carta) {
        this.mano.add(carta);
    }

    public void aggiungiArmaSulCampo(Arma carta) {
        this.carteEquipaggiate.add(carta);
        this.distanza += carta.getDistanza();
    }

    public void aggiungiStrumentoSulCampo(Equipaggiabile carta) {
        this.carteEquipaggiate.add(carta);
        switch (carta.getTipoEquipaggiabile()){
            case Barile:
                break;
            case Mirino:
                break;
            case Mustang:
                break;
        }
    }
}
