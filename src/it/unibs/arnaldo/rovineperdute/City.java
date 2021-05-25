package it.unibs.arnaldo.rovineperdute;

public class City {

    private int id;
    private String nome;
    private Coords coordinate;

    public City(int id, String nome, Coords coordinate) {
        this.id = id;
        this.nome = nome;
        this.coordinate = coordinate;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Coords getCoordinate() {
        return coordinate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCoordinate(Coords coordinate) {
        this.coordinate = coordinate;
    }
}
