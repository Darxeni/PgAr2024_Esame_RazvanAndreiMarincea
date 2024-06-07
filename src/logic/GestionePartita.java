package logic;

import it.kibo.fp.lib.InputData;
import utility.Carta;
import utility.Giocatore;
import utility.Personaggio;
import utility.Ruolo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static costants.costants.NUMERO_GIOCATORI;

public class GestionePartita {
    private static final ArrayList<Giocatore> giocatori = new ArrayList<>();

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

        System.out.println("ciao");
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

}
