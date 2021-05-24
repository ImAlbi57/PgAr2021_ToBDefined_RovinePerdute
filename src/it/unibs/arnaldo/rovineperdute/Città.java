package it.unibs.arnaldo.rovineperdute;

public class Città {

    private String id;
    private String nome;
    private Coords coordinate;

    public Città(String id, String nome, Coords coordinate) {
        this.id = id;
        this.nome = nome;
        this.coordinate = coordinate;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Coords getCoordinate() {
        return coordinate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCoordinate(Coords coordinate) {
        this.coordinate = coordinate;
    }
}
