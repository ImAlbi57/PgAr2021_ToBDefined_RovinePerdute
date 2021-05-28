package it.unibs.arnaldo.rovineperdute;

import it.unibs.tobdefined.utility.Pair;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

/***
 * Classe per inizializzare e gestire la scrittura del file XML di output
 * Class to initialize and manage the writing of the output XML file
 * @author ToBdefined
 */
public class GestoreXMLWriter {

    public static final String ENCODING = "utf-8";
    public static final String ERRORE_INIZ = "Errore nell'inizializzazione del writer:";
    public static final String INIZIO_SCRITTURA = "\nInizio scrittura della mappa per gli esploratori... ";
    public static final String FINE_SCRITTURA = "Fine!\n\nBuon viaggio!";
    private XMLStreamWriter xmlw = null;
    private int nTabs = 0;


    /**
     * Metodo costruttore del writer, crea un writer dato il path
     * Writer constructor method, it creates a writer given the path
     * @param path percorso del file
     *        file executed path
     */
    public GestoreXMLWriter(String path) {
        try {
            XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(path), ENCODING);
        } catch (Exception e) {
            System.out.println(ERRORE_INIZ);
            System.out.println(e.getMessage());
        }
    }


    /**
     * Metodo per la scrittura dell'XML completo,
     * richiama i metodi creati in seguito, usati opportunamente e con dei cicli
     * Method for the complete writing of the output XML file
     * it recalls method created later, properly used and with loops
     * @param firstTeam arraylist contenente le città attraversate dal primo team
     *                  arraylist containing cities crossed by the first team
     * @param secondTeam arraylist contenente le città attraversate dal secondo team
     *                   arraylist containing cities crossed by the second team
     */
    public void scriviXML(Route firstTeam, Route secondTeam) {
        try {
            System.out.print(INIZIO_SCRITTURA);
            //inizio la scrittura del file XML
            iniziaXML();
            apriTag("output");

            //output del primo team
            apriTagConAttr("route", "team", firstTeam.getVeicolo().getName(), "cost", String.format("%.02f", firstTeam.getFuel()), "cities", ""+firstTeam.getCities());
            for(Node n : firstTeam.getPath()) {
                writeCity(n.getCity());
            }
            chiudiTag();

            //output del secondo team
            apriTagConAttr("route", "team", secondTeam.getVeicolo().getName(), "cost", String.format("%.02f", secondTeam.getFuel()), "cities", ""+secondTeam.getCities());
            for(Node n : secondTeam.getPath()) {
                writeCity(n.getCity());
            }
            chiudiTag();

            //concludo la scrittura del file XML
            chiudiTag();
            chiudiXML();

            System.out.println(FINE_SCRITTURA);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Metodo che inizia il file XML, scrivendo l'intestazione (va a capo)
     * Method which stars the XML file by writing the letterhead (starts a new line)
     * @throws XMLStreamException se avvengono errori
     *                            if errors occur
     */
    private void iniziaXML() throws XMLStreamException {
        xmlw.writeStartDocument(ENCODING, "1.0");aCapo();
    }


    /**
     * Metodo che termina l'XML, scrive i dati immagazzinati nella cache, dentro il file e chiude
     * Method which ends the XML file, wrties down the data stored in the cache memory, inside the file and closes
     * @throws XMLStreamException se avvengono errori
     *                            if errors occur
     */
    private void chiudiXML() throws XMLStreamException {
        xmlw.writeEndDocument();
        xmlw.flush();
        xmlw.close();
    }


    /**
     * Metodo da usare per aprire i tag (tabula, stampa e va a capo)
     * Method used to open tags (tabulates, prints and starts a new line)
     * @param tagName stringa del nome del tag
     *                string with tag's name
     * @throws XMLStreamException se avvengono errori
     *                            if errors occur
     */
    private void apriTag(String tagName) throws XMLStreamException {
        tabula(nTabs++);
        xmlw.writeStartElement(tagName);
        aCapo();
    }


    /**
     * Metodo da usare per chiudere i tag (tabula, stampa e va a capo)
     * Method used to close tags (tabulates, prints and stars a new line)
     * @throws XMLStreamException se avvengono errori
     *                            if errors occur
     */
    private void chiudiTag() throws XMLStreamException {
        tabula(--nTabs);
        xmlw.writeEndElement();
        aCapo();
    }


    /**
     * Metodo da usare per la scrittura di una città
     * Costruisce la struttura per gli attributi e passa tutto al metodo writeTagWithAttributes
     * Method used to write cities
     * It builds the structure for attributes and passes everything to writeTagWithAttributes method
     * @param ct città da stampare
     *           city to print
     * @throws XMLStreamException se avvengono errori
     *                            if errors occur
     */
    private void writeCity(City ct) throws XMLStreamException {
        writeTagWithAttributes("city", Pair.buildPairs("id", ""+ct.getId(), "name", ct.getNome()), null);

        //CODICE PRECEDENTE AI PAIR
        //tabula(nTabs);
        //xmlw.writeEmptyElement("city");
        //xmlw.writeAttribute("id", ""+ct.getId());
        //xmlw.writeAttribute("name", ""+ct.getNome());
        //aCapo();
    }


    /**
     * Metodo da usare per la scrittura dei tag con attributi (tabula, stampa e va a capo)
     * tag con testo e attributi, no sotto-tag
     * Method used to write tags with attributes (tabulates, prints and stars a new line)
     * @param tagName stringa col nome del tag
     *                string with the tag's name
     * @param attributes coppie di Stringhe contenenti il nome dell'attributo e il corrispettivo valore
     *                   pairs of strings containing the name of the attribute and the corresponding value
     * @param characters stringa da stampare tra i tag
     *                   string to print between the tags
     * @throws XMLStreamException se avvengono errori
     *                            if error occur
     */
    public void writeTagWithAttributes(String tagName, ArrayList<Pair<String>> attributes, String characters) throws XMLStreamException {
        tabula(nTabs);

        if(characters == null){
            xmlw.writeEmptyElement(tagName);
        }
        else{
            xmlw.writeStartElement(tagName);
        }

        for (Pair<String> pair : attributes) {
            xmlw.writeAttribute(pair.getValue1(), pair.getValue2());
        }

        if(characters != null){
            xmlw.writeCharacters(characters);
            xmlw.writeEndElement();
        }

        aCapo();
    }


    /**
     * Metodo da usare per aprire i tag con attributo (tabula, stampa e va a capo)
     * Method used to open tags with atrribute (tabulates, prints and stars a new line)
     * @param tagName stringa col nome del tag
     *                string with the tag's name
     * @param attr stringa col nome dell'attributo
     *             string with the attribute's name
     * @param val stringa col valore dell'attributo
     *            string with the attribute's value
     * @throws XMLStreamException se avvengono errori
     *                            if errors occur
     */
    private void apriTagConAttr(String tagName, String attr, String val, String attr2, String val2, String attr3, String val3) throws XMLStreamException {
        tabula(nTabs++);
        xmlw.writeStartElement(tagName);
        xmlw.writeAttribute(attr, val);
        xmlw.writeAttribute(attr2, val2);
        xmlw.writeAttribute(attr3, val3);
        aCapo();
    }


    /**
     * Metodo da usare per inserire n tabulazioni
     * Method used to add a number of tabulations
     * @param n numero di indentazioni
     *          number og tabs
     * @throws XMLStreamException se avvengono errori
     *                            if errors occur
     */
    public void tabula(int n) throws XMLStreamException {
        for(;n>0;n--)
            xmlw.writeCharacters("\t");
    }


    /**
     * Metodo da usare per andare a capo
     * Method used to start a new line
     * @throws XMLStreamException se avvengono errori
     *                            if errors occur
     */
    public void aCapo() throws XMLStreamException {
        //inserisco caratteri per andare a capo
        xmlw.writeCharacters("\r\n");
    }
}
