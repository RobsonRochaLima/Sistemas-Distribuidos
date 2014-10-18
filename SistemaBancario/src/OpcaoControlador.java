/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robson Lima
 */
public class OpcaoControlador {
    
    public boolean verificaTamanho(String codigo, String senha) {
        int tam_codigo = codigo.length();
        int tam_senha = senha.length();

        if (tam_codigo == 5 && tam_senha == 5) {
            return true;
        } else {
            return false;
        }

    }
    //#1234512345
    public String retornaConta(String texto){
        int tam = texto.length();
        String c = "";
        
        for(int i=2; i < tam-5; i++){
            c = c + texto.charAt(i);
        }
//        System.out.println("Conta: " + c);
        return c;
        
    }
    
    //#01234512345
    public String retornaSenha(String texto){
        int tam = texto.length();
        String c = "";
        
        for(int i=7; i < tam; i++){
            c = c + texto.charAt(i);
        }
//        System.out.println("Senha: " + c);
        return c;
        
    }
    
    public String retornaOpcaoControlador(String texto){
        String t = texto.charAt(1) + "";
        return t;
    }
    
    
    
    
    
}
