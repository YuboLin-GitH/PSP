public class WaitNotifySimple implements Runnable{
    private boolean ejecutandoMetodo1=false;

    public void metodo1(){
        for (int i = 0; i<10; i++){
            System.out.println("Ejecución "+i);
            if (i==5){
                try{this.wait();}
                catch (InterruptedException e){e.printStackTrace();}
            }
        }
    }

    public void metodo2(){
        for(int i=10; i<20; i++){
            System.out.println("Ejecución "+ i);
            this.notifyAll();
        }
    }

    public void run() {
        if (ejecutandoMetodo1==false){
            ejecutandoMetodo1=true;
            metodo1();
        }
        else { metodo2();}
    }

    public static void main(String[] args) {
        WaitNotifySimple objetoComun = new WaitNotifySimple();
        new Thread(objetoComun).start();
        new Thread(objetoComun).start();
    }


}
