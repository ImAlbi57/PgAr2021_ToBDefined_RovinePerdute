package it.unibs.arnaldo.rovineperdute;

public abstract class Veicolo {

    private double carburante;
    private Coords posizione;
    private String name;
    private NavigationMode mode;

    //metodo astratto per il calcolo del carburante da implementare nei due tipi di veiccoli
    public abstract double calcolaCarburante();

}
