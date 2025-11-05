public class Raton implements Runnable {
    private String nombre;
    private int tiempoAlimentacion;
    private int alimentoConsumido;

    public Raton(String nombre, int tiempoAlimentacion) {
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;
        this.alimentoConsumido=0;
    }

    public void comer(){
        try {
            System.out.println("El raton "+ this.nombre + " ha comenzado a alimentarse");
            Thread.sleep(this.tiempoAlimentacion*1000);
            this.alimentoConsumido++;
            System.out.println("El raton " + this.nombre + " ha terminado de alimentarse");
            System.out.println("Lleva consumidos " + this.alimentoConsumido+" alimentos");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void run() {this.comer();}
}