package costants;

import it.kibo.fp.lib.AnsiColors;

public class costants {
    public final static String CNV = AnsiColors.RED + "Il comando inserito non è valido" + AnsiColors.RESET;
    public final static String STOP = AnsiColors.BLUE + "Premi un tasto per continuare" + AnsiColors.RESET;

    public final static String ERRORE_INPUT = AnsiColors.RED + "Errore rilevato nell'input" + AnsiColors.RESET;

    public static final String PRESENTAZIONE = AnsiColors.CYAN + """
            da scrivere presentazione
            """ + AnsiColors.RESET;

    public static final String SCELTA_REGOLAMENTO = AnsiColors.PURPLE + "Vuoi visualizzare il regolamento del gioco? (y/n)\n" + AnsiColors.RESET;
    public final static String REGOLAMENTO = AnsiColors.PURPLE + """ 
            da scrivere regole
            """ + AnsiColors.RESET;

    public final static String NUMERO_GIOCATORI = "Quanti giocatori siete? (min 4/ max 7)";

    public final static String ERRORE_READER = "Errore rilevato nell'inizializzazione del reader";
}
