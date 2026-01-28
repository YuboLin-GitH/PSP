package com.yubo.servidor;



import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadRecibe implements Runnable{
    private final PrincipalChat main;
    private ObjectInputStream entrada;
    private String mensaje;
    private Socket cliente;

    public ThreadRecibe(Socket cliente, PrincipalChat main) {
        this.cliente = cliente;
        this.main = main;
    }

    public void mostrarMensaje(String mensaje){
        main.areaTexto.append(mensaje);
    }

    @Override
    public void run() {
        try{
            entrada = new ObjectInputStream(cliente.getInputStream());
        }catch (IOException ex){
            Logger.getLogger(ThreadRecibe.class.getName()).log(Level.SEVERE, null, ex);
        }
        do {
            try{
                mensaje = (String) entrada.readObject();
                main.mostrarMensaje(mensaje);
            }catch (EOFException eofException){
                main.mostrarMensaje("Fin de la conexion");
                break;
            }catch (IOException ex){
                Logger.getLogger(ThreadRecibe.class.getName()).log(Level.SEVERE, null, ex);
            }catch (ClassNotFoundException ex){
                Logger.getLogger(ThreadRecibe.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (!mensaje.equals("Servidor>>> TERMINATE"));
        try {
            entrada.close();
            cliente.close();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        main.mostrarMensaje("Fin de la conexion");
        System.exit(0);
    }
}
