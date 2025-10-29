package threads;

public class MNThreadModel {
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            final int threadId = i;
            Thread.startVirtualThread(() -> {
                System.out.println("Thread: " + threadId + " executando.");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}