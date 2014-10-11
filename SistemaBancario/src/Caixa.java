
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
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
        String senha = "", conta = "", concat = "";
        int opt;
        boolean finishCaixa = false, finishValidacao = false, finishOperacao = false, finishOper = false;
        Caixa c = new Caixa();
        Socket caixa = new Socket("127.0.0.1", 12345);
        System.out.println("O cliente se conectou ao servidor!");

        Scanner teclado = new Scanner(System.in);
        PrintStream saida = new PrintStream(caixa.getOutputStream()); //recebe dados do cliente

        Scanner s = new Scanner(caixa.getInputStream());



        while (finishCaixa == false) { //menu inicial
            System.out.println("CAIXA -- (0)Autenticação (5)Sair");
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
                    
                    //System.out.println("[Cliente] s.nextInt(): " + s.nextInt());
                    if (s.nextInt() >= 0) {  //verifica se existe e apresenta menu de operações                        
                        //System.out.println("ID: " + s.nextInt());
                        while(finishOper == false){
                            System.out.println("OPERAÇÕES: -- (1)Saque (2)Deposito (3)Saldo (4)Extrato");
                            int oper = teclado.nextInt();
                            double valor;
                            //String _id;
                           
                            switch(oper){
                                case 1:
                                {                                                                        
                                    System.out.print("Digite o valor saque: ");
                                    valor = teclado.nextDouble();
                                    
                                    saida.println("1" + valor + conta); //opçcao 1: valor-saque + id//                                    
                                    break;   
                                }
                                    
                                case 2:
                                {
                                    System.out.print("Digite o valor deposito: ");
                                    valor = teclado.nextDouble();
                                    
                                    saida.println("2" + valor + conta);                                    
                                    break;
                                }
                                case 3:
                                {                                    
                                    saida.println("3" + concat);
                                    //s.nextLine();
                                    System.out.println("Saldo: " + s.nextLine());
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
                    //stop = true;
                    break;
                }





            }

        }


        saida.close();
        teclado.close();
        caixa.close();
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
//        while(stop == false){
//            System.out.print("Digite a conta: ");
//            conta = teclado.nextLine();
//            System.out.print("Digite a senha: ");
//            senha = teclado.nextLine();
//            stop = c.verificaTamanho(conta, senha);
//            if(stop == false){
//                System.out.println("A CONTA E SENHA DEVEM CONTER 5 DIGITOS  (PADRÃO-> CONTA: 99999 - SENHA: 99999) ");
//            }            
//        }
//        
//        String palavra = "";
//        palavra = palavra + conta + senha; //concatena conta e senha
//       
//        saida.println(palavra); //envia servidor                       
//        System.out.println(s.nextLine());
//int autenticacao = s.nextInt();

