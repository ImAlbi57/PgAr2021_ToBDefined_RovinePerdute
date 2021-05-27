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

        /*
        for (Node iNode : mappa.getNodes()) {
            for (Node jNode : iNode.getLinks()) {
                System.out.println(iNode.getCity().getId() + "-" + jNode.getCity().getId() + ": " + iNode.getCity().getCoordinate().calcolaDifferenzaAltitudine(jNode.getCity().getCoordinate()));
            }
        }*/

        ArrayList<Node> tonathiuh = mappa.getBestPath(NavigationMode.DISTANCE, mappa.getNode(0), mappa.getNode(9999));
        ArrayList<Node> metzetli = mappa.getBestPath(NavigationMode.HEIGHTDIFFERENCE, mappa.getNode(0), mappa.getNode(9999));

        printPathDEBUG(tonathiuh);
        System.out.println();
        System.out.println();
        printPathDEBUG(metzetli);


        //PER DEBUG DELLA SCRITTURA DEL FILE XML
        Route route = new Route("ciao", 33.3, 7);
        GestoreXMLWriter xmlw = new GestoreXMLWriter("outputProva.xml");
        xmlw.scriviXML(route, /*cities*/ mappa);
        System.out.println("Siuuuuuum");
////////////////////////////////////////////////////////////////////////////////////
    }

    private static void printPathDEBUG(ArrayList<Node> path) {
        int i=1;
        System.out.print("CAMPO BASE ->");
        for (Node node : path) {
            if(++i % 10 == 0)
                System.out.println();
            System.out.print(node.getCity().getId() + "\t->\t");
        }
        int countNodi = path.size();
        System.out.println("ANTICHE ROVINE");
        double carburante = path.get(countNodi-1).getDistance();
        System.out.printf("Numero di città percorse: %s, carburante consumato: %s", countNodi, carburante);
    }
}
