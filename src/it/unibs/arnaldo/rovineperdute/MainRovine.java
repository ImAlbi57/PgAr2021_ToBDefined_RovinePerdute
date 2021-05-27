package it.unibs.arnaldo.rovineperdute;

import java.util.ArrayList;

public class MainRovine {
    public static void main(String[] args) {
        System.out.println("Benvenuto!");

        //////  PER I BENCHMARK  //////
        BenchMark.start();
        ///////////////////////////////


        //inizializzo il costruttore della classe dandogli come parametro il testo XML da leggere
        XMLReaderCity xmlr = new XMLReaderCity(GestoreStringhe.MAPPA_10000);
        //richiamo il metodo per leggere l'XML
        Graph mappa = xmlr.read();
        //stampo i valori dell'arraylist ottenuti
        //mappa.printNodes();
        //mappa.printNodesWithLinks();
        System.out.println();


        //POSSIBILE MODIFICA [Veicolo -> non abstract]
        //Route tonathiuh = new Route(new Veicolo("Tonatiuh", NavigationMode.DISTANCE));
        //Route metzetli = new Route(new Veicolo("Metztli", NavigationMode.HEIGHTDIFFERENCE));
        Route tonathiuh = new Route(new VeicoloTonatiuh("Tonatiuh"));
        Route metzetli = new Route(new VeicoloMetztli("Metztli"));

        tonathiuh.startRoute(mappa);
        metzetli.startRoute(mappa);

        //printPathDEBUG(tonathiuh.getPath());
        //System.out.println();
        //System.out.println();
        //printPathDEBUG(metzetli.getPath());

        GestoreXMLWriter xmlw = new GestoreXMLWriter("out.xml");
        xmlw.scriviXML(tonathiuh, metzetli);


        //////  PER I BENCHMARK  //////
        BenchMark.end();
        ///////////////////////////////

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
