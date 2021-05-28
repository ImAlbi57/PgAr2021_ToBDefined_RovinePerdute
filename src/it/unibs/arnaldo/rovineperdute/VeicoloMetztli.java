package it.unibs.arnaldo.rovineperdute;

/***
 *
 * @author ToBdefined
 */
public class VeicoloMetztli extends Veicolo{

    /***
     * VehicleMetztli Constructor
     * @param name vehicle's name
     */
    //costruttore
    public VeicoloMetztli(String name) {
        super(name, NavigationMode.HEIGHTDIFFERENCE);
    }
}
