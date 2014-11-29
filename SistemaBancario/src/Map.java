
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robson Lima
 */
public class Map {    
    private String nome;
    private Socket socket;
    
    PrintStream saida;
    Scanner entrada;
    
    	public Map(Socket socket) {
		this.socket = socket;
		
		try {
			saida = new PrintStream(socket.getOutputStream());
			entrada = new Scanner(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public void enviar(String texto){
        saida.println(texto);
    } 
    
    public String receber(){
        return entrada.nextLine();
    }
    //fecha o socket
    public void sair() throws IOException{
	socket.close();		
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String toString() {
        return "Map{" + "nome=" + nome + ", socket=" + socket + ", saida=" + saida + ", entrada=" + entrada + '}';
    }
    
}
