package it.unibs.arnaldo.rovineperdute;

import javax.xml.stream.XMLStreamConstants;
import java.util.ArrayList;

public class XMLReaderCittà extends GestoreXMLReader {
    /**
     * Richiama il costruttore della superclasse e gli passa il path
     * @param path percorso del file
     */
    public XMLReaderCittà(String path) {
        super(path);
    }

    //inizializzo l'arraylist fuori dal metodo read() solo perchè lo uso anche nel metodo printCittà
    ArrayList<Città> città = new ArrayList<Città>();

    /**
     * Legge i dati delle città e li inserisce nell'ArrayList
     * @return l'ArrayList città
     */
    public ArrayList<Città> read() {

//        ArrayList<Integer> collegamenti = new ArrayList<>();
        //variabili
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

                        //se l'elemento iniziale corrisponde a "city"
                        if(src.equals("city"))
                            //ciclo for per scorrere il numero di attributi presenti nell'elemento iniziale
                            for (int i = 0; i < xmlr.getAttributeCount(); i++){
                                //ricavo il nome dell'attributo
                                switch(xmlr.getAttributeLocalName(i)){
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

                        //passo all'evento successivo
                        xmlr.next();

//aspettare di creare un hashmap per poter collegare ad una città il proprio arraylist di collegamenti e poterlo cancellare al termine di ogni ciclo

//                        if(src.equals("link")){
//                            collegamenti.add(Integer.parseInt(xmlr.getAttributeValue(0)));
//                        }
                        break;
                    //elemento finale
                    case XMLStreamConstants.END_ELEMENT:
                        //controllo che il ciclo abbia realmente acquisito dei valori
                        if(xmlr.getLocalName().equals("city") && id != -1 && !nome.equals("") && x!=-1 && y!= -1 && h != -1 /*&& !collegamenti.equals("")*/) {
                            //creo un oggetto città con i valori acquisiti e li aggiungo ad un arraylist
                            città.add(new Città(id, nome, coordinate));

                            //riporto le variabili ai loro valori originali
                            id = -1;
                            nome = "";
                            coordinate = new Coords(-1,-1,-1);
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
            System.out.println(e);
        }
        //ritorno l'arraylist
        return città;
    }


    //Metodo per stampare tutti gli oggetti Città ricavati dall'XML(per DEBUG)
    public void PrintCittà() {
        for(Città cities: città) {
            System.out.println("id = " + cities.getId()
                    + "\nnome = " + cities.getNome()
                    + "\ncoordinate = (" + cities.getCoordinate().getX() + "; " + cities.getCoordinate().getY()+"; "+cities.getCoordinate().getH()+ ")\n");
        }
    }

}
