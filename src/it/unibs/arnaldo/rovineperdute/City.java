package it.unibs.arnaldo.rovineperdute;

import it.unibs.tobdefined.utility.Coords;

/***
 * Class to define the object City
 * @author ToBdefined
 */
public class City {

    private int id;
    private String nome;
    private Coords coordinate;


    /***
     * City constructor (with id only)
     * @param id id
     */
    public City(int id){
        this.id = id;
    }

    /***
     * City constructor
     * @param id id
     * @param nome city's name
     * @param coordinate city's position
     */
    public City(int id, String nome, Coords coordinate) {
        this.id = id;
        this.nome = nome;
        this.coordinate = coordinate;
    }


    //GETTERS
    /***
     * Get id
     * @return id
     */
    public int getId() {
        return id;
    }

    /***
     * Get name
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /***
     * Get coordinates
     * @return coordinate
     */
    public Coords getCoordinate() {
        return coordinate;
    }


    //SETTERS
    /***
     * Set id
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

}
