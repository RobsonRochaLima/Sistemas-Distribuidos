/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicação;

import java.rmi.RemoteException;

/**
 *
 * @author labs
 */

//INTERFACE
public interface MyRemote extends java.rmi.Remote{
    
    public String sayHello() throws RemoteException;
    
}
