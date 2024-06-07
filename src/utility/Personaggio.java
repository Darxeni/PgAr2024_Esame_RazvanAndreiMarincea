package utility;

public class Personaggio {
    private String nome;
    private int PF;
    private String descrizione;

    public Personaggio(String nome, int PF, String descrizione) {
        this.nome = nome;
        this.PF = PF;
        this.descrizione = descrizione;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
