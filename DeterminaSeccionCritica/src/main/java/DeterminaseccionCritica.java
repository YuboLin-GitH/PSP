public class DeterminaseccionCritica  extends Thread{
    private int contador;
    private static int acumulador;
    private void ajustaContador(){this.contador-=5;}
    private void ajustaAcumulador(){this.acumulador-=5;}

    public void run(){
        for(int j=0; j<10; j++){
            contador++;
            acumulador++;
            System.out.println("c "+contador);
            System.out.println("a "+acumulador);
            if(contador==10) ajustaContador();
            if (acumulador==10) ajustaAcumulador();
        }
    }

    public static void main(String[] args) {
        for (int i=0 ; i<10; i++){
            new DeterminaseccionCritica().start();

        }
    }
}
