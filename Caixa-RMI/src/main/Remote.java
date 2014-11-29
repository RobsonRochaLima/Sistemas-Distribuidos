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
public interface Remote extends java.rmi.Remote{
    
    public String geraId() throws RemoteException;        
    public void gerarSenhaComum(Comum c)throws RemoteException;
    public void gerarSenhaPrioridade(Prioritario p)throws RemoteException;
    public boolean removeSenha(String id)throws RemoteException;
    public boolean alertarNovaSenha()throws RemoteException;    
    public int gerarNumSenha()throws RemoteException;  
    
}
