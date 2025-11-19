public class WaitNotifySimpleConSync implements Runnable{
    private volatile boolean ejecutandoMetodo1=false;

    public synchronized void metodo1(){
        for (int i = 0; i<10; i++){
            System.out.println("Ejecución "+i);
            if (i==5){
                try{this.wait();}
                catch (InterruptedException e){e.printStackTrace();}
            }
        }
    }

    public synchronized void metodo2(){
        for(int i=10; i<20; i++){
            System.out.println("Ejecución "+ i);
        }
        this.notifyAll();
    }

    public void run() {
        if (ejecutandoMetodo1==false){
            ejecutandoMetodo1=true;
            metodo1();
        }
        else { metodo2();}
    }

    public static void main(String[] args) {
        WaitNotifySimpleConSync objetoComun = new WaitNotifySimpleConSync();
        new Thread(objetoComun).start();
        new Thread(objetoComun).start();
    }


}
