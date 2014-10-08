
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
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
        ServerSocket servidor = new ServerSocket(12345);  // cria novo servidor
        System.out.println("Porta 12345 aberta!");
   
        while(true){
             Socket caixa = servidor.accept();  //espera até o cliente se conectar
             System.out.println("Nova conexão com o cliente " + caixa.getInetAddress().getHostAddress());
             
             Conexao con = new Conexao(caixa);           
             con.start();
       
     }
    }
    
}
