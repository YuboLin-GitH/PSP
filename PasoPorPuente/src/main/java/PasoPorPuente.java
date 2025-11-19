
import java.util.Random;

public class PasoPorPuente {
    public static void main(String[] args) {
        final Puente puente = new Puente();
        int tMinLlegada = 1;
        int tMaxLlegada = 10;
        int tMinPaso = 10;
        int tMaxPaso = 25;
        int minPesoPersona = 40;
        int maxPesoPersona = 120;
        int dniP = 1;
        int tLlegadaP;
        int pesoP;

        System.out.println("Comienza la simulacion");
        Random r = new Random();

        while (true){
            tLlegadaP = tMinLlegada + r.nextInt(tMaxLlegada-tMinLlegada+1);
            pesoP= minPesoPersona + r.nextInt(maxPesoPersona-minPesoPersona+1);
            System.out.println("La siguiente persona llega en "+tLlegadaP+" segundos.");
            try {
                Thread.sleep(tLlegadaP*1000);
                Persona p = new Persona(dniP, pesoP, tMinPaso, tMaxPaso, puente);
                Thread hilo = new Thread(p);
                hilo.start();
                dniP++;
            } catch (InterruptedException e) {
                System.out.println("Interrupcion mientas esperaba siguiente peaton");
            }
        }
    }
}