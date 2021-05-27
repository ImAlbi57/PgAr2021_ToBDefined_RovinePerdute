package it.unibs.arnaldo.rovineperdute;

public class Route {
    //variabili inizializzate per prova
    private String team = "ciao";
    private double cost = 33.3;
    private int cities = 7;


    //costruttore di route
    public Route(String _team, double _cost, int _cities) {
        this.team = _team;
        this.cost = _cost;
        this.cities = _cities;
    }


    //GETTERS
    public String getTeam() {
        return team;
    }

    public double getCost() {
        return cost;
    }

    public int getCities() {
        return cities;
    }
}
