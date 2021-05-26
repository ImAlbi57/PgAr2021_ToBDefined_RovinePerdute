package it.unibs.arnaldo.rovineperdute;

import java.util.*;

public class Graph {
    private ArrayList<Node> nodes;

    public Graph(){
        this.nodes = new ArrayList<>();
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
    public Node getNode(int index) {
        return nodes.get(index);
    }

    public void addNode(Node node){
        this.nodes.add(node);
    }
    public void removeNode(Node node){
        this.nodes.remove(node);
    }
    public void printNodes(){
        for (Node node : nodes) {
            System.out.println(node);
        }
    }

    //Provo ad implementare A* (A-asterisco)
    public ArrayList<Node> getBestPath(NavigationMode mode, Node start, Node target){

        //Controllo la modalità
        if(mode != NavigationMode.DISTANCE && mode != NavigationMode.HEIGHTDIFFERENCE)
            return null;

        PriorityQueue<Node> OPEN = new PriorityQueue<>();
        HashSet<Node> CLOSED = new HashSet<>();
        HashMap<Node, Double> DISTANCE = new HashMap<>();
        HashMap<Node, Node> PARENTS = new HashMap<>();

        OPEN.add(new Node(start, 0.0));
        DISTANCE.put(start, 0.0);
        PARENTS.put(start, null);

        while(!OPEN.isEmpty()){
            Node currNode = OPEN.remove();

            if(currNode.equals(target))
                return reconstructPath(currNode, PARENTS);

            if(CLOSED.contains(currNode))
                continue;

            CLOSED.add(currNode);

            for(Node link : currNode.getLinks()){
                if(CLOSED.contains(link))
                    continue;

                //Se NON fosse collegato (?)
                double linkDist = DISTANCE.get(currNode) + currNode.calcDistance(mode, link);

                if(!DISTANCE.containsKey(link) || DISTANCE.get(link) > linkDist) {
                    DISTANCE.put(link, linkDist);
                    PARENTS.put(link, currNode);
                    //distanza + funzione euristica (distanza figlio -> target)
                    OPEN.add(new Node(link, linkDist + link.calcDistance(mode, target)));
                }
            }
        }

        return new ArrayList<>();
    }

    private static ArrayList<Node> reconstructPath(Node current, HashMap<Node, Node> cameFrom){
        ArrayList<Node> path = new ArrayList<>();
        Node currNode = current;

        while (currNode != null){
            path.add(currNode);
            currNode = cameFrom.get(currNode);
        }

        Collections.reverse(path);

        return path;
    }
}
