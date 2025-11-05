import java.util.ArrayList;

public class RatonEstados {
    public static void main (String[] args){

        Raton micker = new Raton("Micker", 3);
        ArrayList<Thread.State> estadosHilo = new ArrayList<>();
        Thread h = new Thread(micker);
        estadosHilo.add(h.getState());
        h.start();
        while (h.getState()!=Thread.State.TERMINATED){
                if (!estadosHilo.contains(h.getState())){
                    estadosHilo.add(h.getState());
                }
        }

        estadosHilo.add(h.getState());
        for (int i = 0; i< estadosHilo.size(); i++){
            System.out.println(estadosHilo.get(i));
        }


    }
}
