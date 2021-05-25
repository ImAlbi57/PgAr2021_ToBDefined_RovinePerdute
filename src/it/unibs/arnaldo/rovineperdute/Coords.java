package it.unibs.arnaldo.rovineperdute;

import it.unibs.tobdefined.utility.InputDati;

import java.util.regex.Pattern;

public class Coords {

    private double x;
    private double y;
    private double h;

    //Metodo costruttore
    public Coords(double x, double y, double h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }

    //Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getH() {
        return h;
    }

    //Setters
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setH(double h) {
        this.h = h;
    }

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

    //Verifica se una stringa è una valida coordinata
    private static boolean validateCoords(String str) {

        String[] parts = str.split(";");
        if(parts.length != 3)
            return false;
        //^[-+]?[0-9]+([\,|\.][0-9]+)?$ regex
        // [+-]? = possibili "+" e "-", [0-9]+ = 1+ corrispondenze, gruppo dopo il + opzionale per i decimali (con virgola o punto)
        return Pattern.matches("^[-+]?[0-9]+([\\,|\\.][0-9]+)?$",parts[0]) && Pattern.matches("^[-+]?[0-9]+([\\,|\\.][0-9]+)?$",parts[1]) && Pattern.matches("^[-+]?[0-9]+([\\,|\\.][0-9]+)?$", parts[2]);

    }

    //metodo per calcolare la distanza euclidea tra due punti
    public double calcolaDistanzaEuclidea(Coords p) {
        return Math.sqrt(Math.pow((p.getX() - this.x), 2) + Math.pow((p.getY() - this.y), 2));
    }

    //metodo per calcolare la differenza di altitudine
    public double calcolaDifferenzaAltitudine(Coords p) {
        return Math.abs((p.getH() - this.h));
    }

}
