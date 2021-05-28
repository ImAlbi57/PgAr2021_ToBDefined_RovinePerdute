package it.unibs.arnaldo.rovineperdute;

/***
 * Classe per definire l'oggetto città
 * Class to define the object city
 * @author ToBdefined
 */
public class City {

    private int id;
    private String nome;
    private Coords coordinate;


    /***
     * Costruttore di città 1
     * City 1 constructor
     * @param id, cioè il numero attribuito alla città nell'xml
     *            which is the numbero attributed to the city in the xml-file
     */
    public City(int id){
        this.id = id;
    }

    /***
     * Costruttore di città 2
     * City 2 constructor
     * @param id, cioè il numero attribuito alla città nell'xml
     *            which is the number attributed to the city in the xml-file
     * @param nome, cioè il nome della città
     *              which is the city's number
     * @param coordinate, cioè la posizione della città
     *                    which is the city's position
     */
    public City(int id, String nome, Coords coordinate) {
        this.id = id;
        this.nome = nome;
        this.coordinate = coordinate;
    }


    //GETTERS
    /***
     * Getter di id
     * id Getter
     * @return id
     */
    public int getId() {
        return id;
    }

    /***
     * Getter di nome
     * name Getter
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /***
     * Getter di coordinate
     * coordinates Getter
     * @return coordinate
     */
    public Coords getCoordinate() {
        return coordinate;
    }


    //SETTERS
    /***
     * Setter di id
     * id Setter
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
