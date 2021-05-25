package it.unibs.arnaldo.rovineperdute;

import java.util.ArrayList;

public class MainRovine {
    public static void main(String[] args) {
        System.out.println("Hello mf");
////////////////////////////  PER IL DEBUG  ////////////////////////////////////////
        //inizializzo il costruttore della classe dandogli come parametro il testo XML da leggere
        XMLReaderCity xmlr = new XMLReaderCity("PgAr_Map_10000.xml");
        //richiamo il metodo per leggere l'XML
        Graph mappa = xmlr.read();
        //stampo i valori dell'arraylist ottenuti
        mappa.printNodes();
        System.out.println();
        System.out.println(mappa.getBestPath(NavigationMode.DISTANCE, mappa.getNode(0), mappa.getNode(4)));
////////////////////////////////////////////////////////////////////////////////////
    }
}
