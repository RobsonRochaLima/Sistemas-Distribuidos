/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicação;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Anderson
 */

//CLIENTE
public class Cliente {
    
    //metodo para se conectar ao servidor
    public void conectar() throws RemoteException, NotBoundException{
        // endereço e porta do servidor
        Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);

        //search for myMessage service -> procura pelo serviço da interface MyMessage 
        MyRemote impl = (MyRemote) myRegistry.lookup("MyMessage");
        // call server's method
        String x = impl.sayHello();
        System.out.println("Cliente: " + x);
    }
 
    
    public static void main(String[] args) throws RemoteException, NotBoundException{
        Scanner ler = new Scanner(System.in);        
        int id = (int) (Math.random() * 10); //gera id randomico
        
        Cliente teste = new Cliente();
        teste.conectar();
        
        System.out.println("Cliente " + id);        
        
        while(ler.hasNextLine()){
            SimpleDateformat time = new SimpleDateformat();
            System.out.println(time.dataHora());            
            ler.nextLine();
        }
    }
}
