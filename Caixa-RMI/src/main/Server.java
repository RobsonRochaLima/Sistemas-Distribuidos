/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static main.Caixa.impl;

/**
 *
 * @author Robson Lima
 */
public class Server extends UnicastRemoteObject implements Remote {

    ArrayList<Prioritario> prioritario = new ArrayList<Prioritario>();
    ArrayList<Comum> comum = new ArrayList<Comum>();
    
    int id=0;
    int numSenha = 0;
    
    public String geraId() {        
        id++;
        return id + "";
    }
    public int gerarNumSenha(){
        numSenha++;
        return numSenha;
    }
            

    public void gerarSenhaComum(Comum c) {        
        comum.add(c);     
    }

    public void gerarSenhaPrioridade(Prioritario p) {
        prioritario.add(p);  
    }
    
    public boolean removeSenha(String idCaixa) throws RemoteException{
       if(prioritario.isEmpty()){ //verifica se senhas prioritarias estão vazias
           if(comum.isEmpty()){ //verifica se senhas comuns estão vazias
              return false;
           }
           Comum removendo = comum.get(0); //guarda o objeto a ser removido
           implement.imprimirSenhaComum(comum.get(0), idCaixa);
           comum.remove(removendo); //remove senha comum           
           return true;
       }else{
           Prioritario removendo = prioritario.get(0); //guarda o objeto a ser removido           
           implement.imprimirSenhaPriotitaria(prioritario.get(0), idCaixa);
           prioritario.remove(removendo); //remove objeto
           return true;
       }       
    }
    
    public boolean alertarNovaSenha(){
        if(!(prioritario.isEmpty() && comum.isEmpty())){ //listas com senhas
            return true;    
        }else{
            return false;
        }        
    }
    
    public Server() throws RemoteException {
        System.out.println("Servidor Rodando...");
    }
    
    //comunicando com painel
    public static RemotePainel implement;
    public void conectar() throws RemoteException, NotBoundException{
        // endereço e porta do servidor
        Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1234);        
        implement = (RemotePainel) myRegistry.lookup("painel");                
    }    
    
    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        //criar regitro na porta 1199
        Registry registry = LocateRegistry.createRegistry(1099);

        Remote service = new Server();
        registry.rebind("Mensagem", service);
        
        Server server = new Server();
        server.conectar();   //conectando ao painel
                
    }
 
}