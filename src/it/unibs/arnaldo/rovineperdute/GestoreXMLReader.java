package it.unibs.arnaldo.rovineperdute;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

public abstract class GestoreXMLReader {
    //Gli attributi sono protected per evitare di dover richiamare continuamente i get nelle classi che ereditano
    protected XMLInputFactory xmlif = null;
    protected XMLStreamReader xmlr = null;
    protected String path;

    /**
     * Istanzia lo StreamReader e gestisce le eccezioni
     * @param path percorso del file
     */
    public GestoreXMLReader(String path) {
        this.path = path;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(path, new FileInputStream(path));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }
    }

}
