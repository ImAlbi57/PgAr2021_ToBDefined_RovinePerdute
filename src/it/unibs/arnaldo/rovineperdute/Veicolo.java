package it.unibs.arnaldo.rovineperdute;

/***
 * Classe per definire l'oggetto veicolo
 * Class to define the object vehicle
 * @author ToBdefined
 */
public abstract class Veicolo {

    private final String name;
    private final NavigationMode mode;
    private double carburante;


    /***
     * Costruttore di veicolo
     * vehicle Constructor
     * @param name
     * @param mode
     */
    public Veicolo(String name, NavigationMode mode) {
        this.name = name;
        this.mode = mode;
    }


    //GETTERS
    /***
     * Getter di name
     * name Getter
     * @return name
     */
    public String getName() {
        return name;
    }

    /***
     * Getter di mode
     * mode Getter
     * @return mode
     */
    public NavigationMode getMode() {
        return mode;
    }

    /***
     * Getter di carburante
     * oil Getter
     * @return carburante
     */
    public double getCarburante() {
        return carburante;
    }


    //SETTERS
    /***
     * Setter di carburante
     * oil Setter
     * @param carburante
     */
    public void setCarburante(double carburante){
        this.carburante = carburante;
    }
}
