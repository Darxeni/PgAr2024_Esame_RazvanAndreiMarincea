package presentation;


import utility.Carta;
import utility.Giocatore;

import java.io.IOException;
import java.util.ArrayList;

import static costants.costants.*;

public class InterfacciaUtente {
    ArrayList<Giocatore> giocatori;
    ArrayList<Carta> mazzo;


    public static void start()  {












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
