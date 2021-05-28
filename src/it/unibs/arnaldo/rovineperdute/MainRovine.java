package it.unibs.arnaldo.rovineperdute;

import it.unibs.tobdefined.utility.InputDati;

/***
 * Classe main del programma
 * @author ToBdefined
 */
public class MainRovine {

    public static final String MESS_CARBURANTE = "\nIl team che ha speso meno carburante e': ";
    public static final String TEAM = "Team ";
    public static final String METZTLI = "Metztli";
    public static final String TONATIUH = "Tonatiuh";
    public static final String FILE_NAME = "Routes.xml";

    /***
     * Metodo main
     * @param args args
     */
    public static void main(String[] args) {
        System.out.println(GestoreStringhe.SALUTO);
        int scelta;
        int easterEgg = 0;

        do{
            scelta = InputDati.leggiInteroNonNegativo(GestoreStringhe.MENU);

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
                    if(++easterEgg > 3)
                        System.out.println(GestoreStringhe.EG);
                    else
                        System.out.println(GestoreStringhe.MESS_ERRORE);
                    break;
            }
        }while(scelta != 0);
    }


    /***
     * Metodo per eseguire il programma data una mappa
     * @param mappa_scelta map choice
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


        System.out.println();


        Route tonathiuh = new Route(new Veicolo(TONATIUH, NavigationMode.DISTANCE));
        Route metzetli = new Route(new Veicolo(METZTLI, NavigationMode.HEIGHTDIFFERENCE));

        tonathiuh.startRoute(mappa);
        metzetli.startRoute(mappa);


        GestoreXMLWriter xmlw = new GestoreXMLWriter(FILE_NAME);
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
     * @return the name of the team
     */
    public static String printTeam(Route firstTeam, Route secondTeam){
        if(firstTeam.getFuel() >= secondTeam.getFuel())
            return TEAM+METZTLI;

        else return TEAM+TONATIUH;
    }
}
