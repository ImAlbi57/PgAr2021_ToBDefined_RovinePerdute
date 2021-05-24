package it.unibs.arnaldo.rovineperdute;

import java.util.HashMap;
import java.util.Objects;

public class Node {
    private Città city;
    private HashMap<Node, Double> links;
    private Node source;
    private double totWeight;

    //Metodo costruttore
    public Node(Città city){
        this.city = city;
        this.links = new HashMap<>();
    }

    //Metodi per la gestione del nodo
    public void setSource(Node source){
        this.source = source;
    }
    public void setTotWeight(double totWeight) {
        this.totWeight = totWeight;
    }
    public void addLink(Node node, double weight){
        this.links.put(node, weight);
    }
    public void removeLink(Node node){
        this.links.remove(node);
    }

    //Getters
    public Città getCity() {
        return this.city;
    }
    public Node getSource() {
        return this.source;
    }
    public double getTotWeight() {
        return this.totWeight;
    }
    public double getLinkedNodeWeight(Node node){
        return this.links.get(node);
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
}
