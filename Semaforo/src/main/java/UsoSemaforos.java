import java.util.concurrent.Semaphore;

public class UsoSemaforos implements  Runnable {

    Semaphore semaforo = new Semaphore(3);
    @Override
    public void run() {
        try {
            semaforo.acquire();
            System.out.println("Paso 1"); Thread.sleep(1000);
            System.out.println("Paso 2"); Thread.sleep(1000);
            System.out.println("Paso 3"); Thread.sleep(1000);
            semaforo.release();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UsoSemaforos us = new UsoSemaforos();
        for (int i = 0; i < 5; i++) {
            new Thread(us).start();
        }
    }
}
