package it.unibs.arnaldo.rovineperdute;

import it.unibs.tobdefined.utility.Pair;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

/***
 * Class to initialize and manage the writing of the output XML file
 * @author ToBdefined
 */
public class GestoreXMLWriter {

    public static final String ENCODING = "utf-8";
    public static final String XML_VERSION = "1.0";
    public static final String ERRORE_INIZ = "Errore nell'inizializzazione del writer:";
    public static final String INIZIO_SCRITTURA = "\nInizio scrittura della mappa per gli esploratori... ";
    public static final String FINE_SCRITTURA = "Fine!\n\nBuon viaggio!";
    public static final String TAB = "\t";
    public static final String NEWLINE = "\r\n";

    public static final String OUTPUT = "output";
    public static final String ROUTE = "route";
    public static final String TEAM = "team";
    public static final String COST = "cost";
    public static final String COST_VALUE = "%.02f";
    public static final String CITIES = "cities";
    public static final String CITY = "city";
    public static final String ID = "id";
    public static final String NAME = "name";

    private XMLStreamWriter xmlw = null;
    private int nTabs = 0;


    /***
     * Writer constructor method, it creates a writer given the path
     * @param path file path
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


    /***
     * Method for the complete writing of the output XML file
     * it recalls method created later, properly used
     * @param firstTeam arraylist containing cities crossed by the first team
     * @param secondTeam arraylist containing cities crossed by the second team
     */
    public void scriviXML(Route firstTeam, Route secondTeam) {
        try {
            System.out.print(INIZIO_SCRITTURA);
            //inizio la scrittura del file XML
            iniziaXML();
            apriTag(OUTPUT);

            //output del primo team
            writeTeam(firstTeam);

            //output del secondo team
            writeTeam(secondTeam);

            chiudiTag();
            //concludo la scrittura del file XML
            chiudiXML();

            System.out.println(FINE_SCRITTURA);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /***
     * Method used to write teams
     * It builds the structure for attributes and passes everything to writeCity method
     * @param team team to print
     * @throws XMLStreamException
     */
    private void writeTeam(Route team) throws XMLStreamException {
        ArrayList<Pair<String>> attr = Pair.buildPairs(TEAM, team.getVeicolo().getName(), COST, String.format(COST_VALUE, team.getFuel()), CITIES, ""+team.getCities());
        apriTagConAttr(ROUTE, attr);
        for(Node n : team.getPath()) {
            writeCity(n.getCity());
        }
        chiudiTag();
    }


    /***
     * Method used to write cities
     * It builds the structure for attributes and passes everything to writeTagWithAttributes method
     * @param ct city to print
     * @throws XMLStreamException if errors occur
     */
    private void writeCity(City ct) throws XMLStreamException {
        writeTagWithAttributes(CITY, Pair.buildPairs(ID, ""+ct.getId(), NAME, ct.getNome()), null);
    }


    /***
     * Method used to write tags with attributes (tabulates, prints and stars a new line)
     * tag with text and attributes, no sub-tags
     * @param tagName string with the tag's name
     * @param attributes pairs of strings containing the name of the attribute and the corresponding value
     * @param characters string to print between tags
     * @throws XMLStreamException if error occur
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


    /***
     * Method that starts to write the XML file by writing the letterhead (starts a new line)
     * @throws XMLStreamException if errors occur
     */
    private void iniziaXML() throws XMLStreamException {
        xmlw.writeStartDocument(ENCODING, XML_VERSION);aCapo();
    }


    /***
     * Method which ends the XML file, writes down the data stored in the cache memory, inside the file and closes
     * @throws XMLStreamException if errors occur
     */
    private void chiudiXML() throws XMLStreamException {
        xmlw.writeEndDocument();
        xmlw.flush();
        xmlw.close();
    }


    /***
     * Method used to open tags (tabulates, prints and starts a new line)
     * @param tagName string with tag's name
     * @throws XMLStreamException if errors occur
     */
    private void apriTag(String tagName) throws XMLStreamException {
        tabula(nTabs++);
        xmlw.writeStartElement(tagName);
        aCapo();
    }


    /***
     * Method used to close tags (tabulates, prints and stars a new line)
     * @throws XMLStreamException if errors occur
     */
    private void chiudiTag() throws XMLStreamException {
        tabula(--nTabs);
        xmlw.writeEndElement();
        aCapo();
    }


    /***
     * Method used to open tags with attribute (tabulates, prints and stars a new line)
     * @param tagName string with the tag's name
     * @param attributes pairs of strings containing the name of the attribute and the corresponding value
     * @throws XMLStreamException if errors occur
     */
    private void apriTagConAttr(String tagName, ArrayList<Pair<String>> attributes) throws XMLStreamException {
        tabula(nTabs++);
        xmlw.writeStartElement(tagName);
        for (Pair<String> pair : attributes) {
            xmlw.writeAttribute(pair.getValue1(), pair.getValue2());
        }
        aCapo();
    }


    /***
     * Method used to add a number of tabulations
     * @param n number of tabs
     * @throws XMLStreamException if errors occur
     */
    public void tabula(int n) throws XMLStreamException {
        for(;n>0;n--)
            xmlw.writeCharacters(TAB);
    }


    /***
     * Method used to start a new line
     * @throws XMLStreamException if errors occur
     */
    public void aCapo() throws XMLStreamException {
        xmlw.writeCharacters(NEWLINE);
    }
}
