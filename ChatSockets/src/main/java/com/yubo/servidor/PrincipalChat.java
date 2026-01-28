package com.yubo.servidor;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrincipalChat extends JFrame {
   public JTextField campoTexto;
   public JTextArea areaTexto;
   private static ServerSocket servidor;
   private static Socket conexion;
   private static String ip = "127.0.0.1";

   public static PrincipalChat main;

   public PrincipalChat(){
       super("Servidor");

       campoTexto = new JTextField();
       campoTexto.setEditable(false);
       add(campoTexto, BorderLayout.NORTH);

       areaTexto = new JTextArea();
       areaTexto.setEditable(false);
       add(new JScrollPane(areaTexto), BorderLayout.CENTER);
       areaTexto.setBackground(Color.orange);
       areaTexto.setForeground(Color.black);
       campoTexto.setBackground(Color.white);

       JMenu menuArchivo = new JMenu("Arquivo");
       JMenuItem salir = new JMenuItem("Salir");
       menuArchivo.add(salir);
       JMenuBar barra = new JMenuBar();
       setJMenuBar(barra);
       barra.add(menuArchivo);

       salir.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               System.exit(0);
           }
       });

       setSize(300, 320);
       setVisible(true);
   }

   public void mostrarMensaje(String mensaje){
       areaTexto.append(mensaje + "\n");
   }
   public void habilitarTexto(boolean editable){
       campoTexto.setEnabled(editable);
   }

   public static void main(String[] args){
       PrincipalChat main = new PrincipalChat();
       main.setLocationRelativeTo(null);
       main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       ExecutorService executor = Executors.newCachedThreadPool();

       try {
           servidor = new ServerSocket(11111, 100);
           main.mostrarMensaje("Esperando Cliente...");

            while(true){
                try {
                    conexion = servidor.accept();
                    main.mostrarMensaje("Conectado a : " + conexion.getInetAddress().getHostAddress());
                    main.habilitarTexto(true);

                    executor.execute(new ThreadRecibe(conexion, main));
                    executor.execute(new ThreadEnvia(conexion, main));
                } catch (IOException ex) {
                    Logger.getLogger(PrincipalChat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

       } catch (IOException ex){
           Logger.getLogger(PrincipalChat.class.getName()).log(Level.SEVERE, null, ex);
       }
       executor.shutdown();
   }


}
