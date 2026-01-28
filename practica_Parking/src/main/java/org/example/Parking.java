package org.example;

import java.util.Arrays;
import java.util.List;

public class Parking {

    private volatile String [] plazas;
    private int nPlazas;

    public Parking(int n){
        this.nPlazas = n;
        this.plazas = new String[this.nPlazas];
        this.initArray();
    }
    //incializamos el array con "" que sino son todo nulls XDD
    public void initArray(){
        for(int i=0; i < this.nPlazas; i++){
            this.plazas[i]= "";
        }
    }
    //comprobamos si hay plazas basicamente si hay un solo "" es que hay plazas asiq con buscar uno nos sirve
    //el metodo es sync porque no quiero a nadie leyendo ni escribiendo en el array que no sea el infeliz que tiene el lock
    public synchronized boolean checkPlazas(){
        for (String s : this.plazas) {
            if ("".equals(s)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void entrar(Coche c){
        try{
            //si no hay plazas pos te esperas ansias hasta que algun otro thread entre aqui y llame al metodo liberar() y te llegue el await()
            while(!this.checkPlazas()){
                System.out.println("hilo de coche -> " + c.getMatricula() +  "  esperando");
                this.wait();
            }
            //que hay plazas pues a ocuparlas
            this.ocupar(c);
        }catch (InterruptedException e){
            System.out.println("hilo de coche -> " + c.getMatricula() +  "  en parking interrumpido");
        }
    }

    //buscamos el primer hueco "" y taca ahora es tuyo
    public synchronized void ocupar(Coche c){
        for (int i = 0; i < this.nPlazas; i++) {
            if (this.plazas[i].equals("")) {
                this.plazas[i] = c.getMatricula();
                break;
            }
        }
        System.out.println("coche -> " + c.getMatricula() + " aparcado");
    }
    //buscamos la matricula que se quiere pirar y taca ahora hay un hueco asique avisamos al resto pa que entren y dejen de esperar como memos
    public synchronized void liberar(Coche c){
        for (int i = 0; i < this.nPlazas; i++) {
            if (this.plazas[i].equals(c.getMatricula())) {
                this.plazas[i] = "";
                break;
            }
        }
        //notificamos al final no queremos que algun proceso "agil" decida segun le llegue el await intentar aparcar sin que la plaza este libre
        this.notifyAll();
        System.out.println("coche -> " + c.getMatricula() + " se marcha");
    }
}
