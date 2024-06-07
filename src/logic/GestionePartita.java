package logic;

import it.kibo.fp.lib.InputData;
import it.kibo.fp.lib.Menu;
import utility.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static costants.costants.NUMERO_GIOCATORI;

public class GestionePartita {
    public static final ArrayList<Giocatore> giocatori = new ArrayList<>();
    public static final ArrayList<? super Carta> carte = new ArrayList<>();
    public static final ArrayList<Carta> mazzo = new ArrayList<>();
    public static final ArrayList<Carta> mazzoScarti = new ArrayList<>();
    public static int turno = 0;

    private static int contaRinnegati = 1;
    private static int contaVice = 0;
    private static int contaFuorilegge = 0;
    private static Random r = new Random();

    public static void assegnazioneRuoli(int i) {
        if(i == 0){
            giocatori.get(i).setTipoRuolo(Ruolo.Sceriffo);
            System.out.println(giocatori.get(i).getNome()+", il tuo ruolo è lo Sceriffo");
        }
        else {
            boolean controllo = true;
            while (controllo){
                if(r.nextInt(3) == 0){
                    if(contaRinnegati != 0){
                        giocatori.get(i).setTipoRuolo(Ruolo.Rinnegato);
                        System.out.println(giocatori.get(i).getNome()+", il tuo ruolo è il Rinnegato");
                        contaRinnegati--;
                        controllo = false;
                    }
                }
                else if (r.nextInt(3) == 1) {
                    if(contaFuorilegge != 0){
                        giocatori.get(i).setTipoRuolo(Ruolo.Fuorilegge);
                        System.out.println(giocatori.get(i).getNome()+", il tuo ruolo è il Fuorilegge");
                        contaFuorilegge--;
                        controllo = false;
                    }
                }
                else {
                    if(contaVice != 0){
                        giocatori.get(i).setTipoRuolo(Ruolo.Vice);
                        System.out.println(giocatori.get(i).getNome()+", il tuo ruolo è il Vice");
                        contaVice--;
                        controllo = false;
                    }
                }
            }
        }
    }

    private static void assegnazionePersonaggi() {
        ArrayList<Personaggio> personaggi = ReadXML.letturaPersonaggiXML("src/resources/listaCarte.xml");
        Collections.shuffle(personaggi);
        for (int i = 0; i < giocatori.size(); i++) {
            giocatori.get(i).setPersonaggio(personaggi.getFirst());
            giocatori.get(i).setPF(personaggi.getFirst().getPF(), i);
            System.out.println(giocatori.get(i).getNome()+", "+personaggi.getFirst().getNome()+" è il tuo personaggio");
            personaggi.removeFirst();
        }
    }

    public static void creazioneGiocatori() {
        int numeroGiocatori = InputData.readIntegerBetween(NUMERO_GIOCATORI, 4, 7);
        System.out.println("A turno ora dovrete inserire il proprio nome e vi verrà fornito il vostro ruolo segreto, il primo a inserire il proprio nome riceverà il ruolo Sceriffo e sarà l'unico giocatore di cui si saprà il ruolo");
        impostazioneNumeroRuoli(numeroGiocatori);

        for (int i = 0; i < numeroGiocatori; i++) {
            giocatori.add(new Giocatore(InputData.readString("Giocatore "+(i+1)+", inserisci il nome:", false)));
            assegnazioneRuoli(i);
        }
        assegnazionePersonaggi();
    }

    private static void impostazioneNumeroRuoli(int numeroGiocatori) {
        switch (numeroGiocatori) {
            case 4:
                contaFuorilegge += 2;
                break;

            case 5:
                contaVice += 1;
                contaFuorilegge += 2;
                break;

            case 6:
                contaVice += 1;
                contaFuorilegge += 3;
                break;

            case 7:
                contaVice += 2;
                contaFuorilegge += 3;
                break;

            default:
                break;
        }
    }


