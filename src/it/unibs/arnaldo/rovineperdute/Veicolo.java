package it.unibs.arnaldo.rovineperdute;

/***
 * Class to define the object Vehicle
 * @author ToBdefined
 */
public class Veicolo {

    private final String name;
    private final NavigationMode mode;
    private double fuel;


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
    public double getFuel() {
        return fuel;
    }


    //SETTERS
    /***
     * Set carburante
     * @param fuel fuel
     */
    public void setFuel(double fuel){
        this.fuel = fuel;
    }
}
