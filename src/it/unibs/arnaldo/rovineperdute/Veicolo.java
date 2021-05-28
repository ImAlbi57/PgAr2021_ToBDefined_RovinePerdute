package it.unibs.arnaldo.rovineperdute;

/***
 * Classe per definire l'oggetto veicolo
 * @author ToBdefined
 */
public abstract class Veicolo {

    private final String name;
    private final NavigationMode mode;
    private double carburante;


    /***
     * Costruttore di veicolo
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /***
     * Getter di mode
     * @return mode
     */
    public NavigationMode getMode() {
        return mode;
    }

    /***
     * Getter di carburante
     * @return carburante
     */
    public double getCarburante() {
        return carburante;
    }


    //SETTERS
    /***
     * Setter di carburante
     * @param carburante
     */
    public void setCarburante(double carburante){
        this.carburante = carburante;
    }
}
