package com.yubo;

public class Raton extends Thread{
    private String nombre;
    private int tiempoAlimentacion;

    public Raton(String nombre, int tiempoAlimentacion) {
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;
    }



    public void comer(){
        try {
            System.out.println("El raton "+ this.nombre + " ha comenzado a alimentarse");
            Thread.sleep(this.tiempoAlimentacion*1000);
            System.out.println("El raton " + this.nombre + " ha terminado de alimentarse");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void run() {this.comer();}
}