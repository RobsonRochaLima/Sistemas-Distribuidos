/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.rmi.RemoteException;

/**
 *
 * @author Robson Lima
 */
public interface RemotePainel extends java.rmi.Remote {
    public void RemotePainel() throws RemoteException; 
    public void imprimirSenhaComum(Comum comum, String idCaixa)throws RemoteException; 
    public void imprimirSenhaPriotitaria(Prioritario prioritario, String idCaixa)throws RemoteException;
    
}
