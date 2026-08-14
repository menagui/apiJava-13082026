package com.guilherme.api13_08_2026;

public class Apresentacao {

    private String nome;
    private int idade;

    public Apresentacao() {
    }
    
    public Apresentacao(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    
    
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }
}