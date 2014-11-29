/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Robson Lima
 */
public class Painel extends UnicastRemoteObject implements RemotePainel{
    

    public Painel() throws RemoteException{
        System.out.println("Painel Rodando...");
    }    
    
    public void imprimirSenhaComum(Comum comum, String idCaixa) throws RemoteException {
        System.out.println("\n=============PAINEL=================");        
        System.out.println("\n  Senhor(a): " + comum.getNome() + "\n  Comparecer ao caixa " + idCaixa + ",\n  Senha: " + comum.getNumero() + "\n  Atendimento comum.");
        System.out.println("\n====================================");
        
    }
    
    public void imprimirSenhaPriotitaria(Prioritario prioritario, String idCaixa) throws RemoteException {
        System.out.println("\n=============PAINEL=================");
        System.out.println(" \n  Senhor(a): " + prioritario.getNome() + "\n  Comparecer ao caixa " + idCaixa + ",\n  Senha: " + prioritario.getNumero() + "\n  Atendimento Prioritário.");
        System.out.println("\n====================================");
        
    }
     
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        //criar regitro na porta 1234
        Registry registry = LocateRegistry.createRegistry(1234);       
        registry.rebind("painel", new Painel());        
    }
    
    public void RemotePainel() throws RemoteException {
        System.out.println("runing...");
    }

    

   
 

    

    
}
