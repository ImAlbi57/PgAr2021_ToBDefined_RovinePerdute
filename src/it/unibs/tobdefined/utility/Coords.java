package it.unibs.tobdefined.utility;

import java.util.regex.Pattern;

/***
 * Class to define the object "Coordinates"
 * @author ToBdefined
 */
public class Coords {

    public static final String REGEX = "^[-+]?[0-9]+([\\,|\\.][0-9]+)?$";
    private double x;
    private double y;
    private double h;


    /***
     * Coords constructor
     * @param x, which is the position relative to the x-axis
     * @param y, which is the position relative to the y-axis
     * @param h, which is the position relative to the z-axis
     */
    //Metodo costruttore
    public Coords(double x, double y, double h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }


    //GETTERS
    /***
     * Get x
     * @return x
     */
    public double getX() {
        return x;
    }

    /***
     * Get y
     * @return y
     */
    public double getY() {
        return y;
    }

    /***
     * Get h
     * @return h
     */
    public double getH() {
        return h;
    }


    //SETTERS
    /***
     * Set x
     * @param x x-axis
     */
    public void setX(double x) {
        this.x = x;
    }

    /***
     * Set y
     * @param y y-axis
     */
    public void setY(double y) {
        this.y = y;
    }

    /***
     * Set h
     * @param h altitude
     */
    public void setH(double h) {
        this.h = h;
    }


    //MAI USATO
    public static Coords leggiCoordinate(String messaggio) {
        //Prendo in input una stringa con sintassi = 1.0;2.0 (finché l'input non è valido)
        String coordinate;
        boolean finito = false;

        //Prendo in input le coordinate finché non sono valide (il replace permette di fare l'input sia con '.' che con ','
        do {
            coordinate = InputDati.leggiStringa(messaggio).replace(',', '.');

            if(validateCoords(coordinate))
                finito = true;
            else
                System.out.println("Errore di sintassi");
        }while(!finito);

        //Creo delle sottostringhe che metto in parts, separando la stringa originale dove ci sono i caratteri ';'
        String[] parts = coordinate.split(";");

        //Parso le sottostringhe in double
        double x = Double.parseDouble(parts[0]);
        double y = Double.parseDouble(parts[1]);
        double h = Double.parseDouble(parts[2]);

        //Ritorno delle nuove coordinate
        return new Coords(x, y, h);
    }


    /***
     * Method to verify coordinates validity
     * @param str, which is the string containing coordinates values
     * @return true if valid, otherwise false
     */
    //Verifica se una stringa è una valida coordinata
    private static boolean validateCoords(String str) {

        String[] parts = str.split(";");
        if(parts.length != 3)
            return false;
        //^[-+]?[0-9]+([\,|\.][0-9]+)?$ regex
        // [+-]? = possibili "+" e "-", [0-9]+ = 1+ corrispondenze, gruppo dopo il + opzionale per i decimali (con virgola o punto)
        return Pattern.matches(REGEX,parts[0]) && Pattern.matches(REGEX,parts[1]) && Pattern.matches("^[-+]?[0-9]+([\\,|\\.][0-9]+)?$", parts[2]);

    }


    /***
     * Method to calculate the Euclidean distance between two points
     * @param p, which is the following coordinate
     * @return distance value
     */
    //metodo per calcolare la distanza euclidea tra due punti
    public double calcolaDistanzaEuclidea(Coords p) {
        return Math.sqrt(Math.pow((p.getX() - this.x), 2) + Math.pow((p.getY() - this.y), 2));
    }


    /***
     * Method to calculate the difference in altitude between two points
     * @param p, which is the following coordinate
     * @return difference in altitude value
     */
    //metodo per calcolare la differenza di altitudine
    public double calcolaDifferenzaAltitudine(Coords p) {
        return Math.abs((p.getH() - this.h));
    }

}
