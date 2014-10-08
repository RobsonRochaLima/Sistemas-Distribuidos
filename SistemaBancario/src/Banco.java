/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robson Lima
 */
public class Banco {
    int id;
    String nome;
    String conta;
    String senha;
    double saldo;
    
    //construtor
    public Banco(int id, String nome, String conta, String senha, double saldo) {
        this.id = id;
        this.nome = nome;
        this.conta = conta;
        this.senha = senha;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Banco{" + "id=" + id + ", nome=" + nome + ", conta=" + conta + ", senha=" + senha + ", saldo=" + saldo + '}';
    }
    
    
    
    
}
