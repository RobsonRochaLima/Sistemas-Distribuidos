
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robson Lima
 */
public class Identificacao {
    String id;

        public Identificacao(String id) {
            this.id = id;
        }

        public Identificacao() {
        }

        public String gerador(){
            Random gerador = new Random();
            int id = gerador.nextInt(10); //pega um numero aleatorio
            String _id = id+"";
                      
            return _id;
        }
    

    @Override
    public String toString() {
        return "Identificacao{" + "id=" + id + '}';
    }
    
    
    
    
}
