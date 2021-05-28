package it.unibs.arnaldo.rovineperdute;

import java.util.ArrayList;

/***
 *Classe per definire l'oggetto Route
 * Class to define the object Route
 * @author ToBdefined
 */
public class Route {

    public static final String MESS_ROTTA = "Team %s: calcolo la rotta... ";
    public static final String MESS_PARTENZA = "Pronti per la partenza!";
    private final Veicolo veicolo;
    private ArrayList<Node> path;
    private int cities;


    /***
     * Costruttore di route
     * Route Constructor
     * @param veicolo
     */
    //costruttore di route
    public Route(Veicolo veicolo) {
        this.veicolo = veicolo;
    }


    //GETTERS
    /***
     * Getter di veicolo
     * vehicle Getter
     * @return veicolo
     */
    public Veicolo getVeicolo() {
        return veicolo;
    }

    /***
     * Getter di path
     * path Getter
     * @return path
     */
    public ArrayList<Node> getPath() {
        return path;
    }

    /***
     * Getter di fuel
     * fuel Getter
     * @return fuel
     */
    public double getFuel() {
        return this.veicolo.getCarburante();
    }

    /***
     * Getter di cities
     * cities Getter
     * @return cities
     */
    public int getCities() {
        return cities;
    }


    /***
     *Metodo per calcolare il percorso migliore
     * Method to calculate the best path
     * @param mappa
     */
    //Metodo per calcolare la rotta
    public void startRoute(Graph mappa){
        System.out.printf(MESS_ROTTA, veicolo.getName());
        this.path = mappa.getBestPath(veicolo.getMode(), mappa.getNode(0), mappa.getNode(mappa.getNodeNumber()-1));
        this.cities = path.size();
        this.veicolo.setCarburante(path.get(this.cities-1).getDistance());
        System.out.println(MESS_PARTENZA);
    }
}
