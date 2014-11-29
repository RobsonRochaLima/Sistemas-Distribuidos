
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
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
public class Caixa {

    public static void main(String[] args) throws UnknownHostException, IOException {
        ArrayList<String> extrato = new ArrayList<String>();                            
        boolean finishCaixa = false, finishValidacao = false, finishOper = false;              
        String senha = "", conta = "", concat = "";
        Caixa c = new Caixa();                                
        int opt;
              
        //---- socket --------
        Socket caixa = new Socket("127.0.0.1", 12345);
        System.out.println("O cliente se conectou ao servidor!");
        
        Socket controlador = new Socket("127.0.0.1", 12346); //novo socket p/ se conectar ao controlador
        System.out.println("O cliente se conectou ao controlador!");
               
	Map control = new Map(controlador);
                
        // -- ler do teclado -------
        Scanner teclado = new Scanner(System.in);
        PrintStream saida = new PrintStream(caixa.getOutputStream()); //recebe dados do cliente

        Scanner s = new Scanner(caixa.getInputStream());
        
        while (finishCaixa == false) { //menu inicial
            System.out.println("----------------------------------");
            System.out.println("CAIXA -- (0)Autenticação (5)Sair |");
            System.out.println("----------------------------------");
            opt = teclado.nextInt();
            teclado.nextLine();
            
            switch (opt) {
                case 0: {
                    while (finishValidacao == false) {   //validação cliente                       

                        System.out.println("Digite a conta: ");
                        conta = teclado.nextLine();                       

                        System.out.println("Digite a senha: ");
                        senha = teclado.nextLine();

                        finishValidacao = c.verificaTamanho(conta, senha); //verifica se o formato da conta e senha são validas

                        if (finishValidacao == false) {
                            System.out.println("A CONTA E SENHA DEVEM CONTER 5 DIGITOS  (PADRÃO-> CONTA: 99999 - SENHA: 99999) ");
                        }

                    }
                    //concatena opção escolhida + conta + senha
                    concat = concat + conta + senha;
                    saida.println(0+concat); //enviando p/ servidor
                                        
                    if (s.nextLine().equalsIgnoreCase("true")) { //valida se cliente existe
                        
                        while(finishOper == false){ 
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("OPERAÇÕES: -- (1)Saque (2)Deposito (3)Saldo (4)Extrato (5)Sair  |");
                            System.out.println("-----------------------------------------------------------------");
                            int oper = teclado.nextInt();
                            double valor;                            
                           
                            switch(oper){
                                case 1: //saque
                                {                                                                        
                                    System.out.print("Digite o valor saque: ");
                                    valor = teclado.nextDouble();
                                    
                                    saida.println("1" + valor + conta); //opçcao 1: valor-saque + id// 
                                    String retorno = s.nextLine();
                                    
                                    if(retorno.equalsIgnoreCase("false")){
                                        System.out.println("Não foi possivel realizar saque, Saldo insuficiente...");
                                    }else{
                                        System.out.println("Saque realizado...");
                                        extrato.add("Sacou " + valor);
                                    }
                                    
                                    break;   
                                }
                                    
                                case 2: //deposito
                                {
                                    System.out.print("Digite o valor deposito: ");
                                    valor = teclado.nextDouble();
                                    
                                    saida.println("2" + valor + conta);
                                    
                                    System.out.println(s.nextLine());
                                    extrato.add("Depositou " + valor);
                                    break;
                                }
                                case 3: //saldo
                                {                                    
                                    saida.println("3" + conta);
                                    String x = s.nextLine();
                                  
                                    System.out.println("Saldo: " + x);                                    
                                    break;
                                }
                                case 4: //extrato
                                {
                                    System.out.println("-------------------------------------------");
                                    System.out.println("Data       hora   Operação                |");
                                    System.out.println("-------------------------------------------");
                                    for(int i=0; i < extrato.size();i++){
                                        System.out.println("99/99/999 12:00   " + extrato.get(i));   
                                    }
                                    System.out.println("-------------------------------------------");
                                    System.out.println("");System.out.println("");System.out.println("");System.out.println("");
                                    
                                    break;
                                }
                                case 5:
                                {
                                    finishOper = true;
                                    control.enviar("");                                    
                                    control.sair(); //fecha socket
                                    break;
                                }                                                                    
                            }
                        }
                        
                    }else{
                        System.out.println("Conta ou senha não encontrado, tente novamente...");
                        finishCaixa = true;
                        break;
                    }
                    break;
                }

                case 5: {
                    finishCaixa = true;                    
                    control.enviar("");
		    control.sair();  //fecha socket                  
                    break;
                }

            }

        }
    }

    public boolean verificaTamanho(String conta, String senha) {
        int tam_conta = conta.length();
        int tam_senha = senha.length();

        if (tam_conta == 5 && tam_senha == 5) {
            return true;
        } else {
            return false;
        }

    }
}
