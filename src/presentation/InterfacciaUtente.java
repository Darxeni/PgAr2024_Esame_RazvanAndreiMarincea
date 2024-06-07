package presentation;


import it.kibo.fp.lib.InputData;
import it.kibo.fp.lib.Menu;
import logic.GestionePartita;
import utility.*;

import java.io.IOException;
import java.util.ArrayList;

import static costants.costants.*;
import static logic.GestionePartita.giocatori;
import static logic.GestionePartita.turno;

public class InterfacciaUtente {
    public static Menu menu;

    public static void start() {
        System.out.println(PRESENTAZIONE);
        if (sceltaRegole()) {
            regole();
        }
        GestionePartita.creazioneGiocatori();
        GestionePartita.creazioneMazzo();
        partita();
    }

    public static void partita() {
        Giocatore giocatore;
        GestionePartita.preparazionePrimoTurno();
        while (turno < giocatori.size()) {
            giocatore = giocatori.get(turno);

            GestionePartita.pescaDueCarte(giocatore);

            ArrayList<String> carte = new ArrayList<>();
            for (int i = 0; i < giocatore.getMano().size(); i++) {
                carte.add(giocatore.getMano().get(i).getNome());
            }

            stampaCarteInMano(giocatore, carte);

            sceltaCartaDaGiocare(giocatore, carte);

            if (giocatore.getMano().size() > giocatore.getPF()){
                rimuoviCarteDallaMano(giocatore, carte);
            }

            if (turno == giocatori.size() - 1) {
                turno = 0;
            }
            else{
                turno++;
            }
        }
    }

    private static void rimuoviCarteDallaMano(Giocatore giocatore, ArrayList<String> carte) {
        int scelta;
        do {
            String[] carteDaScartare = new String[carte.size()];
            for (int i = 0; i < carte.size(); i++) {
                carteDaScartare[i] = carte.get(i);
            }
            menu = new Menu(giocatori.get(turno).getNome()+" Scegli la carta da scartare:", carteDaScartare, true, true, false);
            scelta = menu.choose();
            Carta cartaSelezionata = giocatore.getMano().get(scelta);
            GestionePartita.mazzoScarti.add(cartaSelezionata);
            giocatore.getMano().remove(scelta);
            carte.remove(scelta);
        } while (giocatore.getMano().size() > giocatore.getPF());
    }

    private static void sceltaCartaDaGiocare(Giocatore giocatore, ArrayList<String> carte) {
        int scelta;
        do {
            String[] carteDaGiocare = new String[carte.size()];
            for (int i = 0; i < carte.size(); i++) {
                carteDaGiocare[i] = carte.get(i);
            }
            menu = new Menu(giocatori.get(turno).getNome()+" Scegli la carta che vorresti usare", carteDaGiocare, true, true, false);
            scelta = menu.choose();
            if(scelta == 0){
                break;
            }
            if (giocatore.getMano().get(scelta - 1) instanceof Equipaggiabile) {
                if (giocatore.getMano().get(scelta - 1) instanceof Arma cartaSelezionata) {
                    GestionePartita.equipaggiaArma(giocatore, cartaSelezionata);
                }
                else {
                    Equipaggiabile cartaSelezionata = (Equipaggiabile) giocatore.getMano().get(scelta - 1);
                    GestionePartita.equipaggiaStrumento(giocatore, cartaSelezionata);
                }
            }
            else if (giocatore.getMano().get(scelta - 1) instanceof GiocaEScarta cartaSelezionata) {
                GestionePartita.usaGiocaEScarta(giocatore, cartaSelezionata);
            }
            carte.remove(scelta - 1);
        } while (true);
    }

    private static void stampaCarteInMano(Giocatore giocatore, ArrayList<String> carte) {
        String[] carteInMano = new String[carte.size()];
        for (int i = 0; i < carte.size(); i++) {
            carteInMano[i] = carte.get(i);
        }
        menu = new Menu(giocatori.get(turno).getNome()+" Scegli la carta di cui vorresti leggere le informazioni", carteInMano, true, true, false);
        int scelta = menu.choose();
        if (scelta != 0) {
            System.out.println(giocatore.getMano().get(scelta - 1).getDescrizione());
        }
    }

    /**
     * <code>sceltaPresentazione</code> permette all'utente di scegliere se vuole visualizzare nuovamente la presentazione del gioco
     */
    private static boolean sceltaRegole() {
        char scelta;
        do {
            scelta = InputData.readChar(SCELTA_REGOLAMENTO);
            System.out.println();
            if (scelta == 'y') {
                return true;
            }
            else if (scelta == 'n') {
                return false;
            }
            else {
                System.out.println(CNV);
            }
        } while (true);
    }

    /**
     * <code>presentazione</code> stampa la presentazione iniziale.
     */
    private static void regole() {
        System.out.println(REGOLAMENTO);
        System.out.println(STOP);
        stop();
    }

    /**
     * <code>stop</code> permette di attendere un input da tastiera prima di proseguire il gioco.
     */
    public static void stop() {
        try {
            System.in.read();
        }
        catch (IOException e) {
            System.out.println(ERRORE_INPUT);
        }
    }

    /**
     * <code>attesa</code> permette di visualizzare il delay di tempo tra un comando e l'altro
     */
    public static void attesa(int tempo) {
        Object lock = new Object();
        synchronized (lock) {
            try {
                lock.wait(tempo);
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
