
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
public class Conexao extends Thread {

    private Socket socket;
    private ArrayList<Banco> dados;
    private ArrayList<Control> controlador;
    
    
    Scanner ler = new Scanner(System.in);

    public Conexao(Socket socket, ArrayList<Banco> dados, ArrayList<Control> controlador) {
        this.socket = socket;
        this.dados = dados;
        this.controlador = controlador;       
    }

   
    
     

    public void run() {
        
        
        Scanner s;
        OpcaoCaixa x = new OpcaoCaixa();
        OpcaoControlador y = new OpcaoControlador();
        String conta, senha, idCaixa=""; 
        int achou = 0;
        double saldo;

        try {
            
            s = new Scanner(socket.getInputStream());  //entrada socket
            PrintStream saidaServer = new PrintStream(socket.getOutputStream());//mapeia saida de dados                        
            
            while (s.hasNextLine()) {
                String texto = s.nextLine();                
                String identificacao = x.identificacaoUsuario(texto) + "";
                System.out.println("Texto: " + texto);                
                
                if(texto.charAt(0) == 'X'){ //veirfica se já foi gerado id
                    System.out.println("Id retornado: " + x.retornaId(texto));
                    idCaixa = idCaixa + texto.charAt(1);
                    Identificacao id_caixas = new Identificacao(idCaixa);
                    //System.out.println("id's: " + idCaixa);                    
                }
                
                
                
                
                if(identificacao.equalsIgnoreCase("#")){ //diferencia caixa de controlador
                   int find = 0;
                   String escolha1 = y.retornaOpcaoControlador(texto);
                   System.out.println("escolha: " + escolha1);
                   
                   if(escolha1.equalsIgnoreCase("0")){
                       System.out.println("Entroi escolha 0");
                        String code = y.retornaConta(texto);
                        String password = y.retornaSenha(texto);

                        //verificando se controlador existe
                        for (Control controla : controlador) { //Banco banco : dados
                            System.out.println("for -> codigo: " + code + ", senha: " + password);
                            if(controla.getCodigo().equalsIgnoreCase(code) && controla.getSenha().equalsIgnoreCase(password)){
                                find++;
                                saidaServer.println("true");
                             }
                        }
                        System.out.println("fined: " + find);
                        if(find == 0){
                            saidaServer.println("false");
                        }
                    
                   }
                   
                   if(escolha1.equalsIgnoreCase("1")){
                       System.out.println("Entrou escolha 1");
                       
                   }
                       
                    
                    
                }else{
                    
                    
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
                                    saidaServer.println("Não foi possiverl realizar saque, saldo insuficiente...");
                                }else{
                                    banco.saldo -= valor;
                                    saidaServer.println("Saque realizado...");
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



            }


        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
