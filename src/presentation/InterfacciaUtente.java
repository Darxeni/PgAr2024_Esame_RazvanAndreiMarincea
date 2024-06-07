package presentation;


import it.kibo.fp.lib.InputData;
import logic.GestionePartita;
import utility.Carta;
import utility.Giocatore;

import java.io.IOException;
import java.util.ArrayList;

import static costants.costants.*;

public class InterfacciaUtente {

    public static void start()  {
        System.out.println(PRESENTAZIONE);
        if (sceltaRegole())  {
            regole();
        }
        GestionePartita.creazioneGiocatori();
        System.out.println("ciao");
    }



    /**
     * <code>sceltaPresentazione</code> permette all'utente di scegliere se vuole visualizzare nuovamente la presentazione del gioco
     */
    private static boolean sceltaRegole()     {
        char scelta;
        do  {
            scelta = InputData.readChar(SCELTA_REGOLAMENTO);
            System.out.println();
            if (scelta == 'y')  {
                return true;
            }
            else if (scelta == 'n')     {
                return false;
            }
            else    {
                System.out.println(CNV);
            }
        } while(true);
    }

    /**
     * <code>presentazione</code> stampa la presentazione iniziale.
     */
    private static void regole()     {
        System.out.println(REGOLAMENTO);
        System.out.println(STOP);
        stop();
    }

    /**
     * <code>stop</code> permette di attendere un input da tastiera prima di proseguire il gioco.
     */
    public static void stop()    {
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
    public static void attesa(int tempo)     {
        Object lock = new Object();
        synchronized (lock)     {
            try {
                lock.wait(tempo);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
