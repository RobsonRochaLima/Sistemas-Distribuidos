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
public class GeradorSenha {    
                   
    public static Remote impl; //declarando global
        
    public void conectar() throws RemoteException, NotBoundException{
        // endereço e porta do servidor
        Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
        impl = (Remote) myRegistry.lookup("Mensagem");        
    }
      
    public static void main(String[] args) throws RemoteException, NotBoundException{
        Scanner ler = new Scanner(System.in);
        boolean stop = true;
        int opt;
        String nome;
        int senha=0;
        
        GeradorSenha gerador = new GeradorSenha();
        gerador.conectar();
        
        
       // System.out.println("Num senha: " + senha);        
        while(stop == true){
             System.out.println("Tipo de senha \n (1)Comum \n (2)Prioritária ");
             opt = ler.nextInt();
             ler.nextLine();
             
             switch(opt){
                 case 1:
                 {
                     System.out.print("Digite nome: ");
                     nome = ler.nextLine(); 
                     
                     senha = impl.gerarNumSenha();
                     Comum comum = new Comum(senha, nome);                     
                     impl.gerarSenhaComum(comum);                     
                     break;
                 }
                 case 2:
                 {                     
                     System.out.print("Digite nome: ");
                     nome = ler.nextLine();
                     
                     senha = impl.gerarNumSenha();
                     Prioritario prioritario = new Prioritario(senha, nome);
                     impl.gerarSenhaPrioridade(prioritario);
                     break;
                 }
            }   
      }
    }
}
