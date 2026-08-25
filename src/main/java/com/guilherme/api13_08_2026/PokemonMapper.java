package com.guilherme.api13_08_2026;

import org.springframework.stereotype.Component;

@Component
public class PokemonMapper {

    public Pokemon paraEntity(PokemonRequestDTO dto, Usuario usuario) {
        Pokemon pokemon = new Pokemon();

        pokemon.setNome(dto.getNome());
        pokemon.setUsuario(usuario);

        return pokemon;
    }

    public PokemonResponseDTO paraResponseDTO(Pokemon pokemon) {
        return new PokemonResponseDTO(
                pokemon.getId(),
                pokemon.getNome(),
                pokemon.getUsuario().getId()
        );
    }

    public void atualizarEntity(
            PokemonRequestDTO dto,
            Usuario usuario,
            Pokemon pokemon) {

        pokemon.setNome(dto.getNome());
        pokemon.setUsuario(usuario);
    }

}