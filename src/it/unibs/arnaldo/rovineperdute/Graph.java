package it.unibs.arnaldo.rovineperdute;

import java.util.HashSet;

public class Graph {
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
    public Graph getBestPath(NavigationMode mode, Node start, Node target){
        Graph result = new Graph();

        //Controllo la modalità
        if(mode != NavigationMode.DISTANCE && mode != NavigationMode.HEIGHT)
            return null;

        HashSet<Node> visited = new HashSet<>();
        HashSet<Node> unvisited = new HashSet<>();

        visited.add(start);

        //DA FINIRE

        return result;
    }
}
