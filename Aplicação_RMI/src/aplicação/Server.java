/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicação;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author labs
 */

// SERVIDOR
public class Server extends UnicastRemoteObject implements MyRemote{
    
     public String sayHello(){
         return "Server say, 'Hey'";
    }
    
    public Server() throws RemoteException {
        System.out.println("Servidor Rodando...");
    }
    
    //metodo que levanta o servidor
    public static void main(String[] args){
        
        try{
            //cria registro na porta 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            
            MyRemote service = new Server();
            Naming.rebind("MyMessage", service);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }      
    
}
