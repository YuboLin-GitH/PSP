public class HiloTrabajando extends Thread{
    public void run(){
        try {
            while (true){
                System.out.println("El hilo está corriendo...");
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            System.out.println("El hilo fue interrupido.");
        }
    }
}
