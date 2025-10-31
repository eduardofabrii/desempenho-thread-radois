package threads;

import java.util.ArrayList;
import java.util.List;

public class ThreadOneToOneModel {

    private static final int NUM_THREADS = 1000; 

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Iniciando Modelo 1:1 com " + NUM_THREADS + " threads...");

        List<Thread> threads = new ArrayList<>();
        long tempoInicial = System.currentTimeMillis();

        Runnable task = () -> {
            try {
                Thread.sleep(50); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join(); 
        }

        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicial;

        System.out.println("Tempo total (1:1) com " + NUM_THREADS + " threads: " + tempoTotal + " ms");
    }
}