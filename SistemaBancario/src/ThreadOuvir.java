
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robson Lima
 */
public class ThreadOuvir extends Thread{
    Map map;
    ArrayMap list;

    public ThreadOuvir(ArrayMap list,Map map) {
        this.map = map;
        this.list = list;
    }
    
    /* Thread responsavel por deletar o caixa da lista 
     * quando o cliente desconectar                 */
    public void run(){	
        try {
            String texto = map.receber();
            System.out.println(map.getNome() + " desativado.");
            list.deletarCaixa(map.getNome());            
        } catch (Exception e) {
            
        }
    }    
}
