package it.unibs.arnaldo.rovineperdute;

import java.util.ArrayList;

/***
 * Class to define the object Node (implements Comparable)
 * @author ToBdefined
 */
public class Node implements Comparable<Node> {

    private City city;
    private ArrayList<Node> links;
    private double distance;


    /***
     * Copy Constructor with new distance
     * @param node city
     * @param distance distance between the cities
     */
    //Metodo costruttore
    public Node(Node node, double distance) {
        this.city = node.getCity();
        this.links = new ArrayList<>(node.getLinks());
        this.distance = distance;
    }

    /***
     * Constructor Node
     * @param city node
     */
    //Metodo costruttore
    public Node(City city){
        this.city = city;
        this.links = new ArrayList<>();
    }


    //GETTERS
    /***
     * Get city
     * @return city
     */
    public City getCity() {
        return this.city;
    }

    /***
     * Get links
     * @return links
     */
    public ArrayList<Node> getLinks(){
        return links;
    }

    /***
     * Get distance
     * @return distance
     */
    public double getDistance() {
        return distance;
    }


    //SETTERS
    /***
     * Set city
     * @param city node
     */
    public void setCity(City city) {
        this.city = city;
    }

    /***
     * Set links
     * @param links links between the cities
     */
    public void setLinks(ArrayList<Node> links) {
        this.links = links;
    }


    /***
     * Method to choose how to calculate the actual distance between the cities
     * the choice is made on the basis of the navigation mode
     * @param mode navigation mode
     * @param node cities
     * @return distance
     */
    public double calcDistance(NavigationMode mode, Node node){
        if(mode == NavigationMode.DISTANCE)
            return this.getCity().getCoordinate().calcolaDistanzaEuclidea(node.getCity().getCoordinate());
        if(mode == NavigationMode.HEIGHTDIFFERENCE)
            return this.getCity().getCoordinate().calcolaDifferenzaAltitudine(node.getCity().getCoordinate());
        return -0.0;
    }


    /***
     * Method to verify if two objects are equal
     * @param o object
     * @return true if two object are equal
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
     * Method that returns the id
     * @return id
     */
    @Override
    public int hashCode() {
        return this.getCity().getId();
    }


    /***
     * Method that compares nodes by distance
     * @param o object
     * @return 0 if equal, n > 0 if greater, n < 0 if smaller
     */
    @Override
    public int compareTo(Node o) {
        return Double.compare(this.distance, o.getDistance());
    }


    /***
     * Method toString to display the city's id and its links
     * @return str, which is the string containing the information about the id and the links of a city
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
