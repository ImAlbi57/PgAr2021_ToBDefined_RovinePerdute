package it.unibs.arnaldo.rovineperdute;

import java.util.ArrayList;

/***
 * Classe per definire l'oggetto Node
 * @author ToBdefined
 */
public class Node implements Comparable<Node> {

    private City city;
    private ArrayList<Node> links;
    private double distance;


    /***
     * Costruttore 1 di Node
     * @param node
     * @param distance
     */
    //Metodo costruttore
    public Node(Node node, double distance) {
        this.city = node.getCity();
        this.links = new ArrayList<>(node.getLinks());
        this.distance = distance;
    }

    /***
     * Costruttore 2 di Node
     * @param city
     */
    //Metodo costruttore
    public Node(City city){
        this.city = city;
        this.links = new ArrayList<>();
    }


    //GETTERS
    /***
     * Getter di city
     * @return city
     */
    public City getCity() {
        return this.city;
    }

    /***
     * Getter di links
     * @return links
     */
    public ArrayList<Node> getLinks(){
        return links;
    }

    /***
     * Getter di distance
     * @return distance
     */
    public double getDistance() {
        return distance;
    }


    //SETTERS
    /***
     * Setter di city
     * @param city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /***
     * Setter di links
     * @param links
     */
    public void setLinks(ArrayList<Node> links) {
        this.links = links;
    }


    /***
     * Metodo per scegliere come calcolare l'effettiva distanza tra le città
     * la scelta avviene sulla base del team considerato
     * @param mode
     * @param node
     * @return distanza
     */
    public double calcDistance(NavigationMode mode, Node node){
        if(mode == NavigationMode.DISTANCE)
            return this.getCity().getCoordinate().calcolaDistanzaEuclidea(node.getCity().getCoordinate());
        if(mode == NavigationMode.HEIGHTDIFFERENCE)
            return this.getCity().getCoordinate().calcolaDifferenzaAltitudine(node.getCity().getCoordinate());
        return -0.0;
    }


    /***
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        //Self check
        if (this == o)
            return true;

        //Null and class check
        if (o == null || getClass() != o.getClass())
            return false;

        //Parsing e ritorno se gli id sono uguali
        Node node = (Node) o;
        return this.getCity().getId() == node.getCity().getId();
    }


    /***
     *
     * @return
     */
    @Override
    public int hashCode() {
        return this.getCity().getId();
    }


    /***
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Node o) {
        return Double.compare(this.distance, o.getDistance());
    }


    /***
     * Metodo toString per visualizzare l'id della città e i suoi collegamenti (?)
     * @return str, cioè la stringa con le informazioni sull' id e i link di una città
     */
    @Override
    public String toString() {
        String str = this.getCity().getId() + ": ";
        for (Node node : links) {
            str += "[" + node.getCity().getId() + "] ";
        }

        return str;
    }
}
