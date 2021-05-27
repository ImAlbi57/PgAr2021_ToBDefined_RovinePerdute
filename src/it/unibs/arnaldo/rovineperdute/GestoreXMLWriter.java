package it.unibs.arnaldo.rovineperdute;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class GestoreXMLWriter {
    private XMLOutputFactory xmlof = null;
    private XMLStreamWriter xmlw = null;
    private int nTabs = 0;

    /**
     * Metodo costruttore del writer, crea un writer dato il path
     * @param path percorso del file
     */
    public GestoreXMLWriter(String path) {
        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(path), "utf-8");
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del writer:");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo per la scrittura dell'XML completo,
     * richiama i metodi creati in seguito, usati opportunamente e con dei cicli
     //* @param route oggetto contenente il nome del team, il carburante utilizzato e il numero di città percorse
     * @param firstTeam arraylist contenente le città attraversate dal primo team
     * @param secondTeam arraylist contenente le città attraversate dal secondo team
     */
    public void scriviXML(Route firstTeam, Route secondTeam) {
        try {
            System.out.print("Inizio scrittura della mappa per gli esploratori... ");

            iniziaXML();
            apriTag("output");
            apriTagConAttr("route", "team", firstTeam.getVeicolo().getName(), "cost", ""+firstTeam.getFuel(), "cities", ""+firstTeam.getCities());
            for(Node n : firstTeam.getPath()) {
                writeCity(n.getCity());
            }
            chiudiTag();

//	            apriTag("codici");
//	            apriTagConAttr("invalidi", "numero", ""+cfsInvalidi.size());
//	            for(String s : cfsInvalidi) {
//	                writeSimpleTag("codice", s);
//	            }
//	            chiudiTag();
            //
//	            apriTagConAttr("spaiati", "numero", ""+cfsSpaiati.size());
//	            for(String s : cfsSpaiati) {
//	                writeSimpleTag("codice", s);
//	            }
//	            chiudiTag();
//	            chiudiTag();
            apriTagConAttr("route", "team", secondTeam.getVeicolo().getName(), "cost", ""+secondTeam.getFuel(), "cities", ""+secondTeam.getCities());
            for(Node n : secondTeam.getPath()) {
                writeCity(n.getCity());
            }
            chiudiTag();

            chiudiTag();
            chiudiXML();

            System.out.print("Fine! Buon viaggio!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo che inizia il file XML, scrivendo l'intestazione (va a capo)
     * @throws XMLStreamException
     */
    private void iniziaXML() throws XMLStreamException {
        xmlw.writeStartDocument("utf-8", "1.0");aCapo();
    }

    /**
     * Metodo che termina l'XML, scrive i dati immagazzinati nella cache, dentro il file e chiude
     * @throws XMLStreamException
     */
    private void chiudiXML() throws XMLStreamException {
        xmlw.writeEndDocument();
        xmlw.flush();
        xmlw.close();
    }

    /**
     * Metodo da usare per aprire i tag (tabula, stampa e va a capo)
     * @param tagName stringa del nome del tag
     * @throws XMLStreamException
     */
    private void apriTag(String tagName) throws XMLStreamException {
        tabula(nTabs++);
        xmlw.writeStartElement(tagName);
        aCapo();
    }

    /**
     * Metodo da usare per aprire i tag con attributo (tabula, stampa e va a capo)
     * @param tagName stringa col nome del tag
     * @param attr stringa col nome dell'attributo
     * @param val stringa col valore dell'attributo
     * @throws XMLStreamException
     */
    private void apriTagConAttr(String tagName, String attr, String val, String attr2, String val2, String attr3, String val3) throws XMLStreamException {
        tabula(nTabs++);
        xmlw.writeStartElement(tagName);
        xmlw.writeAttribute(attr, val);
//	        aCapo();
        xmlw.writeAttribute(attr2, val2);
//	        aCapo();
        xmlw.writeAttribute(attr3, val3);
        aCapo();
    }

    /**
     * Metodo da usare per chiudere i tag (tabula, stampa e va a capo)
     * @throws XMLStreamException
     */
    private void chiudiTag() throws XMLStreamException {
        tabula(--nTabs);
        xmlw.writeEndElement();
        aCapo();
    }

    /**
     * Metodo da usare per la scrittura di una città (tabula, stampa e va a capo)
     * @param ct città da stampare
     * @throws XMLStreamException
     */
    private void writeCity(City ct) throws XMLStreamException {
        tabula(nTabs++);
        xmlw.writeEmptyElement("city");
        xmlw.writeAttribute("id", ""+ct.getId());
        xmlw.writeAttribute("name", ""+ct.getNome());
        //aCapo();
        //writeSimpleTag("name", ct.getNome());
        //chiudiTag();
        //xmlw.writeEndElement();
        nTabs--;
        aCapo();
    }

    /**
     * Metodo da usare per la scrittura dei tag semplici (tabula, stampa e va a capo)
     * tag semplici =solo testo, no attributi, no sotto-tag
     * @param tagName stringa col nome del tag
     * @param characters stringa da stampare tra i tag
     */
    public void writeSimpleTag(String tagName, String characters) {
        try {
            tabula(nTabs);
            xmlw.writeStartElement(tagName);
            xmlw.writeCharacters(characters);
            xmlw.writeEndElement();
            aCapo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo da usare per inserire n tabulazioni
     * @param n numero di indentazioni
     */
    public void tabula(int n) {
        try {
            for(;n>0;n--)
                xmlw.writeCharacters("\t");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo da usare per andare a capo
     */
    public void aCapo() {
        try {
            //inserisco caratteri per andare a capo
            xmlw.writeCharacters("\r\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
