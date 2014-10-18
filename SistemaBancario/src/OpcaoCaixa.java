
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
    
    public char identificacaoUsuario(String texto){        
        char x = texto.charAt(0);
        return x;
    }
    
    
    
    
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
    //3 - 12345 - 12345
    public String retornaConta(String conta){
        String c ="";
        for(int i=1; i<conta.length()-5;i++){
            c = c + conta.charAt(i);
        }
        
        return c;
    }
    
    //1 - 2000.0 - 12345
    public String retornaContaComValor(String conta){
        String c ="", b="",a="";
        int tam = conta.length();
        
        for(int j = tam-1; j>=0;j--){
            b = b + conta.charAt(j);
        }
        
        //543210.00021
        for(int i=0; i<5;i++){
            c = c + b.charAt(i);
        }
        
        //54321
        int tam2 = c.length();
        for(int k = tam2-1; k>=0;k--){
            a = a + c.charAt(k);
        }
        
        return a;
    }    
    
    //3 - 12345
    public String retornaContaSaldo(String conta){
        String c ="";
        
        for(int i=1; i<conta.length();i++){
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
    
    //retorna opção
    public char option(String texto){        
        return texto.charAt(0);
    }
    
     //1 - 1000.0 - 12345
     //1 - 200.0 - 12345
     public double retornaValorSaqueDeposito(String v){
        String val="";
        double valor;
        
        for(int i=1; i < v.length()-5; i++){
            val = val + v.charAt(i);            
        }
        valor = Double.parseDouble(val);         
        return valor;
    }
    
    
    
    
}


