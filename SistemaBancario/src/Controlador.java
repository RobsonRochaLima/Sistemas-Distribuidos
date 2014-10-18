
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robson Lima
 */
public class Controlador {
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket controlador = new Socket("127.0.0.1", 12345);
        System.out.println("O controlador se conectou ao servidor!");
        
        Scanner teclado = new Scanner(System.in);
        PrintStream saida = new PrintStream(controlador.getOutputStream()); //recebe dados do cliente
        
        Scanner entrada = new Scanner(controlador.getInputStream());
        
        OpcaoControlador x = new OpcaoControlador();
        boolean finishControlador = false, finishValidacao = false;
        int opt;
        String codigo="", senha="", concat = "";
        
        while(finishControlador == false){
            System.out.println("CONTROLADOR -- (0)Autenticação (5)Sair");
            opt = teclado.nextInt();
            teclado.nextLine();
            
            switch(opt){
                
                case 0:
                {
                    
                    while(finishValidacao == false){                    
                        System.out.println("Digite codigo: ");
                        codigo = teclado.nextLine();

                        System.out.println("Digite senha: ");
                        senha = teclado.nextLine();

                        finishValidacao = x.verificaTamanho(codigo, senha);
                        
                        if (finishValidacao == false) {
                            System.out.println("O CODIGO E SENHA DEVEM CONTER 5 DIGITOS  (PADRÃO-> CODIGO: 99999 - SENHA: 99999) ");
                        }                        
                    }
                    
                    concat = concat + "#0" + codigo + senha;
                    saida.println(concat);                    
                    
                    if(entrada.nextLine().equalsIgnoreCase("true")){
                        System.out.println("OPERAÇÕES: -- (1)Listar Caixas (2)Desativar Caixa");
                        int oper = teclado.nextInt();
                        
                        switch(oper){
                            case 1:
                            {
                                //criar uma lista de caixas
                                break;
                            }
                        }
                        
                        
                        
                        
                        
                    }else{
                        System.out.println("Conta ou senha não encontrado, tente novamente...");
                        finishValidacao = true;
                    }
                    
                    break;
                }
            }
            
            
        }

        
        
        
        
        
        
    }
    
}
