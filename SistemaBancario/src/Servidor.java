
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author labs
 */
public class Servidor {
    /**
     * @param args the command line arguments
     */
    
   
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
            
            ArrayList<Banco> dados = new ArrayList<Banco>();
            Banco a = new Banco(0, "Robson Lima", "12345", "12345", 500);
            Banco b = new Banco(1, "Anderson Lima", "01234", "54321", 200);
            Banco c = new Banco(2, "João da Silva", "23456", "67890", 0);
            //adicionando na lista
            dados.add(a);
            dados.add(b);
            dados.add(c);
                     
            
            //controladores
            ArrayList<Control> controlador = new ArrayList<Control>();
            Control d = new Control("12345","12345");
            Control e = new Control("67890","12345");
            Control f = new Control("54321","12345");
            
            controlador.add(d);
            controlador.add(e);
            controlador.add(f);
            
            
                        
            ServerSocket servidor = new ServerSocket(12345);  // cria novo servidor
            System.out.println("Porta 12345 aberta!");
   
        //cliente ou controlador
        
        
        
            while(true){
                 Socket caixa = servidor.accept();  //espera até o cliente se conectar
                 System.out.println("Nova conexão " + caixa.getInetAddress().getHostAddress());

                 Conexao con = new Conexao(caixa, dados, controlador);
                 con.start();      
            }
    }
    
}
