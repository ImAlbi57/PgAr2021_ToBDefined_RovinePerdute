package it.unibs.arnaldo.rovineperdute;

public class BenchMark {
    private static long startTime;

    public static void start(){
        startTime = System.currentTimeMillis();
    }

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
