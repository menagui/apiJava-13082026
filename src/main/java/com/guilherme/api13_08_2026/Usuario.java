package com.guilherme.api13_08_2026;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@NotBlank(message = "Nome é obrigatório")
    private String nome;
	 
	@NotNull(message = "Idade é obrigatória")
	@Min(value = 0, message = "Idade não pode ser negativa")
    private Integer idade;

    @OneToMany(
            mappedBy = "usuario",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<Pokemon> pokemons = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Integer id, String nome, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public int getId() {
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}