    public static void creazioneMazzo() {
        ReadXML.letturaCarteXML("src/resources/listaCarte.xml", carte);
        for (Object carta : carte) {
            if(carta instanceof Arma arma){
                for (int j = 0; j < arma.getCopie(); j++) {
                    mazzo.add(new Arma(arma.getNome(), arma.getDescrizione(), arma.getValoreSemeArray().get(j)));
                }
            }
            if(carta instanceof Equipaggiabile strumento){
                for (int j = 0; j < strumento.getCopie(); j++) {
                    mazzo.add(new Equipaggiabile(strumento.getNome(), strumento.getDescrizione(), strumento.getValoreSemeArray().get(j)));
                }
            }
            if(carta instanceof GiocaEScarta cartaGiocaEScarta){
                for (int j = 0; j < cartaGiocaEScarta.getCopie(); j++) {
                    mazzo.add(new GiocaEScarta(cartaGiocaEScarta.getNome(), cartaGiocaEScarta.getDescrizione(), cartaGiocaEScarta.getValoreSemeArray().get(j), cartaGiocaEScarta.getTipoGiocaEScarta()));
                }
            }
        }
        Collections.shuffle(mazzo);
    }

    public static void preparazionePrimoTurno() {
        for (Giocatore giocatore : giocatori) {
            for (int j = 0; j < giocatore.getPF(); j++) {
                giocatore.aggiungiCarteAllaMano(mazzo.getFirst());
                mazzo.removeFirst();
            }
        }
    }

    public static void pescaDueCarte(Giocatore giocatore) {
        for (int i = 0; i < 2; i++) {
            giocatore.aggiungiCarteAllaMano(mazzo.getFirst());
            mazzo.removeFirst();
        }
    }

    public static void equipaggiaArma(Giocatore giocatore, Arma carta) {
        giocatore.aggiungiArmaSulCampo(carta);
    }

    public static void equipaggiaStrumento(Giocatore giocatore, Equipaggiabile carta) {
        giocatore.aggiungiStrumentoSulCampo(carta);
    }

    public static void usaGiocaEScarta(Giocatore giocatore, GiocaEScarta carta) {
        Menu menu;
        switch (carta.getTipoGiocaEScarta()){
            case Bang:
                int scelta;
                Giocatore giocatoreAttaccato = null;
                String[] g = new String[giocatori.size()];
                for (int i = 0; i < giocatori.size(); i++) {
                    if(i <= turno + giocatore.getDistanza() && turno != i && giocatori.size() > turno + giocatore.getDistanza()){
                        g[i] = giocatori.get(i).getNome()+"-"+giocatori.get(i).getPF();
                    }
                    if(i >= turno - giocatore.getDistanza() && turno != i && turno - giocatore.getDistanza() < 0){
                        g[i] = giocatori.get(giocatori.size() + (turno - giocatore.getDistanza())).getNome()+"-"+giocatori.get(i).getPF();
                    }
                }
                menu = new Menu("Scegli quale giocatore colpire", g, false, true, false);
                scelta = menu.choose();
                for (int i = 0; i < giocatori.size(); i++) {
                    if (g[scelta].split("-")[0].equals(giocatori.get(i).getNome())){
                        giocatoreAttaccato = giocatori.get(i);
                    }
                }
                assert giocatoreAttaccato != null;
                bang(giocatoreAttaccato);
                break;

            case Mancato:
                boolean yesOrNo = InputData.readYesOrNo("Vuoi annullare il colpo?");
                if (yesOrNo) {
                    giocatore.setPF(giocatore.getPF() + 1);
                }
                break;
            case Birra:
                break;
            case Saloon:
                break;
            case Wells_Fargo:
                break;
            case Panico:
                break;
            case Diligenza:
                break;
            case Cat_Balou:
                break;
            case Gatling:
                break;

        }
    }

    private static void bang(Giocatore giocatoreAttaccato) {
        giocatoreAttaccato.setPF(giocatoreAttaccato.getPF() - 1);
        for (int i = 0; i < giocatoreAttaccato.getMano().size(); i++) {
            if(giocatoreAttaccato.getMano().get(i).getNome().equals("Mancato!")){
              usaGiocaEScarta(giocatoreAttaccato, (GiocaEScarta) giocatoreAttaccato.getMano().get(i));
            }
        }
    }
}
