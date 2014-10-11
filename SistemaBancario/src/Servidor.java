
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
        int num=0;
        
            ArrayList<Banco> dados = new ArrayList<Banco>();
            Banco a = new Banco(0, "Robson Lima", "12345", "12345", 500);
            Banco b = new Banco(1, "Anderson Lima", "01234", "54321", 200);
            Banco c = new Banco(2, "João da Silva", "23456", "67890", 0);
            //adicionando na lista
            dados.add(a);
            dados.add(b);
            dados.add(c);
            
            
        
            
        ServerSocket servidor = new ServerSocket(12345);  // cria novo servidor
        System.out.println("Porta 12345 aberta!");
   
        while(true){
             Socket caixa = servidor.accept();  //espera até o cliente se conectar
             System.out.println("Nova conexão com o cliente " + caixa.getInetAddress().getHostAddress());
             
             Conexao con = new Conexao(caixa, dados);
             con.start();
       
     }
    }
    
}
