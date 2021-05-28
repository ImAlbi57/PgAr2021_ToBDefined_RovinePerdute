package it.unibs.arnaldo.rovineperdute;

import it.unibs.tobdefined.utility.Pair;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

/***
 * Classe per inizializzare e gestire la scrittura del file XML di output
 * @author ToBdefined
 */
public class GestoreXMLWriter {

    private XMLStreamWriter xmlw = null;
    private int nTabs = 0;


    /**
     * Metodo costruttore del writer, crea un writer dato il path
     * @param path percorso del file
     */
    public GestoreXMLWriter(String path) {
        try {
            XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(path), "utf-8");
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del writer:");
            System.out.println(e.getMessage());
        }
    }


    /**
     * Metodo per la scrittura dell'XML completo,
     * richiama i metodi creati in seguito, usati opportunamente e con dei cicli
     * @param firstTeam arraylist contenente le città attraversate dal primo team
     * @param secondTeam arraylist contenente le città attraversate dal secondo team
     */
    public void scriviXML(Route firstTeam, Route secondTeam) {
        try {
            System.out.print("\nInizio scrittura della mappa per gli esploratori... ");
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

            System.out.println("Fine!\n\nBuon viaggio!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Metodo che inizia il file XML, scrivendo l'intestazione (va a capo)
     * @throws XMLStreamException se avvengono errori
     */
    private void iniziaXML() throws XMLStreamException {
        xmlw.writeStartDocument("utf-8", "1.0");aCapo();
    }


    /**
     * Metodo che termina l'XML, scrive i dati immagazzinati nella cache, dentro il file e chiude
     * @throws XMLStreamException se avvengono errori
     */
    private void chiudiXML() throws XMLStreamException {
        xmlw.writeEndDocument();
        xmlw.flush();
        xmlw.close();
    }


    /**
     * Metodo da usare per aprire i tag (tabula, stampa e va a capo)
     * @param tagName stringa del nome del tag
     * @throws XMLStreamException se avvengono errori
     */
    private void apriTag(String tagName) throws XMLStreamException {
        tabula(nTabs++);
        xmlw.writeStartElement(tagName);
        aCapo();
    }


    /**
     * Metodo da usare per chiudere i tag (tabula, stampa e va a capo)
     * @throws XMLStreamException se avvengono errori
     */
    private void chiudiTag() throws XMLStreamException {
        tabula(--nTabs);
        xmlw.writeEndElement();
        aCapo();
    }


    /**
     * Metodo da usare per la scrittura di una città
     * Costruisce la struttura per gli attributi e passa tutto al metodo writeTagWithAttributes
     * @param ct città da stampare
     * @throws XMLStreamException se avvengono errori
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
     * @param tagName stringa col nome del tag
     * @param attributes coppie di Stringhe contenenti il nome dell'attributo e il corrispettivo valore
     * @param characters stringa da stampare tra i tag
     * @throws XMLStreamException se avvengono errori
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
     * @param tagName stringa col nome del tag
     * @param attr stringa col nome dell'attributo
     * @param val stringa col valore dell'attributo
     * @throws XMLStreamException se avvengono errori
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
     * @param n numero di indentazioni
     * @throws XMLStreamException se avvengono errori
     */
    public void tabula(int n) throws XMLStreamException {
        for(;n>0;n--)
            xmlw.writeCharacters("\t");
    }


    /**
     * Metodo da usare per andare a capo
     * @throws XMLStreamException se avvengono errori
     */
    public void aCapo() throws XMLStreamException {
        //inserisco caratteri per andare a capo
        xmlw.writeCharacters("\r\n");
    }
}
