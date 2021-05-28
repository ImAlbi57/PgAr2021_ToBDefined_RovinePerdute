package it.unibs.arnaldo.rovineperdute;

import java.util.*;

/***
 *
 * @author ToBdefined
 */
public class Graph {

    private ArrayList<Node> nodes;


    /***
     * Costruttore di Graph
     */
    public Graph(){
        this.nodes = new ArrayList<>();
    }


    //GETTERS
    /***
     * Getter di nodes
     * @return nodes
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /***
     * Metodo per ricavare un node dall'arraylist attraverso un indice
     * @param index, cioè la posizione del node nell'arraylist
     * @return node
     */
    public Node getNode(int index) {
        return nodes.get(index);
    }

    /***
     * Metodo per ricavare la lunghezza dell'arraylist
     * @return
     */
    public int getNodeNumber(){
        return nodes.size();
    }


    /***
     * Metodo per aggiungere un node all'arraylist
     * @param node, cioè il node da aggiungere
     */
    public void addNode(Node node){
        this.nodes.add(node);
    }


    //MAI USATO
    public void removeNode(Node node){
        this.nodes.remove(node);
    }


    //MAI USATO
    public void printNodes(){
        for (Node node : nodes) {
            System.out.println(node);
        }
    }


    //MAI USATO
    public void printNodesWithLinks(){
        for (Node iNode : nodes) {
            for (Node jNode : iNode.getLinks()) {
                System.out.println(iNode.getCity().getId() + "-" + jNode.getCity().getId() + ": " + iNode.getCity().getCoordinate().calcolaDifferenzaAltitudine(jNode.getCity().getCoordinate()));
            }
        }
    }


    /***
     * Metodo per trovare il percorso meno costoso per raggiungere le rovine perdute
     * @param mode
     * @param start
     * @param target
     * @return percorso migliore
     */
    //Provo ad implementare A* (A-asterisco)
    public ArrayList<Node> getBestPath(NavigationMode mode, Node start, Node target){

        ////Controllo la modalità [CONTROLLO INUTILE]
        //if(mode != NavigationMode.DISTANCE && mode != NavigationMode.HEIGHTDIFFERENCE)
        //    return null;

        PriorityQueue<Node> nodiAperti = new PriorityQueue<>();
        HashSet<Node> nodiChiusi = new HashSet<>();
        HashMap<Node, Double> distanze = new HashMap<>();
        HashMap<Node, Node> nodiGenitori = new HashMap<>();

        //Aggiungo il nodo di partenza
        nodiAperti.add(new Node(start, 0.0));
        distanze.put(start, 0.0);
        nodiGenitori.put(start, null);

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
                //Se è già contenuto nei nodi scartati passo direttamente al ciclo successivo
                if(nodiChiusi.contains(link))
                    continue;

                //calcolo la distanza tra nodo corrente e nodo collegato
                double linkDist = distanze.get(currNode) + currNode.calcDistance(mode, link);

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
     * Metodo che ricostruisce il percorso partendo dall'arrivo e poi ritorna il percorso "invertito"
     * @param current nodo corrente (nodo finale)
     * @param cameFrom hashmap con i link tra nodi (nodi genitori)
     * @return ArrayList<Node> contenente il percorso ottimale
     */
    private static ArrayList<Node> reconstructPath(Node current, HashMap<Node, Node> cameFrom){
        ArrayList<Node> path = new ArrayList<>();
        Node currNode = current;

        //Aggiungo a path il nodo corrente e poi scorro "all'indietro" tramite l'HashMap cameFrom
        while (currNode != null){
            //currNode.setLinks(null);
            path.add(currNode);
            currNode = cameFrom.get(currNode);
        }

        //Inverto il percorso
        Collections.reverse(path);

        return path;
    }
}
