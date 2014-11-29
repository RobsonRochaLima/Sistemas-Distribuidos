
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robson Lima
 */
public class ArrayMap {
    
    ArrayList<Map> lista;

    public ArrayMap() {
        lista = new ArrayList<Map>();
    }
    
    public void add(Map cliente){
        lista.add(cliente);
    }    
    
    //imprime a lista com os caixas em execução
    public void imprimir(){
        for (Map map : lista) {            
            System.out.println("# " + map.getNome() + "                                        |");            
        }
    }
    
    //desativa caixa no controlador
    public void desativarCaixa(String texto){ //nome do caixa a ser desativado
            Map temp = null; //variavel temporaria que armazena o caixa a ser desativado
            for(Map map : lista){
			if(map.getNome().equalsIgnoreCase(texto)){                                                      
                            temp = map;                        
                            System.out.println(texto + " desativado.");
                            break;
			}
		}
            lista.remove(temp); // remove depois que percorrer a lista toda
            
    }

    
    
    //deleta o caixa quando ele é desativado no cliente
    public void deletarCaixa(String nome){
		Map temp = null; //variavel temporaria que armazena o caixa a ser desativado
		for(Map map : lista){
                    if(map.getNome().equals(nome)){
                       temp = map;  //guarda nome do caixa a ser excluido
                    }
		}
		if(!(temp == null)){ //verifica se achou o nome a ser deletado
			lista.remove(temp);
		}else{
			System.out.println("vazio");
		}
    }
    
    
    
    
}
