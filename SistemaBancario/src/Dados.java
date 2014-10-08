/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author labs
 */
public class Dados {
    int id;
    String nome;
    String conta;
    String senha;
    double saldo;
    

    public Dados(int id, String nome, String conta, String senha, double saldo) {
        this.id = id;
        this.nome = nome;
        this.conta = conta;
        this.senha = senha;
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Dados{" + "id=" + id + ", nome=" + nome + ", conta=" + conta + ", senha=" + senha + ", saldo=" + saldo + '}';
    }


    
    
    
    
}
