package threads;

import java.util.ArrayList;
import java.util.List;

public class MNThreadModel {

    private static final int NUM_TASKS = 1000; 

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Iniciando Modelo N:M com " + NUM_TASKS + " tarefas (virtual threads)...");

        List<Thread> threads = new ArrayList<>();
        long tempoInicial = System.currentTimeMillis();

        Runnable task = () -> {
            try {
                Thread.sleep(50); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < NUM_TASKS; i++) {
            Thread virtualThread = Thread.startVirtualThread(task);
            threads.add(virtualThread);
        }

        for (Thread thread : threads) {
            thread.join(); 
        }

        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicial;

        System.out.println("Tempo total (N:M) com " + NUM_TASKS + " tarefas: " + tempoTotal + " ms");
    }
}