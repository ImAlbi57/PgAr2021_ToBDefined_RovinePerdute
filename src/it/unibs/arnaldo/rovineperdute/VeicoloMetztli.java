package it.unibs.arnaldo.rovineperdute;

public class VeicoloMetztli extends Veicolo{

    private double carburante;
    private Coords positionCity1;
    private Coords positionCity2;

    //costruttore
    public VeicoloMetztli(double carburante, Coords positionCity1, Coords positionCity2) {
        this.carburante = carburante;
        this.positionCity1 = positionCity1;
        this.positionCity2 = positionCity2;
    }

    //getters
    public double getCarburante() {
        return carburante;
    }

    public Coords getPositionCity1() {
        return positionCity1;
    }

    public Coords getPositionCity2() {
        return positionCity2;
    }

    //metodo che calcola il carburante considerando l'altitudine
    public double calcolaCarburante() {

        double calcolaCarburante = 0;
        double distanza = positionCity1.calcolaDifferenzaAltitudine(positionCity2);

        return calcolaCarburante + distanza;

        //il carburante consumato è uguale alla distanza percorsa

    }
}
