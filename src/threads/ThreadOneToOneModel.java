package threads;

import java.util.ArrayList;
import java.util.List;

public class ThreadOneToOneModel {
    private static final int NUM_THREADS = 100;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Threads 1:1 com " + NUM_THREADS + " threads");

        List<Thread> threads = new ArrayList<>();
        long tempoInicial = System.currentTimeMillis();

        for (int i = 0; i < NUM_THREADS; i++) {
            Runnable task = () -> {
                try {
                    for (int j = 0; j < 5; j++) {
                        Thread.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicial;

        System.out.println("Tempo total com " + NUM_THREADS + " threads: " + tempoTotal + " ms");
    }
}