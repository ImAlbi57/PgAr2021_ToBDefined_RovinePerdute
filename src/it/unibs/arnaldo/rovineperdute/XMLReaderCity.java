package it.unibs.arnaldo.rovineperdute;

import it.unibs.tobdefined.utility.Coords;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;

/***
 * Class to initialize and manage the reading of the input XML file
 * @author ToBdefined
 */
public class XMLReaderCity {

    public static final String ERRORE_INIZ = "Errore nell'inizializzazione del reader:";
    public static final String MAP = "map";
    public static final String SIZE = "size";
    public static final String CITY = "city";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String X = "x";
    public static final String Y = "y";
    public static final String H = "h";
    public static final String LINK = "link";
    private XMLStreamReader xmlr;
    private final String path;
    private Graph graph;


    /***
     * Instantiates the StreamReader and manages exceptions
     * @param path file path
     */
    public XMLReaderCity(String path) {
        this.path = path;
        this.graph = new Graph();
        try {
            XMLInputFactory xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(path, new FileInputStream(path));
        } catch (Exception e) {
            System.out.println(ERRORE_INIZ);
            System.out.println(e.getMessage());
        }
    }


    /***
     * Reads the cities' data and add them in the arraylist
     * @return graph
     */
    public Graph read() {
        //variabili
        ArrayList<Node> links = new ArrayList<>();
        int id = -1;
        String nome = "";
        int x = -1;
        int y = -1;
        int h = -1;
        Coords coordinate = new Coords(x, y, h);

        try {
            while (xmlr.hasNext()) {
                //switch per determinare il tipo di evento che incontro nella lettur< dell'XML
                switch (xmlr.getEventType()) {
                    //inizio documento
                    case XMLStreamConstants.START_DOCUMENT: break;
                    //elemento iniziale
                    case XMLStreamConstants.START_ELEMENT:
                        //ricavo il nome dell'elemento iniziale
                        String src = xmlr.getLocalName();

                        if(src.equals(MAP)){
                            for (int i = 0; i < xmlr.getAttributeCount(); i++){
                                if(xmlr.getAttributeLocalName(i).equals(SIZE))
                                    initGraph(Integer.parseInt(xmlr.getAttributeValue(i)));
                            }
                        }

                        //se l'elemento iniziale corrisponde a "city"
                        if(src.equals(CITY)) {
                            //ciclo for per scorrere il numero di attributi presenti nell'elemento iniziale
                            for (int i = 0; i < xmlr.getAttributeCount(); i++) {
                                //ricavo il nome dell'attributo
                                switch (xmlr.getAttributeLocalName(i)) {
                                    //caso dell'attributo id
                                    case ID:
                                        id = Integer.parseInt(xmlr.getAttributeValue(i));
                                        break;
                                    //caso dell'attributo nome
                                    case NAME:
                                        nome = xmlr.getAttributeValue(i);
                                        break;
                                    //caso dell'attributo coordinate
                                    case X:
                                        x = Integer.parseInt(xmlr.getAttributeValue(i));
                                        break;
                                    //caso dell'attributo y
                                    case Y:
                                        y = Integer.parseInt(xmlr.getAttributeValue(i));
                                        break;
                                    //caso dell'attributo h
                                    case H:
                                        h = Integer.parseInt(xmlr.getAttributeValue(i));
                                        break;
                                }
                            }
                            //genero una nuova coordinata con i valori di x, y e h
                            coordinate = new Coords(x, y, h);
                        }

                        if(src.equals(LINK)){
                            links.add(graph.getNode(Integer.parseInt(xmlr.getAttributeValue(0))));
                        }

                        //passo all'evento successivo
                        xmlr.next();
                        break;


                    //elemento finale
                    case XMLStreamConstants.END_ELEMENT:
                        //controllo che il ciclo abbia realmente acquisito dei valori
                        if(xmlr.getLocalName().equals(CITY) && id != -1 && !nome.equals("") && x!=-1 && y!= -1 && h != -1 /*&& !collegamenti.equals("")*/) {
                            //creo un oggetto città con i valori acquisiti e li aggiungo ad un arraylist
                            graph.getNode(id).setCity(new City(id, nome, coordinate));
                            graph.getNode(id).setLinks(links);

                            //riporto le variabili ai loro valori originali
                            id = -1;
                            nome = "";
                            coordinate = new Coords(-1,-1,-1);
                            links = new ArrayList<>();
                        }
                    //commenti
                    case XMLStreamConstants.COMMENT: break;
                    //caratteri
                    case XMLStreamConstants.CHARACTERS: break;
                }
                //passo all'evento successivo
                xmlr.next();
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        //ritorno il grafo
        return graph;
    }


    /***
     * Method to initialize the arraylist by adding the nodes
     * @param dim maximum dimension of the arraylist
     */
    private void initGraph(int dim) {
        for(int i=0; i < dim; i++){
            graph.addNode(new Node(new City(i)));
        }
    }
}
