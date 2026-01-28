package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static void main() {
        Random generator = new Random();
        int maxTime = 3000;
        int minTime = 500;
        int carMaxSleep = 20000;
        int carMinSleep = 10000;
        int numeroPlazas = 3;
        String let = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        List<String> mats  = new ArrayList<>();
        Parking parking = new Parking(numeroPlazas);
        while (true) {
            try {
                //creamos una matricula lo mas "aleatoria" posible
                String matricula = String.valueOf(let.charAt(generator.nextInt(0,let.length()))).repeat(3) + String.valueOf(generator.nextInt(0,100));
                //si la matricula ya esta en la lista pues hacemos matriculas hasta que salga una decente
                while(mats.contains(matricula)){
                    matricula = String.valueOf(let.charAt(generator.nextInt(0,let.length()))).repeat(3) + String.valueOf(generator.nextInt(0,100));
                }
                //añadimos matricula a la lista (esta lista solo se llena con todas las combinaciones posibles pero se podria hacer mejor y liberar las matriculas de los hilos cerrados)
                mats.add(matricula);
                //generamos numeritos aleatorios para el tiempo de espera entre llegada de coches y el tiempo que va a ocupar plaza cada coche
                int randomTime = generator.nextInt(minTime, maxTime);
                int carSleep = generator.nextInt(carMinSleep, carMaxSleep);
                new Thread(new Coche(matricula, parking, carSleep)).start();
//                System.out.println("Siguiente coche en -> " + String.valueOf(randomTime) + "ms");
                Thread.sleep(randomTime);

            } catch (InterruptedException e) {
                System.out.println("hilo interrumpido");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("fail main");
                e.printStackTrace();
            }
        }
    }
}