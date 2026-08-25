package com.guilherme.api13_08_2026;

public class PokemonResponseDTO {

    private Integer id;
    private String nome;
    private Integer idUsuario;

    public PokemonResponseDTO() {
    }

    public PokemonResponseDTO(
            Integer id,
            String nome,
            Integer idUsuario
    ) {
        this.id = id;
        this.nome = nome;
        this.idUsuario = idUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
// getters e setters
}