package it.unibs.tobdefined.utility;

import java.util.ArrayList;

public class Pair<T> {
    private T value1;
    private T value2;

    public Pair(T value1, T value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T getValue1() {
        return value1;
    }

    public T getValue2() {
        return value2;
    }

    public static ArrayList<Pair<String>> buildPairs(String... args){
        //Se il numero di stringhe è dispari ritorno
        if(args.length % 2 != 0)
            return null;

        ArrayList<Pair<String>> pairs = new ArrayList<>();
        for(int i=0; i<args.length-1; i=i+2){
            pairs.add(new Pair<>(args[i], args[i+1]));
        }

        return pairs;
    }
}