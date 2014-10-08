
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
    Scanner ler = new Scanner(System.in); 
    
    public Conexao(Socket socket) {
        this.socket = socket;
    }
     
    public void run(){
        
        Scanner s;
        OpcaoCaixa x = new OpcaoCaixa();         
        String conta, senha, opt;
        int autenticacao;
        boolean stop=false; int autorizacao;
        int cont = 0, achou = 0;
        try 
         {
            s = new Scanner(socket.getInputStream());  //entrada socket
            PrintStream saidaServer = new PrintStream(socket.getOutputStream());//mapeia saida de dados
            
            ArrayList<Banco> dados = new ArrayList<Banco>();
            
            Banco a = new Banco(0, "Robson Lima", "12345", "12345", 500);
            Banco b = new Banco(1, "Anderson Lima", "12345", "54321", 200);
            Banco c = new Banco(2, "João da Silva", "12345", "67890", 0);
            //adicionando na lista
            dados.add(a);
            dados.add(b);
            dados.add(c);
            
            while (s.hasNextLine()) {
                String texto = s.nextLine();                
                String escolha = "" + x.option(texto); //retorn a opção do cliente                               
                
                if(escolha.equalsIgnoreCase("0")){                    
                    conta = x.retornaConta(texto);
                    senha = x.retornaSenha(texto);                                        
                    
                    for(int i=0; i < dados.size(); i++){
                        if(conta.equalsIgnoreCase(dados.get(i).conta) && senha.equalsIgnoreCase(dados.get(i).senha)){
                            achou++;                            
                            saidaServer.println(dados.get(i).id);
                        }                                                                     
                    }
                    if(achou <= 0){ //se cliente não encontrado
                        saidaServer.println(-1);
                    }                    
                }
                
                if(escolha.equalsIgnoreCase("1")){ //Saque
                    System.out.println("Entrou escolha 1");
                    double valor = x.retornaValorSaque(texto);
                    String _id = x.retornandoID(texto);
                    int id = Integer.parseInt(_id);
                    
                    System.out.println("ID: " + id + ", Valor: " + valor);
                    
                    for(int i=0; i < dados.size(); i++){
                        if(dados.get(i).id == id){ 
                            double saldo = dados.get(i).saldo - valor;
                            saidaServer.println("SALDO: " + saldo); 
                        }                                                                     
                    }
                    
                    
                }
                
                if(escolha.equalsIgnoreCase("2")){ //Deposito
                    System.out.println("Entrou escolha 2");
                    saidaServer.println("ola");
                    
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

