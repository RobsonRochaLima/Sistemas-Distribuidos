
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robson Lima
 */
public class Controlador {
    
    public static void main(String[] args) throws UnknownHostException, IOException {            
            ServerSocket controlador = new ServerSocket(12346);  // cria novo servidor
            ArrayMap listaCaixas = new ArrayMap();
            
            int id = 1;
            
//            ThreadMenu tmenu = new ThreadMenu(listaCaixas); //passar array listaCaixa contendo os caixa conectados
//            tmenu.start(); //inicia thread Menu 
                 
            while(true){
                 Socket caixa = controlador.accept();  //espera até o cliente se conectar
                  ThreadMenu tmenu = new ThreadMenu(listaCaixas); //passar array listaCaixa contendo os caixa conectados
                  tmenu.start();
                 
                 
                 Map box = new Map(caixa); //instanciando Map com nome do caixa + Socket caixa
                 box.setNome("caixa" + id);
                 listaCaixas.add(box); //adiciona objeto na lista
                 
                 ThreadOuvir touvir = new ThreadOuvir(listaCaixas, box); //passando lista + Objeto com os caixas
		 touvir.start();
                                  
                 id++; //incrementa id
            }
    }    
}
