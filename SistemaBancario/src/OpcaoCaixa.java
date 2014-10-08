
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author labs
 */
public class OpcaoCaixa {
    Scanner ler = new Scanner(System.in);
    ArrayList<Dados> cliente = new ArrayList<Dados>();
    
    public boolean verificaTamanho(String conta, String senha){
        int tam_conta = conta.length();
        int tam_senha = senha.length();
        
        if(tam_conta == 5 && tam_senha == 5){
            return true;
        }else{
            return false;
        }
  
    }
    
    //0 -> 12345 <- 12345
    public String retornaConta(String conta){
        String c ="";
        for(int i=1; i<conta.length()-5;i++){
            c = c + conta.charAt(i);
        }
        return c;
    }
    //0 12345 -> 12345 <-
    public String retornaSenha(String senha){
        String s = "";
        for(int i=6; i<senha.length();i++){
            s = s + senha.charAt(i);
        }
        return s;
    }
    
    public char option(String texto){
        
        return texto.charAt(0);
    }
    
    public double retornaValorSaque(String v){
        String val="";
        double valor;
        
        for(int i=1; i < v.length()-1; i++){
            val = val + v.charAt(i);            
        }
        valor = Double.parseDouble(val);
        
        return valor;
    }
    public String retornandoID(String v){ //150.02
        int tam = v.length()-1;       
        
        String text = "";
        text = text + v.charAt(tam);
        
        return text;   
    }
    
    
    
    
    
    public boolean autenticar(String conta, String senha){
        boolean id = false;
        int cont = 0;
        
        //adcionando clientes a lista
        cliente.add(new Dados(0, "Robson","12345","12345", 0.00));
        cliente.add(new Dados(1, "Anderson","12345","67890", 100.00));
        cliente.add(new Dados(2, "Joao","12345","54321",0.00));
        
        
        for(int i=0; i < cliente.size();i++){
            
            if(cliente.get(i).conta.equalsIgnoreCase(conta) && cliente.get(i).senha.equalsIgnoreCase(senha)){
                System.out.println("Achou clinete...");
                cont++;
                return id = true;
            }   
        }

        return id;

    }
    
    public double sacar(double valor, int id){
        double total;
        cliente.add(new Dados(0, "Robson","12345","12345", 0.00));
        cliente.add(new Dados(1, "Anderson","12345","67890", 100.00));
        cliente.add(new Dados(2, "Joao","12345","54321",0.00));
            
        total = cliente.get(id).saldo - valor;
        
        return total;
    }
    
    public void listarClientes(){
        String aux = "";
        cliente.add(new Dados(0, "Robson","12345","12345", 0.00));
        cliente.add(new Dados(1, "Anderson","12345","67890", 100.00));
        cliente.add(new Dados(2, "Joao","12345","54321",0.00));
        
        
        
        for(int i=0; i < cliente.size();i++){            
            System.out.println("ID: " + cliente.get(i).id);
            System.out.println("Nome: " + cliente.get(i).nome);
            System.out.println("Conta: " + cliente.get(i).conta);
            System.out.println("Senha: " + cliente.get(i).senha);
        }
    }
 
    
   public static void main(String[] args){
       String valor;
       OpcaoCaixa o = new OpcaoCaixa();
      // valor = o.autenticar(12345,123);
       //o.listarClientes();
       //valor = ;
       System.out.println("Valooor: " + o.retornandoID("150.02"));
       //System.out.println(valor);
       
   } 
    
}


