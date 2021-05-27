package it.unibs.arnaldo.rovineperdute;

import java.util.ArrayList;

public class Route {
    private final Veicolo veicolo;
    private ArrayList<Node> path;
    private int cities;


    //costruttore di route
    public Route(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    //Metodo per calcolare la rotta
    public void startRoute(Graph mappa){
        System.out.printf("Team %s: calcolo la rotta... ", veicolo.getName());
        this.path = mappa.getBestPath(veicolo.getMode(), mappa.getNode(0), mappa.getNode(mappa.getNodeNumber()-1));
        this.cities = path.size();
        this.veicolo.setCarburante(path.get(this.cities-1).getDistance());
        System.out.println("Pronti per la partenza!");
    }

    //GETTERS
    public Veicolo getVeicolo() {
        return veicolo;
    }

    public ArrayList<Node> getPath() {
        return path;
    }

    public double getFuel() {
        return this.veicolo.getCarburante();
    }

    public int getCities() {
        return cities;
    }
}
