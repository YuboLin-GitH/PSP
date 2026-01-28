package org.example;

import java.util.Random;

public class Coche implements Runnable{

    private String matricula;
    private final Parking aparcamiento;
    private int sleepTime;



    public Coche(String matricula, Parking aparcamiento, int t) {
        this.matricula = matricula;
        this.aparcamiento = aparcamiento;
        this.sleepTime = t;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public void run() {
        try {
            //el coche llama a parking e intenta entrar pasandose "a si mismo" como arg (lo cual es innecesario se puede hacer solo con la matricula)
            aparcamiento.entrar(this);
            Thread.sleep(sleepTime);
            //el coche se pira loco!!!
            aparcamiento.liberar(this);
        }catch(InterruptedException e){
            //alguien me ha robado el coche...
            System.out.println("hilo coche interrumpido: " + e);
        }

    }
}
