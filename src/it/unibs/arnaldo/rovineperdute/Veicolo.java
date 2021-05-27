package it.unibs.arnaldo.rovineperdute;

public abstract class Veicolo {
    private final String name;
    private final NavigationMode mode;
    private double carburante;

    public Veicolo(String name, NavigationMode mode) {
        this.name = name;
        this.mode = mode;
    }

    //Getters
    public String getName() {
        return name;
    }
    public NavigationMode getMode() {
        return mode;
    }
    public double getCarburante() {
        return carburante;
    }

    //Setter
    public void setCarburante(double carburante){
        this.carburante = carburante;
    }
}
