package it.unibs.arnaldo.rovineperdute;

public class VeicoloTonatiuh extends Veicolo{

    private double carburante;
    private Coords posizioneCity1;
    private Coords posizioneCity2;

    //costruttore
    public VeicoloTonatiuh(Coords posizioneCity1, Coords posizioneCity2) {
        this.posizioneCity1 = posizioneCity1;
        this.posizioneCity2 = posizioneCity2;
    }

    //getters
    public double getCarburante() {
        return carburante;
    }

    public Coords getPosizioneCity1() {
        return posizioneCity1;
    }

    public Coords getPosizioneCity2() {
        return posizioneCity2;
    }

    //metodo che calcola il carburante basandosi sulla distanza euclidea
    public double calcolaCarburante() {

        double totaleCarburante = 0;
        double distanza = posizioneCity1.calcolaDistanzaEuclidea(posizioneCity2);

        return totaleCarburante + distanza;

        //il carburante consumato è uguale alla distanza percorsa
    }

}
