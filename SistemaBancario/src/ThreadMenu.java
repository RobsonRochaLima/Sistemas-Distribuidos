
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robson Lima
 */
public class ThreadMenu extends Thread{
    private ArrayMap lista;
    Scanner ler = new Scanner(System.in);
    
    public ThreadMenu(ArrayMap lista) {
        this.lista = lista;        
    }

    
    /*Thread que disponibilizar as opções do controlado
     * ->listar caixas
     * ->desconectar caixas*/
    public void run(){
        int opt;
        boolean stop = true;
        
        while(stop == true){
      
        System.out.println("MENU -- (1)Listar Caixa (2)Desconectar");
        //opt = ler.nextInt();
        String _opt = ler.nextLine();
        opt = Integer.parseInt(_opt);
        switch(opt){
            case 1:
            {
                System.out.println("------------------------------------------------");
                System.out.println("                 LISTA DE CAIXAS               |");
                System.out.println("------------------------------------------------");                
                lista.imprimir();
                System.out.println("------------------------------------------------");                
                System.out.println("");System.out.println("");
                break;
            }
            case 2:
            {
                System.out.print("Digite o nome do caixa para desativar: ");
                String nome = ler.nextLine();
                lista.desativarCaixa(nome);          
                break;
            }
            
         }
                      
        }
    }
}
            

