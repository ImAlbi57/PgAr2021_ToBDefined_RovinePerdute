package it.unibs.arnaldo.rovineperdute;

import java.util.*;

/***
 * Class to define the object Graph
 * @author ToBdefined
 */
public class Graph {

    private ArrayList<Node> nodes;


    /***
     * Graph constructor
     */
    public Graph(){
        this.nodes = new ArrayList<>();
    }


    //GETTERS
    /***
     * Method to extract a node from the arraylist by the index
     * @param index node's position in the arraylist
     * @return node
     */
    public Node getNode(int index) {
        return nodes.get(index);
    }

    /***
     * Method to obtain the length of the arraylist
     * @return length
     */
    public int getNodeNumber(){
        return nodes.size();
    }


    /***
     * Method to add a node to the arraylist
     * @param node node to add
     */
    public void addNode(Node node){
        this.nodes.add(node);
    }


    /***
     * Method to find the less expensive path in order to reach the lost ruins
     * @param mode navigation mode
     * @param start city where the path starts
     * @param target target
     * @return best path
     */
    //Provo ad implementare A* (A-asterisco)
    public ArrayList<Node> getBestPath(NavigationMode mode, Node start, Node target){

        PriorityQueue<Node> nodiAperti = new PriorityQueue<>();
        HashSet<Node> nodiChiusi = new HashSet<>();
        HashMap<Node, Double> distanze = new HashMap<>();
        HashMap<Node, Node> nodiGenitori = new HashMap<>();

        //Aggiungo il nodo di partenza
        nodiAperti.add(new Node(start, 0.0));
        distanze.put(start, 0.0);
        nodiGenitori.put(start, null);

        //Finché ci sono nodiAperti
        while(!nodiAperti.isEmpty()){
            Node currNode = nodiAperti.remove();

            //Se ho raggiunto le rovine ricostruisci il percorso
            if(currNode.equals(target))
                return reconstructPath(currNode, nodiGenitori);

            //Se uno dei nodi chiusi contiene il nodo corrente allora passo al prossimo ciclo
            if(nodiChiusi.contains(currNode))
                continue;

            nodiChiusi.add(currNode);

            //Scorro ogni collegamento del nodo
            for(Node link : currNode.getLinks()){
                //Se è già contenuto nei nodi chiusi passo direttamente al ciclo successivo
                if(nodiChiusi.contains(link))
                    continue;

                //calcolo la distanza tra nodo corrente e nodo collegato
                double linkDist = distanze.get(currNode) + currNode.calcDistance(mode, link);

                //Se il valore della distanza non esiste (o se è migliore di un esistente) lo aggiungo
                if(!distanze.containsKey(link) || distanze.get(link) > linkDist) {
                    distanze.put(link, linkDist);
                    nodiGenitori.put(link, currNode);

                    //distanza "pesata": distanza + funzione euristica (distanza figlio -> target)
                    nodiAperti.add(new Node(link, linkDist + link.calcDistance(mode, target)));
                }
            }
        }

        return new ArrayList<>();
    }


    /***
     * Method to rebuild the path starting from the end and then return the reversed path
     * @param current current node (final node)
     * @param cameFrom hashmap with the link between the nodes (parents nodes)
     * @return ArrayList<Node> containing the optimal path
     */
    private static ArrayList<Node> reconstructPath(Node current, HashMap<Node, Node> cameFrom){
        ArrayList<Node> path = new ArrayList<>();
        Node currNode = current;

        //Aggiungo a path il nodo corrente e poi scorro "all'indietro" tramite l'HashMap cameFrom
        while (currNode != null){
            //Rimuovo i collegamenti agli altri nodi che sono superflui per la stampa
            currNode.setLinks(null);
            path.add(currNode);
            currNode = cameFrom.get(currNode);
        }

        //Inverto il percorso
        Collections.reverse(path);

        return path;
    }
}
