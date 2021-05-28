package it.unibs.arnaldo.rovineperdute;

/***
 * Classe per definire l'oggetto città
 * @author ToBdefined
 */
public class City {

    private int id;
    private String nome;
    private Coords coordinate;


    /***
     * Costruttore di città 1
     * @param id, cioè il numero attribuito alla città nell'xml
     */
    public City(int id){
        this.id = id;
    }

    /***
     * Costruttore di città 2
     * @param id, cioè il numero attribuito alla città nell'xml
     * @param nome, cioè il nome della città
     * @param coordinate, cioè la posizione della città
     */
    public City(int id, String nome, Coords coordinate) {
        this.id = id;
        this.nome = nome;
        this.coordinate = coordinate;
    }


    //GETTERS
    /***
     * Getter di id
     * @return id
     */
    public int getId() {
        return id;
    }

    /***
     * Getter di nome
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /***
     * Getter di coordinate
     * @return coordinate
     */
    public Coords getCoordinate() {
        return coordinate;
    }


    //SETTERS
    /***
     * Setter di id
     * @param id
     */
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
