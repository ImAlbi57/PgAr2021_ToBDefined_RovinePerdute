package it.unibs.arnaldo.rovineperdute;

import java.util.HashSet;

public class Graph {
    public static final String MODE_DISTANCE = "DISTANCE";
    public static final String MODE_HEIGHT = "HEIGHT";
    private HashSet<Node> nodes;

    public Graph(){
        this.nodes = new HashSet<>();
    }

    public void addNode(Node node){
        this.nodes.add(node);
    }

    public void removeNode(Node node){
        this.nodes.remove(node);
    }

    //Provo ad implementare A* (A-asterisco)
    public Graph getBestPath(String mode, Node start, Node target){
        Graph result = new Graph();

        //Controllo la modalità
        if(!mode.equals(MODE_DISTANCE) && !mode.equals(MODE_HEIGHT))
            return null;

        HashSet<Node> visited = new HashSet<>();
        HashSet<Node> unvisited = new HashSet<>();

        visited.add(start);

        //DA FINIRE

        return result;
    }
}
