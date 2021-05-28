package it.unibs.arnaldo.rovineperdute;

/***
 * Classe per mandare in output i benchmark
 * @author ToBdefined
 */
public class BenchMark {

    private static long startTime;


    /***
     * Metodo per iniziare il cronometraggio del tempo impiegato a leggere il programma
     */
    public static void start(){
        startTime = System.currentTimeMillis();
    }


    /***
     * Metodo per mandare in output al termine del programma il tempo e lo spazio di memoria impiegati
     */
    public static void end(){
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.printf("\n\nIl programma è stato eseguito in %d ms\n", elapsedTime);

        //Per la memoria usata, fa partire il garbage collector
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.printf("Sono stati usati %.2f kb di memoria", (float) memory/1000);
    }
}
