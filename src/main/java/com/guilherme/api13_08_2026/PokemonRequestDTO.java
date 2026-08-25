package com.guilherme.api13_08_2026;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class PokemonRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(
            min = 2,
            max = 50,
            message = "Nome deve possuir entre 2 e 50 caracteres"
    )
    private String nome;

    @NotNull(message = "Usuário é obrigatório")
    @Positive(message = "ID do usuário deve ser positivo")
    private Integer idUsuario;

    public PokemonRequestDTO() {
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