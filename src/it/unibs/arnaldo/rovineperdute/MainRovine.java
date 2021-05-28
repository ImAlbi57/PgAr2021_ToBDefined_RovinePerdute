package it.unibs.arnaldo.rovineperdute;

import it.unibs.tobdefined.utility.InputDati;
import java.util.ArrayList;

/***
 * Classe main del programma
 * @author ToBdefined
 */
public class MainRovine {

    public static final String MESS_CARBURANTE = "\nIl team che ha speso meno carburante e': ";
    public static final String METZTLI = "Team Metztli";
    public static final String TONATIUH = "Team Tonatiuh";

    /***
     * Metodo main
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(GestoreStringhe.getSALUTO());
        int scelta = -1;

        do{
            scelta = InputDati.leggiInteroNonNegativo(GestoreStringhe.getMENU());

            //switch per scegliere il file XML da leggere
            switch(scelta){
                //file con 5 città
                case 1:
                    esecuzione(GestoreStringhe.MAPPA_5);
                    break;
                //file con 12 città(o 13)
                case 2:
                    esecuzione(GestoreStringhe.MAPPA_12);
                    break;
                //file con 50 città
                case 3:
                    esecuzione(GestoreStringhe.MAPPA_50);
                    break;
                //file con 200 città
                case 4:
                    esecuzione(GestoreStringhe.MAPPA_200);
                    break;
                //file con 2000 città
                case 5:
                    esecuzione(GestoreStringhe.MAPPA_2000);
                    break;
                //file con 10000 città
                case 6:
                    esecuzione(GestoreStringhe.MAPPA_10000);
                    break;
                //uscita dal programma
                case 0:
                    break;
                //scelte diverse dalle precedenti: output errore
                default:
                    System.out.println(GestoreStringhe.getMessErrore());
                    break;
            }
        }while(scelta != 0);




    }


    /***
     * Metodo per eseguire il programma data una mappa
     * @param mappa_scelta
     */
    //Metodo per l'esecuzione del programma
    public static void esecuzione(String mappa_scelta){
        //////  PER I BENCHMARK  //////
        BenchMark.start();
        ///////////////////////////////


        //inizializzo il costruttore della classe dandogli come parametro il testo XML da leggere
        XMLReaderCity xmlr = new XMLReaderCity(mappa_scelta); //GestoreStringhe.MAPPA_10000
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

        System.out.println(MESS_CARBURANTE + printTeam(tonathiuh, metzetli));

        //////  PER I BENCHMARK  //////
        BenchMark.end();
        ///////////////////////////////
    }


    /***
     * Metodo per verificare quale dei due team ha risparmiato più carburante
     * @param firstTeam, cioè Tonatiuh
     * @param secondTeam, cioè Metztli
     * @return
     */
    public static String printTeam(Route firstTeam, Route secondTeam){
        if(firstTeam.getFuel() >= secondTeam.getFuel())
            return METZTLI;

        else return TONATIUH;
    }


    /***
     * PER DEBUG
     * stampa il percorso effettuato con costo del carburante e numero di città attraversate
     * @param path, cioè il percorso del primo o del secondo team
     */
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
