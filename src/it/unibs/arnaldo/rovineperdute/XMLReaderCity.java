package it.unibs.arnaldo.rovineperdute;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;

/***
 * Classe per inizializzare e gestire la lettura del file XML di input
 * @author ToBdefined
 */
public class XMLReaderCity {

    private XMLStreamReader xmlr;
    private final String path;
    private Graph graph;


    /**
     * Istanzia lo StreamReader e gestisce le eccezioni
     * @param path percorso del file
     */
    public XMLReaderCity(String path) {
        this.path = path;
        this.graph = new Graph();
        try {
            XMLInputFactory xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(path, new FileInputStream(path));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }
    }


    /**
     * Legge i dati delle città e li inserisce nell'ArrayList
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

                        if(src.equals("map")){
                            for (int i = 0; i < xmlr.getAttributeCount(); i++){
                                if(xmlr.getAttributeLocalName(i).equals("size"))
                                    initGraph(Integer.parseInt(xmlr.getAttributeValue(i)));
                            }
                        }

                        //se l'elemento iniziale corrisponde a "city"
                        if(src.equals("city")) {
                            //ciclo for per scorrere il numero di attributi presenti nell'elemento iniziale
                            for (int i = 0; i < xmlr.getAttributeCount(); i++) {
                                //ricavo il nome dell'attributo
                                switch (xmlr.getAttributeLocalName(i)) {
                                    //caso dell'attributo id
                                    case "id":
                                        id = Integer.parseInt(xmlr.getAttributeValue(i));
                                        break;
                                    //caso dell'attributo nome
                                    case "name":
                                        nome = xmlr.getAttributeValue(i);
                                        break;
                                    //caso dell'attributo coordinate
                                    case "x":
                                        x = Integer.parseInt(xmlr.getAttributeValue(i));
                                        break;
                                    //caso dell'attributo y
                                    case "y":
                                        y = Integer.parseInt(xmlr.getAttributeValue(i));
                                        break;
                                    //caso dell'attributo h
                                    case "h":
                                        h = Integer.parseInt(xmlr.getAttributeValue(i));
                                        break;
                                }
                            }
                            //genero una nuova coordinata con i valori di x, y e h
                            coordinate = new Coords(x, y, h);
                        }

                        if(src.equals("link")){
                            links.add(graph.getNode(Integer.parseInt(xmlr.getAttributeValue(0))));
                        }

                        //passo all'evento successivo
                        xmlr.next();
                        break;


                    //elemento finale
                    case XMLStreamConstants.END_ELEMENT:
                        //controllo che il ciclo abbia realmente acquisito dei valori
                        if(xmlr.getLocalName().equals("city") && id != -1 && !nome.equals("") && x!=-1 && y!= -1 && h != -1 /*&& !collegamenti.equals("")*/) {
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
     * Metodo per aggiungere node all'arraylist (?)
     * @param dim, cioè la dimensione massima dell'arraylist (?)
     */
    private void initGraph(int dim) {
        for(int i=0; i < dim; i++){
            graph.addNode(new Node(new City(i)));
        }
    }

    /*
    //Metodo per stampare tutti gli oggetti Città ricavati dall'XML(per DEBUG)
    public void PrintCity() {
        for(City ct: cities) {
            System.out.println("id = " + ct.getId()
                    + "\nnome = " + ct.getNome()
                    + "\ncoordinate = (" + ct.getCoordinate().getX() + "; " + ct.getCoordinate().getY()+"; "+ct.getCoordinate().getH()+ ")\n");
        }
    }
    */
}
