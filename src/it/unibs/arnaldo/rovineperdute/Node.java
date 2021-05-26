package it.unibs.arnaldo.rovineperdute;

import java.util.ArrayList;

public class Node implements Comparable<Node> {
    private City city;
    private ArrayList<Node> links;
    private double distance;

    //Metodo costruttore
    public Node(Node node, double distance) {
        this.city = node.getCity();
        this.links = new ArrayList<>(node.getLinks());
        this.distance = distance;
    }

    //Metodo costruttore
    public Node(City city){
        this.city = city;
        this.links = new ArrayList<>();
    }

    //Metodi per la gestione del nodo
    public void addLink(Node node){
        this.links.add(node);
    }
    public void removeLink(Node node){
        this.links.remove(node);
    }

    //Getters
    public City getCity() {
        return this.city;
    }
    public ArrayList<Node> getLinks(){
        return links;
    }
    public double getDistance() {
        return distance;
    }

    //Setters
    public void setCity(City city) {
        this.city = city;
    }
    public void setLinks(ArrayList<Node> links) {
        this.links = links;
    }

    public double calcDistance(NavigationMode mode, Node node){
        if(mode == NavigationMode.DISTANCE)
            return this.getCity().getCoordinate().calcolaDistanzaEuclidea(node.getCity().getCoordinate());
        if(mode == NavigationMode.HEIGHTDIFFERENCE)
            return this.getCity().getCoordinate().calcolaDifferenzaAltitudine(node.getCity().getCoordinate());
        return -0.0;
    }


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

    @Override
    public int hashCode() {
        return this.getCity().getId();
    }

    @Override
    public int compareTo(Node o) {
       return Double.compare(this.distance, o.getDistance());
    }

    @Override
    public String toString() {
        String str = this.getCity().getId() + ": ";
        for (Node node : links) {
            str += "[" + node.getCity().getId() + "] ";
        }

        return str;
    }
}
