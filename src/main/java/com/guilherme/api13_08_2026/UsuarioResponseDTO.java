package com.guilherme.api13_08_2026;

public class UsuarioResponseDTO {

    private Integer id;
    private String nome;
    private Integer idade;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Integer id, String nome, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }
}