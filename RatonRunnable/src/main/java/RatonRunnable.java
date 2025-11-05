public class RatonRunnable {
    public static void main (String[] args){

        Raton fievel = new Raton("Fievel", 4);
        Raton jerry = new Raton("Jerry", 5);
        Raton pinky = new Raton("Pinky", 3);
        Raton micker = new Raton("Micker", 6);
        new Thread(fievel).start();
        new Thread(jerry).start();
        new Thread(pinky).start();
        new Thread(micker).start();


    }
}
