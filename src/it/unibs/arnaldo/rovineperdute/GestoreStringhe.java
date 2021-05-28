package it.unibs.arnaldo.rovineperdute;

/***
 * Classe per contenere i nomi dei file xml di input
 * @author ToBdefined
 */
public class GestoreStringhe {
    public static String MAPPA_5 = "XML/PgAr_Map_5.xml";
    public static String MAPPA_12 = "XML/PgAr_Map_12.xml";
    public static String MAPPA_50 = "XML/PgAr_Map_50.xml";
    public static String MAPPA_200 = "XML/PgAr_Map_200.xml";
    public static String MAPPA_2000 = "XML/PgAr_Map_2000.xml";
    public static String MAPPA_10000 = "XML/PgAr_Map_10000.xml";
    private static final String MESS_ERRORE = "La scelta inserita non e' valida!";


    private static final String SALUTO = "" +
            "\n///////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "\n  Benvenuto nel programma per l'ottimizzazione del vostro viaggio alla ricerca delle Rovine Perdute!" +
            "\n  Grazie per aver scelto il nostro gruppo di programmatori, ToBdefined" +
            "\n///////////////////////////////////////////////////////////////////////////////////////////////////////";

    private static final String MENU = "" +
            "\nScegli la mappa che vuoi utilizzare per l'esplorazione: " +
            "\n1) MAPPA CON 5 CITTA'" +
            "\n2) MAPPA CON 12 CITTA'" +
            "\n3) MAPPA CON 50 CITTA'" +
            "\n4) MAPPA CON 200 CITTA'" +
            "\n5) MAPPA CON 2000 CITTA'" +
            "\n6) MAPPA CON 10000 CITTA'" +
            "\n0) ESCI DAL PROGRAMMA" +
            "\nScelta: ";


    //GETTERS

    public static String getSALUTO() {
        return SALUTO;
    }

    public static final String getMENU() {
        return MENU;
    }

    public static String getMessErrore() {
        return MESS_ERRORE;
    }
}
