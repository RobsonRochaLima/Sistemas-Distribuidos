
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.nio.cs.Surrogate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author labs
 */
public class ThreadCaixa extends Thread {

    private Socket socket;
    private ArrayList<Banco> dados;
    private ArrayList<Control> controlador;
    
    Scanner ler = new Scanner(System.in);

    public ThreadCaixa(Socket socket, ArrayList<Banco> dados, ArrayList<Control> controlador) {
        this.socket = socket;
        this.dados = dados;
        this.controlador = controlador;       
    }

    public ThreadCaixa(Socket socket) {
        this.socket = socket;
    }
    
    public void run() {      
        Scanner s;
        OpcaoCaixa x = new OpcaoCaixa();
        OpcaoControlador y = new OpcaoControlador();
        String conta, senha;
        
        try {
            
            s = new Scanner(socket.getInputStream());  //entrada socket
            PrintStream saidaServer = new PrintStream(socket.getOutputStream());//mapeia saida de dados                        
            
            while (s.hasNextLine()) {
                String texto = s.nextLine();                
                    String escolha = "" + x.option(texto); //retorn a opção do cliente
                
                    if (escolha.equalsIgnoreCase("0")) {
                        int find=0;
                        conta = x.retornaConta(texto);
                        senha = x.retornaSenha(texto);
                        
                        for (Banco banco : dados) {                        
                            if (banco.getConta().equalsIgnoreCase(conta) && banco.getSenha().equalsIgnoreCase(senha)) {
                                find++;
                                saidaServer.println("true");
                            }                                                        
                        }
                        if(find == 0){
                                saidaServer.println("false");
                        }                        
                    }

                                    
                    if (escolha.equalsIgnoreCase("1")) { //Saque                      
                        String _conta = x.retornaContaComValor(texto); //retorna somente a conta qndo o texto possuir valor do saque incluso                    
                        double valor = x.retornaValorSaqueDeposito(texto);                   

                        for (Banco banco : dados) {
                            if (banco.getConta().equalsIgnoreCase(_conta)) {

                                if((banco.saldo - valor) < 0){
                                    saidaServer.println("false");
                                }else{
                                    banco.saldo -= valor;
                                    saidaServer.println("true");
                                }
                            }
                        }
                    }


                    if (escolha.equalsIgnoreCase("2")) { //Deposito
                        String _conta = x.retornaContaComValor(texto);
                        double valor = x.retornaValorSaqueDeposito(texto);
                        
                        for (Banco banco : dados) {
                            if (banco.getConta().equalsIgnoreCase(_conta)) {                                
                                banco.saldo += valor;
                                saidaServer.println("Deposito realizado...");
                            }
                        }
                        
                        
                        
                    }


                    if (escolha.equalsIgnoreCase("3")) {
                        String _conta = x.retornaContaSaldo(texto);

                        for (Banco banco : dados) {
                            if (banco.getConta().equalsIgnoreCase(_conta)) {
                                String sald = banco.getSaldo() + "";
                                saidaServer.println(sald);                            
                            }
                        }
                    }
             }
            } catch (IOException ex) {
                Logger.getLogger(ThreadCaixa.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
