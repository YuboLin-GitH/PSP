import java.util.ArrayList;

public class RatonUnObjeto {
    public static void main (String[] args){

        Raton fievel = new Raton("Fievel", 4);
        new Thread(fievel).start();
        new Thread(fievel).start();
        new Thread(fievel).start();
        new Thread(fievel).start();

    }
}
