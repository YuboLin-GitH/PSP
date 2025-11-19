
public class Puente {
    public static final int PESO_MAXIMO=200;
    public static final int MAX_PERSONAS=3;
    private int peso;
    private int numPersonas;

    public Puente(){
        this.peso=0;
        this.numPersonas=0;
    }

    public Puente(int peso, int numPersonas){
        this.peso=peso;
        this.numPersonas=numPersonas;
    }

    synchronized public int getPeso() {
        return peso;
    }

    synchronized public int getNumPersonas() {
        return numPersonas;
    }

    synchronized public boolean autorizaionPaso(Persona persona){
        if (this.numPersonas>=Puente.MAX_PERSONAS) return false;
        if (this.peso+persona.getPeso()>Puente.PESO_MAXIMO) return false;
        this.numPersonas++;
        this.peso+=persona.getPeso();
        return true;
    }
    synchronized public void terminaPaso(Persona persona){
        this.numPersonas--;
        this.peso-=persona.getPeso();
    }

}