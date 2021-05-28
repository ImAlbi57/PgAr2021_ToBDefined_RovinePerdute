package it.unibs.arnaldo.rovineperdute;

/***
 * Class to define the object Vehicle
 * @author ToBdefined
 */
public abstract class Veicolo {

    private final String name;
    private final NavigationMode mode;
    private double carburante;


    /***
     * Vehicle Constructor
     * @param name vehicle's name
     * @param mode navigation mode
     */
    public Veicolo(String name, NavigationMode mode) {
        this.name = name;
        this.mode = mode;
    }


    //GETTERS
    /***
     * Get name
     * @return name
     */
    public String getName() {
        return name;
    }

    /***
     * Get mode
     * @return mode
     */
    public NavigationMode getMode() {
        return mode;
    }

    /***
     * Get carburante
     * @return carburante
     */
    public double getCarburante() {
        return carburante;
    }


    //SETTERS
    /***
     * Set carburante
     * @param carburante fuel
     */
    public void setCarburante(double carburante){
        this.carburante = carburante;
    }
}
