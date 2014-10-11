
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    
/**
 *
 * @author labs
 */
public class Conexao extends Thread{
    private Socket socket;
    private ArrayList<Banco> dados;
    
    Scanner ler = new Scanner(System.in);

   
    public Conexao(Socket socket, ArrayList dados) {
        this.socket = socket;
        this.dados = dados;
    }
   
   
    public void run(){
        
        Scanner s;
        OpcaoCaixa x = new OpcaoCaixa();         
        String conta, senha, opt;
        int autenticacao;
        boolean stop=false; int autorizacao;
        int cont = 0, achou = 0;
        double saldo;
  
        
        
        try 
         {
            s = new Scanner(socket.getInputStream());  //entrada socket
            PrintStream saidaServer = new PrintStream(socket.getOutputStream());//mapeia saida de dados
            
            
            while (s.hasNextLine()) {
                String texto = s.nextLine();                
                String escolha = "" + x.option(texto); //retorn a opção do cliente                               
                
                System.out.println("[servidor] chegou: -> " + texto);                                
                                
                if(escolha.equalsIgnoreCase("0")){  
                    conta = x.retornaConta(texto);
                    senha = x.retornaSenha(texto);                                                           
                    
                    for (Banco banco : dados) {
//                        System.out.println("[servidor] Banco.conta: " + banco.conta + ", Conta: " + conta);
                        if(banco.getConta().equalsIgnoreCase(conta)){
//                         System.out.println("Achou...");
                                    
                           achou++;
//                         System.out.println("[servido] autenticacao: " + banco.id);
                           saidaServer.println(banco.id); 
                        }
                    
                    }
                    if(achou <= 0){ //se cliente não encontrado
                        saidaServer.println(-1);
                    } 
                    
                 }

                
                //1 - 200.0 - 12345
                if(escolha.equalsIgnoreCase("1")){ //Saque                      
//                  System.out.println("[servidor] escolha 1: " + texto);
                    String _conta = x.retornaContaComValor(texto); //retorna somente a conta qndo o texto possuir valor do saque incluso                    
                    double valor = x.retornaValorSaqueDeposito(texto);
                    
//                    String _id = x.retornandoID(texto);
//                    int id = Integer.parseInt(_id);
                    
                   
                    System.out.println("[saque] conta: " + _conta + ", valor: " + valor);
                    
                    for (Banco banco : dados) {
                        if(banco.getConta().equalsIgnoreCase(_conta)){
                            banco.saldo -= valor;
                            System.out.println("[servidor] saque Saldo: " + banco.saldo);
                        }
                    }
                   
                                        
                }
                
                
                //2 - 100.0 - 12345 - 12345
                if(escolha.equalsIgnoreCase("2")){ //Deposito                    
                    String _conta = x.retornaContaComValor(texto);
                    double valor = x.retornaValorSaqueDeposito(texto);
                    
                    System.out.println("[deposito] Conta: " + _conta + ", valor: " + valor);
                    
                    for (Banco banco : dados) {
                        if(banco.getConta().equalsIgnoreCase(_conta)){
                            banco.saldo += valor;
                            System.out.println("[servidor] deposito Saldo: " + banco.saldo);
                            
                        }
                    }
                    
                    
                }
                //3 - 12345 - 12345
                if(escolha.equalsIgnoreCase("3")){
                    System.out.println("Entrou escolha 3: " + texto);
                    String _conta = x.retornaConta(texto);
                    
                    System.out.println("[servido] Conta: " + _conta);
                    
                    for (Banco banco : dados) {                       
                        if(banco.getConta().equalsIgnoreCase(_conta)){
//                            System.out.println("Saldo: " + banco.getSaldo());
                            String sald = ""+banco.getSaldo();
                            saidaServer.print(sald);
                            System.out.println("saldo: " + sald);
                            
                        }
                        
                    }
                    
                    
                    
                }
                
                
                
                
                
                
                
                if(escolha.equalsIgnoreCase("9")){ //retorna ID
                    System.out.println("Entrou escolha 9");
                    //buscando id cliente    
                    conta = x.retornaConta(texto);
                    senha = x.retornaSenha(texto);
                    for(int i=0; i < dados.size(); i++){
                        if(dados.get(i).conta.equalsIgnoreCase(conta) && dados.get(i).senha.equalsIgnoreCase(senha)){
                            System.out.println("retorna id: " + dados.get(i).id);
                            saidaServer.println(""+dados.get(i).id);
                        }
                    }
                }
                
                
                
                
                
                
                
                
                
                
            }               
              
            
         } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } 
    
}
}




//                for(int i=0; i < dados.size(); i++){
//                   System.out.println("Id: " + dados.get(i).id);
//                   System.out.println("nome: " + dados.get(i).nome);
//                   System.out.println("Conta: " + dados.get(i).conta);
//                   System.out.println("Senha: " + dados.get(i).senha);
//                   dados.get(i).saldo = 100;
//                   System.out.println("Saldo: " + dados.get(i).saldo);
//                }

