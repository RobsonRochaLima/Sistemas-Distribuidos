/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author Robson Lima
 */
public class Caixa {
    public static Remote impl;
    public void conectar() throws RemoteException, NotBoundException{
        // endereço e porta do servidor
        Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
        
        impl = (Remote) myRegistry.lookup("Mensagem");        
        
    }
    
    
    public static void main(String[] args) throws RemoteException, NotBoundException{
        Scanner ler = new Scanner(System.in);
        
        
        Caixa caixa = new Caixa();
        caixa.conectar();
        
        String x = impl.geraId();
        System.out.println("Caixa: " + x + " conectado");
        
        System.out.println("Pressione ENTER para solicitar próxima senha...");        
        while(ler.hasNextLine()){            
            ler.nextLine();            

            boolean cond = impl.removeSenha(x);  //verifica se não há senhas
            System.out.print("Senha solicitada: " + cond);
            int parar = 0;
            
            if(cond == false){
                boolean stop=false;                
                while(stop == false){                    
                    stop = impl.alertarNovaSenha();
                    if(parar<=0){
                        System.out.println("\nEsperando nova senha...");
                        parar++;
                    }
                }
                System.out.println("Nova senha gerada...");
            }
            
        }
          
    }   
}
